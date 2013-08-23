package mod.gui.employee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import static mod.gui.employee.employee_edit.getPicPath;
import mod.model.EmpLeaveDetails;
import mod.model.Employee;
import mod.model.EmployeeShift;
import mod.model.LeaveApp;
import mod.model.LeaveType;
import mod.model.PayrollPeriod;
import mod.model.Shift;
import mod.model.controller.DeptC;
import mod.model.controller.DivC;
import mod.model.controller.EmpLeaveDetailsC;
import mod.model.controller.EmployeeC;
import mod.model.controller.EmployeeShiftC;
import mod.model.controller.LeaveAppC;
import mod.model.controller.LeaveTypeC;
import mod.model.controller.PayrollPeriodC;
import mod.model.controller.SectionC;
import mod.model.controller.ShiftC;
import mod.others.myFunction;
import mod.others.myReports;
//import mod.others.myReports;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class account_management extends javax.swing.JInternalFrame {

    static Statement stmt;
    public static int ppid;
    static String nowDate = myFunction.getDate();
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellRowColorRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellEnableRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model8, model19, model20, model21;
    static String sid;
    static int empid, raid, aid, deptid, secid, divid, levelid, ltid, month;
    Employee emp = new Employee();
    EmployeeC empc = new EmployeeC();
    SectionC secc = new SectionC();
    DivC divc = new DivC();
    DeptC deptc = new DeptC();
    LeaveApp leaveapp = new LeaveApp();
    LeaveAppC leaveappc = new LeaveAppC();
    EmpLeaveDetails eld = new EmpLeaveDetails();
    EmpLeaveDetailsC eldc = new EmpLeaveDetailsC();
    EmployeeShift es = new EmployeeShift();
    EmployeeShiftC esc = new EmployeeShiftC();
    Shift shift = new Shift();
    ShiftC shiftc = new ShiftC();
    public static leave_summary frmSummary;
    public static leaveapp_deny frmDeny;
    public static leave_denied_remarks frmRemarksLeave;

    public account_management() {
        initComponents();

        setalltab();
        setlogoff();
        getRootPane().setDefaultButton(cmdlogin);
        setComboPPModel();
        setComboEmpTypeModel();


        setdates();
        calculateDays();

        TableColumn idClmn2 = tbldaycalc.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);

        TableColumn idClmn3 = tblleavecredits.getColumn("id");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);



        tblleavepending.setCellSelectionEnabled(false);
        tblleavepending.setRowSelectionAllowed(true);
        tblleavepending.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        populateCMB();
    }

    public void populateLeaveCredits() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT e.elc_id, lt.leave_type_desc ,e.leave_credits, e.credits_used FROM emp_leave_credits e INNER JOIN leave_type lt ON e.leave_type_id=lt.leave_type_id WHERE emp_id=" + empid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tblleavecredits.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tblleavecredits.setRowHeight(28);
            tblleavecredits.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleavecredits.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);

            model.setNumRows(0);
            int cnt = 0;
            NumberFormat nf4 = new DecimalFormat("#,##0.00");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("elc_id"), rs.getString("leave_type_desc"), nf4.format(rs.getDouble("leave_credits")), nf4.format(rs.getDouble("credits_used")), ""});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void ShowFrmDeny() {
        frmDeny = new leaveapp_deny(this, true);
        frmDeny.setVisible(true);
    }

    public void ShowFrmRemarksLeave() {
        frmRemarksLeave = new leave_denied_remarks(this, true);
        frmRemarksLeave.setVisible(true);
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

    public void ShowFrmSummary() {
        frmSummary = new leave_summary(this, true);
        frmSummary.setVisible(true);
    }

    public void setPic() {
        captured.setIcon(new ImageIcon(getClass().getResource("/images/nophoto.jpg")));
        try {
            String path = getPicPath();
            File file = new File(path + empid + ".jpg");
            BufferedImage myImage = ImageIO.read(file);
            captured.setIcon(new ImageIcon(myImage));
            captured.revalidate();
        } catch (IOException ex) {
        }
    }

    public static String getPicPath() {
        String Str = mod.others.paths.getPicPathConfig();
        return Str;
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

    void resetNewLeaveApp() {
        cmblt.setSelectedIndex(0);
        cmbra.setSelectedIndex(0);
        cmba.setSelectedIndex(0);
        lblnd.setText("0.0");
        setdates();
        txtreason.setText("");
    }

    public final void populateCombRA() {
        //Populate Combo Area
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) cmbra.getModel();
        theModel.removeAllElements();
        cmbra.addItem("--SELECT--");

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT emp_id, CONCAT(last_name, ', ', first_name, ' ', COALESCE(middle_name,''),' ', COALESCE(suffix_name,'')), position_desc, gender "
                + "FROM employee e, `position` p, section s, division d "
                + "WHERE e.position_id=p.position_id AND e.sec_id=s.sec_id AND s.div_id=d.div_id AND p.pos_level_id>" + levelid + " and d.dept_id=" + deptid;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                String img = null;
                switch (rs.getString("gender")) {
                    case "M":
                        img = "male.png";
                        break;
                    case "F":
                        img = "female.png";
                        break;
                }

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/" + img) + "></td><td>" + rs.getString(2) + "<br>" + "<font color=orange>" + rs.getString(3) + "</font></td></th>";
                cmbra.addItem(new Item(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public final void populateCombA() {
        //Populate Combo Area
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) cmba.getModel();
        theModel.removeAllElements();
        cmba.addItem("--SELECT--");

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT emp_id, CONCAT(last_name, ', ', first_name, ' ', COALESCE(middle_name,''),' ', COALESCE(suffix_name,'')), position_desc, gender "
                + "FROM employee e, `position` p, section s, division d "
                + "WHERE e.position_id=p.position_id AND e.sec_id=s.sec_id AND s.div_id=d.div_id AND p.pos_level_id>3 ";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                String img = null;
                switch (rs.getString("gender")) {
                    case "M":
                        img = "male.png";
                        break;
                    case "F":
                        img = "female.png";
                        break;
                }

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/" + img) + "></td><td>" + rs.getString(2) + "<br>" + "<font color=orange>" + rs.getString(3) + "</font></td></th>";
                cmba.addItem(new Item1(rs.getInt(1), lbl));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.getStackTrace();
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

    class Item1 {

        private int id;
        private String description;

        public Item1(int id, String description) {
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

    int GetEmpLevelID() {
        int UserID = 0;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT p.pos_level_id FROM employee e INNER JOIN position p on e.position_id=p.position_id WHERE e.emp_id=" + empid;


        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                UserID = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return UserID;
    }

    void populateProfile() {
        String nym = empc.GetLname(empid) + ", " + empc.GetFname(empid) + " " + empc.GetMname(empid) + " " + empc.GetSuffix(empid);
        lblnym.setText(nym);
        this.setTitle("Employees Account Management: " + nym);


    }

    void calculateDays() {
        populateDateRange();
        calculateNow();
    }

    void calculateNow() {
        int r = tbldaycalc.getRowCount();
        int c;
        c = 0;
        //System.out.println("------");
        double cnt = 0;
        while (c < r + 1) {
            try {
                String isexclude = tbldaycalc.getValueAt(c, 3).toString();
                String ishalf = tbldaycalc.getValueAt(c, 4).toString();

                if ("false".equals(isexclude)) {
                    if ("true".equals(ishalf)) {
                        cnt = cnt + 0.5;
                    } else {
                        cnt = cnt + 1;
                    }
                } else {
                    tbldaycalc.setValueAt(false, c, 4);
                }



                //System.out.println(ishalf+"-"+isexclude);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;

        }
        lblnd.setText(String.valueOf(cnt));

    }

    void populateDateRange() {
        model = (DefaultTableModel) tbldaycalc.getModel();
        model.setNumRows(0);
        Date start = txtstartla.getDate();
        Date end = txtendla.getDate();

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

            DateTimeFormatter fmt5 = DateTimeFormat.forPattern("yyyy");
            String y = fmt5.print(dateStart);

            String lbl1 = null;

            String lbl2 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                    + "<tr><td font color=green><b>" + m + "</b></font></td>&nbsp<td>" + y + "</td></th>";

            if ("Sat".equals(dd)) {
//                lbl1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
//                        + "<tr><td font color=blue>" + dte + " " + dd + "<font></td></th>";
            } else if ("Sun".equals(dd)) {
//                lbl1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
//                        + "<tr><td font color=red>" + dte + " " + dd + "<font></td></th>";
            } else {
                lbl1 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td font color=orange><b>" + dte + "</b></font></td>&nbsp<td>" + dd + "</td></th>";
                insertIntoTBL(f, lbl2, lbl1);
            }
        }
    }

    public void insertIntoTBL(String dte, String m, String d) {
        try {
            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            cellAlignRightRenderer.setEnabled(iconable);

            tbldaycalc.setRowHeight(28);
            tbldaycalc.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
            //tbldaycalc.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);

            tbldaycalc.setColumnSelectionAllowed(false);

            model.addRow(new Object[]{dte, m, d, false, false});
        } catch (Exception e) {
        }

    }

    void setdates() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date theDate = null;
        try {
            theDate = sdf.parse(nowDate);
        } catch (ParseException e) {
        }
        //txtdate.setDateFormatString(nowDate);
        txtstartla.setDate(theDate);
        txtendla.setDate(theDate);
    }

    void setComboEmpTypeModel() {
        cmblt.setModel(new LeaveTypeC().getLeaveTypeComboModel());
    }

    void setComboPPModel() {
        cmbpp.setModel(new PayrollPeriodC().getPayPerComboModel(yr2.getYear()));
    }

    void setlogoff() {
        tab.setEnabledAt(0, true);
        tab.getTabComponentAt(0).setEnabled(true);
        tab.setEnabledAt(1, false);
        tab.getTabComponentAt(1).setEnabled(false);
        tab.setEnabledAt(2, false);
        tab.getTabComponentAt(2).setEnabled(false);
        tab.setEnabledAt(3, false);
        tab.getTabComponentAt(3).setEnabled(false);
        tab.setEnabledAt(4, false);
        tab.getTabComponentAt(4).setEnabled(false);
        tab.setEnabledAt(5, false);
        tab.getTabComponentAt(5).setEnabled(false);
        tab.setEnabledAt(6, false);
        tab.getTabComponentAt(6).setEnabled(false);
        tab.setEnabledAt(7, false);
        tab.getTabComponentAt(7).setEnabled(false);
        tab.setEnabledAt(8, false);
        tab.getTabComponentAt(8).setEnabled(false);
    }

    void setlogin() {
        tab.setEnabledAt(0, false);
        tab.getTabComponentAt(0).setEnabled(false);
        tab.setEnabledAt(1, true);
        tab.getTabComponentAt(1).setEnabled(true);
        tab.setEnabledAt(2, true);
        tab.getTabComponentAt(2).setEnabled(true);
        tab.setEnabledAt(3, true);
        tab.getTabComponentAt(3).setEnabled(true);
        tab.setEnabledAt(4, true);
        tab.getTabComponentAt(4).setEnabled(true);
        tab.setEnabledAt(5, true);
        tab.getTabComponentAt(5).setEnabled(true);
        tab.setEnabledAt(6, true);
        tab.getTabComponentAt(6).setEnabled(true);
        tab.setEnabledAt(7, true);
        tab.getTabComponentAt(7).setEnabled(true);
        tab.setEnabledAt(8, true);
        tab.getTabComponentAt(8).setEnabled(true);

    }

    void setalltab() {
        setTabLeave();
        setTabDTR();
        setTabProfile();
        setAv();
        setTabInquiry();
        setTabSecurity();
        setTabOvertime();
        setTabLeaveNew();
        setTabLeaveApproved();
        setTabLeaveDenied();
        setTabLeavePending();
        setTabLogoff();
        setTabLeaveCredits();
        setTabLApproval();
        setTabApproveOvertime();
        setTabApproveLeave();
        setTabApproved();
        setTabDenied();
        setTabApprovedApprovedOvertime();
        setTabApprovedApprovedLeaves();
        setTabApprovedApprovedOvertime1();
        setTabApprovedApprovedLeaves1();
    }

    void setAv() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/av.png") + ">&nbsp</td><td>Account Login<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(0, lbl);

    }

    void setTabLeave() {
        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/leaveapp3.png") + ">&nbsp</td><td>Leave Management<font></td></th>");

        lbl.setIconTextGap(5);
        tab.setTabComponentAt(3, lbl);

    }

    void setTabDTR() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/dtr.png") + ">&nbsp</td><td>Daily Time Record<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(2, lbl);

    }

    void setTabProfile() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/secset.png") + ">&nbsp</td><td>My Profile<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(1, lbl);

    }

    void setTabInquiry() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/myinquiry.png") + ">&nbsp</td><td>Inquiry<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(5, lbl);

    }

    void setTabSecurity() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/lockd.png") + ">&nbsp</td><td>Security Settings<font></td></th>");

        lbl.setIconTextGap(5);
        //lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tab.setTabComponentAt(6, lbl);

    }

    void setTabOvertime() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/ot.png") + ">&nbsp</td><td>Overtime Management<font></td></th>");

        lbl.setIconTextGap(5);

        tab.setTabComponentAt(4, lbl);

    }

    void setTabLeaveNew() {

        //LEAVE APP 
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/new1.png") + ">&nbsp</td><td>New&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<font></td></th>");
        lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        tableave.setTabComponentAt(0, lbl);


    }

    void setTabLeaveApproved() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/approved1.png") + ">&nbsp</td><td>Approved<font></td></th>");
        tableave.setTabComponentAt(1, lbl);

    }

    void setTabLeaveDenied() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/trash.png") + ">&nbsp</td><td>Denied&nbsp&nbsp&nbsp&nbsp<font></td></th>");
        tableave.setTabComponentAt(2, lbl);

    }

    void setTabLeavePending() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/pending1.png") + ">&nbsp</td><td>Pending&nbsp&nbsp<font></td></th>");
        tableave.setTabComponentAt(3, lbl);

    }

    void setTabLeaveCredits() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/ledger3.png") + ">&nbsp</td><td>Credits&nbsp&nbsp&nbsp&nbsp<font></td></th>");
        tableave.setTabComponentAt(4, lbl);

    }

    void setTabLogoff() {

        //MAIN
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/logoff2.png") + ">&nbsp</td><td>Log-Out<font></td></th>");
        tab.setTabComponentAt(8, lbl);

    }

    void setTabLApproval() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/employer.png") + ">&nbsp</td><td>Approval<font></td></th>");
        tab.setTabComponentAt(7, lbl);

    }

    void setTabApproveLeave() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/leaveapp3.png") + ">&nbsp</td><td>Leave&nbsp&nbsp&nbsp<font></td></th>");
        tabapprove.setTabComponentAt(0, lbl);

    }

    void setTabApproveOvertime() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/ot.png") + ">&nbsp</td><td>Overtime<font></td></th>");
        tabapprove.setTabComponentAt(1, lbl);

    }

    void setTabApproved() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/approved1.png") + ">&nbsp</td><td>Approved<font></td></th>");
        tabapprove.setTabComponentAt(2, lbl);

    }

    void setTabDenied() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/trash.png") + ">&nbsp</td><td>Denied&nbsp&nbsp<font></td></th>");
        tabapprove.setTabComponentAt(3, lbl);

    }

    void setTabApprovedApprovedLeaves() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/leaveapp3.png") + ">&nbsp</td><td>List of Approved Leaves<font></td></th>");
        tabapprovedleave.setTabComponentAt(0, lbl);

    }

    void setTabApprovedApprovedOvertime() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/ot.png") + ">&nbsp</td><td>List of Approved Overtime<font></td></th>");
        tabapprovedleave.setTabComponentAt(1, lbl);

    }

    void setTabApprovedApprovedLeaves1() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/leaveapp3.png") + ">&nbsp</td><td>List of Denied Leaves<font></td></th>");
        tabdenieds.setTabComponentAt(0, lbl);

    }

    void setTabApprovedApprovedOvertime1() {

        //LEAVE
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/ot.png") + ">&nbsp</td><td>List of Denied Overtime<font></td></th>");
        tabdenieds.setTabComponentAt(1, lbl);

    }

    boolean IsValidUser(String eid, String pcode) {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM employee WHERE emp_id='" + eid + "' AND pin_code='" + pcode + "'";

        int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                rc++;
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        if (rc > 0) {
            found = true;
        }

        return found;
    }

    public void populateTBLLeavePending() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app WHERE status_id=0 AND emp_id=" + empid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tblleavepending.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleavepending.setRowHeight(120);

            tblleavepending.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleavepending.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleavepending.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleavepending.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);


            tblleavepending.getColumn("Summary").setCellRenderer(new ButtonRenderer(1));
            tblleavepending.getColumn("Summary").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
            tblleavepending.getColumn("Print").setCellRenderer(new ButtonRenderer(2));
            tblleavepending.getColumn("Print").setCellEditor(new ButtonEditor(new JCheckBox(), 2));
            tblleavepending.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String ltypedec = GetLeaveTypeDesc(rs.getInt("leave_type_id"));
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td>Date Applied: </td>&nbsp<td>" + rs.getDate("trans_date") + "</td></tr>"
                        + "<tr font color=maroon><td>Inclusive Dates:</td>&nbsp<td >" + " From " + rs.getDate("start_date_time") + " to " + rs.getDate("end_date_time") + "</font></td></tr>"
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

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
                    int row = tblleavepending.getSelectedRow(); //get value of selected value
                    String id = tblleavepending.getValueAt(row, col).toString();

                    leave_summary.laid = Integer.parseInt(id);
                    ShowFrmSummary();

                } else if (flg == 2) {
                    int col = 0; //set column value to 0
                    int row = tblleavepending.getSelectedRow(); //get value of selected value
                    String id = tblleavepending.getValueAt(row, col).toString();

                    int cltid = leaveappc.GetLeaveTypeId(Integer.parseInt(id));
                    int ceid = leaveappc.GetEmpId(Integer.parseInt(id));
                    String ltypedesc = GetLeaveTypeDesc(cltid);
                    String deptdesc = GetEmpDept();

                    int shiftid = esc.GetShiftId(empid);
                    String shiftdesc = shiftc.getShiftDesc(shiftid);
                    try {


                        myReports.rptLeaveApp(ltypedesc, Integer.parseInt(id), deptdesc, shiftdesc, "", "", 5, 5);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(account_management.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(account_management.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/summary.png"));
            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"));

            if (iflg == 1) {
                setIcon(ico1);
            } else if (iflg == 2) {
                setIcon(ico2);
            }


            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    //----------------------------------------------------------------------------------------------
    public void populateTBLLeaveApproved() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE status_id=0 AND (r_approved_by=" + empid + " OR approved_by=" + empid + ")";
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
                repopulateapprovealready();
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
                        ShowFrmDeny();
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

    //-------------------------------------DENIED
    public void populateTBLLeaveDenied() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE MONTH(trans_date)=" + month + " AND YEAR(trans_date)=" + yr11.getYear() + "  AND status_id=1 AND (r_approved_by=" + empid + " OR approved_by=" + empid + ") ORDER BY trans_date DESC";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model8 = (DefaultTableModel) tblleavedenied.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleavedenied.setRowHeight(140);

            tblleavedenied.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleavedenied.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied.getColumn("Remarks").setCellRenderer(new ButtonRenderer3(1));
            tblleavedenied.getColumn("Remarks").setCellEditor(new ButtonEditor3(new JCheckBox(), 1));
            tblleavedenied.setColumnSelectionAllowed(false);

            model8.setNumRows(0);
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
                int status_id = rs.getInt("status_id");

                String rastat = null;
                String astat = null;

                if (status_id == 0) {
                    if (raflg == 0) {
                        rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                                + "</th>";
                    } else if (raflg == 1) {
                        rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=orange><td>" + "APPROVED" + "</td></tr>"
                                + "</th>";
                    }


                    if (aflg == 0) {
                        astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                                + "</th>";
                    } else if (aflg == 1) {
                        astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=orange><td>" + "APPROVED" + "</td></tr>"
                                + "</th>";
                    }
                } else {
                    rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=red><td>" + "DENIED" + "</td></tr>"
                            + "</th>";
                    astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=red><td>" + "DENIED" + "</td></tr>"
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
                model8.addRow(new Object[]{nf4.format(rs.getInt("leave_app_id")), lbl, lblra, lbla, ""});
                cnt++;
            }

            // lbl1.setText(cnt + " pending application/s ");

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
     //-------------------------------------DENIED
    public void populateTBLLeaveDenied1() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE status_id=1 AND l.emp_id="+empid+" AND YEAR(trans_date)=" + yr13.getYear() + " ORDER BY trans_date DESC";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model21 = (DefaultTableModel) tblleavedenied1.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleavedenied1.setRowHeight(140);

            tblleavedenied1.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied1.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleavedenied1.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied1.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tblleavedenied1.getColumn("Remarks").setCellRenderer(new ButtonRenderer4(1));
            tblleavedenied1.getColumn("Remarks").setCellEditor(new ButtonEditor4(new JCheckBox(), 1));
            tblleavedenied1.setColumnSelectionAllowed(false);

            model21.setNumRows(0);
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
                int status_id = rs.getInt("status_id");

                String rastat = null;
                String astat = null;

                if (status_id == 0) {
                    if (raflg == 0) {
                        rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                                + "</th>";
                    } else if (raflg == 1) {
                        rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=orange><td>" + "APPROVED" + "</td></tr>"
                                + "</th>";
                    }


                    if (aflg == 0) {
                        astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=maroon><td>" + "PENDING" + "</td></tr>"
                                + "</th>";
                    } else if (aflg == 1) {
                        astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                                + "<tr font color=orange><td>" + "APPROVED" + "</td></tr>"
                                + "</th>";
                    }
                } else {
                    rastat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=red><td>" + "DENIED" + "</td></tr>"
                            + "</th>";
                    astat = "<html><table border=0 cellpadding=0 cellspacing=0>"
                            + "<tr font color=red><td>" + "DENIED" + "</td></tr>"
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
                model21.addRow(new Object[]{nf4.format(rs.getInt("leave_app_id")), lbl, lblra, lbla, ""});
                cnt++;
            }

            // lbl1.setText(cnt + " pending application/s ");

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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
                    int row = tblleavedenied.getSelectedRow(); //get value of selected value
                    String id = tblleavedenied.getValueAt(row, col).toString();

                    leave_denied_remarks.laid = Integer.parseInt(id);
                    ShowFrmRemarksLeave();
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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/leaveapp.png"));
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

    class ButtonEditor4 extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        int flg;

        public ButtonEditor4(JCheckBox checkBox, int x) {
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
                    int row = tblleavedenied1.getSelectedRow(); //get value of selected value
                    String id = tblleavedenied1.getValueAt(row, col).toString();

                    leave_denied_remarks.laid = Integer.parseInt(id);
                    ShowFrmRemarksLeave();
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

    class ButtonRenderer4 extends JButton implements TableCellRenderer {

        int iflg;

        public ButtonRenderer4(int ico) {
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

            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/leaveapp.png"));
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

    
    //----------------------------------------------------------------------------------------------
    public void populateTBLLeaveApprovedAlready() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE status_id=2 AND (r_approved_by=" + empid + " OR approved_by=" + empid + ") ORDER BY trans_date DESC";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model20 = (DefaultTableModel) tblleaveapprovedleave.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleaveapprovedleave.setRowHeight(140);

            tblleaveapprovedleave.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleaveapprovedleave.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave.setColumnSelectionAllowed(false);

            model20.setNumRows(0);
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
                model20.addRow(new Object[]{nf4.format(rs.getInt("leave_app_id")), lbl, lblra, lbla, "", ""});
                cnt++;
            }

            //lbl1.setText(cnt + " pending application/s ");

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    //----------------------------------------------------------------------------------------------
    public void populateTBLLeaveApprovedAlreadyPerEmp() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM leave_app l INNER JOIN employee e ON l.emp_id=e.emp_id WHERE l.emp_id="+empid+" AND status_id=2 AND YEAR(l.trans_date)=" + yr12.getYear() + "";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model19 = (DefaultTableModel) tblleaveapprovedleave1.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);


            tblleaveapprovedleave1.setRowHeight(120);

            tblleaveapprovedleave1.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave1.getColumnModel().getColumn(1).setCellRenderer(cellRowColorRenderer);
            tblleaveapprovedleave1.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave1.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tblleaveapprovedleave1.setColumnSelectionAllowed(false);

            model19.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String ltypedec = GetLeaveTypeDesc(rs.getInt("leave_type_id"));
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td>Date Applied: </td>&nbsp<td>" + rs.getDate("trans_date") + "</td></tr>"
                        + "<tr font color=maroon><td>Inclusive Dates:</td>&nbsp<td >" + " From " + rs.getDate("start_date_time") + " to " + rs.getDate("end_date_time") + "</font></td></tr>"
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
                model19.addRow(new Object[]{nf4.format(rs.getInt("leave_app_id")), lbl, lblra, lbla, "", ""});
                cnt++;
            }

           // lbl1.setText(cnt + " pending application/s ");

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tab = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        d = new javax.swing.JDesktopPane();
        bck = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        cmdlogin = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();
        txtpincode = new javax.swing.JPasswordField();
        txteid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblnym = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        ldept = new javax.swing.JLabel();
        ldiv = new javax.swing.JLabel();
        lsec = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        captured = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        cmbpp = new javax.swing.JComboBox();
        yr2 = new com.toedter.calendar.JYearChooser();
        cmdsearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        tableave = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cmblt = new javax.swing.JComboBox();
        txtstartla = new com.toedter.calendar.JDateChooser();
        txtendla = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtreason = new javax.swing.JTextField();
        lblnd = new javax.swing.JLabel();
        cmdsubmitleave = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();
        cmbra = new javax.swing.JComboBox();
        cmba = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldaycalc = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel40 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblleaveapprovedleave1 = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        yr12 = new com.toedter.calendar.JYearChooser();
        jLabel36 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel38 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblleavedenied1 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        yr13 = new com.toedter.calendar.JYearChooser();
        jButton2 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel36 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblleavepending = new javax.swing.JTable();
        cmdcancelpendingleaveapp = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblleavecredits = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        tabapprove = new javax.swing.JTabbedPane();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jPanel44 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblleaveapprove = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel46 = new javax.swing.JPanel();
        tabapprovedleave = new javax.swing.JTabbedPane();
        jPanel47 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblleaveapprovedleave = new javax.swing.JTable();
        jPanel48 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        yr = new com.toedter.calendar.JYearChooser();
        cmbmonth = new javax.swing.JComboBox();
        cmdview1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel52 = new javax.swing.JPanel();
        tabdenieds = new javax.swing.JTabbedPane();
        jPanel53 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblleavedenied = new javax.swing.JTable();
        jPanel54 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        yr11 = new com.toedter.calendar.JYearChooser();
        cmbmonth2 = new javax.swing.JComboBox();
        cmdview2 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmdexit1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Employees Account Management");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acctmanage.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tab.setToolTipText("");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setLayout(new java.awt.GridBagLayout());

        cmdlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdlogin.setMnemonic('L');
        cmdlogin.setText("Login");
        cmdlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdloginActionPerformed(evt);
            }
        });

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Close");
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        txtpincode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtpincode.setForeground(new java.awt.Color(51, 51, 51));
        txtpincode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txteid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txteid.setForeground(new java.awt.Color(51, 51, 51));
        txteid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txteid.setFocusCycleRoot(true);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(cmdlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtpincode)
                        .addComponent(txteid, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txteid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpincode, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setText("PIN CODE:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("SID:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logopayroll3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)))
                    .addContainerGap(47, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(jLabel4)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2)))
                    .addContainerGap(16, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel16.add(jPanel13, gridBagConstraints);

        javax.swing.GroupLayout bckLayout = new javax.swing.GroupLayout(bck);
        bck.setLayout(bckLayout);
        bckLayout.setHorizontalGroup(
            bckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bckLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        bckLayout.setVerticalGroup(
            bckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bckLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        bck.setBounds(110, 70, 560, 310);
        d.add(bck, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(d, javax.swing.GroupLayout.DEFAULT_SIZE, 1227, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(d, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab.addTab("", jPanel11);

        jLabel22.setBackground(new java.awt.Color(102, 102, 102));
        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/personal.png"))); // NOI18N
        jLabel22.setText("PERSONAL INFORMATION");
        jLabel22.setOpaque(true);

        jLabel24.setForeground(new java.awt.Color(0, 102, 0));
        jLabel24.setText("Name of Employee");

        lblnym.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblnym.setText("Name of Employee");

        jLabel14.setText("Deparment:");

        jLabel18.setText("Division:");

        ldept.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        ldiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lsec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ldept, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ldiv, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lsec, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(ldept, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(ldiv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lsec, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
        );

        jLabel20.setText("Section:");

        jLabel23.setBackground(new java.awt.Color(102, 102, 102));
        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/employment.png"))); // NOI18N
        jLabel23.setText("EMPLOYMENT INFORMATION");
        jLabel23.setOpaque(true);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(lblnym, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(591, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(25, 25, 25)
                .addComponent(lblnym)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(19, 19, 19)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel32);

        captured.setForeground(new java.awt.Color(255, 102, 0));
        captured.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nophoto.jpg"))); // NOI18N
        captured.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        captured.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                capturedMouseReleased(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                capturedMousePressed(evt);
            }
        });
        captured.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                capturedMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                capturedMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab.addTab("", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));

        jLabel7.setText("Year:");

        jLabel8.setText("Payroll Period:");

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

        cmdsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdsearch.setMnemonic('R');
        cmdsearch.setText("View");
        cmdsearch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(yr2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(cmbpp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(cmdsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 187, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(yr2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(cmbpp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdsearch))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "date", "Month", "Day", "in1id", "IN1 ", "EI1", "out1id", "OUT1", "EO1", "in2id", "IN2", "EI2", "out2id", "OUT2", "EO2", "TL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, true, false, false, true, false, false, true, true
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
        tbl.getColumnModel().getColumn(0).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(90);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(10).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(13).setPreferredWidth(150);

        tbl.getColumnModel().getColumn(5).setMaxWidth(70);
        tbl.getColumnModel().getColumn(8).setMaxWidth(70);
        tbl.getColumnModel().getColumn(11).setMaxWidth(70);
        tbl.getColumnModel().getColumn(14).setMaxWidth(70);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(741, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addGap(496, 496, 496)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        tab.addTab("", jPanel8);

        jPanel29.setBackground(new java.awt.Color(153, 153, 153));

        tableave.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tableave.setAutoscrolls(true);

        jPanel25.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        cmblt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmblt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbltActionPerformed(evt);
            }
        });

        txtstartla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtstartla.setDateFormatString("MM/dd/yyyy");
        txtstartla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtstartlaPropertyChange(evt);
            }
        });

        txtendla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtendla.setDateFormatString("MM/dd/yyyy");
        txtendla.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txtendlaHierarchyChanged(evt);
            }
        });
        txtendla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtendlaPropertyChange(evt);
            }
        });
        txtendla.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtendlaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtendlaInputMethodTextChanged(evt);
            }
        });
        txtendla.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                txtendlaAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtendla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtendlaKeyReleased(evt);
            }
        });
        txtendla.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtendlaVetoableChange(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("FROM");

        jLabel11.setForeground(new java.awt.Color(255, 102, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("UNTIL");

        txtreason.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lblnd.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblnd.setForeground(new java.awt.Color(0, 102, 0));
        lblnd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnd.setText("0.0");
        lblnd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        cmdsubmitleave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/moveup.png"))); // NOI18N
        cmdsubmitleave.setMnemonic('S');
        cmdsubmitleave.setText("Submit");
        cmdsubmitleave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdsubmitleave.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdsubmitleave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsubmitleaveActionPerformed(evt);
            }
        });

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('R');
        cmdcancel.setText("Reset");
        cmdcancel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdcancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        cmbra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbraActionPerformed(evt);
            }
        });

        cmba.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmblt, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtstartla, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtendla, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblnd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtreason, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cmdsubmitleave, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmba, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(cmblt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtstartla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtendla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(7, 7, 7)
                .addComponent(lblnd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtreason, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbra, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsubmitleave)
                    .addComponent(cmdcancel))
                .addContainerGap())
        );

        jLabel13.setText("Reason:");

        jLabel12.setText("No. of Days:");

        jLabel9.setText("Date:");

        jLabel1.setText("Type of Leave:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("New Application for Leave");

        jLabel15.setForeground(new java.awt.Color(0, 102, 0));
        jLabel15.setText("Please check EXCLUDE if a particular date is not included and also");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel16.setForeground(new java.awt.Color(0, 102, 0));
        jLabel16.setText("check HALFDAY if the leave of that specific date is halfday!");

        tbldaycalc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "MMM-YEAR", "DAY", "EXCLUDE", "HALFDAY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbldaycalc.setColumnSelectionAllowed(true);
        tbldaycalc.getTableHeader().setReorderingAllowed(false);
        tbldaycalc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbldaycalcMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbldaycalc);
        tbldaycalc.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbldaycalc.getColumnModel().getColumn(3).setMaxWidth(90);
        tbldaycalc.getColumnModel().getColumn(4).setMaxWidth(90);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("No. of Days Calculator");

        jLabel27.setForeground(new java.awt.Color(255, 51, 0));
        jLabel27.setText("Note: Saturday and Sunday are not included.");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setText("Recommending Approval:");

        jLabel26.setText("Approved by:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(143, 143, 143))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel9)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel25);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        tableave.addTab("", jPanel24);

        jPanel27.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 0));
        jLabel30.setText("Approved Leave Application");

        tblleaveapprovedleave1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblleaveapprovedleave1.getTableHeader().setReorderingAllowed(false);
        jScrollPane20.setViewportView(tblleaveapprovedleave1);
        tblleaveapprovedleave1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleaveapprovedleave1.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleaveapprovedleave1.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleaveapprovedleave1.getColumnModel().getColumn(3).setPreferredWidth(200);

        yr12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        yr12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yr12MouseClicked(evt);
            }
        });
        yr12.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                yr12AncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        yr12.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                yr12HierarchyChanged(evt);
            }
        });
        yr12.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yr12CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yr12InputMethodTextChanged(evt);
            }
        });
        yr12.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yr12PropertyChange(evt);
            }
        });
        yr12.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                yr12AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                yr12AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        yr12.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                yr12VetoableChange(evt);
            }
        });

        jLabel36.setText("Year:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"))); // NOI18N
        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yr12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yr12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(jPanel40);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tableave.addTab("", jPanel26);

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("Denied Leave Application Request");

        tblleavedenied1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblleavedenied1.getTableHeader().setReorderingAllowed(false);
        jScrollPane21.setViewportView(tblleavedenied1);
        tblleavedenied1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleavedenied1.getColumnModel().getColumn(4).setHeaderValue("Remarks");
        tblleavedenied1.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleavedenied1.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleavedenied1.getColumnModel().getColumn(3).setPreferredWidth(200);

        jLabel37.setText("Year:");

        yr13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        yr13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yr13MouseClicked(evt);
            }
        });
        yr13.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                yr13AncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        yr13.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                yr13HierarchyChanged(evt);
            }
        });
        yr13.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                yr13CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                yr13InputMethodTextChanged(evt);
            }
        });
        yr13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yr13PropertyChange(evt);
            }
        });
        yr13.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                yr13AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                yr13AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        yr13.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                yr13VetoableChange(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"))); // NOI18N
        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yr13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yr13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel38);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        tableave.addTab("", jPanel28);

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 0, 0));
        jLabel28.setText("Pending Request for Leave Application");

        tblleavepending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Leave Details", "Recommending Approval by", "Approved by", "Summary", "Print"
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
        tblleavepending.setColumnSelectionAllowed(true);
        tblleavepending.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblleavepending);
        tblleavepending.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleavepending.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleavepending.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleavepending.getColumnModel().getColumn(3).setPreferredWidth(200);

        cmdcancelpendingleaveapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdcancelpendingleaveapp.setMnemonic('R');
        cmdcancelpendingleaveapp.setText("Cancel");
        cmdcancelpendingleaveapp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdcancelpendingleaveapp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdcancelpendingleaveapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelpendingleaveappActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdcancelpendingleaveapp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancelpendingleaveapp)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel36);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        tableave.addTab("", jPanel22);

        jPanel23.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel23.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Leave Credits ");

        tblleavecredits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Leave Type", "Credits", "Used"
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
        tblleavecredits.setColumnSelectionAllowed(true);
        tblleavecredits.getTableHeader().setReorderingAllowed(false);
        jScrollPane19.setViewportView(tblleavecredits);
        tblleavecredits.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel51.setText("List of leave type with employee's credit and credit used values.");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(531, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel51)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tableave.addTab("", jPanel23);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(tableave))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableave, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab.addTab("", jPanel10);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(700, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
        );

        tab.addTab("", jPanel15);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1227, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        tab.addTab("", jPanel18);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 912, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 401, Short.MAX_VALUE))
        );

        tab.addTab("", jPanel20);

        jPanel43.setBackground(new java.awt.Color(153, 153, 153));

        tabapprove.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabapprove.setAutoscrolls(true);
        tabapprove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabapproveMouseClicked(evt);
            }
        });
        tabapprove.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                tabapproveHierarchyChanged(evt);
            }
        });

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
        tblleaveapprove.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleaveapprove.getColumnModel().getColumn(4).setHeaderValue("Deny");
        tblleaveapprove.getColumnModel().getColumn(5).setHeaderValue("Approve");
        tblleaveapprove.getColumnModel().getColumn(1).setPreferredWidth(350);
        tblleaveapprove.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleaveapprove.getColumnModel().getColumn(3).setPreferredWidth(200);

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 0, 0));
        jLabel31.setText("Pending Request for Leave Application");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jScrollPane17.setViewportView(jPanel44);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        tabapprove.addTab("", jPanel55);

        jPanel57.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel57.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabapprove.addTab("", jPanel57);

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
        tblleaveapprovedleave.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleaveapprovedleave.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleaveapprovedleave.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleaveapprovedleave.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jScrollPane16.setViewportView(jPanel62);

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1352, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabapprovedleave.addTab("tab1", jPanel47);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1352, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        tabapprovedleave.addTab("tab2", jPanel48);

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

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabapprovedleave)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabapprovedleave, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane12.setViewportView(jPanel46);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        tabapprove.addTab("", jPanel45);

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
        tblleavedenied.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleavedenied.getColumnModel().getColumn(4).setHeaderValue("Remarks");
        tblleavedenied.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblleavedenied.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblleavedenied.getColumnModel().getColumn(3).setPreferredWidth(200);

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jScrollPane15.setViewportView(jPanel59);

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        tabdenieds.addTab("tab1", jPanel53);

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1352, Short.MAX_VALUE)
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        tabdenieds.addTab("tab2", jPanel54);

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
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbmonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yr11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(cmdview2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbmonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yr11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cmdview2)))
                .addContainerGap())
        );

        jLabel35.setText("Year:");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabdenieds)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabdenieds, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane13.setViewportView(jPanel52);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        tabapprove.addTab("", jPanel50);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(tabapprove))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabapprove, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        tab.addTab("", jPanel2);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Click to exit account");

        cmdexit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        cmdexit1.setMnemonic('x');
        cmdexit1.setText("Log-Out");
        cmdexit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdexit1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(767, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cmdexit1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 191, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 197, Short.MAX_VALUE))
        );

        tab.addTab("", jPanel41);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        bck.setBounds(0, 0, d.getWidth(), d.getHeight());
        bck.requestFocus();
        txteid.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdloginActionPerformed

        try {
            empid = 0;
            sid = "";
            secid = 0;
            levelid = 0;
            String eid = txteid.getText();
            int ieid = Integer.parseInt(eid);
            String pcode = txtpincode.getText();



            boolean val = IsValidUser(String.valueOf(ieid), pcode); //VALIDATE USER IF VALID

            if (val == false) {
                JOptionPane.showMessageDialog(this, "Access Denied! Account Verification Failed!");
                txteid.requestFocus();
            } else if (val == true) {
                empid = ieid;
                sid = empc.GetSecId(empid);
                secid = Integer.parseInt(sid);
                lsec.setText(secc.GetSecName(secid));
                divid = secc.GetDivID(secid);
                ldiv.setText(divc.GetDivName(divid));
                deptid = divc.GetDeptId(divid);
                ldept.setText(deptc.GetDeptName(deptid));
                levelid = GetEmpLevelID();
                // System.out.println(levelid);

                setPic();
                populateCombRA();
                populateCombA();

                populateProfile();
                
                populateLeaveCredits();

                populateTBLLeavePending();
                populateTBLLeaveApproved();


                txteid.setText("");
                txtpincode.setText("");
                cmdlogin.setEnabled(false);

                setlogin();
                tab.setSelectedIndex(1);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Access Denied! Account Verification Failed!");
            txteid.requestFocus();
        }
    }//GEN-LAST:event_cmdloginActionPerformed

    private void cmdexit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexit1ActionPerformed
        this.setTitle("Employees Account Management");
        setlogoff();
        tab.setSelectedIndex(0);
        txteid.requestFocus();
        cmdlogin.setEnabled(true);
    }//GEN-LAST:event_cmdexit1ActionPerformed

    private void capturedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseReleased
    }//GEN-LAST:event_capturedMouseReleased

    private void capturedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMousePressed
    }//GEN-LAST:event_capturedMousePressed

    private void capturedMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseDragged
    }//GEN-LAST:event_capturedMouseDragged

    private void capturedMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseMoved
    }//GEN-LAST:event_capturedMouseMoved

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

    private void yr2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yr2VetoableChange
    }//GEN-LAST:event_yr2VetoableChange

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
//        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    private void cmbltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbltActionPerformed
        ltid = 0;
        try {
            ltid = ((LeaveType) cmblt.getSelectedItem()).getLeaveTypeId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbltActionPerformed

    private void cmdsubmitleaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsubmitleaveActionPerformed
        String reason = txtreason.getText().replace("'", " ");
        //String abbreviationParam = txtabbreviation.getText().replace("'", " ");


        if (reason.isEmpty() == true || "--SELECT--".equals(cmblt.getSelectedItem().toString()) || "--SELECT--".equals(cmbra.getSelectedItem().toString()) || "--SELECT--".equals(cmba.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Please fill-up all the required fields!");
        } else {
            leaveapp.setEmpId(empid);
            leaveapp.setLeaveTypeId(Short.valueOf(String.valueOf(ltid)));
            leaveapp.setStartDateTime(txtstartla.getDate());
            leaveapp.setEndDateTime(txtendla.getDate());

            BigDecimal ldays = BigDecimal.valueOf(Double.parseDouble(lblnd.getText().replace(",", "")));
            leaveapp.setLeaveDays(ldays);
            leaveapp.setCreditsUsed(ldays);
            leaveapp.setTransDate(myFunction.getSystemNowDateTime());
            leaveapp.setReason(reason);
            leaveapp.setRApprovedBy(raid);
            leaveapp.setApprovedBy(aid);
            leaveapp.setStatusId(Short.valueOf("0"));

            int lastid = leaveappc.addLeaveApp(leaveapp);
            if (lastid > 0) {
                PersistAllDates(lastid);
                resetNewLeaveApp();
                populateTBLLeavePending();
                tableave.setSelectedIndex(3);
                JOptionPane.showMessageDialog(null, "Your New Leave Application is Successfully Submitted! Note: Please regularly check pending leave applications!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save");
            }
        }
    }//GEN-LAST:event_cmdsubmitleaveActionPerformed

    void PersistAllDates(int lappid) {

        int r = tbldaycalc.getRowCount();
        int c;
        c = 0;

        BigDecimal hrs = null;

        while (c < r + 1) {
            try {
                String isexclude = tbldaycalc.getValueAt(c, 3).toString();
                String ishalf = tbldaycalc.getValueAt(c, 4).toString();

                if ("false".equals(isexclude)) {
                    if ("true".equals(ishalf)) {
                        hrs = BigDecimal.valueOf(Double.parseDouble("4"));
                    } else {
                        hrs = BigDecimal.valueOf(Double.parseDouble("8"));
                    }
                }

                String dte = tbldaycalc.getValueAt(c, 0).toString();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date fdte = df.parse(dte);

                eld.setLeaveAppId(lappid);
                eld.setLeaveDate(fdte);
                eld.setLeaveHours(hrs);

                if (eldc.addEmpLeaveDetails(eld) > 0) {
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save");
                }

            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
    }

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void txtendlaAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtendlaAncestorMoved
    }//GEN-LAST:event_txtendlaAncestorMoved

    private void txtendlaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txtendlaHierarchyChanged
    }//GEN-LAST:event_txtendlaHierarchyChanged

    private void txtendlaVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtendlaVetoableChange
    }//GEN-LAST:event_txtendlaVetoableChange

    private void txtendlaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtendlaKeyReleased
    }//GEN-LAST:event_txtendlaKeyReleased

    private void txtendlaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtendlaInputMethodTextChanged
    }//GEN-LAST:event_txtendlaInputMethodTextChanged

    private void txtendlaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtendlaCaretPositionChanged
    }//GEN-LAST:event_txtendlaCaretPositionChanged

    private void txtendlaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtendlaPropertyChange
        calculateDays();
    }//GEN-LAST:event_txtendlaPropertyChange

    private void txtstartlaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtstartlaPropertyChange
        calculateDays();
    }//GEN-LAST:event_txtstartlaPropertyChange

    private void tbldaycalcMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldaycalcMouseReleased
        calculateNow();
    }//GEN-LAST:event_tbldaycalcMouseReleased

    private void cmbraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbraActionPerformed
        try {
            Item item = (Item) cmbra.getSelectedItem();
            raid = item.getId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbraActionPerformed

    private void cmbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbaActionPerformed
        try {
            Item1 item = (Item1) cmba.getSelectedItem();
            aid = item.getId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbaActionPerformed

    private void cmbmonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmonthActionPerformed
        try {
            Item item = (Item) cmbmonth.getSelectedItem();
            month = item.getId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbmonthActionPerformed

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
        try {
            Item5 item = (Item5) cmbmonth2.getSelectedItem();
            month = item.getId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbmonth2ActionPerformed

    private void cmdview2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdview2ActionPerformed
        populateTBLLeaveDenied();
    }//GEN-LAST:event_cmdview2ActionPerformed

    private void tabapproveHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tabapproveHierarchyChanged
    }//GEN-LAST:event_tabapproveHierarchyChanged

    private void tabapproveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabapproveMouseClicked
        if (tabapprove.getSelectedIndex() == 0) {
            populateTBLLeaveApproved();
        }
    }//GEN-LAST:event_tabapproveMouseClicked

    private void cmdview1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdview1ActionPerformed
        populateTBLLeaveApprovedAlready();
    }//GEN-LAST:event_cmdview1ActionPerformed

    private void cmdcancelpendingleaveappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelpendingleaveappActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdcancelpendingleaveappActionPerformed

    private void yr12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yr12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12MouseClicked

    private void yr12AncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr12AncestorMoved1
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12AncestorMoved1

    private void yr12HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr12HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12HierarchyChanged

    private void yr12InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr12InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12InputMethodTextChanged

    private void yr12CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr12CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12CaretPositionChanged

    private void yr12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yr12PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12PropertyChange

    private void yr12AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr12AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12AncestorMoved

    private void yr12AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr12AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12AncestorAdded

    private void yr12VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yr12VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr12VetoableChange

    private void yr13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yr13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13MouseClicked

    private void yr13AncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr13AncestorMoved1
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13AncestorMoved1

    private void yr13HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_yr13HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13HierarchyChanged

    private void yr13CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr13CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13CaretPositionChanged

    private void yr13InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_yr13InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13InputMethodTextChanged

    private void yr13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yr13PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13PropertyChange

    private void yr13AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr13AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13AncestorMoved

    private void yr13AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_yr13AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13AncestorAdded

    private void yr13VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_yr13VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_yr13VetoableChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
                populateTBLLeaveDenied1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        populateTBLLeaveApprovedAlreadyPerEmp();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void repopulateapprovealready() {
        tabapprove.setSelectedIndex(2);
        cmbmonth.setSelectedIndex(Integer.parseInt(myFunction.getMonth()));
        populateTBLLeaveApprovedAlready();
    }

    public void repopulateapprove() {
        tabapprove.setSelectedIndex(3);
        cmbmonth2.setSelectedIndex(Integer.parseInt(myFunction.getMonth()));
        populateTBLLeaveDenied();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bck;
    private javax.swing.JLabel captured;
    private javax.swing.JComboBox cmba;
    private javax.swing.JComboBox cmblt;
    private javax.swing.JComboBox cmbmonth;
    private javax.swing.JComboBox cmbmonth2;
    private javax.swing.JComboBox cmbpp;
    private javax.swing.JComboBox cmbra;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdcancelpendingleaveapp;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdexit1;
    private javax.swing.JButton cmdlogin;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JButton cmdsubmitleave;
    private javax.swing.JButton cmdview1;
    private javax.swing.JButton cmdview2;
    private javax.swing.JDesktopPane d;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblnd;
    private javax.swing.JLabel lblnym;
    private javax.swing.JLabel ldept;
    private javax.swing.JLabel ldiv;
    private javax.swing.JLabel lsec;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTabbedPane tabapprove;
    private javax.swing.JTabbedPane tabapprovedleave;
    private javax.swing.JTabbedPane tabdenieds;
    private javax.swing.JTabbedPane tableave;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbldaycalc;
    private javax.swing.JTable tblleaveapprove;
    private javax.swing.JTable tblleaveapprovedleave;
    private javax.swing.JTable tblleaveapprovedleave1;
    private javax.swing.JTable tblleavecredits;
    private javax.swing.JTable tblleavedenied;
    private javax.swing.JTable tblleavedenied1;
    private javax.swing.JTable tblleavepending;
    private javax.swing.JTextField txteid;
    private com.toedter.calendar.JDateChooser txtendla;
    private javax.swing.JPasswordField txtpincode;
    private javax.swing.JTextField txtreason;
    private com.toedter.calendar.JDateChooser txtstartla;
    private com.toedter.calendar.JYearChooser yr;
    private com.toedter.calendar.JYearChooser yr11;
    private com.toedter.calendar.JYearChooser yr12;
    private com.toedter.calendar.JYearChooser yr13;
    private com.toedter.calendar.JYearChooser yr2;
    // End of variables declaration//GEN-END:variables
}
