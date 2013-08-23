package mod.gui.section;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import mod.model.Department;
import mod.model.Division;
import mod.model.controller.DeptC;
import mod.model.controller.DivC;
import mod.others.myFunction;

public class section_list_manage extends javax.swing.JInternalFrame {

    public static section_add frmNew;
    public static section_edit frmEdit;
    static Statement stmt;
    static int deptid, divid;

    public section_list_manage() {
        initComponents();
        setComboDeptModel();
        setComboDivModel(0);

    }

    void setComboDeptModel() {
        cmbdept.setModel(new DeptC().getDeptComboModel());
    }

    void setComboDivModel(int did) {
        cmbdiv.setModel(new DivC().getDivComboModel(did));
    }

    public final void populateCombo() {
        //Populate Combo Area
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) cmbdept.getModel();
        theModel.removeAllElements();
        cmbdept.addItem("--SELECT--");

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM department ORDER BY dept_name";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/build.png") + ">&nbsp</td><td>" + rs.getString(2).trim() + "<font color=#E7C30F>" + " (" + rs.getString(3) + ")" + "<font></td></th>";
                cmbdept.addItem(new Item2(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    class Item2 {

        private int id;
        private String description;

        public Item2(int id, String description) {
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

    public final void populateCombo1(int did) {
        //Populate Combo Area
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) cmbdiv.getModel();
        theModel.removeAllElements();
        cmbdiv.addItem("--SELECT--");

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM division WHERE dept_id=" + did + " ORDER BY div_name";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/div.png") + ">&nbsp</td><td>" + rs.getString(2).trim() + "<font></td></th>";
                cmbdiv.addItem(new Item3(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    class Item3 {

        private int id;
        private String description;

        public Item3(int id, String description) {
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

    boolean check_existance(int sid) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT sec_id FROM employee WHERE sec_id=" + sid;

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
        frmNew = new section_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new section_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void populateLst() {
        //Populate Combo Area
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM section WHERE div_id=" + divid + " ORDER BY sec_name";

        try {
            DefaultListModel model = new DefaultListModel();
            this.lst.setModel(model);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/section.png") + ">&nbsp</td><td>" + rs.getString(2).trim() + "<font></td></th>";
                model.addElement(new Item(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Populating List!");
        }
    }

    public static void remove_sec(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM section "
                + "WHERE sec_id=" + id;

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
        cmbdiv = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Section");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sectionm.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
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
        jLabel2.setText("Select Department");

        lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst);

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setText("List of Section/s");

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

        cmbdiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdivActionPerformed(evt);
            }
        });
        cmbdiv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbdivKeyPressed(evt);
            }
        });

        jLabel3.setDisplayedMnemonic('L');
        jLabel3.setText("Select Division");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(442, 442, 442))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(cmbdept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(293, 293, 293))
                                    .addComponent(cmbdiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdiv, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        lst.setSelectedIndex(0);
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdeptActionPerformed

        deptid = 0;
        try {
            int id = deptid = ((Department) cmbdept.getSelectedItem()).getDeptId();

            setComboDivModel(id);
            divid = 0;
            populateLst();

        } catch (Exception e) {
            DefaultListModel model = (DefaultListModel) lst.getModel();
            model.removeAllElements();
            setComboDivModel(0);
        }

    }//GEN-LAST:event_cmbdeptActionPerformed

    private void cmbdeptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbdeptKeyPressed
    }//GEN-LAST:event_cmbdeptKeyPressed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();

            boolean ifexist = check_existance(id);

            if (ifexist == true) {
                JOptionPane.showMessageDialog(null, "Record cannot be remove! There are Employee/s depends on this Section!");
            } else {

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {

                    remove_sec(id);
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

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            Item item = (Item) lst.getSelectedValue();
            int id = item.getId();
            section_edit.secid = id;
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Item Category Selected!");
        }
    }//GEN-LAST:event_cmdeditActionPerformed

//    public static void assign_Shift(int empid, int shift_id, Date start, Date end, int userid, Date tdate,int pflag) {
//        Connection conn = myDBConn.getConnection();
//        String createString= "INSERT INTO employee_shift("
//                    + "emp_id, "
//                    + "shift_id "
//                    + "start_date, "
//                    + "end_date, "
//                    + "user_id, "
//                    + "trans_date, "
//                    + "p_flag"
//                    +") "
//                    + "VALUES ("
//                    + empid + ","
//                    + shift_id +","
//                    + start+","
//                    + end+","
//                    + userid + ","
//                    + tdate+","
//                    + pflag +" " 
//                    + ")";
//
//        try {
//            stmt = conn.createStatement();
//            stmt.executeUpdate(createString);
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//        
//
//
//
//    }
    private void cmdrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefreshActionPerformed
        DefaultListModel model = (DefaultListModel) lst.getModel();
        model.removeAllElements();

        setComboDeptModel();
        setComboDivModel(0);
    }//GEN-LAST:event_cmdrefreshActionPerformed

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        if (cmbdiv.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a Division!");
        } else {
            section_add.divid = divid;
            ShowFrmNew();
        }
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmbdivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdivActionPerformed
        DefaultListModel model = (DefaultListModel) lst.getModel();
        model.removeAllElements();
        divid = 0;
        try {
            divid = ((Division) cmbdiv.getSelectedItem()).getDivId();
            populateLst();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbdivActionPerformed

    private void cmbdivKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbdivKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbdivKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JComboBox cmbdiv;
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdrefresh;
    private javax.swing.JButton cmdremove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lst;
    // End of variables declaration//GEN-END:variables
}
