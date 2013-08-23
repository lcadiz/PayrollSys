package mod.gui.employee_shift;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import mod.main.mdi;
import mod.model.EmployeeShift;
import mod.model.controller.EmployeeShiftC;
import mod.others.myFunction;

public class assign_shift extends javax.swing.JDialog {

    public static assign frmParent;
    static String nowDate = myFunction.getDatewTym();
    public static int shiftid, empid;
    EmployeeShift eshift = new EmployeeShift();
    EmployeeShiftC eshiftc = new EmployeeShiftC();
    //private Object dateFormat;
    static Statement stmt;

    public assign_shift(assign parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdassign);
        //setDateFields();
        setdates();
    }

    public static void assign_Shift(int empid, int shift_id, java.sql.Date start, java.sql.Date end, int userid, java.sql.Date tdate, short pflag) {
        Connection conn = myDBConn.getConnection();
        String createString = "INSERT INTO employee_shift("
                + "emp_id, "
                + "shift_id ,"
                + "start_date, "
                + "end_date, "
                + "user_id, "
                + "trans_date, "
                + "p_flag "
                + ") "
                + "VALUES ("
                + empid + ","
                + shift_id + ","
                + "'" + start + "',"
                + "'" + end + "',"
                + userid + ","
                + "'" + tdate + "',"
                + pflag + " "
                + ")";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void assign_Shift2(int empid, int shift_id, int userid, java.sql.Date tdate, short pflag) {
        Connection conn = myDBConn.getConnection();
        String createString = "INSERT INTO employee_shift("
                + "emp_id, "
                + "shift_id ,"
                + "user_id, "
                + "trans_date, "
                + "p_flag "
                + ") "
                + "VALUES ("
                + empid + ","
                + shift_id + ","
                + userid + ","
                + "'" + tdate + "',"
                + pflag + " "
                + ")";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdcancel = new javax.swing.JButton();
        cmdassign = new javax.swing.JButton();
        txtstart = new com.toedter.calendar.JDateChooser();
        txtend = new com.toedter.calendar.JDateChooser();
        chck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Assign Shift");

        jLabel1.setText("Start Date:");

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

        cmdassign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdassign.setMnemonic('A');
        cmdassign.setText("Assign");
        cmdassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdassignActionPerformed(evt);
            }
        });

        txtstart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtstart.setDateFormatString("MM/dd/yyyy");

        txtend.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtend.setDateFormatString("MM/dd/yyyy");

        chck.setSelected(true);
        chck.setText("Permanent");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdassign, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtstart, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chck))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtstart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(chck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdcancel)
                    .addComponent(cmdassign)))
        );

        jLabel2.setText("End Date:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdassignActionPerformed
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
            //
            Date td = null;
            td = dateFormat.parse(nowDate);
            
            if (chck.isSelected()==true){
                assign_Shift2(empid, shiftid,  mdi.getUserID(), new java.sql.Date(td.getTime()), Short.valueOf("0"));
            }else{
                assign_Shift(empid, shiftid, new java.sql.Date(txtstart.getDate().getTime()), new java.sql.Date(txtend.getDate().getTime()), mdi.getUserID(), new java.sql.Date(td.getTime()), Short.valueOf("1"));
            }
            
            frmParent.populateTBLShift();
            frmParent.populateTBLAssign();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Shift successfully assigned");
        } catch (ParseException ex) {
            Logger.getLogger(assign_shift.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cmdassignActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                assign_shift dialog = new assign_shift(frmParent, true);
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
    private javax.swing.JCheckBox chck;
    private javax.swing.JButton cmdassign;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser txtend;
    private com.toedter.calendar.JDateChooser txtstart;
    // End of variables declaration//GEN-END:variables
}
