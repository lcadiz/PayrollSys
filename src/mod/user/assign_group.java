package mod.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;

public class assign_group extends javax.swing.JDialog {

    public static accounts frmParent;
    static Statement stmt;
    static int userid;
    static String userinfo;

    public assign_group(accounts parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        lbl.setText(userinfo);
        populateLst();
    }

    void populateLst() {
        populateUNASSIGN();
        populateASSIGN();
    }

    void populateUNASSIGN() {
        //Populate Combo Area
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM user_group WHERE grp_id NOT IN (SELECT grp_id FROM user_group_assigned WHERE user_id=" + userid + ")";

        try {
            DefaultListModel model = new DefaultListModel();
            lst1.setModel(model);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/group2.png") + ">&nbsp</td><td>" + rs.getString(2) + "</td></th>";
                model.addElement(new Item(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }
    }

    void populateASSIGN() {
        //Populate Combo Area
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM user_group WHERE grp_id IN (SELECT grp_id FROM user_group_assigned WHERE user_id=" + userid + ")";

        try {
            DefaultListModel model = new DefaultListModel();
            lst2.setModel(model);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/group.png") + ">&nbsp</td><td>" + rs.getString(2) + "</td></th>";
                model.addElement(new Item2(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
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

    class Item2 {

        private int id;
        private String description;

        public Item2(int id, String description) {
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

    public static void UnAssign(int UGID) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "DELETE FROM user_group_assigned WHERE user_id=" + userid + " AND grp_id=" + UGID;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void Assign(int UGID) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "INSERT INTO user_group_assigned(user_id, grp_id) "
                + "VALUES (" + userid + "," + UGID + ")";

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

        jScrollPane1 = new javax.swing.JScrollPane();
        lst2 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst1 = new javax.swing.JList();
        cmdremove = new javax.swing.JButton();
        cmdassign = new javax.swing.JButton();
        cmdunassign = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group Assignment");
        setResizable(false);

        lst2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lst2);

        lst1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lst1);

        cmdremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdremove.setMnemonic('x');
        cmdremove.setText("Exit");
        cmdremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremoveActionPerformed(evt);
            }
        });

        cmdassign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"))); // NOI18N
        cmdassign.setMnemonic('A');
        cmdassign.setText("Assign");
        cmdassign.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdassign.setInheritsPopupMenu(true);
        cmdassign.setVerifyInputWhenFocusTarget(false);
        cmdassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdassignActionPerformed(evt);
            }
        });

        cmdunassign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/outtt.png"))); // NOI18N
        cmdunassign.setMnemonic('U');
        cmdunassign.setText("Unassign");
        cmdunassign.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdunassign.setVerifyInputWhenFocusTarget(false);
        cmdunassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdunassignActionPerformed(evt);
            }
        });

        jLabel1.setDisplayedMnemonic('r');
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setLabelFor(lst1);
        jLabel1.setText("List of user group not assigned to this user");

        jLabel2.setDisplayedMnemonic('f');
        jLabel2.setForeground(new java.awt.Color(0, 102, 0));
        jLabel2.setLabelFor(lst2);
        jLabel2.setText("List of user group assigned to this user");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdremove))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmdunassign)
                                    .addComponent(cmdassign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(cmdassign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdunassign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdremove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremoveActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdremoveActionPerformed

    private void cmdunassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdunassignActionPerformed
        try {
            Item2 item = (Item2) lst2.getSelectedValue();
            int id = item.getId();
            UnAssign(id);
            populateLst();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Group Selected!");
            return;
        }
    }//GEN-LAST:event_cmdunassignActionPerformed

    private void cmdassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdassignActionPerformed
        try {
            Item item = (Item) lst1.getSelectedValue();
            int id = item.getId();
            Assign(id);
            populateLst();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No User Group Selected!");
            return;
        }


    }//GEN-LAST:event_cmdassignActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                assign_group dialog = new assign_group(frmParent, true);
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
    private javax.swing.JButton cmdassign;
    private javax.swing.JButton cmdremove;
    private javax.swing.JButton cmdunassign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl;
    private javax.swing.JList lst1;
    private javax.swing.JList lst2;
    // End of variables declaration//GEN-END:variables
}
