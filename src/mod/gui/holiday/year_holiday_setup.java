package mod.gui.holiday;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import mod.connection.myDBConn;
import mod.gui.employee.dependent_edit;
import static mod.gui.employee.employee_add.frmEdit;
import mod.main.mdi;
import mod.model.YearHoliday;
import mod.model.controller.YearHolidayC;
import mod.others.myFunction;

public class year_holiday_setup extends javax.swing.JInternalFrame {

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2;
    static Statement stmt;
    YearHoliday holiday = new YearHoliday();
    YearHolidayC holidayc = new YearHolidayC();
    public static boolean chk;
    protected CheckBoxHeader rendererComponent;
    protected boolean mousePressed = true;
    public static year_holiday_edit frmEdit;

    public year_holiday_setup() {
        initComponents();


        TableColumn idClmn = tbl1.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);
//
        TableColumn idClmn2 = tbl2.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);

        TableColumn idClmn3 = tbl1.getColumn("type");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);

        TableColumn idClmn4 = tbl1.getColumn("m");
        idClmn4.setMaxWidth(0);
        idClmn4.setMinWidth(0);
        idClmn4.setPreferredWidth(0);

        TableColumn idClmn5 = tbl1.getColumn("d");
        idClmn5.setMaxWidth(0);
        idClmn5.setMinWidth(0);
        idClmn5.setPreferredWidth(0);

        populateTBL1();
        populateTBL2();

        TableColumn tc = tbl2.getColumnModel().getColumn(0);
        tc.setCellEditor(tbl2.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbl2.getDefaultRenderer(Boolean.class));
        tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        rendererComponent.setSelected(false);
       
    }


    
    
    public void ShowFrmEdit() {
        frmEdit = new year_holiday_edit(this, true);
        frmEdit.setVisible(true);
    }

    public void populateTBL1() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM holiday WHERE holiday_id NOT IN (SELECT holiday_id FROM year_holiday WHERE year=" + yr.getYear() + ") ORDER BY default_month, default_day";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl1.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl1.setRowHeight(27);
            tbl1.setColumnSelectionAllowed(false);

            model.setNumRows(0);

            int cnt = 0;

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/holidaymini.png") + ">&nbsp</td><td><b>" + rs.getString("holiday_name") + "</b></td></th>";

                String monthname = null;
                String dayfactor = null;
                int defaultMonth = rs.getInt("default_month");
                int defaultDay = rs.getInt("default_day");

                switch (defaultMonth) {
                    case 1:
                        monthname = "JANUARY";
                        break;
                    case 2:
                        monthname = "FEBRUARY";
                        break;
                    case 3:
                        monthname = "MARCH";
                        break;
                    case 4:
                        monthname = "APRIL";
                        break;
                    case 5:
                        monthname = "MAY";
                        break;
                    case 6:
                        monthname = "JUNE";
                        break;
                    case 7:
                        monthname = "JULY";
                        break;
                    case 8:
                        monthname = "AUGUST";
                        break;
                    case 9:
                        monthname = "SEPTEMBER";
                        break;
                    case 10:
                        monthname = "OCTOBER";
                        break;
                    case 11:
                        monthname = "NOVEMBER";
                        break;
                    case 12:
                        monthname = "DECEMBER";
                        break;
                }

                if (defaultDay == 1) {
                    dayfactor = "st";
                } else if (defaultDay == 2) {
                    dayfactor = "nd";
                } else if (defaultDay == 3) {
                    dayfactor = "rd";
                } else {
                    dayfactor = "th";
                }

                String dd = "<html><b><font color=orange>" + defaultDay + "</font></b>" + dayfactor + " day of " + monthname + "</html>";
                model.addRow(new Object[]{rs.getInt("holiday_id"), true, lbl, dd, rs.getInt("constant_flag"), rs.getInt("default_month"), rs.getInt("default_day")});
                cnt++;
            }

            //lbl.setText(wat);

            stmt.close();
            conn.close();



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    class MyItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = tbl2.getRowCount(); x < y; x++) {
                tbl2.setValueAt(new Boolean(checked), x, 0);
            }
        }
    }

    public void populateTBL2() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM holiday h INNER JOIN year_holiday yh ON h.holiday_id=yh.holiday_id "
                + "WHERE h.holiday_id IN (SELECT holiday_id FROM year_holiday WHERE year=" + yr.getYear() + ") GROUP BY h.holiday_id ORDER BY yh.holiday_date";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tbl2.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl2.getColumn("Edit").setCellRenderer(new ButtonRenderer(1));
            tbl2.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), 1));

            tbl2.setRowHeight(27);
            tbl2.setColumnSelectionAllowed(false);

            model2.setNumRows(0);

            int cnt = 0;

            while (rs.next()) {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/holidaymini.png") + ">&nbsp</td><td><b>" + rs.getString("holiday_name") + "</b></td></th>";

                String monthname = null;
                String dayfactor = null;
                int defaultMonth = rs.getInt("default_month");
                int defaultDay = rs.getInt("default_day");

                switch (defaultMonth) {
                    case 1:
                        monthname = "JANUARY";
                        break;
                    case 2:
                        monthname = "FEBRUARY";
                        break;
                    case 3:
                        monthname = "MARCH";
                        break;
                    case 4:
                        monthname = "APRIL";
                        break;
                    case 5:
                        monthname = "MAY";
                        break;
                    case 6:
                        monthname = "JUNE";
                        break;
                    case 7:
                        monthname = "JULY";
                        break;
                    case 8:
                        monthname = "AUGUST";
                        break;
                    case 9:
                        monthname = "SEPTEMBER";
                        break;
                    case 10:
                        monthname = "OCTOBER";
                        break;
                    case 11:
                        monthname = "NOVEMBER";
                        break;
                    case 12:
                        monthname = "DECEMBER";
                        break;
                }

                if (defaultDay == 1) {
                    dayfactor = "st";
                } else if (defaultDay == 2) {
                    dayfactor = "nd";
                } else if (defaultDay == 3) {
                    dayfactor = "rd";
                } else {
                    dayfactor = "th";
                }

                int htype = rs.getInt("pay_comp_id");
                String holtype = "";
                if (htype == 8) {
                    holtype = "Regular";
                } else if (htype == 7) {
                    holtype = "Special";
                }

                int cf = rs.getInt("constant_flag");
                String cfdesc = "";
                if (cf == 1) {
                    cfdesc = "<html><font color=blue>Constant</font></html>";
                } else if (cf == 2) {
                    cfdesc = "<html><font color=orange>Changeable</font></html>";;
                }

                String dd = "<html><b><font color=orange>" + defaultDay + "</font></b>" + dayfactor + " day of " + monthname + " " + yr.getYear() + "</html>";
                model2.addRow(new Object[]{chk, rs.getInt("year_holiday_id"), lbl, rs.getDate("holiday_date"), cfdesc, holtype});
                cnt++;
            }

            //lbl.setText(wat);

            stmt.close();
            conn.close();



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void selectCustom() {
        int r = tbl1.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                tbl1.setValueAt(false, c, 1);


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void selectAll() {
        int r = tbl1.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                tbl1.setValueAt(true, c, 1);


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void selectConstant() {
        int r = tbl1.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                String cf = tbl1.getValueAt(c, 4).toString();
                int fcf = Integer.parseInt(cf);

                if (fcf == 1) {
                    tbl1.setValueAt(true, c, 1);
                } else if (fcf == 2) {
                    tbl1.setValueAt(false, c, 1);
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    int isCheckItemFound() {

        int r = tbl1.getRowCount();
        int c = 0;

        int chkitems = 0;
        while (c < r + 1) {
            try {
                String ischeck = tbl1.getValueAt(c, 1).toString();
                if (!"false".equals(ischeck)) {
                    chkitems++;
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        return chkitems;
    }
    
    int isCheckItemFound2() {

        int r = tbl2.getRowCount();
        int c = 0;

        int chkitems = 0;
        while (c < r + 1) {
            try {
                String ischeck = tbl2.getValueAt(c, 0).toString();
                if (!"false".equals(ischeck)) {
                    chkitems++;
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        return chkitems;
    }

    void selectChangeable() {
        int r = tbl1.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                String cf = tbl1.getValueAt(c, 4).toString();
                int fcf = Integer.parseInt(cf);

                if (fcf == 2) {
                    tbl1.setValueAt(true, c, 1);
                } else if (fcf == 1) {
                    tbl1.setValueAt(false, c, 1);
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void addnow() {
        int r = tbl1.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                String hid = tbl1.getValueAt(c, 0).toString();
                String ischeck = tbl1.getValueAt(c, 1).toString();
                String m = tbl1.getValueAt(c, 5).toString();
                String d = tbl1.getValueAt(c, 6).toString();

                if (!"false".equals(ischeck)) {
                    setHoliday(Integer.parseInt(hid), Integer.parseInt(m), Integer.parseInt(d));
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void removeNow() {
        int r = tbl2.getRowCount();
        int c = 0;


        while (c < r + 1) {
            try {
                String hid = tbl2.getValueAt(c, 1).toString();
                String ischeck = tbl2.getValueAt(c, 0).toString();

                if (!"false".equals(ischeck)) {
                    removeFromYearHoliday(Integer.parseInt(hid));
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void editDateValue() {
        int col = 1; //set column value to 0
        int row = tbl2.getSelectedRow(); //get value of selected value
        String id = tbl2.getValueAt(row, col).toString();
        String cf = tbl2.getValueAt(row, 4).toString();
        //System.out.println(cf);
        if (cf.equals("<html><font color=orange>Changeable</font></html>")) {
            year_holiday_edit.yhid = Integer.parseInt(id);
            year_holiday_edit.yrsetup = yr.getYear();
            ShowFrmEdit();
        } else {
            JOptionPane.showMessageDialog(this, "Date value of this holiday is constant!");
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
                    editDateValue();
                } else if (flg == 2) {
//                    int col = 0; //set column value to 0
//                    int row = tbl.getSelectedRow(); //get value of selected value
//                    String id = tbl.getValueAt(row, col).toString();
//                    ledger_payments_unpaid.stid = Integer.parseInt(id);
//                    ShowFrmPayments();
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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/editm.png"));
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

    class CheckBoxHeader extends JCheckBox
            implements TableCellRenderer, MouseListener {

        protected int column;

        public CheckBoxHeader(ItemListener itemListener) {
            rendererComponent = this;
            rendererComponent.addItemListener(itemListener);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    rendererComponent.setForeground(header.getForeground());
                    rendererComponent.setBackground(header.getBackground());
                    rendererComponent.setFont(header.getFont());


                    header.addMouseListener(rendererComponent);
                }
            }
            setColumn(column);
            rendererComponent.setText("Check All");
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return rendererComponent;
        }

        protected void setColumn(int column) {
            this.column = column;
        }

        public int getColumn() {
            return column;
        }

        protected void handleClickEvent(MouseEvent e) {

            if (mousePressed) {
                mousePressed = false;
                JTableHeader header = (JTableHeader) (e.getSource());
                JTable tableView = header.getTable();
                TableColumnModel columnModel = tableView.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tableView.convertColumnIndexToModel(viewColumn);

                if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                    doClick();
                }
            }
        }

        public void mouseClicked(MouseEvent e) {
            handleClickEvent(e);
            ((JTableHeader) e.getSource()).repaint();
        }

        public void mousePressed(MouseEvent e) {
            mousePressed = true;
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        optchangeable = new javax.swing.JRadioButton();
        optall = new javax.swing.JRadioButton();
        optcustom = new javax.swing.JRadioButton();
        optconstant = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        yr = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        cmdcancel = new javax.swing.JButton();
        cmdaddnew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        cmdexit = new javax.swing.JButton();
        cmdexit1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Year Holidays Setup");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/holidays.png"))); // NOI18N
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        buttonGroup1.add(optchangeable);
        optchangeable.setMnemonic('h');
        optchangeable.setText("Changeable");
        optchangeable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optchangeableActionPerformed(evt);
            }
        });

        buttonGroup1.add(optall);
        optall.setMnemonic('A');
        optall.setSelected(true);
        optall.setText("All");
        optall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optallActionPerformed(evt);
            }
        });

        buttonGroup1.add(optcustom);
        optcustom.setMnemonic('m');
        optcustom.setText("Custom");
        optcustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optcustomActionPerformed(evt);
            }
        });

        buttonGroup1.add(optconstant);
        optconstant.setMnemonic('o');
        optconstant.setText("Constant");
        optconstant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optconstantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optall)
                .addGap(18, 18, 18)
                .addComponent(optconstant)
                .addGap(10, 10, 10)
                .addComponent(optchangeable)
                .addGap(18, 18, 18)
                .addComponent(optcustom)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optall)
                    .addComponent(optconstant)
                    .addComponent(optchangeable)
                    .addComponent(optcustom))
                .addContainerGap())
        );

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

        jLabel2.setText("Select Year:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yr, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setDisplayedMnemonic('L');
        jLabel3.setText("List of available holiday/s");

        jLabel1.setDisplayedMnemonic('L');
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("List of holiday/s in this particular year");

        jLabel4.setDisplayedMnemonic('L');
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setText("NOTE: You can only edit holidays with changeable date value. ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(790, 790, 790))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(220, 220, 220))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "", "Holiday Description", "Date", "type", "m", "d"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);
        tbl1.getColumnModel().getColumn(1).setMaxWidth(30);
        tbl1.getColumnModel().getColumn(2).setPreferredWidth(130);

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/outtt.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmdaddnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"))); // NOI18N
        cmdaddnew.setMnemonic('S');
        cmdaddnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddnewActionPerformed(evt);
            }
        });

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "id", "Holiday Description", "Date", "Type", "Date Value", "Edit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl2);
        tbl2.getColumnModel().getColumn(0).setMaxWidth(20);
        tbl2.getColumnModel().getColumn(2).setPreferredWidth(130);
        tbl2.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbl2.getColumnModel().getColumn(6).setMaxWidth(50);

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        cmdexit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        cmdexit1.setMnemonic('x');
        cmdexit1.setText("Refresh");
        cmdexit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdaddnew)
                            .addComponent(cmdcancel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdexit1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(cmdaddnew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdexit1)
                    .addComponent(cmdexit, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdaddnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddnewActionPerformed
        int hasFoundCheckItems = isCheckItemFound();
        if (hasFoundCheckItems == 0) {
            JOptionPane.showMessageDialog(this, "No check items found!");
        } else {
            addnow();
            populateTBL1();
            populateTBL2();
        }
    }//GEN-LAST:event_cmdaddnewActionPerformed

    void setHoliday(int hid, int mm, int dd) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holiday.setYear(yr.getYear());
        holiday.setHolidayId(hid);
        String yhdate = yr.getYear() + "-" + mm + "-" + dd;

        Date fdate = null;
        try {
            fdate = dateFormat.parse(yhdate);
        } catch (ParseException ex) {
            Logger.getLogger(year_holiday_setup.class.getName()).log(Level.SEVERE, null, ex);
        }

        holiday.setHolidayDate(fdate);


        holiday.setTransDate(myFunction.getDatewTymDateFormat());
        holiday.setUserId(mdi.getUserID());

        if (holidayc.addyHoliday(holiday) > 0) {
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save");
        }

    }

    void removeFromYearHoliday(int yhid) {


        holiday.setYearHolidayId(yhid);

        if (holidayc.deleteyHoliday(holiday) > 0) {
        } else {
            JOptionPane.showMessageDialog(null, "Failed to Removed!");
        }



    }

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        int hasFoundCheckItems = isCheckItemFound2();
        if (hasFoundCheckItems == 0) {
            JOptionPane.showMessageDialog(this, "No check items found!");
        } else {
        int i = myFunction.msgboxYesNo("Are you sure you want to remove this holiday from this particular year holiday setup?");
        if (i == 0) {
            removeNow();
            populateTBL1();
            populateTBL2();
            JOptionPane.showMessageDialog(null, "Holiday Successfully Removed!");
        } else if (i == 1) {
        } else if (i == 2) {
            this.dispose(); //exit window
        }
        }
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdexit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexit1ActionPerformed
        //populateTBL1();
    }//GEN-LAST:event_cmdexit1ActionPerformed

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
        try {
            populateTBL1();
            populateTBL2();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_yrPropertyChange

    private void yrAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorMoved
    }//GEN-LAST:event_yrAncestorMoved

    private void yrAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yrAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yrAncestorAdded

    private void yrVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yrVetoableChange
    }//GEN-LAST:event_yrVetoableChange

    private void optcustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optcustomActionPerformed
        selectCustom();
    }//GEN-LAST:event_optcustomActionPerformed

    private void optallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optallActionPerformed
        selectAll();
    }//GEN-LAST:event_optallActionPerformed

    private void optconstantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optconstantActionPerformed
        selectConstant();
    }//GEN-LAST:event_optconstantActionPerformed

    private void optchangeableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optchangeableActionPerformed
        selectChangeable();
    }//GEN-LAST:event_optchangeableActionPerformed

    private void formAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorMoved
        populateTBL1();
        populateTBL2();
    }//GEN-LAST:event_formAncestorMoved

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        optcustom.setSelected(true);
    }//GEN-LAST:event_tbl1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdaddnew;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdexit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton optall;
    private javax.swing.JRadioButton optchangeable;
    private javax.swing.JRadioButton optconstant;
    private javax.swing.JRadioButton optcustom;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private com.toedter.calendar.JYearChooser yr;
    // End of variables declaration//GEN-END:variables
}
