package mod.gui.employee.loans;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.LoanAgency;
import mod.model.LoanType;
import mod.model.controller.LoanAgencyC;
import mod.model.controller.LoanTypeC;

public class emp_loans_manage extends javax.swing.JInternalFrame {

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3, model4;
    static Statement stmt;
    static int empid;
    static int laid, ltid;
    public static emp_loans_add frmNew;
    public static emp_loans_ledger frmLedger;
    public static emp_loans_edit frmEdit;
    public static emp_loans_log frmLogs;

    public emp_loans_manage() {
        initComponents();

        TableColumn idClmn1 = tbl1.getColumn("id");
        idClmn1.setMaxWidth(0);
        idClmn1.setMinWidth(0);
        idClmn1.setPreferredWidth(0);

        TableColumn idClmn2 = tbl2.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);

        TableColumn idClmn3 = tbl3.getColumn("id");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);
        
        setComboModels();
        setComboModelLTs();

        getRootPane().setDefaultButton(cmdsearch);
    }

    public void ShowFrmNew() {
        frmNew = new emp_loans_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new emp_loans_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void ShowFrmLedger() {
        frmLedger = new emp_loans_ledger(this, true);
        frmLedger.setVisible(true);
    }

    public void ShowFrmLog() {
        frmLogs = new emp_loans_log(this, true);
        frmLogs.setVisible(true);
    }

    void setComboModels() {
        cmbLA.setModel(new LoanAgencyC().getLoanAgencyComboModel());
    }

    void setComboModelLTs() {
        cmbLT.setModel(new LoanTypeC().getLoanTypeComboModel(laid));
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
            tbl2.getColumn("Loans").setCellRenderer(new ButtonRenderer(1));
            tbl2.getColumn("Loans").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
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
                    String nym = tbl2.getValueAt(row, 1).toString();
                    empid = Integer.parseInt(id);
                    lbl.setText(nym + "'s Current Loans");
                    cmbLA.setSelectedIndex(0);
                    cmbLT.setSelectedIndex(0);

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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/emploans2.png"));
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

    public void populateTBL1() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM employee_loan WHERE emp_id=" + empid + " AND loan_id=" + ltid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model3 = (DefaultTableModel) tbl1.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl1.setRowHeight(28);

            tbl1.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl1.setColumnSelectionAllowed(false);
            tbl1.getColumn("Ledger").setCellRenderer(new ButtonRenderer2(1));
            tbl1.getColumn("Ledger").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tbl1.getColumn("Logs").setCellRenderer(new ButtonRenderer2(2));
            tbl1.getColumn("Logs").setCellEditor(new ButtonEditor2(new JCheckBox(), 2));
            tbl1.getColumn("Edit").setCellRenderer(new ButtonRenderer2(3));
            tbl1.getColumn("Edit").setCellEditor(new ButtonEditor2(new JCheckBox(), 3));

            model3.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                model3.addRow(new Object[]{rs.getString("emp_loan_id"),
                    rs.getString("particular"),
                    rs.getString("term"),
                    rs.getString("loan_date"),
                    rs.getString("due_date"),
                    rs.getString("amount"),
                    rs.getString("monthly"),
                    "", "", ""});
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
                    int row = tbl1.getSelectedRow(); //get value of selected value
                    String id = tbl1.getValueAt(row, col).toString();
                    emp_loans_ledger.elid = Integer.parseInt(id);
                    ShowFrmLedger();
                } else if (flg == 2) {
                    int col = 0; //set column value to 0
                    int row = tbl1.getSelectedRow(); //get value of selected value
                    String id = tbl1.getValueAt(row, col).toString();
                    emp_loans_log.elid = Integer.parseInt(id);
                    ShowFrmLog();
                } else if (flg == 3) {
                    int col = 0; //set column value to 0
                    int row = tbl1.getSelectedRow(); //get value of selected value
                    String id = tbl1.getValueAt(row, col).toString();
                    emp_loans_edit.elid = Integer.parseInt(id);
                    ShowFrmEdit();
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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/ledgermini.png"));
            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/logsmini.png"));
            Icon ico3 = new javax.swing.ImageIcon(getClass().getResource("/images/editm.png"));

            if (iflg == 1) {
                setIcon(ico1);
            } else if (iflg == 2) {
                setIcon(ico2);
            } else if (iflg == 3) {
                setIcon(ico3);
            }

            setText((value == null) ? "" : value.toString());
            return this;
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
        lbl = new javax.swing.JLabel();
        cmbLA = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbLT = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        cmdnew = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmdexit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        yr = new com.toedter.calendar.JYearChooser();
        cmdnew1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Employees Loans Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emploans.png"))); // NOI18N

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
        jLabel49.setText("(Click to View and Manage Loans)");

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Loans"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsearch)
                .addContainerGap())
        );

        lbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl.setForeground(new java.awt.Color(0, 102, 0));
        lbl.setText("Employee's Current Loans");

        cmbLA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLAActionPerformed(evt);
            }
        });
        cmbLA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLAKeyPressed(evt);
            }
        });

        jLabel4.setDisplayedMnemonic('y');
        jLabel4.setText("Select Loan Agency");

        jLabel5.setDisplayedMnemonic('e');
        jLabel5.setText("Select Loan Type");

        cmbLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLTActionPerformed(evt);
            }
        });
        cmbLT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLTKeyPressed(evt);
            }
        });

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Particular", "Term (mos.)", "Date of Loan", "Due Date", "Amount", "Monthly", "Ledger", "Logs", "Edit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true, true
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
        tbl1.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl1.getColumnModel().getColumn(7).setMaxWidth(50);
        tbl1.getColumnModel().getColumn(8).setMaxWidth(50);
        tbl1.getColumnModel().getColumn(9).setMaxWidth(50);

        cmdnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdnew.setMnemonic('N');
        cmdnew.setText("New Loan");
        cmdnew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnewActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("Previous Loans");

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

        tbl3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Particular", "Term (Months)", "Date of Loan", "Due Date", "Amount", "Monthly", "Ledger", "Logs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl3);
        tbl3.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl3.getColumnModel().getColumn(7).setMaxWidth(50);
        tbl3.getColumnModel().getColumn(8).setMaxWidth(50);

        jLabel7.setDisplayedMnemonic('y');
        jLabel7.setText("Select Year:");

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
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yrInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yrCaretPositionChanged(evt);
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

        cmdnew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancelitem.png"))); // NOI18N
        cmdnew1.setMnemonic('l');
        cmdnew1.setText("Cancel Loan");
        cmdnew1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdnew1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdnew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdnew1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cmbLA, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbLT, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdnew, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdnew1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLA, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLT, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdnew)
                    .addComponent(cmdexit)
                    .addComponent(cmdnew1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yr, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    private void cmbLAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLAActionPerformed
        try {
            laid = ((LoanAgency) cmbLA.getSelectedItem()).getLoanAgencyId();
            // System.out.println(laid);
            setComboModelLTs();
            model3.setNumRows(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbLAActionPerformed

    private void cmbLAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLAKeyPressed
    }//GEN-LAST:event_cmbLAKeyPressed

    private void cmbLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLTActionPerformed
        try {
            ltid = ((LoanType) cmbLT.getSelectedItem()).getLoanId();
            // System.out.println(laid);
            // setComboModelLTs();
            populateTBL1();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbLTActionPerformed

    private void cmbLTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLTKeyPressed

    private void cmdnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnewActionPerformed
        if (empid == 0) {
            JOptionPane.showMessageDialog(this, "Please select an Employee!");
        } else {
            if (cmbLA.getSelectedIndex() != 0) {

                if (cmbLT.getSelectedIndex() != 0) {
                    emp_loans_add.empid = empid;
                    emp_loans_add.ltid = ltid;
                    ShowFrmNew();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a Type of Loan!");
                }
            } else {

                JOptionPane.showMessageDialog(this, "Please select an Agency!");
            }
        }
    }//GEN-LAST:event_cmdnewActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void yrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yrMouseClicked
    }//GEN-LAST:event_yrMouseClicked

    private void yrAncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yrAncestorMoved1
    }//GEN-LAST:event_yrAncestorMoved1

    private void yrHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yrHierarchyChanged
    }//GEN-LAST:event_yrHierarchyChanged

    private void yrCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yrCaretPositionChanged
    }//GEN-LAST:event_yrCaretPositionChanged

    private void yrInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yrInputMethodTextChanged
    }//GEN-LAST:event_yrInputMethodTextChanged

    private void yrPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yrPropertyChange
    }//GEN-LAST:event_yrPropertyChange

    private void yrAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorMoved
    }//GEN-LAST:event_yrAncestorMoved

    private void yrAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yrAncestorAdded

    private void yrVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yrVetoableChange
    }//GEN-LAST:event_yrVetoableChange

    private void cmdnew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdnew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdnew1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbLA;
    private javax.swing.JComboBox cmbLT;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdnew;
    private javax.swing.JButton cmdnew1;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTable tbl3;
    private javax.swing.JTextField txtsearch;
    private com.toedter.calendar.JYearChooser yr;
    // End of variables declaration//GEN-END:variables
}
