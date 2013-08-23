package mod.gui.scale.bir;

import java.math.BigDecimal;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import mod.model.BIR;
import mod.model.controller.BIRC;
import mod.others.myFunction;

public class bir extends javax.swing.JInternalFrame {

    static Statement stmt;
    static DefaultTableCellRenderer AlignRight = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer AlignCenter = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer AlignLeft = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableModel moverowmodel;
    public static bir_add_range frmNew;
    public static bir_edit_range frmEdit;
    BIR bir = new BIR();
    BIRC birc = new BIRC();
    ArrayList<BIR> BIRTemp = new ArrayList<>();

    public bir() {
        initComponents();
        model = (DefaultTableModel) tbl.getModel();
        setTableModels();
        tbl.setCellSelectionEnabled(false);
        tbl.setRowSelectionAllowed(true);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    void setTableModels() {
        tbl.setModel(new BIRC().getBIRTableModel(tbl));
    }

    public void ShowFrmNew() {
        frmNew = new bir_add_range(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new bir_edit_range(this, true);
        frmEdit.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cmdremove2 = new javax.swing.JButton();
        cmdnew = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();
        cmdmoveup = new javax.swing.JButton();
        cmdedit = new javax.swing.JButton();
        cmdsave = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setForeground(java.awt.Color.black);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("BIR Contribultion Scale Table");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bir.png"))); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Range No.", "Range Start", "Range End", "Tax Minimum Amount", "Tax Additional %"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        cmdremove2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/movedown.png"))); // NOI18N
        cmdremove2.setMnemonic('R');
        cmdremove2.setText("Move Down");
        cmdremove2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdremove2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdremove2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremove2ActionPerformed(evt);
            }
        });

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('A');
        cmdnew.setText("Insert");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
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

        cmdmoveup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/moveup.png"))); // NOI18N
        cmdmoveup.setMnemonic('R');
        cmdmoveup.setText("Move Up");
        cmdmoveup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdmoveup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdmoveup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdmoveupActionPerformed(evt);
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

        cmdsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logs.png"))); // NOI18N
        cmdsave.setMnemonic('S');
        cmdsave.setText("Save");
        cmdsave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsaveActionPerformed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdnew, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cmdedit, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cmdremove, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cmdmoveup, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cmdremove2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cmdsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdremove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdmoveup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdremove2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdexit)
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/birb.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        ShowFrmNew();
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
    }//GEN-LAST:event_tblMouseClicked

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value
            String id = tbl.getValueAt(row, col).toString();
            bir_edit_range.birid = id;
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Range Selected!");
        }
    }//GEN-LAST:event_cmdeditActionPerformed

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

                    bir.setBirId(Short.parseShort(id));

                    if (birc.deleteBIR(bir) > 0) {
                        setTableModels();
                        //this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed!");
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
    private void tblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMousePressed

    private void cmdmoveupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdmoveupActionPerformed
        try {
            move_row(1); //MOVE SELECTED ROW UP
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdmoveupActionPerformed

    void move_row(int move) {//UP OR DOWN LOCATION
        int selrow = tbl.getSelectedRow();
        int moveto = 0;
        if (move == 1) {
            moveto = selrow - 1; //MOVE UP
        } else if (move == 2) {
            moveto = selrow + 1; //MOVE DOWN
        }

        String sid = tbl.getValueAt(selrow, 0).toString();
        String pid = tbl.getValueAt(moveto, 0).toString();


        //GET ALL CELL VALUES OF PREVIOUS ROW
        String[] prevcol = new String[6];
        int ip = 0;
        prevcol[ip++] = tbl.getValueAt(moveto, 0).toString();
        prevcol[ip++] = tbl.getValueAt(moveto, 1).toString();
        prevcol[ip++] = myFunction.amountFormat2(birc.GetRangeStart(Integer.parseInt(pid)));
        prevcol[ip++] = myFunction.amountFormat2(birc.GetRangeEnd(Integer.parseInt(pid)));
        prevcol[ip++] = myFunction.amountFormat2(birc.GetTaxMinAmt(Integer.parseInt(pid)));
        prevcol[ip++] = myFunction.amountFormat2(birc.GetTaxAddPercentage(Integer.parseInt(pid)));


        //GET CELL ALL VALUE OF SELECTED ROW
        String[] selcol = new String[6];
        int is = 0;
        selcol[is++] = tbl.getValueAt(selrow, 0).toString();
        selcol[is++] = tbl.getValueAt(selrow, 1).toString();
        selcol[is++] = myFunction.amountFormat2(birc.GetRangeStart(Integer.parseInt(sid)));
        selcol[is++] = myFunction.amountFormat2(birc.GetRangeEnd(Integer.parseInt(sid)));
        selcol[is++] = myFunction.amountFormat2(birc.GetTaxMinAmt(Integer.parseInt(pid)));
        selcol[is++] = myFunction.amountFormat2(birc.GetTaxAddPercentage(Integer.parseInt(pid)));

        //SET THE ALL CELL VALUES OF THE PREVIOUS ROW
        int iss = 0;
        while (iss != 6) {
            if (iss == 2) {
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + selcol[iss].toString() + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";
                tbl.setValueAt(start, moveto, iss);
            } else if (iss == 3) {
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + selcol[iss].toString() + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";
                tbl.setValueAt(start, moveto, iss);
            } else {
                tbl.setValueAt(selcol[iss].toString(), moveto, iss);
            }
            iss++;
        }

        //SET THE ALL CELL VALUES OF THE PREVIOUS ROW
        int ipp = 0;
        while (ipp != 6) {

            if (ipp == 2) {
                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + prevcol[ipp].toString() + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";
                tbl.setValueAt(end, selrow, ipp);
            } else if (ipp == 3) {
                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + prevcol[ipp].toString() + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";
                tbl.setValueAt(end, selrow, ipp);
            } else {
                tbl.setValueAt(prevcol[ipp].toString(), selrow, ipp);
            }

            ipp++;
        }

        //REFRESH FOCUS
        tbl.changeSelection(moveto, selrow - 1, false, false);


    }

    private void cmdremove2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremove2ActionPerformed
        try {
            move_row(2); //MOVE SELECTED ROW DOWN
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdremove2ActionPerformed

    public void setBIRTempDatas() {
        String start, end, sbase, ee;
        int rc = tbl.getRowCount();
        int cr = 0; //CURRENT ROW
        BIRTemp.clear();

        while (cr < rc + 1) {
            try {
                String sid = tbl.getValueAt(cr, 0).toString();
                //System.out.println(sid);

                //SET DATA OF ROW
                start = birc.GetRangeStart(Integer.parseInt(sid));
                end = birc.GetRangeEnd(Integer.parseInt(sid));
                sbase = birc.GetTaxMinAmt(Integer.parseInt(sid));
                ee = birc.GetTaxAddPercentage(Integer.parseInt(sid));


                //ADD TO TEMPORARY VECTOR
                BIR list = new BIR();

                list.setRangeStart(BigDecimal.valueOf(Double.parseDouble(start)));
                list.setRangeEnd(BigDecimal.valueOf(Double.parseDouble(end)));
                list.setTaxMinAmt(BigDecimal.valueOf(Double.parseDouble(sbase)));
                list.setTaxAddPercentage(BigDecimal.valueOf(Double.parseDouble((ee))));

                BIRTemp.add(list);
            } catch (Exception e) {
                e.getStackTrace();
            }
            cr++;
        }
    }

    void rePersistDatas() {
        for (BIR bir : BIRTemp) {
            bir.setRangeStart(BigDecimal.valueOf(Double.parseDouble(bir.getRangeStart().toString())));
            bir.setRangeEnd(BigDecimal.valueOf(Double.parseDouble(bir.getRangeEnd().toString())));
            bir.setTaxMinAmt(BigDecimal.valueOf(Double.parseDouble(bir.getTaxMinAmt().toString())));
            bir.setTaxAddPercentage(BigDecimal.valueOf(Double.parseDouble(bir.getTaxAddPercentage().toString())));

            if (birc.addBIR(bir) > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save");
            }
        }
    }

    void deleteAllBIRDatas() {
        if (birc.deleteAll(bir) > 0) {
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save");
        }
    }

    private void cmdsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsaveActionPerformed
        setBIRTempDatas();
        deleteAllBIRDatas();
        rePersistDatas();
        setTableModels();
    }//GEN-LAST:event_cmdsaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdedit;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdmoveup;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdremove;
    private javax.swing.JButton cmdremove2;
    private javax.swing.JButton cmdsave;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
