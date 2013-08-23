package mod.gui.employee.loans;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import mod.model.EmployeeLoan;
import mod.model.LoanLedger;
import mod.model.controller.EmployeeLoanC;
import mod.model.controller.LoanLedgerC;
import mod.others.myFunction;

public class emp_loans_add extends javax.swing.JDialog {

    public static emp_loans_manage frmParent;
    static String nowDate = myFunction.getDatewTym();
    EmployeeLoan el = new EmployeeLoan();
    EmployeeLoanC elc = new EmployeeLoanC();
    LoanLedger ll = new LoanLedger();
    LoanLedgerC llc = new LoanLedgerC();
    public static int empid, ltid;

    public emp_loans_add(emp_loans_manage parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdadd);
        setdates();
        //   txtend.setVisible(false);

    }

    void setdates() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date theDate = null;
        try {
            theDate = sdf.parse(nowDate);
        } catch (ParseException e) {
        }
        //txtdate.setDateFormatString(nowDate);
        txtstart.setDate(theDate);
        txtend.setDate(theDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        cmdcancel = new javax.swing.JButton();
        cmdadd = new javax.swing.JButton();
        txtparticular = new javax.swing.JTextField();
        txtstart = new com.toedter.calendar.JDateChooser();
        txtend = new com.toedter.calendar.JDateChooser();
        txtamount = new javax.swing.JFormattedTextField();
        txtmonthly = new javax.swing.JFormattedTextField();
        txtterm = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Loan");
        setResizable(false);

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmdadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdadd.setMnemonic('A');
        cmdadd.setText("Add");
        cmdadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddActionPerformed(evt);
            }
        });

        txtparticular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtstart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtstart.setDateFormatString("MM/dd/yyyy");
        txtstart.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txtstartHierarchyChanged(evt);
            }
        });
        txtstart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtstartPropertyChange(evt);
            }
        });
        txtstart.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtstartVetoableChange(evt);
            }
        });

        txtend.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtend.setDateFormatString("MM/dd/yyyy");
        txtend.setEnabled(false);

        txtamount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtamount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtamount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtamountKeyReleased(evt);
            }
        });

        txtmonthly.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtmonthly.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtmonthly.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonthly.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtmonthly.setEnabled(false);

        txtterm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtterm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtterm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtterm.setText("0");
        txtterm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttermActionPerformed(evt);
            }
        });
        txtterm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txttermFocusGained(evt);
            }
        });
        txtterm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttermKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttermKeyReleased(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("Month/s");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdadd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel)
                .addGap(0, 170, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtparticular)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtamount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtstart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addComponent(txtmonthly, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtterm, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7)))
                            .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtparticular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtterm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(txtstart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdcancel)
                    .addComponent(cmdadd)))
        );

        jLabel2.setText("Term:");

        jLabel1.setText("Particular:");

        jLabel3.setText("Date of Loan:");

        jLabel4.setText("Due Date:");

        jLabel5.setText("Amount:");

        jLabel6.setText("Monthly:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddActionPerformed
        String particular = txtparticular.getText().replace("'", " ").replace(",", "");
        String term = txtterm.getText().replace("'", " ").replace(",", "");
        String amount = txtamount.getText().replace("'", " ").replace(",", "");
        String monthly = txtmonthly.getText().replace("'", " ").replace(",", "");

        if (particular.isEmpty() == true || term.isEmpty() == true || amount.isEmpty() == true || monthly.isEmpty() == true || txtamount.getText().isEmpty() == true || txtmonthly.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please fill-up all the required fields!");
        } else {
            if (Double.parseDouble(monthly) > Double.parseDouble(amount)) {
                JOptionPane.showMessageDialog(null, "Monthly Amortization is greater that the Loan Amount!");
            } else {
//                Double cMonthly = Double.parseDouble(amount) / Double.parseDouble(term);
//
//                if (cMonthly != Double.parseDouble(monthly)) {
//                    JOptionPane.showMessageDialog(null, "Monthly Amortization is Overlapping!");
//                } else {

                Date date1 = myFunction.GetSystemNowDate();
                Date date2 = txtstart.getDate();
                int res = date1.compareTo(date2);
                
                if (res>0){
                    JOptionPane.showMessageDialog(null, "Date is ealier than the current date!");
                }else{
                BigDecimal ba = BigDecimal.valueOf(Double.parseDouble(txtamount.getText().replace(",", "")));
                BigDecimal bm = BigDecimal.valueOf(Double.parseDouble(txtmonthly.getText().replace(",", "")));
                el.setParticular(particular);
                el.setTerm(Short.valueOf(term));
                el.setLoanDate(txtstart.getDate());
                el.setDueDate(txtend.getDate());
                el.setAmount(ba);
                el.setMonthly(bm);
                el.setLoanId(ltid);
                el.setEmpId(empid);

                int lastid = elc.addEmployeeLoan(el);
                System.out.println(lastid);
                if (lastid > 0) {
                    ll.setEmpLoanId(lastid);
                    ll.setParticular(particular);
                    ll.setTransDate(txtstart.getDate());
                    ll.setDebit(BigDecimal.valueOf(0.00));
                    ll.setCredit(ba);
                    ll.setPayrollPeriodId(0);

                    if (llc.addLoanLedger(ll) > 0) {
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to save to loan ledger");
                    }

                    frmParent.populateTBL1();
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "New Loan Record Successfully Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save");
                }
                //}
                }
            }
        }
    }//GEN-LAST:event_cmdaddActionPerformed

    private void txtstartHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txtstartHierarchyChanged
    }//GEN-LAST:event_txtstartHierarchyChanged

    private void txttermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttermActionPerformed
    }//GEN-LAST:event_txttermActionPerformed

    private void txttermKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttermKeyPressed
    }//GEN-LAST:event_txttermKeyPressed

    private void txtstartVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtstartVetoableChange
    }//GEN-LAST:event_txtstartVetoableChange

    private void txtstartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtstartPropertyChange
        setDueDate();
    }//GEN-LAST:event_txtstartPropertyChange

    private void txttermKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttermKeyReleased
        if (txtterm.getText().isEmpty() == true) {
            txtterm.setText("0");
        }
        setDueDate();
    }//GEN-LAST:event_txttermKeyReleased

    private void txttermFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttermFocusGained
        txtterm.selectAll();
    }//GEN-LAST:event_txttermFocusGained

    private void txtamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtamountKeyReleased
        double term = Double.parseDouble(txtterm.getText().replace(",", ""));
        double amount = Double.parseDouble(txtamount.getText().replace(",", ""));
        double monthly;
        
        monthly = amount/term;
        txtmonthly.setText(String.valueOf(monthly));
    }//GEN-LAST:event_txtamountKeyReleased

    void setDueDate() {
        try {
            txtend.setDate(plusDate(txtstart.getDate(), Integer.parseInt(txtterm.getText())));
            //lblduedate.setText(plusDateStr(txtstart.getDate(), Integer.parseInt(txtterm.getText())));
        } catch (Exception e) {
        }
    }

    static Date plusDate(Date current, int term) {
        //System.out.println(current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + term));
        current = cal.getTime();
        //System.out.println(current);
        return current;
    }

    static String plusDateStr(Date current, int term) {
        //System.out.println(current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + term));
        current = cal.getTime();
        //System.out.println(current);
        return current.toString();
    }

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
            java.util.logging.Logger.getLogger(emp_loans_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(emp_loans_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(emp_loans_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(emp_loans_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                emp_loans_add dialog = new emp_loans_add(frmParent, true);
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
    private javax.swing.JButton cmdadd;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txtamount;
    private com.toedter.calendar.JDateChooser txtend;
    private javax.swing.JFormattedTextField txtmonthly;
    private javax.swing.JTextField txtparticular;
    private com.toedter.calendar.JDateChooser txtstart;
    private javax.swing.JFormattedTextField txtterm;
    // End of variables declaration//GEN-END:variables
}
