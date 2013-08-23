package mod.gui.employee_shift;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.EmployeeShift;
import mod.model.controller.EmployeeShiftC;
import mod.model.controller.ShiftC;
import mod.others.myFunction;

public class assign extends javax.swing.JInternalFrame {

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3, model4;
    static Statement stmt;
    static int empid;
    public static assign_shift frmAssign;
    public static assign_edit frmUnAssign;
    EmployeeShift eshift = new EmployeeShift();
    EmployeeShiftC eshiftc = new EmployeeShiftC();

    public assign() {
        initComponents();

        TableColumn idClmn2 = tbl2.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);


        TableColumn idClmn3 = tblshift.getColumn("id");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);

        TableColumn idClmn4 = tblassign.getColumn("id");
        idClmn4.setMaxWidth(0);
        idClmn4.setMinWidth(0);
        idClmn4.setPreferredWidth(0);
        //setTableModelShift();
    }

    public void ShowFrmAssign() {
        frmAssign = new assign_shift(this, true);
        frmAssign.setVisible(true);
    }

    public void ShowFrmUnAssign() {
        frmUnAssign = new assign_edit(this, true);
        frmUnAssign.setVisible(true);
    }

    public void setTableModelShift() {
        tblshift.setModel(new ShiftC().getAssignShiftTableModel(tblshift));
    }

    public static void unasssignShift(int empid, int eshiftid) {
        Connection conn = myDBConn.getConnection();
        String createString = "DELETE FROM  employee_shift "
                + "WHERE emp_id=" + empid + " AND emp_shift_id=" + eshiftid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void populateTBL() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT emp_id, CONCAT(last_name,', ',first_name,' ',middle_name,' ',COALESCE(suffix_name,'')), gender FROM employee WHERE CONCAT(last_name,', ',first_name,' ',middle_name,' ',COALESCE(suffix_name,'')) LIKE '%" + txtsearch.getText() + "%' ORDER BY last_name, first_name";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tbl2.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl2.setRowHeight(28);

            //tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl2.getColumn("Shift").setCellRenderer(new ButtonRenderer(1));
            tbl2.getColumn("Shift").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
            tbl2.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String img = null;
                switch (rs.getString(3)) {
                    case "M":
                        img = "/images/malemini.png";
                        break;
                    case "F":
                        img = "/images/femalemini.png";
                        break;
                }

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource(img) + ">&nbsp</td><td>" + rs.getString(2) + "</td></th>";
                model2.addRow(new Object[]{rs.getString(1), lbl, ""});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        int flg;

        public ButtonEditor(JCheckBox checkBox, int x) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            flg = x;

            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"));
            button.setIcon(ico1);

            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (flg == 1) {
                    int col = 0; //set column value to 0
                    int row = tbl2.getSelectedRow(); //get value of selected value
                    String id = tbl2.getValueAt(row, col).toString();
                    empid = Integer.parseInt(id);
                    populateTBLShift();
                    populateTBLAssign();
                } else if (flg == 2) {
                }

            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        int iflg;

        public ButtonRenderer(int ico) {
            setOpaque(true);
            iflg = ico;
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/logintime.png"));
            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/ledgermini.png"));

            if (iflg == 1) {
                setIcon(ico1);
            } else if (iflg == 2) {
                setIcon(ico2);
            }


            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    public void populateTBLShift() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM shift WHERE shift_id NOT IN (SELECT shift_id FROM employee_shift WHERE emp_id=" + empid + ") ";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model3 = (DefaultTableModel) tblshift.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tblshift.setRowHeight(60);

            //tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblshift.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
            tblshift.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblshift.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
//            tblshift.getColumn("Assign").setCellRenderer(new ButtonRenderer2(1));
//            tblshift.getColumn("Assign").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tblshift.setColumnSelectionAllowed(false);

            model3.setNumRows(0);
            int cnt = 0;

            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/shiftmini.png") + ">&nbsp</td><td>" + rs.getString("shift_desc") + "<font></td>";

                //in_time1, out_time1, in_time2, out_time2, shift_des

                String time1 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(rs.getTime("in_time1")) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(rs.getTime("out_time1")) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                String time2 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(rs.getTime("in_time2")) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(rs.getTime("out_time2")) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";
                model3.addRow(new Object[]{rs.getInt("shift_id"), lbl, time1, time2});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    class ButtonEditor2 extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        int flg;

        public ButtonEditor2(JCheckBox checkBox, int x) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            flg = x;

            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"));
            button.setIcon(ico1);

            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (flg == 1) {
                    int col = 0; //set column value to 0
                    int row = tbl2.getSelectedRow(); //get value of selected value
                    String id = tbl2.getValueAt(row, col).toString();
//                    empid = Integer.parseInt(id);
//                    poulateDatas(Integer.parseInt(id));
//                    populateDependents(Integer.parseInt(id));
//                    getEmploymentData(Integer.parseInt(id));
//                    setTaxCode();
//                    cmdchange2.setVisible(true);
                } else if (flg == 2) {
                }

            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    class ButtonRenderer2 extends JButton implements TableCellRenderer {

        int iflg;

        public ButtonRenderer2(int ico) {
            setOpaque(true);
            iflg = ico;
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/save.png"));
            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/ledgermini.png"));

            if (iflg == 1) {
                setIcon(ico1);
            } else if (iflg == 2) {
                setIcon(ico2);
            }


            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    public void populateTBLAssign() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM employee_shift e INNER JOIN shift s ON e.shift_id=s.shift_id "
                + " WHERE e.emp_id=" + empid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model4 = (DefaultTableModel) tblassign.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tblassign.setRowHeight(60);

            //tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblassign.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
            tblassign.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblassign.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tblassign.getColumnModel().getColumn(6).setCellRenderer(cellAlignCenterRenderer);
//            tblshift.getColumn("Assign").setCellRenderer(new ButtonRenderer2(1));
//            tblshift.getColumn("Assign").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tblassign.setColumnSelectionAllowed(false);

            model4.setNumRows(0);
            int cnt = 0;

            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yy");

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/shiftmini.png") + ">&nbsp</td><td>" + rs.getString("shift_desc") + "<font></td>";

                //in_time1, out_time1, in_time2, out_time2, shift_des

                String time1 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(rs.getTime("in_time1")) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(rs.getTime("out_time1")) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                String time2 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(rs.getTime("in_time2")) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(rs.getTime("out_time2")) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                String p = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/permanent.png") + "</tr>"
                        + "</table>"
                        + "</html>";

                String np = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/notpermanent.png") + "</tr>"
                        + "</table>"
                        + "</html>";


                int flg = rs.getInt("p_flag");
                if (flg == 0) {
                    model4.addRow(new Object[]{rs.getString(1), lbl, time1, time2, "N/A", "N/A", p});
                } else {
                    model4.addRow(new Object[]{rs.getString(1), lbl, time1, time2, dateFormat2.format(rs.getDate("start_date")), dateFormat2.format(rs.getDate("end_date")), np});
                }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblshift = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblassign = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmdunassign = new javax.swing.JButton();
        cmdeditrange = new javax.swing.JButton();
        cmdassign = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Employees Shift Assigment Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/assign.png"))); // NOI18N

        jLabel2.setDisplayedMnemonic('L');
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("List of Employee/s");

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setText("Search Employee Here:");

        cmdsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdsearch.setMnemonic('R');
        cmdsearch.setText("Search Now");
        cmdsearch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsearchActionPerformed(evt);
            }
        });

        txtsearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel49.setForeground(new java.awt.Color(255, 153, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("(Click to View and Edit Shift Assignment)");

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl2);
        tbl2.getColumnModel().getColumn(1).setPreferredWidth(300);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtsearch)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmdsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsearch))
        );

        tblshift.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Description", "Time 1", "Time 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblshift);

        tblassign.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Description", "Time 1", "Time 2", "From", "Until", "Permanent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblassign);
        tblassign.getColumnModel().getColumn(6).setMaxWidth(100);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Available Shift/s");

        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Employee's Assigned Shift");

        cmdunassign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdunassign.setMnemonic('A');
        cmdunassign.setText("Unassign");
        cmdunassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdunassignActionPerformed(evt);
            }
        });

        cmdeditrange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        cmdeditrange.setMnemonic('A');
        cmdeditrange.setText("Edit Range");
        cmdeditrange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdeditrangeActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdunassign, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdeditrange, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdassign, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmdassign)
                        .addGap(0, 194, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmdunassign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdeditrange)
                        .addContainerGap(198, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Exit");
        cmdcancel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdcancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(385, 385, 385)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdcancel))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    private void cmdassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdassignActionPerformed

        try {
            int col = 0; //set column value to 0
            int row = tblshift.getSelectedRow(); //get value of selected value
            String id = tblshift.getValueAt(row, col).toString();
            assign_shift.shiftid = Integer.parseInt(id);
            assign_shift.empid = empid;
            ShowFrmAssign();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Shift is Selected!");
        }

    }//GEN-LAST:event_cmdassignActionPerformed

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdunassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdunassignActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tblassign.getSelectedRow(); //get value of selected value

            //trap user incase if no row selected
            if (tblassign.isRowSelected(row) != true) {
                JOptionPane.showMessageDialog(this, "Select a Range!");
            } else {
                String id = tblassign.getValueAt(row, col).toString();
                //System.out.println(empid+"-"+id);
                int i = myFunction.msgboxYesNo("Are you sure you want to unassign this shift?");
                if (i == 0) {

                    unasssignShift(empid, Integer.parseInt(id));

                    populateTBLShift();
                    populateTBLAssign();
                    JOptionPane.showMessageDialog(null, "Shift Successfully Unaasign!");
                } else if (i == 1) {
                } else if (i == 2) {
                    this.dispose(); //exit window
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No Record Selected!");
        }
    }//GEN-LAST:event_cmdunassignActionPerformed

    private void cmdeditrangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditrangeActionPerformed

        int col = 0; //set column value to 0
        int row = tblassign.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tblassign.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "Select a Range!");
        } else {
            String id = tblassign.getValueAt(row, col).toString();
            assign_edit.esid =Integer.parseInt(id);
            ShowFrmUnAssign();
        }
    }//GEN-LAST:event_cmdeditrangeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdassign;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdeditrange;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JButton cmdunassign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl2;
    private javax.swing.JTable tblassign;
    private javax.swing.JTable tblshift;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
