package mod.gui.employee_shift;

import java.sql.Connection;
import java.sql.ResultSet;
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
import mod.others.myFunction;

public class assign_edit extends javax.swing.JDialog {

    public static assign frmParent;
    static String nowDate = myFunction.getDatewTym();
    static Statement stmt;
    public static int esid;
    static int pflg;
    public static int shiftid, empid;

    public assign_edit(assign parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdunassign);
        //setdates();
        populatefields();
    }

    String populatefields() {
        String div = "";
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT start_date, end_date, p_flag FROM employee_shift WHERE emp_shift_id=" + esid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                int flg = rs.getInt("p_flag");
                pflg = flg;
                if (flg == 0) {
                    setdates();
                    chck.setSelected(true);
                } else {
                    txtstart.setDate(rs.getDate("start_date"));
                    txtend.setDate(rs.getDate("end_date"));
                    chck.setSelected(false);
                }
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return div;
    }

    public static void edit_Shift(java.sql.Date start, java.sql.Date end, int userid, java.sql.Date tdate, short pflag) {
        Connection conn = myDBConn.getConnection();
        String createString = "UPDATE employee_shift SET "
                + "start_date='" + start + "', "
                + "end_date='" + end + "', "
                + "user_id=" + userid + ", "
                + "trans_date='" + tdate + "', "
                + "p_flag=" + pflag + " "
                + "WHERE emp_shift_id=" + esid;


        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void edit_Shift2(int userid, java.sql.Date tdate, short pflag) {
        Connection conn = myDBConn.getConnection();
        String createString = "UPDATE employee_shift SET "
                + "user_id=" + userid + ", "
                + "trans_date='" + tdate + "', "
                + "p_flag=" + pflag + " "
                + "WHERE emp_shift_id=" + esid;


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

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdcancel = new javax.swing.JButton();
        cmdunassign = new javax.swing.JButton();
        txtstart = new com.toedter.calendar.JDateChooser();
        txtend = new com.toedter.calendar.JDateChooser();
        chck = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Shift Range");

        jLabel2.setText("End Date:");

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

        cmdunassign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdunassign.setMnemonic('A');
        cmdunassign.setText("Save");
        cmdunassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdunassignActionPerformed(evt);
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
                        .addComponent(cmdunassign, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cmdunassign)))
        );

        jLabel1.setText("Start Date:");

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
                .addContainerGap(43, Short.MAX_VALUE))
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
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdunassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdunassignActionPerformed
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
            //
            Date td = null;
            td = dateFormat.parse(nowDate);

            if (chck.isSelected() == true) {
                edit_Shift2(mdi.getUserID(), new java.sql.Date(td.getTime()), Short.valueOf("0"));
            } else {
                edit_Shift(new java.sql.Date(txtstart.getDate().getTime()), new java.sql.Date(txtend.getDate().getTime()), mdi.getUserID(), new java.sql.Date(td.getTime()), Short.valueOf("1"));
            }

            frmParent.populateTBLShift();
            frmParent.populateTBLAssign();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Shift successfully assigned");
        } catch (ParseException ex) {
            Logger.getLogger(assign_edit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdunassignActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                assign_edit dialog = new assign_edit(frmParent, true);
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
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdunassign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser txtend;
    private com.toedter.calendar.JDateChooser txtstart;
    // End of variables declaration//GEN-END:variables
}
