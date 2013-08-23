package mod.gui.holiday;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mod.main.mdi;
import mod.model.Holiday;
import mod.model.controller.HolidayC;
import mod.others.myFunction;

public class holiday_edit extends javax.swing.JDialog {

    public static holiday_manage frmParent;
    static int month;
    public static int hid;
    Holiday holiday = new Holiday();
    HolidayC holidayc = new HolidayC();

    public holiday_edit(holiday_manage parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        //  getRootPane().setDefaultButton(cmdaddnew);

        initComponents();
        setLocationRelativeTo(this);
        populateCMB();
        populateFields();

    }

    public void populateFields() {
        txtdesc.setText(holidayc.GetHoildayName(hid));
        int dm = holidayc.getDefaultMonth(hid);
        int dd = holidayc.getDefaultDay(hid);
        int pci = holidayc.getPayCompId(hid);
        int cf = holidayc.getCFlag(hid);

        //set value
        cmbmonth.setSelectedIndex(dm);
        cmbday.setSelectedIndex(dd - 1);

        if (pci == 8) {
            optregular.setSelected(true);
        } else if (pci == 7) {
            optspecial.setSelected(true);
        }

        if (cf == 1) {
            optconstant.setSelected(true);
        } else if (cf == 2) {
            optchangeable.setSelected(true);
        }

    }

    public void populateCMB() {
        //Populate Combo Area
        cmbmonth.addItem("--SELECT--");
        cmbmonth.addItem(new Item(1, "January".toUpperCase()));
        cmbmonth.addItem(new Item(2, "February".toUpperCase()));
        cmbmonth.addItem(new Item(3, "March".toUpperCase()));
        cmbmonth.addItem(new Item(4, "April".toUpperCase()));
        cmbmonth.addItem(new Item(5, "May".toUpperCase()));
        cmbmonth.addItem(new Item(6, "June".toUpperCase()));
        cmbmonth.addItem(new Item(7, "July".toUpperCase()));
        cmbmonth.addItem(new Item(8, "August".toUpperCase()));
        cmbmonth.addItem(new Item(9, "September".toUpperCase()));
        cmbmonth.addItem(new Item(10, "October".toUpperCase()));
        cmbmonth.addItem(new Item(11, "November".toUpperCase()));
        cmbmonth.addItem(new Item(12, "December".toUpperCase()));
    }

    public void populateCMBDay() {
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) cmbday.getModel();
        theModel.removeAllElements();
        int lstday = myFunction.getMaxDayofTheMonth(month);

        int d = 1;

        while (d < lstday + 1) {
            cmbday.addItem(d);
            d++;
        }
    }

    class Item {

        private int id;
        private String description;

        public Item(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmdcancel = new javax.swing.JButton();
        cmdaddnew = new javax.swing.JButton();
        txtdesc = new javax.swing.JTextField();
        cmbmonth = new javax.swing.JComboBox();
        cmbday = new javax.swing.JComboBox();
        optconstant = new javax.swing.JRadioButton();
        optchangeable = new javax.swing.JRadioButton();
        optregular = new javax.swing.JRadioButton();
        optspecial = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Current Holiday");
        setResizable(false);

        jLabel1.setText("Holiday Description:");

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmdaddnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdaddnew.setMnemonic('S');
        cmdaddnew.setText("Save");
        cmdaddnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddnewActionPerformed(evt);
            }
        });

        txtdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        cmbmonth.setMaximumRowCount(20);
        cmbmonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        cmbmonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmonthActionPerformed(evt);
            }
        });

        cmbday.setMaximumRowCount(20);
        cmbday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        cmbday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdayActionPerformed(evt);
            }
        });

        buttonGroup2.add(optconstant);
        optconstant.setMnemonic('t');
        optconstant.setSelected(true);
        optconstant.setText("Constant");

        buttonGroup2.add(optchangeable);
        optchangeable.setMnemonic('h');
        optchangeable.setText("Changeable");

        buttonGroup1.add(optregular);
        optregular.setMnemonic('R');
        optregular.setSelected(true);
        optregular.setText("Regular");

        buttonGroup1.add(optspecial);
        optspecial.setMnemonic('S');
        optspecial.setText("Special");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdaddnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel)
                .addGap(0, 166, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdesc)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbday, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(optconstant)
                                .addGap(18, 18, 18)
                                .addComponent(optchangeable))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(optregular)
                                .addGap(18, 18, 18)
                                .addComponent(optspecial)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbday, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optregular)
                    .addComponent(optspecial))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optconstant)
                    .addComponent(optchangeable))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdcancel)
                    .addComponent(cmdaddnew))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Default Month:");

        jLabel5.setText("Date Value:");

        jLabel3.setText("Default Day:");

        jLabel4.setText("Type of holiday:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdaddnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddnewActionPerformed
        String desc = txtdesc.getText().replace("'", " ");

        if (desc.isEmpty() == true || "--SELECT--".equals(cmbmonth.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Please fill-up all the required fields!");
        } else {
            holiday.setHolidayName(desc);
            holiday.setDefaultMonth(Short.parseShort(String.valueOf(month)));
            holiday.setDefaultDay(Short.parseShort(cmbday.getSelectedItem().toString()));

            int pci = 0;

            if (optregular.isSelected() == true) {
                pci = 8;
            }
            if (optspecial.isSelected() == true) {
                pci = 7;
            }

            holiday.setTransDate(myFunction.getDatewTymDateFormat());

            holiday.setPayCompId(pci);
            holiday.setUserId(mdi.getUserID());

            int cf = 0;

            if (optconstant.isSelected() == true) {
                cf = 1;
            }
            if (optchangeable.isSelected() == true) {
                cf = 2;
            }

            holiday.setConstantFlag(Short.valueOf(String.valueOf(cf)));
            holiday.setHolidayId(hid);

            if (holidayc.editHoliday(holiday) > 0) {
                frmParent.setListModel();
                this.dispose();
                JOptionPane.showMessageDialog(null, "New Holiday Record Successfully Added!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save");
            }
        }
    }//GEN-LAST:event_cmdaddnewActionPerformed

    private void cmbmonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmonthActionPerformed
        try {
            Item item = (Item) cmbmonth.getSelectedItem();
            month = item.getId();
            populateCMBDay();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbmonthActionPerformed

    private void cmbdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbdayActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                holiday_edit dialog = new holiday_edit(frmParent, true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbday;
    private javax.swing.JComboBox cmbmonth;
    private javax.swing.JButton cmdaddnew;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton optchangeable;
    private javax.swing.JRadioButton optconstant;
    private javax.swing.JRadioButton optregular;
    private javax.swing.JRadioButton optspecial;
    private javax.swing.JTextField txtdesc;
    // End of variables declaration//GEN-END:variables
}
