/*
 * This reads an XML file that has already been written into a TreeModel
 */
package ca.mtooh.dirsnap;

import java.io.IOException;
import java.nio.file.Path;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

/**
 *
 * @author mtoohey
 */
public class ReadXMLToTree {

    ElementFinder ef = new ElementFinder();

    public DefaultTreeModel readXMLToTree(Path selectedInputFile, Boolean ignoreStoreAndThumbs, Integer... depth) {
        try {
            //Loads the document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(selectedInputFile.toString());

            //Gets the root element of the XML
            Element rootXML = doc.getDocumentElement();

            DefaultMutableTreeNode rootTree = new DefaultMutableTreeNode(ef.displayString(rootXML));
            DefaultTreeModel treeModel = new DefaultTreeModel(rootTree, true);

            //Checks if an imput depth has been used and calls the recursive method with a depth limit decreased by one, without a depth limit, or displays the depth limit right away based on what it finds
            if (depth.length > 0) {
                if (depth[0] == 0) {
                    DefaultMutableTreeNode l = new DefaultMutableTreeNode("Selected Depth Limit Reached.");
                    l.setAllowsChildren(false);
                    rootTree.add(l);
                } else {
                    readXMLToTreeRecursive(rootTree, rootXML, ignoreStoreAndThumbs, 1, depth[0] - 1);
                }
            } else {
                readXMLToTreeRecursive(rootTree, rootXML, ignoreStoreAndThumbs, 0);
            }

            //Returns the treemodel
            return treeModel;

        } catch (ParserConfigurationException | IOException | SAXException pce) {
            return null;
        }

    }

    public void readXMLToTreeRecursive(DefaultMutableTreeNode currentTreeNode, Element currentXMLNode, Boolean ignoreStoreAndThumbs, Integer depthCause, Integer... depth) {
        //Creates a nodelist from the children of the "currentXMLNode"
        NodeList children = ef.children(currentXMLNode).getChildNodes();
        //Repeats for each of the children
        for (int i = 0; i < children.getLength(); i++) {
            //If the child is a directory...
            if (children.item(i).getNodeName().equals("dir")) {
                //Gets the display string and adds it to the tree
                DefaultMutableTreeNode n = new DefaultMutableTreeNode(ef.displayString(children.item(i)));
                currentTreeNode.add(n);
                //If a depth length is present it uses the recursive method again with the depth decreased by 1, if it's not present it uses the recursive method without a depth, and if it's 0 or less it displays the correct depth limit message
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
                        readXMLToTreeRecursive(n, (Element) children.item(i), ignoreStoreAndThumbs, depthCause, depth[0] - 1);
                    }
                } else {
                    readXMLToTreeRecursive(n, (Element) children.item(i), ignoreStoreAndThumbs, depthCause);
                }
                //If the child is a file and the boolean for ignoring .DS_Store and Thumbs.db files is not enabled then it gets the display string adds the file to the tree
            } else if (children.item(i).getNodeName().equals("file") && !ignoreStoreAndThumbs) {
                DefaultMutableTreeNode n = new DefaultMutableTreeNode(ef.displayString(children.item(i)));
                //Diables the ability for children to be added since it is a file, which also changes the icon so it is a file

                n.setAllowsChildren(false);
                currentTreeNode.add(n);
                //If the child is a file and the boolean for ignoring .DS_Store and Thumbs.db files is enabled then it checks that the file isn't one of those before getting the display string and adding it to the tree
            } else if (children.item(i).getNodeName().equals("file") && ignoreStoreAndThumbs && !ef.name(children.item(i)).equals(".DS_Store") && !ef.name(children.item(i)).equals("Thumbs.db")) {
                DefaultMutableTreeNode n = new DefaultMutableTreeNode(ef.displayString(children.item(i)));
                //Diables the ability for children to be added since it is a file, which also changes the icon so it is a file
                n.setAllowsChildren(false);
                currentTreeNode.add(n);
                //If the child is a depth limit file it is added to the tree
            } else if (children.item(i).getNodeName().equals("depthLimit")) {
                DefaultMutableTreeNode l = new DefaultMutableTreeNode("Snapshot depth limit reached.");
                //Diables the ability for children to be added since it should not continue past this point, which also changes the icon so it is a file
                l.setAllowsChildren(false);
                currentTreeNode.add(l);
            }
        }
    }
}
