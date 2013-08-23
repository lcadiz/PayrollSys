package mod.gui.position;

import java.sql.Statement;
import javax.swing.JOptionPane;
import mod.model.Position;
import mod.model.PositionLevel;
import mod.model.Salary;
import mod.model.controller.PositionC;
import mod.model.controller.PositionLevelC;
import mod.model.controller.SalaryC;

public final class position_add extends javax.swing.JDialog {

    public static position frmParent;
    static Statement stmt;
    Position position = new Position();
    PositionC positionc = new PositionC();
    public static int secid, salaryid, pid;

    public position_add(position parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setComboModels();
        setComboLevelModel();
        getRootPane().setDefaultButton(cmdaddnew);
        setLocationRelativeTo(this);
    }

    void setComboModels() {
        cmbrv.setModel(new SalaryC().getSalaryComboModel());
    }

    void setComboLevelModel() {
        cmblevel.setModel(new PositionLevelC().getPositionLevelComboModel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmdcancel = new javax.swing.JButton();
        cmdaddnew = new javax.swing.JButton();
        txtdesc = new javax.swing.JTextField();
        cmbrv = new javax.swing.JComboBox();
        cmblevel = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Department");
        setResizable(false);

        jLabel1.setText("Position Description:");

        jLabel2.setText("Rank:");

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmdaddnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdaddnew.setMnemonic('A');
        cmdaddnew.setText("Add");
        cmdaddnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddnewActionPerformed(evt);
            }
        });

        txtdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        cmbrv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbrv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbrv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbrvActionPerformed(evt);
            }
        });

        cmblevel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmblevel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmblevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmblevelActionPerformed(evt);
            }
        });

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
                        .addComponent(cmbrv, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmblevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbrv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmblevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdcancel)
                    .addComponent(cmdaddnew))
                .addContainerGap())
        );

        jLabel3.setText("Rank:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdaddnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddnewActionPerformed
        String pd = txtdesc.getText().replace("'", " ");
        String rv = cmbrv.getSelectedItem().toString();

        if (pd.isEmpty() == true || rv.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please fill-up all the required fields!");
        } else {
            position.setPositionDesc(pd);
            position.setSalaryId(Short.parseShort(String.valueOf(salaryid)));
            position.setSecId(secid);
            position.setPosLevelId(Short.valueOf(String.valueOf(pid)));

            if (positionc.addPosition(position) > 0) {
                frmParent.populateTBL();
                this.dispose();
                JOptionPane.showMessageDialog(null, "New Position Record Successfully Added!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save");
            }
        }
    }//GEN-LAST:event_cmdaddnewActionPerformed

    private void cmbrvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbrvActionPerformed
        try {
            salaryid = ((Salary) cmbrv.getSelectedItem()).getSalaryId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbrvActionPerformed

    private void cmblevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmblevelActionPerformed
        pid=0;
        try {
            pid = ((PositionLevel) cmblevel.getSelectedItem()).getPosLevelId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmblevelActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                position_add dialog = new position_add(frmParent, true);
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
    private javax.swing.JComboBox cmblevel;
    private javax.swing.JComboBox cmbrv;
    private javax.swing.JButton cmdaddnew;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtdesc;
    // End of variables declaration//GEN-END:variables
}
