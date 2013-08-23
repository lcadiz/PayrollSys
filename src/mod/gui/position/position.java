/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.gui.position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.Department;
import mod.model.Division;
import mod.model.Position;
import mod.model.Section;
import mod.model.controller.DeptC;
import mod.model.controller.DivC;
import mod.model.controller.PositionC;
import mod.model.controller.SectionC;
import mod.others.myFunction;

public final class position extends javax.swing.JInternalFrame {

    static Statement stmt;
    public static position_add frmNew;
    public static position_edit frmEdit;
    Position position = new Position();
    PositionC positionc = new PositionC();
    static int deptid, divid, secid;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3, model4;

    public position() {
        initComponents();
//        setListModel();

        setDeptComboModel();
        setDivComboModel(0);
        setSecComboModel(0);
//        lst.setSelectedIndex(0);


        TableColumn idClmn1 = tbl.getColumn("id");
        idClmn1.setMaxWidth(0);
        idClmn1.setMinWidth(0);
        idClmn1.setPreferredWidth(0);

    }

//    public void setListModel() {
//        lst.setModel(new PositionC().getPositionListModel(secid));
//    }
    public void populateTBL() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT p.position_id, p.position_desc, s.rank_value, l.description "
                + "FROM position p, salary s, position_level l  "
                + "WHERE p.salary_id=s.salary_id AND p.pos_level_id=l.pos_level_id AND p.sec_id=" + secid + " "
                + "GROUP BY p.position_id; ";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tbl.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);

            tbl.setRowHeight(28);

            tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/positionmini.png") + ">&nbsp</td><td>" + rs.getString(2) + "<font></td></th>";
                model2.addRow(new Object[]{rs.getString(1), lbl, rs.getString(3), rs.getString(4)});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void setDeptComboModel() {
        cmbdept.setModel(new DeptC().getDeptComboModel());
    }

    void setDivComboModel(int did) {
        cmbdiv.setModel(new DivC().getDivComboModel(did));
    }

    void setSecComboModel(int did) {
        cmbsec.setModel(new SectionC().getSecComboModel(did));
    }

    public void ShowFrmEdit() {
        frmEdit = new position_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void ShowFrmNew() {
        frmNew = new position_add(this, true);
        frmNew.setVisible(true);
    }

    boolean check_existance(int did) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT position_id FROM employee WHERE position_id=" + did;

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

    public static void remove_position(int id) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM department "
                + "WHERE position_id=" + id;

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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdexit = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdnew = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbdiv = new javax.swing.JComboBox();
        cmbdept = new javax.swing.JComboBox();
        cmbsec = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Plantilla Positions");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buildm.png"))); // NOI18N

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setText("List of Plantilla Position/s");

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

        jLabel2.setDisplayedMnemonic('L');
        jLabel2.setText("Select Department");

        jLabel3.setDisplayedMnemonic('L');
        jLabel3.setText("Select Division");

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

        cmbsec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbsec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsecActionPerformed(evt);
            }
        });

        jLabel4.setDisplayedMnemonic('L');
        jLabel4.setText("Select Section");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Position", "Rank", "Position Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(272, 272, 272))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(293, 293, 293))
                            .addComponent(cmbdiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbsec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbdept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdiv, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbsec, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        if (secid == 0) {
            JOptionPane.showMessageDialog(null, "Please specify the section!");
        } else {
            position_add.secid = secid;
            ShowFrmNew();
        }
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value
            String id = tbl.getValueAt(row, col).toString();
            position_edit.positionid = Integer.parseInt(id);
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Record Selected!");
            e.getMessage();
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value
            String id = tbl.getValueAt(row, col).toString();

            boolean ifexist = check_existance(Integer.parseInt(id));

            if (ifexist == true) {
                JOptionPane.showMessageDialog(null, "Record cannot be remove! There are record/s under on this position!");
            } else {

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {
                    position.setPositionId(Short.parseShort(String.valueOf(id)));

                    if (positionc.deletePosition(position) > 0) {
                        this.populateTBL();
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

    private void cmbdivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdivActionPerformed
        divid = 0;
        try {
            int id = divid = ((Division) cmbdiv.getSelectedItem()).getDivId();
            setSecComboModel(id);
            //setSecComboModel(0);
            //JOptionPane.showMessageDialog(null,  deptid);
            model2.setNumRows(0);
            secid = 0;
        } catch (Exception e) {
        }
        if (cmbdiv.getSelectedItem() == "--SELECT--") {
            setSecComboModel(0);
            secid = 0;
            populateTBL();
        }
    }//GEN-LAST:event_cmbdivActionPerformed

    private void cmbdivKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbdivKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbdivKeyPressed

    private void cmbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdeptActionPerformed
        deptid = 0;
        try {
            int id = deptid = ((Department) cmbdept.getSelectedItem()).getDeptId();
            setDivComboModel(id);
            setSecComboModel(0);
            model2.setNumRows(0);
            //JOptionPane.showMessageDialog(null,  deptid);
            divid = 0;
            secid = 0;
            populateTBL();
        } catch (Exception e) {
        }
        if (cmbdept.getSelectedItem() == "--SELECT--") {
            setDivComboModel(0);
            setSecComboModel(0);
            divid = 0;
            secid = 0;
            populateTBL();
        }
    }//GEN-LAST:event_cmbdeptActionPerformed

    private void cmbdeptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbdeptKeyPressed
   }//GEN-LAST:event_cmbdeptKeyPressed

    private void cmbsecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsecActionPerformed
        secid = 0;
        try {
            secid = ((Section) cmbsec.getSelectedItem()).getSecId();
            populateTBL();
        } catch (Exception e) {
        }
        if (cmbsec.getSelectedItem() == "--SELECT--") {
            secid = 0;
            populateTBL();
        }
    }//GEN-LAST:event_cmbsecActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JComboBox cmbdiv;
    private javax.swing.JComboBox cmbsec;
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdremove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
