package mod.gui.holiday;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import mod.main.mdi;
import mod.model.YearHoliday;
import mod.model.controller.YearHolidayC;
import mod.others.myFunction;

public final class year_holiday_edit extends javax.swing.JDialog {

    public static year_holiday_setup frmParent;
    public static String dateval;
    public static int yhid, yrsetup;
    static Statement stmt;
    YearHoliday yholiday = new YearHoliday();
    YearHolidayC yholidayc = new YearHolidayC();

    public year_holiday_edit(year_holiday_setup parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdsave);
        setdates();
    }

    void setdates() {
        java.util.Date theDate = null;
        theDate = yholidayc.GetDateValue(yhid);
        txtdatevalue.setDate(theDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtdatevalue = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        cmdsave = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Holiday Date");
        setResizable(false);

        txtdatevalue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdatevalue.setDateFormatString("MM/dd/yyyy");

        jLabel1.setText("Date Value:");

        cmdsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdsave.setMnemonic('A');
        cmdsave.setText("Save");
        cmdsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsaveActionPerformed(evt);
            }
        });

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel))
                    .addComponent(txtdatevalue, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(txtdatevalue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdcancel)
                    .addComponent(cmdsave))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsaveActionPerformed

        SimpleDateFormat sdfyr = new SimpleDateFormat("yyyy");

        String cyr = sdfyr.format(txtdatevalue.getDate());

        //System.out.println(dateDv);

        if (yrsetup != Integer.parseInt(cyr)) {
            JOptionPane.showMessageDialog(null, "Date value is not within the year setup!");
        } else {
            yholiday.setHolidayDate(txtdatevalue.getDate());
            yholiday.setYearHolidayId(yhid);
            yholiday.setTransDate(myFunction.getDatewTymDateFormat());
            yholiday.setUserId(mdi.getUserID());

            if (yholidayc.edityHoliday(yholiday) > 0) {
                frmParent.populateTBL2();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save");
            }
        }

    }//GEN-LAST:event_cmdsaveActionPerformed

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                year_holiday_edit dialog = new year_holiday_edit(frmParent, true);
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
    private javax.swing.JButton cmdsave;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JDateChooser txtdatevalue;
    // End of variables declaration//GEN-END:variables
}
