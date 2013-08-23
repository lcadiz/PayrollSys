package mod.gui.employee.loans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;

public class emp_loans_log extends javax.swing.JDialog {

    public static emp_loans_manage frmParent;
    public static int elid;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3, model4;
    static Statement stmt;

    public emp_loans_log(emp_loans_manage parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);

        TableColumn idClmn1 = tbl1.getColumn("id");
        idClmn1.setMaxWidth(0);
        idClmn1.setMinWidth(0);
        idClmn1.setPreferredWidth(0);
        populateTBL();
    }

    public void populateTBL() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT lll.loan_ledger_log_id, lll.trans_date, lll.notification, lll.due_date,lll.monthly, u.full_name FROM loan_ledger_log lll INNER JOIN user u ON lll.user_id=u.user_id WHERE lll.emp_loan_id=" + elid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tbl1.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl1.setRowHeight(28);

            //tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl1.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
              String note= "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/logsmini.png") + "></td>&nbsp<td >" + rs.getString("notification") + "</td></th>";

                model2.addRow(new Object[]{rs.getString(1),rs.getString(2), note, rs.getString(4), rs.getString(5), rs.getString(6)});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        cmdexit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Loan Activity Logs");
        setResizable(false);

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Date", "Notification", "Due Date", "Monthly", "User"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl1);
        tbl1.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbl1.getColumnModel().getColumn(5).setPreferredWidth(200);

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 804, Short.MAX_VALUE)
                        .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdexit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                emp_loans_log dialog = new emp_loans_log(frmParent, true);
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
    private javax.swing.JButton cmdexit;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl1;
    // End of variables declaration//GEN-END:variables
}
