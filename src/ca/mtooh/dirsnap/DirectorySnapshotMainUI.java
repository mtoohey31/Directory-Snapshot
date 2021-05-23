/*
 * This is the Main UI of the program
 * It allows the user to control the functions of the program, displays the program's output, and informs the user when an incorrect input has been entered
 */
package ca.mtooh.dirsnap;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.filechooser.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mtoohey
 */
public class DirectorySnapshotMainUI extends javax.swing.JFrame {

    /**
     * Creates new form DirectorySnapshotUI
     */
    public DirectorySnapshotMainUI() {
        //Sets the applications look and feel to follow the operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        initComponents();

        //Adds file filters to the file chooser and removes the accept all filter
        fileOnlyChooser.setFileFilter(new FileNameExtensionFilter("Directory Snapshot Files", "dirsnap"));
        fileOnlyChooser.setAcceptAllFileFilterUsed(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dirOnlyChooser = new javax.swing.JFileChooser();
        fileOnlyChooser = new javax.swing.JFileChooser();
        Title = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        snapshotInputDirectoryPathBox = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        snapshotTree = new javax.swing.JTree();
        snapshotInputDirectorySelectButton = new javax.swing.JButton();
        snapshotOutputDirectoryPathBox = new javax.swing.JTextField();
        snapshotInputDirectoryLabel = new javax.swing.JLabel();
        snapshotOutputDirectoryLabel = new javax.swing.JLabel();
        snapshotOutputDirectorySelectButton = new javax.swing.JButton();
        snapshotGoButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        inputDirectoryLabel1 = new javax.swing.JLabel();
        viewInputDirectoryPathBox = new javax.swing.JTextField();
        viewInputDirectorySelectButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        viewTree = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        compareSplitPane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        compareTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        compareTree2 = new javax.swing.JTree();
        compareInputDirectoryLabel1 = new javax.swing.JLabel();
        compareInputDirectoryPathBox1 = new javax.swing.JTextField();
        compareInputDirectorySelectButton1 = new javax.swing.JButton();
        compareInputDirectoryLabel2 = new javax.swing.JLabel();
        compareInputDirectoryPathBox2 = new javax.swing.JTextField();
        compareInputDirectorySelectButton2 = new javax.swing.JButton();
        compareButton = new javax.swing.JButton();
        ignoreStoreAndThumbs = new javax.swing.JCheckBox();
        depthLimitBox = new javax.swing.JTextField();
        depthLimitLabel = new javax.swing.JLabel();

        dirOnlyChooser.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        dirOnlyChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        fileOnlyChooser.setFileFilter(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Title.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        Title.setText("Directory Snapshot");

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        snapshotInputDirectoryPathBox.setEditable(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Select input and output directories and click snapshot to create a snapshot.");
        snapshotTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane3.setViewportView(snapshotTree);

        snapshotInputDirectorySelectButton.setText("Select");
        snapshotInputDirectorySelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotInputDirectorySelectButtonActionPerformed(evt);
            }
        });

        snapshotOutputDirectoryPathBox.setEditable(false);

        snapshotInputDirectoryLabel.setText("Input Directory:");

        snapshotOutputDirectoryLabel.setText("Output Directory:");

        snapshotOutputDirectorySelectButton.setText("Select");
        snapshotOutputDirectorySelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotOutputDirectorySelectButtonActionPerformed(evt);
            }
        });

        snapshotGoButton.setText("Snapshot");
        snapshotGoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotGoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(snapshotOutputDirectoryLabel)
                                .addGap(18, 18, 18)
                                .addComponent(snapshotOutputDirectoryPathBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(snapshotOutputDirectorySelectButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(snapshotInputDirectoryLabel)
                                .addGap(18, 18, 18)
                                .addComponent(snapshotInputDirectoryPathBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(snapshotInputDirectorySelectButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(snapshotGoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(snapshotInputDirectorySelectButton)
                            .addComponent(snapshotInputDirectoryPathBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snapshotInputDirectoryLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(snapshotOutputDirectoryLabel)
                            .addComponent(snapshotOutputDirectoryPathBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snapshotOutputDirectorySelectButton)))
                    .addComponent(snapshotGoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Snapshot", jPanel1);

        inputDirectoryLabel1.setText("Input File:");

        viewInputDirectoryPathBox.setEditable(false);

        viewInputDirectorySelectButton.setText("Select");
        viewInputDirectorySelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInputDirectorySelectButtonActionPerformed(evt);
            }
        });

        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Select the file you would like to view and click view to view it.");
        viewTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane4.setViewportView(viewTree);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(inputDirectoryLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(viewInputDirectoryPathBox, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewInputDirectorySelectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewButton)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputDirectoryLabel1)
                        .addComponent(viewInputDirectoryPathBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewInputDirectorySelectButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View", jPanel3);

        compareSplitPane.setDividerLocation(375);
        compareSplitPane.setResizeWeight(0.5);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Select two files.");
        compareTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(compareTree1);

        compareSplitPane.setLeftComponent(jScrollPane1);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Then click compare to view changes.");
        compareTree2.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(compareTree2);

        compareSplitPane.setRightComponent(jScrollPane2);

        compareInputDirectoryLabel1.setText("Input File 1:");

        compareInputDirectoryPathBox1.setEditable(false);

        compareInputDirectorySelectButton1.setText("Select");
        compareInputDirectorySelectButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareInputDirectorySelectButton1ActionPerformed(evt);
            }
        });

        compareInputDirectoryLabel2.setText("Input File 2:");

        compareInputDirectoryPathBox2.setEditable(false);

        compareInputDirectorySelectButton2.setText("Select");
        compareInputDirectorySelectButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareInputDirectorySelectButton2ActionPerformed(evt);
            }
        });

        compareButton.setText("Compare");
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compareSplitPane)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(compareInputDirectoryLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(compareInputDirectoryPathBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compareInputDirectorySelectButton2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(compareInputDirectoryLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(compareInputDirectoryPathBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compareInputDirectorySelectButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(compareInputDirectorySelectButton1)
                            .addComponent(compareInputDirectoryPathBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(compareInputDirectoryLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(compareInputDirectorySelectButton2)
                            .addComponent(compareInputDirectoryPathBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(compareInputDirectoryLabel2)))
                    .addComponent(compareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Compare", jPanel2);

        ignoreStoreAndThumbs.setSelected(true);
        ignoreStoreAndThumbs.setText("Ignore .DS_Store/Thumbs.db");

        depthLimitLabel.setText("Depth Limit (Leave blank for none)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Title)
                        .addGap(18, 18, 18)
                        .addComponent(ignoreStoreAndThumbs)
                        .addGap(18, 18, 18)
                        .addComponent(depthLimitBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depthLimitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(depthLimitBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(depthLimitLabel)
                            .addComponent(ignoreStoreAndThumbs))
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //Close the program
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void snapshotInputDirectorySelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotInputDirectorySelectButtonActionPerformed
        //Lets the user select a directory and see the path in the text box when they click the select buttonn

        int returnVal = dirOnlyChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            snapshotInputDirectoryPathBox.setText(dirOnlyChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_snapshotInputDirectorySelectButtonActionPerformed

    private void snapshotOutputDirectorySelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotOutputDirectorySelectButtonActionPerformed
        //Lets the user select a directory and see the path in the text box when they click the select buttonn

        int returnVal = dirOnlyChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            snapshotOutputDirectoryPathBox.setText(dirOnlyChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_snapshotOutputDirectorySelectButtonActionPerformed

    private void snapshotGoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotGoButtonActionPerformed
        //Checks to make sure that both selections are filled, it does not need to validate them otherwise because they can only be modified with the filechooser that can only select valid files
        if (snapshotOutputDirectoryPathBox.getText().length() == 0 || snapshotOutputDirectoryPathBox.getText().length() == 0) {
            DefaultMutableTreeNode incompleteSelectionsErrorTree = new DefaultMutableTreeNode("Please complete both selections.");
            DefaultTreeModel incompleteSelectionsErrorModel = new DefaultTreeModel(incompleteSelectionsErrorTree);

            snapshotTree.setModel(incompleteSelectionsErrorModel);
        } else {
            SnapToXML stx = new SnapToXML();
            ReadXMLToTree rxt = new ReadXMLToTree();

            //If both selections are good then it checks if the path in the output is a file or directory, and changes it to the parent directory if it's a file
            if (snapshotOutputDirectoryPathBox.getText().endsWith(".dirsnap")) {
                snapshotOutputDirectoryPathBox.setText(Paths.get(snapshotOutputDirectoryPathBox.getText()).getParent().toString());
            }

            //It checks if a depth limit is present, if it is it calls the snapshot then view functions using that depth limit, if the depth limit is present but invalid it tells the user, and if it's not present it calls those functions without a depth limit
            if (depthLimitBox.getText().length() == 0) {
                Path output = stx.snapToXML(Paths.get(snapshotInputDirectoryPathBox.getText()), Paths.get(snapshotOutputDirectoryPathBox.getText()), ".dirsnap");

                snapshotOutputDirectoryPathBox.setText(output.toString());

                snapshotTree.setModel(rxt.readXMLToTree(output, ignoreStoreAndThumbs.isSelected()));

                snapshotTree.expandPath(new TreePath(snapshotTree.getModel().getRoot()));
            } else {
                try {
                    Integer depthLimit = Integer.parseInt(depthLimitBox.getText());
                    if (depthLimit >= 0) {
                        Path output = stx.snapToXML(Paths.get(snapshotInputDirectoryPathBox.getText()), Paths.get(snapshotOutputDirectoryPathBox.getText()), ".dirsnap", depthLimit);

                        snapshotOutputDirectoryPathBox.setText(output.toString());

                        snapshotTree.setModel(rxt.readXMLToTree(output, ignoreStoreAndThumbs.isSelected()));

                        snapshotTree.expandPath(new TreePath(snapshotTree.getModel().getRoot()));
                    } else {
                        DefaultMutableTreeNode subZeroDepthLimitErrorTree = new DefaultMutableTreeNode("The depth cannot be less than 0.");
                        DefaultTreeModel subZeroDepthLimitModel = new DefaultTreeModel(subZeroDepthLimitErrorTree);

                        snapshotTree.setModel(subZeroDepthLimitModel);
                    }
                } catch (NumberFormatException e) {
                    DefaultMutableTreeNode nonIntegerDepthLimitErrorTree = new DefaultMutableTreeNode("The depth limit must be an integer.");
                    DefaultTreeModel nonIntegerDepthLimitModel = new DefaultTreeModel(nonIntegerDepthLimitErrorTree);

                    snapshotTree.setModel(nonIntegerDepthLimitModel);
                }
            }
        }


    }//GEN-LAST:event_snapshotGoButtonActionPerformed

    private void viewInputDirectorySelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInputDirectorySelectButtonActionPerformed
        //Lets the user select a file and see the path in the text box when they click the select buttonn

        int returnVal = fileOnlyChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            viewInputDirectoryPathBox.setText(fileOnlyChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_viewInputDirectorySelectButtonActionPerformed

    private void compareInputDirectorySelectButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareInputDirectorySelectButton1ActionPerformed
        //Lets the user select a file and see the path in the text box when they click the select buttonn

        int returnVal = fileOnlyChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            compareInputDirectoryPathBox1.setText(fileOnlyChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_compareInputDirectorySelectButton1ActionPerformed

    private void compareInputDirectorySelectButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareInputDirectorySelectButton2ActionPerformed
        //Lets the user select a file and see the path in the text box when they click the select buttonn

        int returnVal = fileOnlyChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            compareInputDirectoryPathBox2.setText(fileOnlyChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_compareInputDirectorySelectButton2ActionPerformed

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        //Confirms that both selections are present and informs the user if they are not
        if (compareInputDirectoryPathBox1.getText().length() == 0 || compareInputDirectoryPathBox2.getText().length() == 0) {
            DefaultMutableTreeNode incompleteSelectionsErrorTree1 = new DefaultMutableTreeNode("Please complete both selections...");
            DefaultTreeModel incompleteSelectionsErrorModel1 = new DefaultTreeModel(incompleteSelectionsErrorTree1);

            compareTree1.setModel(incompleteSelectionsErrorModel1);

            DefaultMutableTreeNode incompleteSelectionsErrorTree2 = new DefaultMutableTreeNode("...and try again.");
            DefaultTreeModel incompleteSelectionsErrorModel2 = new DefaultTreeModel(incompleteSelectionsErrorTree2);

            compareTree2.setModel(incompleteSelectionsErrorModel2);
        } else {
            //If both selections are good then it calls the compare function, after checking whether a depth limit should be used and informing the user if they are invalid
            
            CompareXMLs cx = new CompareXMLs();

            if (depthLimitBox.getText().length() == 0) {
                DefaultTreeModel[] models = cx.compareXMLs(Paths.get(compareInputDirectoryPathBox1.getText()), Paths.get(compareInputDirectoryPathBox2.getText()), ignoreStoreAndThumbs.isSelected());

                compareTree1.setModel(models[0]);
                compareTree2.setModel(models[1]);

                compareTree1.expandPath(new TreePath(compareTree1.getModel().getRoot()));
                compareTree2.expandPath(new TreePath(compareTree2.getModel().getRoot()));
            } else {
                try {
                    Integer depthLimit = Integer.parseInt(depthLimitBox.getText());
                    if (depthLimit >= 1) {
                        DefaultTreeModel[] models = cx.compareXMLs(Paths.get(compareInputDirectoryPathBox1.getText()), Paths.get(compareInputDirectoryPathBox2.getText()), ignoreStoreAndThumbs.isSelected(), depthLimit);

                        compareTree1.setModel(models[0]);
                        compareTree2.setModel(models[1]);

                        compareTree1.expandPath(new TreePath(compareTree1.getModel().getRoot()));
                        compareTree2.expandPath(new TreePath(compareTree2.getModel().getRoot()));
                    } else {
                        DefaultMutableTreeNode subZeroDepthLimitErrorTree1 = new DefaultMutableTreeNode("The depth must be at least 1...");
                        DefaultTreeModel subZeroDepthLimitModel1 = new DefaultTreeModel(subZeroDepthLimitErrorTree1);

                        compareTree1.setModel(subZeroDepthLimitModel1);

                        DefaultMutableTreeNode subZeroDepthLimitErrorTree2 = new DefaultMutableTreeNode("...please change it and try again.");
                        DefaultTreeModel subZeroDepthLimitModel2 = new DefaultTreeModel(subZeroDepthLimitErrorTree2);

                        compareTree2.setModel(subZeroDepthLimitModel2);
                    }
                } catch (NumberFormatException e) {
                    DefaultMutableTreeNode nonIntegerDepthLimitErrorTree = new DefaultMutableTreeNode("The depth limit must be an integer...");
                    DefaultTreeModel nonIntegerDepthLimitModel = new DefaultTreeModel(nonIntegerDepthLimitErrorTree);

                    compareTree1.setModel(nonIntegerDepthLimitModel);

                    DefaultMutableTreeNode nonIntegerDepthLimitErrorTree2 = new DefaultMutableTreeNode("...please change it and try again.");
                    DefaultTreeModel nonIntegerDepthLimitModel2 = new DefaultTreeModel(nonIntegerDepthLimitErrorTree2);

                    compareTree2.setModel(nonIntegerDepthLimitModel2);
                }
            }
        }
    }//GEN-LAST:event_compareButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        //Checks if an input file has been selected
        if (viewInputDirectoryPathBox.getText().length() == 0) {
            DefaultMutableTreeNode incompleteSelectionErrorTree = new DefaultMutableTreeNode("Please select a file.");
            DefaultTreeModel incompleteSelectionErrorModel = new DefaultTreeModel(incompleteSelectionErrorTree);

            viewTree.setModel(incompleteSelectionErrorModel);
        } else {
            //If a file has been seleced it checks the depth limit and calls the function or informs the user of an error with the depth limit
            ReadXMLToTree rxt = new ReadXMLToTree();
            if (depthLimitBox.getText().length() == 0) {
                viewTree.setModel(rxt.readXMLToTree(Paths.get(viewInputDirectoryPathBox.getText()), ignoreStoreAndThumbs.isSelected()));
                viewTree.expandPath(new TreePath(viewTree.getModel().getRoot()));
            } else {
                try {
                    Integer depthLimit = Integer.parseInt(depthLimitBox.getText());
                    if (depthLimit >= 0) {
                        viewTree.setModel(rxt.readXMLToTree(Paths.get(viewInputDirectoryPathBox.getText()), ignoreStoreAndThumbs.isSelected(), depthLimit));
                    } else {
                        DefaultMutableTreeNode subZeroDepthLimitErrorTree = new DefaultMutableTreeNode("The depth cannot be less than 0.");
                        DefaultTreeModel subZeroDepthLimitModel = new DefaultTreeModel(subZeroDepthLimitErrorTree);

                        viewTree.setModel(subZeroDepthLimitModel);
                    }
                } catch (NumberFormatException e) {
                    DefaultMutableTreeNode nonIntegerDepthLimitErrorTree = new DefaultMutableTreeNode("The depth limit must be integer.");
                    DefaultTreeModel nonIntegerDepthLimitModel = new DefaultTreeModel(nonIntegerDepthLimitErrorTree);

                    viewTree.setModel(nonIntegerDepthLimitModel);
                }
            }
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DirectorySnapshotMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DirectorySnapshotMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DirectorySnapshotMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DirectorySnapshotMainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DirectorySnapshotMainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton compareButton;
    private javax.swing.JLabel compareInputDirectoryLabel1;
    private javax.swing.JLabel compareInputDirectoryLabel2;
    private javax.swing.JTextField compareInputDirectoryPathBox1;
    private javax.swing.JTextField compareInputDirectoryPathBox2;
    private javax.swing.JButton compareInputDirectorySelectButton1;
    private javax.swing.JButton compareInputDirectorySelectButton2;
    private javax.swing.JSplitPane compareSplitPane;
    private javax.swing.JTree compareTree1;
    private javax.swing.JTree compareTree2;
    private javax.swing.JTextField depthLimitBox;
    private javax.swing.JLabel depthLimitLabel;
    private javax.swing.JFileChooser dirOnlyChooser;
    private javax.swing.JButton exitButton;
    private javax.swing.JFileChooser fileOnlyChooser;
    private javax.swing.JCheckBox ignoreStoreAndThumbs;
    private javax.swing.JLabel inputDirectoryLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton snapshotGoButton;
    private javax.swing.JLabel snapshotInputDirectoryLabel;
    private javax.swing.JTextField snapshotInputDirectoryPathBox;
    private javax.swing.JButton snapshotInputDirectorySelectButton;
    private javax.swing.JLabel snapshotOutputDirectoryLabel;
    private javax.swing.JTextField snapshotOutputDirectoryPathBox;
    private javax.swing.JButton snapshotOutputDirectorySelectButton;
    private javax.swing.JTree snapshotTree;
    private javax.swing.JButton viewButton;
    private javax.swing.JTextField viewInputDirectoryPathBox;
    private javax.swing.JButton viewInputDirectorySelectButton;
    private javax.swing.JTree viewTree;
    // End of variables declaration//GEN-END:variables
}
