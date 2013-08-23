package mod.gui.shift;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import mod.model.Shift;
import mod.model.controller.ShiftC;
import mod.others.myFunction;

public class shift extends javax.swing.JInternalFrame {

    public static shift_add frmNew;
    public static shift_edit frmEdit;
    Shift shift = new Shift();
    ShiftC shiftc = new ShiftC();

    public shift() {
        initComponents();
        setTableModels();

        tbl.setCellSelectionEnabled(false);
        tbl.setRowSelectionAllowed(true);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setTableModels() {
        tbl.setModel(new ShiftC().getShiftTableModel(tbl));
    }

    public void ShowFrmNew() {
        frmNew = new shift_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new shift_edit(this, true);
        frmEdit.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.mm.exp.clock.TimePicker();
        timePicker2 = new com.mm.exp.clock.TimePicker();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdnew = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Shift Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shift.png"))); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Description", "Time 1", "Time 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(0).setResizable(false);
        tbl.getColumnModel().getColumn(3).setResizable(false);

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('N');
        cmdnew.setText("New");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
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

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(cmdnew, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdedit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdremove, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdnew)
                    .addComponent(cmdedit)
                    .addComponent(cmdexit)
                    .addComponent(cmdremove))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        ShowFrmNew();
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value
            String id = tbl.getValueAt(row, col).toString();
            shift_edit.shiftid = Integer.parseInt(id);
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Range Selected!");
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value

            //trap user incase if no row selected
            if (tbl.isRowSelected(row) != true) {
                JOptionPane.showMessageDialog(this, "Select a Range!");
            } else {
                String id = tbl.getValueAt(row, col).toString();

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {

                    shift.setShiftId(Integer.parseInt(id));


                    if (shiftc.deleteShift(shift) > 0) {
                        setTableModels();
                        //this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Record Successfully Removed!");
                    }
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdremove;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTable tbl;
    private com.mm.exp.clock.TimePicker timePicker1;
    private com.mm.exp.clock.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables
}
