package mod.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import mod.connection.myDBConn;
import mod.others.myFunction;

public class accounts extends javax.swing.JInternalFrame {

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static Statement stmt;
    public static new_account frmNew;
    public static edit_account frmEdit;
    public static assign_group frmGroup;

    public accounts() {
        initComponents();
        populateLst();
    }

    public void ShowFrmNew() {
        frmNew = new new_account(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmAssign() {
        frmGroup = new assign_group(this, true);
        frmGroup.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new edit_account(this, true);
        frmEdit.setVisible(true);
    }

    public void populateLst() {
        //Populate Combo Area
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT user_id, full_name, username, gender FROM user ORDER BY full_name";

        try {
            DefaultListModel model = new DefaultListModel();
            this.lst.setModel(model);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                String genderstr = "";
                int gender = rs.getInt(4);
                if (gender == 1) {
                    genderstr = "male.png";
                } else if (gender == 2) {
                    genderstr = "female.png";
                }

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/" + genderstr) + "></td><td>" + rs.getString(2) + "<br>" + "<font color=#E7C30F>Username: " + rs.getString(3) + "</font></td></th>";

                model.addElement(new Item(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    class Item {

        private int id;
        private String description;

        public Item(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }

    public static void DeleteUser(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM user WHERE user_id=" + id;


        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdnew = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();
        cmdprev = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        cmdreset = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("User Accounts");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('N');
        cmdnew.setText("New User");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
            }
        });

        cmdedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        cmdedit.setMnemonic('E');
        cmdedit.setText("Edit User Info");
        cmdedit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdeditActionPerformed(evt);
            }
        });

        cmdremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdremove.setMnemonic('R');
        cmdremove.setText("Remove");
        cmdremove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremoveActionPerformed(evt);
            }
        });

        cmdprev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/privileges.png"))); // NOI18N
        cmdprev.setMnemonic('u');
        cmdprev.setText("User Group");
        cmdprev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdprev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdprevActionPerformed(evt);
            }
        });

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst);

        jLabel1.setDisplayedMnemonic('f');
        jLabel1.setText("List of Item Authorized User/s");

        cmdreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        cmdreset.setMnemonic('w');
        cmdreset.setText("Clear Password");
        cmdreset.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdremove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdprev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdreset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdnew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdedit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdreset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdremove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdprev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdexit)
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        ShowFrmNew();
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
            edit_account.userid = id;

            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Account Selected!");
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    public static void reset_password(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;

        String pmd5 = myFunction.convertMD5("");
        createString = "UPDATE user SET password='" + pmd5 + "' "
                + "WHERE user_id=" + id;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cmdresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdresetActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
            int i = myFunction.msgboxYesNo("Are you sure you want to reset the password this user account?");
            if (i == 0) {
                reset_password(id);
                this.populateLst();
                JOptionPane.showMessageDialog(null, "Password Successfully Resetted!");
            } else if (i == 1) {
            } else if (i == 2) {
                this.dispose(); //exit window
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Account Selected!");
        }
    }//GEN-LAST:event_cmdresetActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
//            boolean ifexist = check_existance(id);

//            if (ifexist == true) {
//                JOptionPane.showMessageDialog(null, "Record cannot be remove! There are item depends on this Category!");
//            } else {

            int i = myFunction.msgboxYesNo("Are you sure you want to delete this user account?");
            if (i == 0) {
                DeleteUser(id);
                this.populateLst();
                JOptionPane.showMessageDialog(null, "User Account Removed!");

            } else if (i == 1) {
            } else if (i == 2) {
                this.dispose(); //exit window
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Account Selected!");
        }
    }//GEN-LAST:event_cmdremoveActionPerformed

    private void cmdprevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdprevActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
            assign_group.userid = id;
            assign_group.userinfo = lst.getSelectedValue().toString();
            ShowFrmAssign();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Account Selected!");
        }
    }//GEN-LAST:event_cmdprevActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdprev;
    private javax.swing.JButton cmdremove;
    private javax.swing.JButton cmdreset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lst;
    // End of variables declaration//GEN-END:variables
}
