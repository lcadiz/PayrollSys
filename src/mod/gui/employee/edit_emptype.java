package mod.gui.employee;

import mod.model.*;
import mod.model.controller.*;

public class edit_emptype extends javax.swing.JDialog {

    public static employee_edit frmParent;
    static int deptid, divid, secid, etype, eto, rv, qf, posid;

    public edit_emptype(employee_edit parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdadd);
        //setdates();
        setComboEmpTypeModel();
        setComboEmployerTypeModel();
        cmbpos.addItem("--SELECT--");
        setDeptComboModel();
        setDivComboModel(0);
        setSecComboModel(0);
    }

    void setComboEmpTypeModel() {
        cmbemptype.setModel(new EmpTypeC().getEmpTypeComboModel());
    }

    void setComboEmployerTypeModel() {
        cmbemployer.setModel(new EmployerC().getEmployerComboModel());
    }

    void setComboPositionModel() {
        cmbpos.setModel(new PositionC().getPositionComboModel(secid));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbemptype = new javax.swing.JComboBox();
        cmbemployer = new javax.swing.JComboBox();
        cmbpos = new javax.swing.JComboBox();
        cmbsec = new javax.swing.JComboBox();
        cmbdiv = new javax.swing.JComboBox();
        cmbdept = new javax.swing.JComboBox();
        cmdadd = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Employment Status");
        setResizable(false);

        cmbemptype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbemptype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbemptypeActionPerformed(evt);
            }
        });

        cmbemployer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbemployer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbemployerActionPerformed(evt);
            }
        });

        cmbpos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbpos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbposActionPerformed(evt);
            }
        });

        cmbsec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbsec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsecActionPerformed(evt);
            }
        });

        cmbdiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbdiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdivActionPerformed(evt);
            }
        });

        cmbdept.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdeptActionPerformed(evt);
            }
        });

        cmdadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdadd.setMnemonic('A');
        cmdadd.setText("Apply Changes");
        cmdadd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdadd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddActionPerformed(evt);
            }
        });

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdcancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbemptype, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmbemployer, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(cmbsec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbdept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbdiv, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(cmbpos, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cmdadd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cmbemptype, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbemployer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdiv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbsec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbpos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdadd)
                    .addComponent(cmdcancel))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel27.setText("Department:");

        jLabel28.setText("Division:");

        jLabel25.setDisplayedMnemonic('l');
        jLabel25.setText("Employed to:");

        jLabel26.setDisplayedMnemonic('s');
        jLabel26.setText("Plantilla Position:");

        jLabel24.setDisplayedMnemonic('o');
        jLabel24.setText("Employment Type:");

        jLabel30.setText("Section:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbemptypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbemptypeActionPerformed
        try {
            etype = ((EmploymentType) cmbemptype.getSelectedItem()).getEmpTypeId();
            // JOptionPane.showMessageDialog(null,  etype);
            if (etype > 1) {
                cmbpos.removeAllItems();
//            String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
//                        + "<tr><td><img src=" + getClass().getResource("/images/na.png") + ">&nbsp</td><td>NOT APPLICABLE<font></td></th>";
                cmbpos.addItem("NOT APPLICABLE");
                cmbpos.setEnabled(false);
                //    txtrank.setEnabled(false);
//                txtstep.setEnabled(false);
//                // l1.setEnabled(false);
//                l2.setEnabled(false);
//                chckqualified.setEnabled(false);
//                cmdaddd.setEnabled(false);
//                model.setNumRows(0);
            } else {
                cmbpos.setEnabled(true);
                setComboPositionModel();
//                chckqualified.setEnabled(true);
//                //  txtrank.setEnabled(true);
//                txtstep.setEnabled(true);
//                //  l1.setEnabled(true);
//                l2.setEnabled(true);
//                cmdaddd.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbemptypeActionPerformed

    private void cmbemployerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbemployerActionPerformed
        try {
            eto = ((Employer) cmbemployer.getSelectedItem()).getEmployerId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbemployerActionPerformed

    private void cmbposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbposActionPerformed
        try {

            posid = ((Position) cmbpos.getSelectedItem()).getPositionId();
            //txtrank.setText(String.valueOf(rv));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbposActionPerformed

    private void cmbsecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsecActionPerformed
        secid = 0;
        try {
            secid = ((Section) cmbsec.getSelectedItem()).getSecId();
            setComboPositionModel();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbsecActionPerformed

    private void cmbdivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdivActionPerformed
        divid = 0;
        try {
            int id = divid = ((Division) cmbdiv.getSelectedItem()).getDivId();
            setSecComboModel(id);
            //JOptionPane.showMessageDialog(null,  deptid);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbdivActionPerformed

    private void cmbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdeptActionPerformed
        deptid = 0;
        try {
            int id = deptid = ((Department) cmbdept.getSelectedItem()).getDeptId();
            setDivComboModel(id);
            //JOptionPane.showMessageDialog(null,  deptid);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbdeptActionPerformed

    private void cmdaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddActionPerformed
        try {
            frmParent.setLblValues(cmbemptype.getSelectedItem().toString(),
                    cmbemployer.getSelectedItem().toString(),
                    cmbpos.getSelectedItem().toString(),
                    cmbdept.getSelectedItem().toString(),
                    cmbdiv.getSelectedItem().toString(),
                    cmbsec.getSelectedItem().toString());
            
            frmParent.setNewValues(etype, eto, posid, secid);
            this.dispose();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdaddActionPerformed

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                edit_emptype dialog = new edit_emptype(frmParent, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JComboBox cmbdiv;
    private javax.swing.JComboBox cmbemployer;
    private javax.swing.JComboBox cmbemptype;
    private javax.swing.JComboBox cmbpos;
    private javax.swing.JComboBox cmbsec;
    private javax.swing.JButton cmdadd;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
