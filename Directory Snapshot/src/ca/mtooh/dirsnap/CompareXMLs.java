/*
 * This compares two XML files for differences in file sizes and last modified dates, and for directories and files that have been created or deleted
 */
package ca.mtooh.dirsnap;

import java.io.IOException;
import java.nio.file.Path;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.tree.TreeNode;
import org.xml.sax.SAXException;

/**
 *
 * @author mtoohey
 */
public class CompareXMLs {

    ElementFinder ef = new ElementFinder();
    ReadXMLToTree rxt = new ReadXMLToTree();

    public DefaultTreeModel[] compareXMLs(Path selectedInputFile1, Path selectedInputFile2, Boolean ignoreStoreAndThumbs, Integer... inputDepth) {
        try {
            //The program checks to make sure that the two files weren't the exact same files, and if they are it displays an error message
            if (selectedInputFile1.equals(selectedInputFile2)) {
                DefaultTreeModel[] sameErrorTwoTrees = new DefaultTreeModel[2];

                DefaultMutableTreeNode sameErrorTree1 = new DefaultMutableTreeNode("You cannot compare a file to itself.");
                DefaultTreeModel sameErrorModel1 = new DefaultTreeModel(sameErrorTree1);

                DefaultMutableTreeNode sameErrorTree2 = new DefaultMutableTreeNode("Please change one of your selections and try again.");
                DefaultTreeModel sameErrorModel2 = new DefaultTreeModel(sameErrorTree2);

                sameErrorTwoTrees[0] = sameErrorModel1;
                sameErrorTwoTrees[1] = sameErrorModel2;

                return sameErrorTwoTrees;
            }

            //The documents and root elements are loaded
            DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder1;
            docBuilder1 = docFactory1.newDocumentBuilder();
            Document doc1 = docBuilder1.parse(selectedInputFile1.toString());

            Element rootXML1 = doc1.getDocumentElement();

            DefaultMutableTreeNode rootTree1 = new DefaultMutableTreeNode(ef.displayString(rootXML1));
            DefaultTreeModel treeModel1 = new DefaultTreeModel(rootTree1, true);

            DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder2;
            docBuilder2 = docFactory2.newDocumentBuilder();
            Document doc2 = docBuilder2.parse(selectedInputFile2.toString());

            Element rootXML2 = doc2.getDocumentElement();

            DefaultMutableTreeNode rootTree2 = new DefaultMutableTreeNode(ef.displayString(rootXML2));
            DefaultTreeModel treeModel2 = new DefaultTreeModel(rootTree2, true);

            //Variables for checking depth and what the reason for the depth limit is are declared
            Integer depth = null;
            Integer depthCause = -1;

            //This if statement checks to see if there are any depth limit elements in either of the files, if there aren't any, the variables are not changed
            if (rootXML1.getElementsByTagName("depthLimit").getLength() != 0 && rootXML2.getElementsByTagName("depthLimit").getLength() == 0) {
                //If the first file has one but the second doesn't the depth and cause are set appropriately
                depth = (nodesToRoot(rootXML1.getElementsByTagName("depthLimit").item(0), 0) / 2) - 1;
                depthCause = 3;
            } else if (rootXML1.getElementsByTagName("depthLimit").getLength() == 0 && rootXML2.getElementsByTagName("depthLimit").getLength() != 0) {
                //If the second file has one but the first doesn't the depth and cause are set appropriately
                depth = (nodesToRoot(rootXML2.getElementsByTagName("depthLimit").item(0), 0) / 2) - 1;
                depthCause = 4;
            } else if (rootXML1.getElementsByTagName("depthLimit").getLength() != 0 && rootXML2.getElementsByTagName("depthLimit").getLength() != 0) {
                //If both of the files have them, they are compared to check which one has a lesser depth limit, and the limit is set to that file's deptph limit
                if (nodesToRoot(rootXML1.getElementsByTagName("depthLimit").item(0), 0) <= nodesToRoot(rootXML2.getElementsByTagName("depthLimit").item(0), 0)) {
                    depth = (nodesToRoot(rootXML1.getElementsByTagName("depthLimit").item(0), 0) / 2) - 1;
                    depthCause = 3;
                } else if (nodesToRoot(rootXML1.getElementsByTagName("depthLimit").item(0), 0) >= nodesToRoot(rootXML2.getElementsByTagName("depthLimit").item(0), 0)) {
                    depth = (nodesToRoot(rootXML2.getElementsByTagName("depthLimit").item(0), 0) / 2) - 1;
                    depthCause = 4;
                }
            }

            //If the user has inputted a depth limit, that depth limit is compared to the one that was necessitated by the files, and which ever one of them is lesser is chosen
            if (inputDepth.length > 0) {
                if (depthCause != -1) {
                    if (inputDepth[0] < depth) {
                        depth = inputDepth[0];
                        depthCause = 1;
                    }
                } else {
                    depth = inputDepth[0];
                    depthCause = 1;
                }
            }

            //If a depth limit has was not present in any of the files and the user didn't choose one, then the recursive method is called without a depth limit, but if there was one then it is called with a depth limit decreased by 1
            if (depthCause == -1) {
                recursiveCompare(rootXML1, rootXML2, rootTree1, rootTree2, ignoreStoreAndThumbs, depthCause);
            } else {
                recursiveCompare(rootXML1, rootXML2, rootTree1, rootTree2, ignoreStoreAndThumbs, depthCause, depth - 1);
            }

            //After the comparison is done, it checks if there were any differences found. If there were none it returns a message explaining this
            if (rootTree1.getChildCount() == 0 && rootTree2.getChildCount() == 0) {
                DefaultTreeModel[] noDifferencesErrorTwoTrees = new DefaultTreeModel[2];

                DefaultMutableTreeNode noDifferencesErrorTree1 = new DefaultMutableTreeNode("No differences were found...");
                DefaultTreeModel noDifferencesErrorModel1 = new DefaultTreeModel(noDifferencesErrorTree1);

                if (depthCause == 1) {
                    DefaultMutableTreeNode noDifferencesErrorTree2 = new DefaultMutableTreeNode("...within the selected depth limit.");
                    DefaultTreeModel noDifferencesErrorModel2 = new DefaultTreeModel(noDifferencesErrorTree2);
                    noDifferencesErrorTwoTrees[1] = noDifferencesErrorModel2;
                } else if (depthCause == 2) {
                    DefaultMutableTreeNode noDifferencesErrorTree2 = new DefaultMutableTreeNode("...within the files' depth limits.");
                    DefaultTreeModel noDifferencesErrorModel2 = new DefaultTreeModel(noDifferencesErrorTree2);
                    noDifferencesErrorTwoTrees[1] = noDifferencesErrorModel2;
                } else if (depthCause == 3) {
                    DefaultMutableTreeNode noDifferencesErrorTree2 = new DefaultMutableTreeNode("...within the first file's depth limit.");
                    DefaultTreeModel noDifferencesErrorModel2 = new DefaultTreeModel(noDifferencesErrorTree2);
                    noDifferencesErrorTwoTrees[1] = noDifferencesErrorModel2;
                } else if (depthCause == 4) {
                    DefaultMutableTreeNode noDifferencesErrorTree2 = new DefaultMutableTreeNode("...within the second file's depth limit.");
                    DefaultTreeModel noDifferencesErrorModel2 = new DefaultTreeModel(noDifferencesErrorTree2);
                    noDifferencesErrorTwoTrees[1] = noDifferencesErrorModel2;
                } else {
                    DefaultMutableTreeNode noDifferencesErrorTree2 = new DefaultMutableTreeNode("...the contents of both files is identical.");
                    DefaultTreeModel noDifferencesErrorModel2 = new DefaultTreeModel(noDifferencesErrorTree2);
                    noDifferencesErrorTwoTrees[1] = noDifferencesErrorModel2;
                }

                noDifferencesErrorTwoTrees[0] = noDifferencesErrorModel1;

                return noDifferencesErrorTwoTrees;
            }

            //If that error message wasn't returned, then the two trees containing the differences are returned
            DefaultTreeModel[] twoTrees = new DefaultTreeModel[2];

            twoTrees[0] = treeModel1;
            twoTrees[1] = treeModel2;

            return twoTrees;

        } catch (ParserConfigurationException | IOException | SAXException pce) {
            return null;
        }
    }

    public void recursiveCompare(Element currentXMLNode1, Element currentXMLNode2, DefaultMutableTreeNode treeNode1, DefaultMutableTreeNode treeNode2, Boolean ignoreStoreAndThumbs, Integer depthCause, Integer... depth) {
        NodeList allChildren1 = ef.children(currentXMLNode1).getChildNodes();
        NodeList allChildren2 = ef.children(currentXMLNode2).getChildNodes();

        List<Node> dirChildren1 = new ArrayList<>();
        List<Node> fileChildren1 = new ArrayList<>();
        List<Node> dirChildren2 = new ArrayList<>();
        List<Node> fileChildren2 = new ArrayList<>();

        //NodeLists are created from all the children of the current two directories, then those are searched for directory and file elements, and .DS_Store and thunbs
        
        for (int i = 0; i < allChildren1.getLength(); i++) {
            if (allChildren1.item(i).getNodeName().equals("dir")) {
                dirChildren1.add(allChildren1.item(i));
            } else if (allChildren1.item(i).getNodeName().equals("file") && !ignoreStoreAndThumbs) {
                fileChildren1.add(allChildren1.item(i));
            } else if (allChildren1.item(i).getNodeName().equals("file") && ignoreStoreAndThumbs && !ef.name(allChildren1.item(i)).equals(".DS_Store") && !ef.name(allChildren1.item(i)).equals("Thumbs.db")) {
                fileChildren1.add(allChildren1.item(i));
            }
        }
        for (int i = 0; i < allChildren2.getLength(); i++) {
            if (allChildren2.item(i).getNodeName().equals("dir")) {
                dirChildren2.add(allChildren2.item(i));
            } else if (allChildren2.item(i).getNodeName().equals("file") && !ignoreStoreAndThumbs) {
                fileChildren2.add(allChildren2.item(i));
            } else if (allChildren2.item(i).getNodeName().equals("file") && ignoreStoreAndThumbs && !ef.name(allChildren2.item(i)).equals(".DS_Store") && !ef.name(allChildren2.item(i)).equals("Thumbs.db")) {
                fileChildren2.add(allChildren2.item(i));

            }
        }

        //Boolean arrays are also created with the same lengths as the lists containing the files and directories of both files, so that the objects that have been found can be recorded
        boolean[] foundDirChildren1 = new boolean[dirChildren1.size()];
        boolean[] foundFileChildren1 = new boolean[fileChildren1.size()];

        boolean[] foundDirChildren2 = new boolean[dirChildren2.size()];
        boolean[] foundFileChildren2 = new boolean[fileChildren2.size()];

        //The second list of files is searched for the contents of the first lsit of files
        for (int i = 0; i < fileChildren1.size(); i++) {
            for (int n = 0; n < fileChildren2.size(); n++) {
                if (ef.name(fileChildren1.get(i)).equals(ef.name(fileChildren2.get(n)))) {
                    
                    //If something is found then the corresponding item in the boolean array is set to true
                    foundFileChildren1[i] = true;
                    foundFileChildren2[n] = true;

                    boolean modifiedMatches = ef.lastModified(fileChildren1.get(i)).equals(ef.lastModified(fileChildren2.get(n)));
                    boolean sizeMatches = ef.size(fileChildren1.get(i)).equals(ef.size(fileChildren2.get(n)));

                    //The sizes andn last modified dates are compared, and if there is a difference it is reported to the modified difference reporting method
                    if (!modifiedMatches || !sizeMatches) {
                        addModifiedDifferenceToTree(fileChildren1.get(i), fileChildren2.get(n), treeNode1, treeNode2);
                    }
                    break;
                }
            }
        }

        //These loops itteratre through the boolean arrays and reports anything that's false to the missing difference reporting method
        for (int i = 0; i < foundFileChildren1.length; i++) {
            if (!foundFileChildren1[i]) {
                addMissingDifferenceToTree(fileChildren1.get(i), false, treeNode1, ignoreStoreAndThumbs, depthCause);
            }
        }
        for (int i = 0; i < foundFileChildren2.length; i++) {
            if (!foundFileChildren2[i]) {
                addMissingDifferenceToTree(fileChildren2.get(i), false, treeNode2, ignoreStoreAndThumbs, depthCause);
            }
        }

        //The second list of directories is searched for the contents of the first lsit of directories
        for (int i = 0; i < dirChildren1.size(); i++) {
            for (int n = 0; n < dirChildren2.size(); n++) {
                if (ef.name(dirChildren1.get(i)).equals(ef.name(dirChildren2.get(n)))) {
                    
                    //If something is found then the corresponding item in the boolean array is set to true
                    foundDirChildren1[i] = true;
                    foundDirChildren2[n] = true;

                    Element one = (Element) dirChildren1.get(i);
                    Element two = (Element) dirChildren2.get(n);

                    //The contents of the directory are compared using the correct method based on the depth. If there is a depth it is decreased by one when the method is used
                    if (depth.length > 0) {
                        if (depth[0] > 0) {
                            recursiveCompare(one, two, treeNode1, treeNode2, ignoreStoreAndThumbs, depthCause, depth[0] - 1);
                        } else {
                        }
                    } else {
                        recursiveCompare(one, two, treeNode1, treeNode2, ignoreStoreAndThumbs, depthCause);
                    }
                    break;
                }
            }
        }

        //These loops itteratre through the boolean arrays and reports anything that's false to the missing difference reporting method
        for (int i = 0; i < foundDirChildren1.length; i++) {
            if (!foundDirChildren1[i]) {
                if (depth.length > 0) {
                    addMissingDifferenceToTree(dirChildren1.get(i), true, treeNode1, ignoreStoreAndThumbs, depthCause, depth[0]);
                } else {
                    addMissingDifferenceToTree(dirChildren1.get(i), true, treeNode1, ignoreStoreAndThumbs, depthCause);
                }
            }
        }
        for (int i = 0; i < foundDirChildren2.length; i++) {
            if (!foundDirChildren2[i]) {
                if (depth.length > 0) {
                    addMissingDifferenceToTree(dirChildren2.get(i), true, treeNode2, ignoreStoreAndThumbs, depthCause, depth[0]);
                } else {
                    addMissingDifferenceToTree(dirChildren2.get(i), true, treeNode2, ignoreStoreAndThumbs, depthCause);
                }
            }
        }
    }

    public void addModifiedDifferenceToTree(Node node1, Node node2, DefaultMutableTreeNode treeNode1, DefaultMutableTreeNode treeNode2) {
        //The paths to root are found using the Node Path to Root method, and other variables are declared
        Node[] pathArray1 = nodePathToRoot(node1);
        Node[] pathArray2 = nodePathToRoot(node2);
        DefaultMutableTreeNode currentTreeNode1 = treeNode1;
        DefaultMutableTreeNode currentTreeNode2 = treeNode2;
        String message1 = null;
        String message2 = null;
        
        //The correct messages are created depending on how the files have changed
        if (Long.parseLong(ef.lastModified(node1)) > Long.parseLong(ef.lastModified(node2))) {
            message1 = " Item was modified more recently in this scan.";
            message2 = " Item was modified more recently in the other scan.";
        } else if (Long.parseLong(ef.lastModified(node1)) < Long.parseLong(ef.lastModified(node2))) {
            message1 = " Item was modified more recently in this scan.";
            message2 = " Item was modified more recently in the other scan.";
        } else {
            message1 = " Sizes are different but modification dates are not!";
            message2 = " Sizes are different but modification dates are not!";
        }
        
        //The path to root, then the item itself with the message attached are added using the add if not present method for each of the two items
        for (int i = 1; i < pathArray1.length; i++) {
            currentTreeNode1 = addIfNotPresent(currentTreeNode1, ef.displayString(pathArray1[i]));
        }
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(ef.displayString(node1) + message1);
        n.setAllowsChildren(false);
        currentTreeNode1.add(n);
        for (int i = 1; i < pathArray2.length; i++) {
            currentTreeNode2 = addIfNotPresent(currentTreeNode2, ef.displayString(pathArray2[i]));
        }
        DefaultMutableTreeNode t = new DefaultMutableTreeNode(ef.displayString(node2) + message2);
        t.setAllowsChildren(false);
        currentTreeNode2.add(t);
    }

    public void addMissingDifferenceToTree(Node node, boolean dir, DefaultMutableTreeNode treeNode, Boolean ignoreStoreAndThumbs, Integer depthCause, Integer... depth) {
        //The path to root for this missing difference is found and variables are declared
        Node[] pathArray = nodePathToRoot(node);
        DefaultMutableTreeNode currentTreeNode = treeNode;
        //The path to root is added to the tree using the add if not present method
        for (int i = 1; i < pathArray.length; i++) {
            currentTreeNode = addIfNotPresent(currentTreeNode, ef.displayString(pathArray[i]));
        }
        
        //The item itself is created
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(ef.displayString(node) + " Item is missing in the other scan.");
        
        //If it is a directory it displays the contents of it up to the depth limit if there is one present, but if the depth limit has already been reached it displays that method
        if (dir) {
            Element element = (Element) node;
            if (depth.length > 0) {
                if (depth[0] <= 0) {
                    String message = "Error";
                    switch (depthCause) {
                        case 1:
                            message = "Selected depth limit reached.";
                            break;
                        case 2:
                            message = "Files' depth limits reached.";
                            break;
                        case 3:
                            message = "First file's depth limit reached.";
                            break;
                        case 4:
                            message = "Second file's depth limit reached.";
                            break;
                    }
                    DefaultMutableTreeNode l = new DefaultMutableTreeNode(message);
                    l.setAllowsChildren(false);
                    n.add(l);
                } else {
                    rxt.readXMLToTreeRecursive(n, element, ignoreStoreAndThumbs, depthCause, depth[0] - 1);
                }
            } else {
                rxt.readXMLToTreeRecursive(n, element, ignoreStoreAndThumbs, depthCause);
            }
        } else {
            //The allows children is set to false otherwise so it displays with a file icon
            n.setAllowsChildren(false);
        }
        //Finally the item itself is added to the tree
        currentTreeNode.add(n);
    }

    public DefaultMutableTreeNode addIfNotPresent(DefaultMutableTreeNode parent, String nodeName) {
        //This recursive method searches the children of the parent directory, and if an item with the nodeName variable as a name is not present it is added
        ArrayList<TreeNode> childList = Collections.list(parent.children());
        for (TreeNode childList1 : childList) {
            if (childList1.toString().equals(nodeName)) {
                return (DefaultMutableTreeNode) childList1;
            }
        }
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(nodeName);
        n.setAllowsChildren(true);
        parent.add(n);
        return n;
    }

    public Node[] nodePathToRoot(Node input) {
        //This uses the nodes to root method to find out how far it will need to go to reach the root
        int n = nodesToRoot(input, 0);
        
        //Creates an array with that length for all the nodes to be added to
        Node[] allNodesToRoot = new Node[n];

        Node currentNode = input;

        //Repeats upwards through parents adding them to the array as it goes
        for (int i = n - 1; i >= 0; i--) {
            allNodesToRoot[i] = currentNode.getParentNode();
            currentNode = currentNode.getParentNode();
        }

        //Then a new array is created and it is looped through, this array skips the children elements because they are not necessary
        Node[] nodesToRoot = new Node[(int) Math.floor(allNodesToRoot.length / 2)];
        int r = 1;
        for (int i = 0; i < Math.floor(allNodesToRoot.length / 2); i++) {
            nodesToRoot[i] = allNodesToRoot[r];
            r = r + 2;
        }

        //The array is returned
        return nodesToRoot;
    }

    public int nodesToRoot(Node input, int distance) {
        //This method gets the parent, and if the parent doesn't equal null it returns itself with the distance variable increased by one
        if (input.getParentNode() == null) {
            //If it is null then the current distance is returned
            return distance;
        } else {
            return nodesToRoot(input.getParentNode(), distance + 1);
        }
    }
}
