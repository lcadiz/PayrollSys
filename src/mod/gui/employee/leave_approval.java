package mod.gui.employee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import mod.connection.myDBConn;
import mod.model.LeaveApp;
import mod.model.controller.LeaveAppC;
import mod.others.myFunction;

public class leave_approval extends javax.swing.JInternalFrame {

    static Statement stmt;
    static String nowDate = myFunction.getDate();
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellRowColorRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellEnableRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model8, model19, model20, model21;
    LeaveApp leaveapp = new LeaveApp();
    LeaveAppC leaveappc = new LeaveAppC();
    static int empid, raid, aid, deptid, secid, divid, levelid, ltid, month;

    public leave_approval() {
        initComponents();
        populateTBLLeaveApproved();

        tblleaveapprove.setCellSelectionEnabled(false);
        tblleaveapprove.setRowSelectionAllowed(true);
        tblleaveapprove.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setTabPending();
        setTabApprove();
        setTabDenied();
        populateCMB();
    }

     public void populateCMB() {
        //Populate Combo Area
        cmbmonth.addItem("--SELECT--");
        cmbmonth.addItem(new Item5(1, "January".toUpperCase()));
        cmbmonth.addItem(new Item5(2, "February".toUpperCase()));
        cmbmonth.addItem(new Item5(3, "March".toUpperCase()));
        cmbmonth.addItem(new Item5(4, "April".toUpperCase()));
        cmbmonth.addItem(new Item5(5, "May".toUpperCase()));
        cmbmonth.addItem(new Item5(6, "June".toUpperCase()));
        cmbmonth.addItem(new Item5(7, "July".toUpperCase()));
        cmbmonth.addItem(new Item5(8, "August".toUpperCase()));
        cmbmonth.addItem(new Item5(9, "September".toUpperCase()));
        cmbmonth.addItem(new Item5(10, "October".toUpperCase()));
        cmbmonth.addItem(new Item5(11, "November".toUpperCase()));
        cmbmonth.addItem(new Item5(12, "December".toUpperCase()));
        //Populate Combo Area2
        cmbmonth2.addItem("--SELECT--");
        cmbmonth2.addItem(new Item5(1, "January".toUpperCase()));
        cmbmonth2.addItem(new Item5(2, "February".toUpperCase()));
        cmbmonth2.addItem(new Item5(3, "March".toUpperCase()));
        cmbmonth2.addItem(new Item5(4, "April".toUpperCase()));
        cmbmonth2.addItem(new Item5(5, "May".toUpperCase()));
        cmbmonth2.addItem(new Item5(6, "June".toUpperCase()));
        cmbmonth2.addItem(new Item5(7, "July".toUpperCase()));
        cmbmonth2.addItem(new Item5(8, "August".toUpperCase()));
        cmbmonth2.addItem(new Item5(9, "September".toUpperCase()));
        cmbmonth2.addItem(new Item5(10, "October".toUpperCase()));
        cmbmonth2.addItem(new Item5(11, "November".toUpperCase()));
        cmbmonth2.addItem(new Item5(12, "December".toUpperCase()));
    }

    class Item5 {

        private int id;
        private String description;

        public Item5(int id, String description) {
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
    
    void setTabPending() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/pending1.png") + ">&nbsp</td><td>Pending<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(0, lbl);

    }

    void setTabApprove() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/approved1.png") + ">&nbsp</td><td>Approved<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(1, lbl);

    }

    void setTabDenied() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/trash.png") + ">&nbsp</td><td>Denied<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(2, lbl);

    }

    String GetEmpName(int eid) {
        String nym = "";

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT CONCAT(e.last_name, ', ' , e.first_name, ' ' ,COALESCE(e.middle_name,''), ' ' , COALESCE(e.suffix_name,'')) as nym "
                + "FROM employee e INNER JOIN `position` p ON e.position_id=p.position_id WHERE e.emp_id=" + eid;


        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                nym = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return nym;
    }

    String GetEmpPos(int eid) {
        String pos = "";

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT p.position_desc "
                + "FROM employee e INNER JOIN `position` p ON e.position_id=p.position_id WHERE e.emp_id=" + eid;


        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                pos = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return pos;
    }

    String GetEmpDept() {
        String pos = "";

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT dept_name FROM department WHERE dept_id=" + deptid;


        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                pos = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return pos;
    }

    String GetLeaveTypeDesc(int ltypid) {
        String pos = "";

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT leave_type_desc FROM leave_type WHERE leave_type_id=" + ltypid;


        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                pos = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return pos;
    }

    public void populateTBLLeaveApproved() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE status_id=0 ";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tblleaveapprove.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleaveapprove.setRowHeight(140);

            tblleaveapprove.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprove.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleaveapprove.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprove.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);


            tblleaveapprove.getColumn("Deny").setCellRenderer(new ButtonRenderer2(1));
            tblleaveapprove.getColumn("Deny").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tblleaveapprove.getColumn("Approve").setCellRenderer(new ButtonRenderer2(2));
            tblleaveapprove.getColumn("Approve").setCellEditor(new ButtonEditor2(new JCheckBox(), 2));
            tblleaveapprove.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String ltypedec = GetLeaveTypeDesc(rs.getInt("leave_type_id"));
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td>Date Applied: </td>&nbsp<td>" + rs.getDate("trans_date") + "</td></tr>"
                        + "<tr font color=maroon><td>Inclusive Dates:</td>&nbsp<td >" + " From " + rs.getDate("start_date_time") + " to " + rs.getDate("end_date_time") + "</font></td></tr>"
                        + "<tr font><td>Applicant:</td>&nbsp<td><b>" + rs.getString("last_name") + ", " + rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("suffix_name") + "</b></td></tr>"
                        + "<tr font color=orange><td>Reason:</td>&nbsp<td>" + rs.getString("reason") + "</td></tr>"
                        + "<tr ><td>No. of Days:</td>&nbsp<td>" + rs.getString("leave_days") + "</td></tr>"
                        + "<tr><td>Credits Used:</td>&nbsp<td>" + rs.getString("credits_used") + "</td></tr>"
                        + "<tr><td>Leave Type:</td>&nbsp<td>" + ltypedec + "</td></tr>"
                        + "</th>";

                int ragid = rs.getInt("r_approved_by");
                int agid = rs.getInt("approved_by");
                String raname = GetEmpName(ragid);
                String aname = GetEmpName(agid);
                String rapos = GetEmpPos(ragid);
                String apos = GetEmpPos(agid);

                int raflg = rs.getInt("r_approve_flag");
                int aflg = rs.getInt("approve_flag");

                String rastat = null;
                if (raflg == 0) {
                    rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                            + "</th>";
                } else if (raflg == 1) {
                    rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=green><td>" + "APPROVED" + "</td></tr>"
                            + "</th>";
                }

                String astat = null;
                if (aflg == 0) {
                    astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                            + "</th>";
                } else if (aflg == 1) {
                    astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=green><td>" + "APPROVED" + "</td></tr>"
                            + "</th>";
                }

                String lblra = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr align=center><td>" + raname + "</td></tr>"
                        + "<tr align=center font color=orange><td>" + rapos + "</td></tr>"
                        + "<tr align=center><td>" + "Status: " + rastat + "</td></tr>"
                        + "</th>";

                String lbla = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr align=center><td>" + aname + "</td></tr>"
                        + "<tr align=center font color=orange><td>" + apos + "</td></tr>"
                        + "<tr align=center><td>" + "Status: " + astat + "</td></tr>"
                        + "</th>";

                NumberFormat nf4 = new DecimalFormat("000000");
                model2.addRow(new Object[]{nf4.format(rs.getInt("leave_app_id")), lbl, lblra, lbla, "", ""});
                cnt++;
            }

            lbl1.setText(cnt + " pending application/s ");

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void ApprovedLeave(int laid) {
        Connection conn = myDBConn.getConnection();
        String createString = "UPDATE leave_app SET status_id=2, approve_flag=1 "
                + "WHERE leave_app_id=" + laid;


        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void RApprovedLeave(int laid) {
        Connection conn = myDBConn.getConnection();
        String createString = "UPDATE leave_app SET r_approve_flag=1 "
                + "WHERE leave_app_id=" + laid;


        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void approveNow(int laid) {
        int ra = leaveappc.GetRApprovedId(laid);
        int a = leaveappc.GetApprovedId(laid);
        int raflg = leaveappc.GetRApprovedFlg(laid);

        if (empid == ra) {
            if (raflg == 1) {
                JOptionPane.showMessageDialog(null, "Reccomending Approval is already approved");
            } else {
                RApprovedLeave(laid);
                populateTBLLeaveApproved();
            }
        } else if (empid == a) {
            if (raflg == 0) {
                JOptionPane.showMessageDialog(null, "Cannot Approve this leave application because the recommending approval has not been yet approved!");
            } else {
                ApprovedLeave(laid);
                //repopulateapprovealready();
            }
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
                try {
                    if (flg == 1) {
                        int col = 0; //set column value to 0
                        int row = tblleaveapprove.getSelectedRow(); //get value of selected value
                        String id = tblleaveapprove.getValueAt(row, col).toString();

                        leaveapp_deny.laid = Integer.parseInt(id);
                        // ShowFrmDeny();
                    } else if (flg == 2) {
                        int col = 0; //set column value to 0
                        int row = tblleaveapprove.getSelectedRow(); //get value of selected value
                        String id = tblleaveapprove.getValueAt(row, col).toString();



                        int i = myFunction.msgboxYesNo("You are now approving this leave application, proceed?");
                        if (i == 0) {
                            approveNow(Integer.parseInt(id));

                        } else if (i == 1) {
                        } else if (i == 2) {
                        }


                    }
                } catch (Exception e) {
                    e.getMessage();
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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"));
            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/employer.png"));

            if (iflg == 1) {
                setIcon(ico1);
            } else if (iflg == 2) {
                setIcon(ico2);
            }


            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblleaveapprove = new javax.swing.JTable();
        lbl1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel46 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        yr = new com.toedter.calendar.JYearChooser();
        cmbmonth = new javax.swing.JComboBox();
        cmdview1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblleaveapprovedleave = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel52 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        yr11 = new com.toedter.calendar.JYearChooser();
        cmbmonth2 = new javax.swing.JComboBox();
        cmdview2 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblleavedenied = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Leave Approval - HR");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 0, 0));
        jLabel31.setText("Pending Request for Leave Application");

        tblleaveapprove.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Leave Details", "Recommending Approval by", "Approved by", "Deny", "Approve"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblleaveapprove.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tblleaveapprove);
        tblleaveapprove.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleaveapprove.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleaveapprove.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        tab.addTab("tab1", jPanel1);

        jLabel32.setText("Month:");

        yr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        yr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yrMouseClicked(evt);
            }
        });
        yr.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                yrAncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        yr.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                yrHierarchyChanged(evt);
            }
        });
        yr.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yrCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yrInputMethodTextChanged(evt);
            }
        });
        yr.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yrPropertyChange(evt);
            }
        });
        yr.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                yrAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                yrAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        yr.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                yrVetoableChange(evt);
            }
        });

        cmbmonth.setMaximumRowCount(20);
        cmbmonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        cmbmonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmonthActionPerformed(evt);
            }
        });

        cmdview1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdview1.setMnemonic('V');
        cmdview1.setText("View");
        cmdview1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdview1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdview1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdview1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(cmdview1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cmdview1)))
                .addContainerGap())
        );

        jLabel33.setText("Year:");

        tblleaveapprovedleave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Leave Details", "Recommending Approval by", "Approved by"
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
        tblleaveapprovedleave.getTableHeader().setReorderingAllowed(false);
        jScrollPane18.setViewportView(tblleaveapprovedleave);
        tblleaveapprovedleave.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleaveapprovedleave.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleaveapprovedleave.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jScrollPane12.setViewportView(jPanel46);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("tab2", jPanel3);

        jLabel34.setText("Month:");

        yr11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        yr11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yr11MouseClicked(evt);
            }
        });
        yr11.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                yr11AncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        yr11.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                yr11HierarchyChanged(evt);
            }
        });
        yr11.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yr11CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yr11InputMethodTextChanged(evt);
            }
        });
        yr11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yr11PropertyChange(evt);
            }
        });
        yr11.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                yr11AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                yr11AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        yr11.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                yr11VetoableChange(evt);
            }
        });

        cmbmonth2.setMaximumRowCount(20);
        cmbmonth2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        cmbmonth2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmonth2ActionPerformed(evt);
            }
        });

        cmdview2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdview2.setMnemonic('V');
        cmdview2.setText("View");
        cmdview2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdview2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdview2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdview2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbmonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yr11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(cmdview2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(cmbmonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yr11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cmdview2)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabel35.setText("Year:");

        tblleavedenied.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Leave Details", "Recommending Approval by", "Approved by", "Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblleavedenied.setColumnSelectionAllowed(true);
        tblleavedenied.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(tblleavedenied);
        tblleavedenied.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleavedenied.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleavedenied.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(921, Short.MAX_VALUE))
            .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1315, Short.MAX_VALUE)
                    .addGap(32, 32, 32)))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(538, Short.MAX_VALUE))
            .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel52Layout.createSequentialGroup()
                    .addGap(103, 103, 103)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane13.setViewportView(jPanel52);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("tab3", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yr11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yr11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11MouseClicked

    private void yr11AncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr11AncestorMoved1
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11AncestorMoved1

    private void yr11HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr11HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11HierarchyChanged

    private void yr11InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr11InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11InputMethodTextChanged

    private void yr11CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr11CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11CaretPositionChanged

    private void yr11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yr11PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11PropertyChange

    private void yr11AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr11AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11AncestorMoved

    private void yr11AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr11AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11AncestorAdded

    private void yr11VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yr11VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr11VetoableChange

    private void cmbmonth2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmonth2ActionPerformed
//        try {
//            Item5 item = (Item5) cmbmonth2.getSelectedItem();
//            month = item.getId();
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_cmbmonth2ActionPerformed

    private void cmdview2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdview2ActionPerformed
//        populateTBLLeaveDenied();
    }//GEN-LAST:event_cmdview2ActionPerformed

    private void yrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yrMouseClicked

    }//GEN-LAST:event_yrMouseClicked

    private void yrAncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yrAncestorMoved1

    }//GEN-LAST:event_yrAncestorMoved1

    private void yrHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yrHierarchyChanged

    }//GEN-LAST:event_yrHierarchyChanged

    private void yrInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yrInputMethodTextChanged

    }//GEN-LAST:event_yrInputMethodTextChanged

    private void yrCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yrCaretPositionChanged

    }//GEN-LAST:event_yrCaretPositionChanged

    private void yrPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yrPropertyChange
        //        try {
            //            populateTBL1();
            //            populateTBL2();
            //        } catch (Exception e) {
            //        }
    }//GEN-LAST:event_yrPropertyChange

    private void yrAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorMoved

    }//GEN-LAST:event_yrAncestorMoved

    private void yrAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yrAncestorAdded

    private void yrVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yrVetoableChange

    }//GEN-LAST:event_yrVetoableChange

    private void cmbmonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmonthActionPerformed
//        try {
//            Item item = (Item) cmbmonth.getSelectedItem();
//            month = item.getId();
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_cmbmonthActionPerformed

    private void cmdview1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdview1ActionPerformed
//        populateTBLLeaveApprovedAlready();
    }//GEN-LAST:event_cmdview1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbmonth;
    private javax.swing.JComboBox cmbmonth2;
    private javax.swing.JButton cmdview1;
    private javax.swing.JButton cmdview2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JLabel lbl1;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblleaveapprove;
    private javax.swing.JTable tblleaveapprovedleave;
    private javax.swing.JTable tblleavedenied;
    private com.toedter.calendar.JYearChooser yr;
    private com.toedter.calendar.JYearChooser yr11;
    // End of variables declaration//GEN-END:variables
}
