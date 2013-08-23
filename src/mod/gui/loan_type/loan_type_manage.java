/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.gui.loan_type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import mod.model.LoanAgency;
import mod.model.LoanType;
import mod.model.controller.LoanAgencyC;
import mod.model.controller.LoanTypeC;
import mod.others.myFunction;

public class loan_type_manage extends javax.swing.JInternalFrame {

    static int laid, ltid;
    public static loan_type_add frmNew;
    public static loan_type_edit frmEdit;
    static Statement stmt;
    LoanType lt = new LoanType();
    LoanTypeC ltc = new LoanTypeC();

    public loan_type_manage() {
        initComponents();
        setComboModels();
    }

    public void ShowFrmNew() {
        frmNew = new loan_type_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new loan_type_edit(this, true);
        frmEdit.setVisible(true);
    }

    void setComboModels() {
        cmbLA.setModel(new LoanAgencyC().getLoanAgencyComboModel());
        setListModel();
    }

    public void setListModel() {
        lst.setModel(new LoanTypeC().getLoanTypeListModel(laid));
    }

    boolean check_existance(int lid) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT loan_id FROM employee_loan WHERE loan_id=" + lid;

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cmbLA = new javax.swing.JComboBox();
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
        setTitle("Loan Type Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loantype.png"))); // NOI18N

        cmbLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLAActionPerformed(evt);
            }
        });
        cmbLA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLAKeyPressed(evt);
            }
        });

        jLabel2.setDisplayedMnemonic('y');
        jLabel2.setText("Select Loan Agency");

        lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst);

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setText("List of Loan/s Offered by this Loan Agency");

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
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(278, 278, 278))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(cmbLA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbLA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 145, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLAActionPerformed
        DefaultListModel model = (DefaultListModel) lst.getModel();;
        model.removeAllElements();

        laid = 0;

        try {
            laid = ((LoanAgency) cmbLA.getSelectedItem()).getLoanAgencyId();
            // System.out.println(laid);
            setListModel();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbLAActionPerformed

    private void cmbLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLAKeyPressed
    }//GEN-LAST:event_cmbLAKeyPressed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        try {
            int id = ((LoanType) lst.getSelectedValue()).getLoanId();

            boolean ifexist = check_existance(id);

            if (ifexist == true) {
                JOptionPane.showMessageDialog(null, "Record cannot be remove! There  are employee loan record/s under on this loan type!");
            } else {

                int i = myFunction.msgboxYesNo("Are you sure you want to delete this record?");
                if (i == 0) {
                    lt.setLoanId(id);

                    if (ltc.deleteLoanType(lt) > 0) {
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

    private void cmdeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditActionPerformed
        try {
            int id = ((LoanType) lst.getSelectedValue()).getLoanId();
            loan_type_edit.ltid = id;
            ShowFrmEdit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Item Selected!");
            e.getMessage();
        }
    }//GEN-LAST:event_cmdeditActionPerformed

    private void cmdrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefreshActionPerformed
        DefaultListModel model = (DefaultListModel) lst.getModel();;
        model.removeAllElements();

        laid = 0;        
        setComboModels();
    }//GEN-LAST:event_cmdrefreshActionPerformed

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        if (cmbLA.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an Agency!");
        } else {
            loan_type_add.laid = laid;
            ShowFrmNew();
        }
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbLA;
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
