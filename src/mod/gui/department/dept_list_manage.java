/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.gui.department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import mod.model.Department;
import mod.model.controller.DeptC;
import mod.others.myFunction;

public final class dept_list_manage extends javax.swing.JInternalFrame {

    static Statement stmt;
    public static dept_add frmNew;
    public static dept_edit frmEdit;
    Department dept = new Department();
    DeptC deptc = new DeptC();

    public dept_list_manage() {
        initComponents();
        setListModel();
        lst.setSelectedIndex(0);
    }

    public void setListModel() {
        lst.setModel(new DeptC().getDeptListModel());
    }

    public void ShowFrmEdit() {
        frmEdit = new dept_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void ShowFrmNew() {

        frmNew = new dept_add(this, true);
        frmNew.setVisible(true);
    }

    boolean check_existance(int did) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT dept_id FROM employee WHERE dept_id=" + did;

        int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                rc++;
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        if (rc > 0) {
            found = true;
        }

        return found;
    }

    public static void remove_dept(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM department "
                + "WHERE dept_id=" + id;

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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdexit = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdnew = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Departments");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buildm.png"))); // NOI18N

        lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst);

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setLabelFor(lst);
        jLabel1.setText("List of Department/s");

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        cmdedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        cmdedit.setMnemonic('E');
        cmdedit.setText("Edit");
        cmdedit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdeditActionPerformed(evt);
            }
        });

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('N');
        cmdnew.setText("New");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdremove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cmdnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdremove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdexit)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        ShowFrmNew();
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            int id = ((Department) lst.getSelectedValue()).getDeptId();
            dept_edit.deptid = id;
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Item Category Selected!");
            e.getMessage();
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            int id = ((Department) lst.getSelectedValue()).getDeptId();

            boolean ifexist = check_existance(id);

            if (ifexist == true) {
                JOptionPane.showMessageDialog(null, "Record cannot be remove! There are division record/s under on this department!");
            } else {

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {
                    dept.setDeptId(id);

                    if (deptc.deleteDept(dept) > 0) {
                        setListModel();
                        //this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Record Successfully Removed!");
                    }
                } else if (i == 1) {
                } else if (i == 2) {
                    this.dispose(); //exit window
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Record Selected!");
        }
    }//GEN-LAST:event_cmdremoveActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdremove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lst;
    // End of variables declaration//GEN-END:variables
}
