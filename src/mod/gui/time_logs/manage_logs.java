package mod.gui.time_logs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.PayrollPeriod;
import mod.model.controller.PayrollPeriodC;
import mod.others.myFunction;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class manage_logs extends javax.swing.JInternalFrame {

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellEnableRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2;
    static Statement stmt, stmt1, stmt2, stmt3, stmt4;
    PayrollPeriod payper = new PayrollPeriod();
    PayrollPeriodC payperc = new PayrollPeriodC();
    public static int ppid, empid, flg;
    public static String nym;
    public static String in1start, in1end, out1start, out1end, in2start, in2end, out2start, out2end;
    public static String in1, out1, in2, out2;
    ActionListener taskperformer;
    Timer tmr, tmr2, tmr3;
    public static view_logs frmlogs;
    public static edit_tlogs frmedit;
    Date din1;

    public manage_logs() {
        initComponents();
        
        getRootPane().setDefaultButton(cmdsearch);
        

        TableColumn idClmn2 = tbl2.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);

        TableColumn idClmn3 = tbl.getColumn("date");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);

        TableColumn idClmn4 = tbl.getColumn("in1id");
        idClmn4.setMaxWidth(0);
        idClmn4.setMinWidth(0);
        idClmn4.setPreferredWidth(0);

        TableColumn idClmn5 = tbl.getColumn("out1id");
        idClmn5.setMaxWidth(0);
        idClmn5.setMinWidth(0);
        idClmn5.setPreferredWidth(0);

        TableColumn idClmn6 = tbl.getColumn("in2id");
        idClmn6.setMaxWidth(0);
        idClmn6.setMinWidth(0);
        idClmn6.setPreferredWidth(0);

        TableColumn idClmn7 = tbl.getColumn("out2id");
        idClmn7.setMaxWidth(0);
        idClmn7.setMinWidth(0);
        idClmn7.setPreferredWidth(0);



        setComboPPModel();
        model = (DefaultTableModel) tbl.getModel();
        
        
    }

    void setComboPPModel() {
        cmbpp.setModel(new PayrollPeriodC().getPayPerComboModel(yr2.getYear()));
    }

    public void ShowFrmView() {
        frmlogs = new view_logs(this, true);
        frmlogs.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmedit = new edit_tlogs(this, true);
        frmedit.setVisible(true);
    }

    void MakeNormal() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tbl2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tbl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    void MakeLoading() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tbl2.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tbl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void go() {
        if ("--SELECT--".equals(cmbpp.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Please select a period!");
        } else {
            if (flg == 1) {
                JOptionPane.showMessageDialog(this, "Previous task is still in progress!, Please wait!");
            } else {
                try{
                model.setNumRows(0);
                flg = 1;
                MakeLoading();
                tbl2.setRowSelectionAllowed(false);
                tbl2.setColumnSelectionAllowed(false);

                lblcap.setText("Loading all time logs..! This may take a few seconds! please wait!");
                model.setNumRows(0);
                load();
                }catch(Exception e){
                    
                }
            }
        }

    }

    void load() {
        taskperformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String lblc = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr>"
                        + "<td>" + nym + "<font></td>"
                        + "</th>";
                lbl.setText(lblc);
                populateDateRange();
                populateDTR();
                populateTLogs(in1start, in1end, 3, 4, 1);
                populateTLogs(out1start, out1end, 6, 7, 2);
                populateTLogs(in2start, in2end, 9, 10, 3);
                populateTLogs(out2start, out2end, 12, 13, 4);
                MakeNormal();
                lblcap.setText("Done!");
                flg = 0;
                tbl2.setRowSelectionAllowed(true);
                tbl2.setColumnSelectionAllowed(true);
                tmr2.stop();
            }
        };

        tmr2 = new Timer(1000, taskperformer);
        tmr2.start();
    }

    void populateDTR() {
        GetShiftRanges(empid);
    }

    void GetShiftRanges(int empid) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM employee_shift e INNER JOIN shift s ON e.shift_id=s.shift_id "
                + " WHERE  e.emp_id=" + empid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                in1start = rs.getString("in1_log_start");
                in1end = rs.getString("in1_log_end");
                out1start = rs.getString("out1_log_start");
                out1end = rs.getString("out1_log_end");
                in2start = rs.getString("in2_log_start");
                in2end = rs.getString("in2_log_end");
                out2start = rs.getString("out2_log_start");
                out2end = rs.getString("out2_log_end");

                SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

                in1 = dateFormat.format(rs.getTime("in_time1"));
                out1 = dateFormat.format(rs.getTime("out_time1"));
                in2 = dateFormat.format(rs.getTime("in_time2"));
                out2 = dateFormat.format(rs.getTime("out_time2"));

            }
            stmt.close();
            conn.close();


            //System.out.println(in1start+" to "+in1end +"//"+out1start+" to "+out1end);
            //System.out.println(in2start+" to "+in2end +"//"+out2start+" to "+out2end);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void populateTLogs(String t1, String t2, int rwid, int rwv, int flg) {
        //System.out.println(empid);
        int r = tbl.getRowCount();
        int c;
        c = 0;
        //System.out.println(in1start + "-" + in1end);
        while (c < r + 1) {
            try {
                String ddte = tbl.getValueAt(c, 0).toString();
                //IN1
                Connection conn = myDBConn.getConnection();
                String createString;
                createString = "SELECT time_log_id, MIN(time(log_date_time)) FROM time_log where time(log_date_time) between time('" + t1 + "') and time('" + t2 + "')"
                        + " AND DATE(log_date_time) = '" + ddte + "' AND emp_id= " + empid;
                try {
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(createString);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
                    String trange = null;

                    while (rs.next()) {

                        tbl.setValueAt(rs.getInt(1), c, rwid);

                        String ds1 = dateFormat.format(rs.getTime(2));

                        int diff = 0;
                        String clor = null;
                        switch (flg) {
                            case 1:
                                diff = myFunction.getMinuteDiff(in1, ds1);
                                if (diff <= 0) {
                                    clor = "black";
                                } else {
                                    clor = "red";
                                }
                                break;
                            case 2:
                                diff = myFunction.getMinuteDiff(out1, ds1);
                                if (diff >= 0) {
                                    clor = "black";
                                } else {
                                    clor = "red";
                                }
                                break;
                            case 3:
                                diff = myFunction.getMinuteDiff(in2, ds1);
                                if (diff <= 0) {
                                    clor = "black";
                                } else {
                                    clor = "red";
                                }
                                break;
                            case 4:
                                diff = myFunction.getMinuteDiff(out2, ds1);
                                if (diff >= 0) {
                                    clor = "black";
                                } else {
                                    clor = "red";
                                }
                                break;
                        }

                        String lblin1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr>"
                                + "<td><b><font color=" + clor + ">" + ds1 + "</b><font></td>"
                                + "</th>";

                        tbl.setValueAt(lblin1, c, rwv);
                    }
                    stmt.close();
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                //END IN1
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
    }

    public void insertIntoTBL(String dte, String m, String d) {
        try {


            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            cellAlignRightRenderer.setEnabled(iconable);

            tbl.setRowHeight(28);
            tbl.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tbl.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
            tbl.getColumnModel().getColumn(4).setCellRenderer(cellAlignCenterRenderer);
            tbl.getColumnModel().getColumn(7).setCellRenderer(cellAlignCenterRenderer);
            tbl.getColumnModel().getColumn(10).setCellRenderer(cellAlignCenterRenderer);
            tbl.getColumnModel().getColumn(13).setCellRenderer(cellAlignCenterRenderer);

            tbl.getColumn("EI1").setCellRenderer(new ButtonRenderer2(1));
            tbl.getColumn("EI1").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tbl.getColumn("EO1").setCellRenderer(new ButtonRenderer2(1));
            tbl.getColumn("EO1").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tbl.getColumn("EI2").setCellRenderer(new ButtonRenderer2(1));
            tbl.getColumn("EI2").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tbl.getColumn("EO2").setCellRenderer(new ButtonRenderer2(1));
            tbl.getColumn("EO2").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));
            tbl.getColumn("TL").setCellRenderer(new ButtonRenderer3(1));
            tbl.getColumn("TL").setCellEditor(new ButtonEditor3(new JCheckBox(), 1));

            tbl.setColumnSelectionAllowed(false);

            

            model.addRow(new Object[]{dte, m, d, "", "", "EI1", "", "", "EO1", "", "", "EI2", "", "", "EO2",""});
        } catch (Exception e) {
        }

    }

    void populateDateRange() {
        Date start = payperc.GetStartDate(ppid);
        Date end = payperc.GetEndDate(ppid);

        LocalDate dateStart = new LocalDate(start);
        LocalDate dateEnd = new LocalDate(end);

        dateStart = dateStart.minusDays(1);
        dateEnd = dateEnd.minusDays(1);

        while (dateStart.compareTo(dateEnd) != 1) {

            dateStart = dateStart.plusDays(1);
            DateTimeFormatter fmt1 = DateTimeFormat.forPattern("EEE");
            String dd = fmt1.print(dateStart);

            DateTimeFormatter fmt2 = DateTimeFormat.forPattern("dd");
            String dte = fmt2.print(dateStart);

            DateTimeFormatter fmt3 = DateTimeFormat.forPattern("yyyy-MM-dd");
            String f = fmt3.print(dateStart);

            DateTimeFormatter fmt4 = DateTimeFormat.forPattern("MMM");
            String m = fmt4.print(dateStart);

            String lbl1 = null;
            if ("Sat".equals(dd)) {
                lbl1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td font color=blue>" + dte + " " + dd + "<font></td></th>";
            } else if ("Sun".equals(dd)) {
                lbl1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td font color=red>" + dte + " " + dd + "<font></td></th>";
            } else {
                lbl1 = dte + " " + dd;
            }

            String lbl2 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                    + "<tr><td font color=green>" + m + "<font></td></th>";

            insertIntoTBL(f, lbl2, lbl1);
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
            tbl2.getColumn("View").setCellRenderer(new ButtonRenderer(1));
            tbl2.getColumn("View").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
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
                    String n = tbl2.getValueAt(row, 1).toString();
                    empid = Integer.parseInt(id);
                    nym = n;

                    go();
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
                    //int col = 0; //set column value to 0
                    int row = tbl.getSelectedRow(); //get value of selected value
                    String logid = null, tl = null, tldate = null;
                    switch (label) {
                        case "EI1":
                            edit_tlogs.tlflg=1;
                            logid = tbl.getValueAt(row, 3).toString();
                            tl = tbl.getValueAt(row, 4).toString();
                            break;
                        case "EO1":
                            edit_tlogs.tlflg=2;
                            logid = tbl.getValueAt(row, 6).toString();
                            tl = tbl.getValueAt(row, 7).toString();
                            break;
                        case "EI2":
                             edit_tlogs.tlflg=3;
                            logid = tbl.getValueAt(row, 9).toString();
                            tl = tbl.getValueAt(row, 10).toString();
                            break;
                        case "EO2":
                             edit_tlogs.tlflg=4;
                            logid = tbl.getValueAt(row, 12).toString();
                            tl = tbl.getValueAt(row, 13).toString();
                            break;
                    }
                    int intlogid = 0;

                    if (logid == null) {
                        intlogid = 0;

                    } else {
                        intlogid = Integer.parseInt(logid);
                    }


                    tldate = tbl.getValueAt(row, 0).toString();
                    edit_tlogs.ddate = tldate;
                    edit_tlogs.empid=empid;
                    //.out.println(tldate);

                    edit_tlogs.logid = intlogid;
                    ShowFrmEdit();
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

    class ButtonEditor3 extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        int flg;

        public ButtonEditor3(JCheckBox checkBox, int x) {
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
                    int row = tbl.getSelectedRow(); //get value of selected value
                    String ddate = tbl.getValueAt(row, col).toString();
                    view_logs.ddate = ddate;
                    view_logs.empid = empid;
                    //System.err.println(ddate +"-" +empid);
                    ShowFrmView();
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

    class ButtonRenderer3 extends JButton implements TableCellRenderer {

        int iflg;

        public ButtonRenderer3(int ico) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cmbpp = new javax.swing.JComboBox();
        yr2 = new com.toedter.calendar.JYearChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        lblcap = new javax.swing.JLabel();
        cmdsave = new javax.swing.JButton();
        cmdsave1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Time Log Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/downloaded.png"))); // NOI18N

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
        jLabel49.setText("(Click to Edit)");

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "View"
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
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmdsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Year:");

        jLabel4.setText("Payroll Period:");

        cmbpp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbppActionPerformed(evt);
            }
        });

        yr2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        yr2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yr2MouseClicked(evt);
            }
        });
        yr2.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                yr2AncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        yr2.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                yr2HierarchyChanged(evt);
            }
        });
        yr2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yr2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yr2InputMethodTextChanged(evt);
            }
        });
        yr2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yr2PropertyChange(evt);
            }
        });
        yr2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                yr2AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                yr2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        yr2.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                yr2VetoableChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(yr2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
            .addComponent(cmbpp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(yr2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(cmbpp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "date", "Month", "Day", "in1id", "IN1 ", "EI1", "out1id", "OUT1", "EO1", "in2id", "IN2", "EI2", "out2id", "OUT2", "EO2", "NOTIFICATION", "TL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, true, false, false, true, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl);
        tbl.getColumnModel().getColumn(0).setResizable(false);
        tbl.getColumnModel().getColumn(1).setResizable(false);
        tbl.getColumnModel().getColumn(2).setResizable(false);
        tbl.getColumnModel().getColumn(3).setResizable(false);
        tbl.getColumnModel().getColumn(4).setResizable(false);
        tbl.getColumnModel().getColumn(5).setResizable(false);
        tbl.getColumnModel().getColumn(6).setResizable(false);
        tbl.getColumnModel().getColumn(7).setResizable(false);
        tbl.getColumnModel().getColumn(8).setResizable(false);
        tbl.getColumnModel().getColumn(9).setResizable(false);
        tbl.getColumnModel().getColumn(10).setResizable(false);
        tbl.getColumnModel().getColumn(11).setResizable(false);
        tbl.getColumnModel().getColumn(12).setResizable(false);
        tbl.getColumnModel().getColumn(13).setResizable(false);
        tbl.getColumnModel().getColumn(14).setResizable(false);
        tbl.getColumnModel().getColumn(15).setResizable(false);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(10).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(13).setPreferredWidth(150);

        tbl.getColumnModel().getColumn(15).setPreferredWidth(200);
        tbl.getColumnModel().getColumn(5).setMaxWidth(70);
        tbl.getColumnModel().getColumn(8).setMaxWidth(70);
        tbl.getColumnModel().getColumn(11).setMaxWidth(70);
        tbl.getColumnModel().getColumn(14).setMaxWidth(70);

        jLabel3.setDisplayedMnemonic('L');
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DAILY TIME RECORD MANAGEMENT");

        lbl.setDisplayedMnemonic('L');
        lbl.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lbl.setForeground(new java.awt.Color(0, 0, 204));
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblcap.setForeground(new java.awt.Color(0, 0, 204));

        cmdsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdsave.setMnemonic('A');
        cmdsave.setText("Save Changes");
        cmdsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsaveActionPerformed(evt);
            }
        });

        cmdsave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        cmdsave1.setMnemonic('A');
        cmdsave1.setText("Print");
        cmdsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdsave1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdsave, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdsave)
                        .addComponent(cmdsave1))
                    .addComponent(lblcap, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    private void cmbppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbppActionPerformed
        try {
            ppid = ((PayrollPeriod) cmbpp.getSelectedItem()).getPayrollPeriodId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbppActionPerformed

    private void yr2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yr2MouseClicked
   }//GEN-LAST:event_yr2MouseClicked

    private void yr2AncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr2AncestorMoved1
        setComboPPModel();
    }//GEN-LAST:event_yr2AncestorMoved1

    private void yr2HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr2HierarchyChanged
   }//GEN-LAST:event_yr2HierarchyChanged

    private void yr2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr2CaretPositionChanged
   }//GEN-LAST:event_yr2CaretPositionChanged

    private void yr2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr2InputMethodTextChanged
   }//GEN-LAST:event_yr2InputMethodTextChanged

    private void yr2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yr2PropertyChange
        setComboPPModel();
    }//GEN-LAST:event_yr2PropertyChange

    private void yr2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr2AncestorMoved
   }//GEN-LAST:event_yr2AncestorMoved

    private void yr2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yr2AncestorAdded

    private void yr2VetoableChange(java.beans.PropertyChangeEvent evt) throws java.beans.PropertyVetoException {//GEN-FIRST:event_yr2VetoableChange
   }//GEN-LAST:event_yr2VetoableChange

    private void cmdsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsaveActionPerformed

    }//GEN-LAST:event_cmdsaveActionPerformed

    private void cmdsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsave1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdsave1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbpp;
    private javax.swing.JButton cmdsave;
    private javax.swing.JButton cmdsave1;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblcap;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbl2;
    private javax.swing.JTextField txtsearch;
    private com.toedter.calendar.JYearChooser yr2;
    // End of variables declaration//GEN-END:variables
}
