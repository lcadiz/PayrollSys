package mod.gui.time_logs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import mod.connection.myDBConn;

public class edit_tlogs extends javax.swing.JDialog {

    public static manage_logs frmParent;
    public static int logid;
    static Statement stmt;
    public static String in1, ddate;
    public static int empid, tlflg;

    public edit_tlogs(manage_logs parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdsave);
        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        Date d1;
        try {
            d1 = dateFormat.parse("24:00");
            SpinnerDateModel sm1 = new SpinnerDateModel(d1, null, null, Calendar.HOUR_OF_DAY);
            tl.setModel(sm1);
            JSpinner.DateEditor de1 = new JSpinner.DateEditor(tl, "kk:mm");
            tl.setEditor(de1);
        } catch (ParseException ex) {
            Logger.getLogger(edit_tlogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(this, logid);
        try {
            setData();
        } catch (Exception e) {
        }
    }

    void setData() {

        String t = getData();


        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        Date d1;
        try {
            if ("".equals(t)) {
                d1 = dateFormat.parse("24:00");
                SpinnerDateModel sm1 = new SpinnerDateModel(d1, null, null, Calendar.HOUR_OF_DAY);
                tl.setModel(sm1);
                JSpinner.DateEditor de1 = new JSpinner.DateEditor(tl, "kk:mm");
                tl.setEditor(de1);
            } else {
                d1 = dateFormat.parse(in1);
                SpinnerDateModel sm1 = new SpinnerDateModel(d1, null, null, Calendar.HOUR_OF_DAY);
                tl.setModel(sm1);
                JSpinner.DateEditor de1 = new JSpinner.DateEditor(tl, "kk:mm");
                tl.setEditor(de1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(edit_tlogs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String getData() {
        String l = null;
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT  time(log_date_time),  date(log_date_time) FROM time_log where time_log_id=" + logid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);
            SimpleDateFormat df = new SimpleDateFormat("kk:mm");
            while (rs.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

                in1 = dateFormat.format(rs.getTime(1));
                l = in1;
                ddate = rs.getString(2);
            }
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return l;
    }

    public static void edit_tl(String tl) {
        Connection conn = myDBConn.getConnection();
        String createString = "update time_log set log_date_time='" + tl + "' where time_log_id=" + logid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void insert_tl(String tl) {
        Connection conn = myDBConn.getConnection();
        String createString = "insert into time_log (emp_id, log_date_time) VALUES("+empid+",'" + tl + "') ";

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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdsave = new javax.swing.JButton();
        tl = new javax.swing.JSpinner();
        cmdcancel = new javax.swing.JButton();
        cmdremove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Time Log");
        setResizable(false);

        jLabel1.setText("Time Log:");

        cmdsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdsave.setMnemonic('A');
        cmdsave.setText("Save");
        cmdsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsaveActionPerformed(evt);
            }
        });

        tl.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1365577200000L), null, null, java.util.Calendar.MINUTE));
        tl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmdremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdremove.setMnemonic('R');
        cmdremove.setText("Remove Logs");
        cmdremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdsave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdremove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel))
                    .addComponent(tl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsave)
                    .addComponent(cmdremove)
                    .addComponent(cmdcancel))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsaveActionPerformed

        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        String ctl = dateFormat.format(tl.getValue());

        if (logid == 0) {
            System.out.println(ddate);
            insert_tl(ddate + " " + ctl + ":00");
            JOptionPane.showMessageDialog(null, "New Time Log inserted!");
        } else {
            edit_tl(ddate + " " + ctl + ":00");
            JOptionPane.showMessageDialog(null, "Changes has been successfully Save");
        }
        frmParent.go();
        this.dispose();
    }//GEN-LAST:event_cmdsaveActionPerformed

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdremoveActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                edit_tlogs dialog = new edit_tlogs(frmParent, true);
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
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdremove;
    private javax.swing.JButton cmdsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner tl;
    // End of variables declaration//GEN-END:variables
}
