package mod.gui.division;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import mod.model.Department;
import mod.model.controller.DeptC;
import mod.others.myFunction;

public class div_list_manage extends javax.swing.JInternalFrame {

    public static div_add frmNew;
    public static div_edit frmEdit;
    static Statement stmt;
    static int deptid;

    public div_list_manage() {
        initComponents();
        
        setComboModels();
       // populateLst();
    }

    void setComboModels() {
        cmbdept.setModel(new DeptC().getDeptComboModel());
    }

    boolean check_existance(int did) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT div_id FROM section WHERE div_id=" + did;

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

    public void ShowFrmNew() {
        frmNew = new div_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new div_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void populateLst() {
        //Populate Combo Area
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM division WHERE dept_id=" + deptid + " ORDER BY div_name";

        try {
            DefaultListModel model = new DefaultListModel();
            this.lst.setModel(model);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/div.png") + ">&nbsp</td><td>" + rs.getString(2).trim() + "<font></td></th>";
                model.addElement(new Item(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Populating List!");
        }
    }

    public static void remove_div(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM division "
                + "WHERE div_id=" + id;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cmbdept = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdremove = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdrefresh = new javax.swing.JButton();
        cmdnew = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Division");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/divm.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        cmbdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdeptActionPerformed(evt);
            }
        });
        cmbdept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbdeptKeyPressed(evt);
            }
        });

        jLabel2.setDisplayedMnemonic('L');
        jLabel2.setLabelFor(cmbdept);
        jLabel2.setText("Select Department");

        lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst);

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setLabelFor(lst);
        jLabel1.setText("List of Division/s");

        cmdremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdremove.setMnemonic('R');
        cmdremove.setText("Remove");
        cmdremove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdremove.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremoveActionPerformed(evt);
            }
        });

        cmdedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        cmdedit.setMnemonic('E');
        cmdedit.setText("Edit");
        cmdedit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdedit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdeditActionPerformed(evt);
            }
        });

        cmdrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        cmdrefresh.setMnemonic('f');
        cmdrefresh.setText("Refresh");
        cmdrefresh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdrefresh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdrefreshActionPerformed(evt);
            }
        });

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('N');
        cmdnew.setText("New");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
            }
        });

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdnew, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdedit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdremove, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cmdnew)
                .addGap(5, 5, 5)
                .addComponent(cmdedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdremove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdrefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdexit)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(303, 303, 303))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(cmbdept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
            div_edit.divid = id;
            //div_edit.unitdesc = lst.getSelectedValue().toString();

            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Item Category Selected!");
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();

            boolean ifexist = check_existance(id);

            if (ifexist == true) {
                JOptionPane.showMessageDialog(null, "Record cannot be remove! There are section/s under on this Division!");
            } else {

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {


                    remove_div(id);
                    this.populateLst();
                    JOptionPane.showMessageDialog(null, "Record Successfully Removed!");

                } else if (i == 1) {
                } else if (i == 2) {
                    this.dispose(); //exit window
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Record Selected!");
        }
    }//GEN-LAST:event_cmdremoveActionPerformed

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        if (cmbdept.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Department!");
        } else {
            div_add.deptid = deptid;
            ShowFrmNew();
        }
    }//GEN-LAST:event_cmdnewActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        lst.setSelectedIndex(0);
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefreshActionPerformed
        DefaultListModel model = (DefaultListModel) lst.getModel();
        model.removeAllElements();
        setComboModels();
    }//GEN-LAST:event_cmdrefreshActionPerformed

    private void cmbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdeptActionPerformed
//        DefaultListModel model = (DefaultListModel) lst.getModel();
//        model.removeAllElements();
        deptid = 0;
        try {
            deptid = ((Department) cmbdept.getSelectedItem()).getDeptId();
            populateLst();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbdeptActionPerformed

    private void cmbdeptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbdeptKeyPressed
    }//GEN-LAST:event_cmbdeptKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdrefresh;
    private javax.swing.JButton cmdremove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lst;
    // End of variables declaration//GEN-END:variables
}
