package mod.gui.employee;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.gui.capturer.Capturer;
//import mod.gui.capturer.Capturer;
import mod.main.mdi;
import mod.model.*;
import mod.model.controller.*;
import mod.others.myFunction;

public final class employee_edit extends javax.swing.JInternalFrame {

    static int deptid, divid, secid, etype, eto, rv, qf, empid, ctype, cempto, cposid, csecid, cdivid, cdeptid, cflag;
    static String nowDate = myFunction.getDate();
    public static dependent_eadd frmNew;
    public static dependent_eedit frmEdit;
    public static browse_photo frmBrowse;
    public static Capturer frmCapture;
    public static edit_emptype frmChange;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2;
    public static String taxcode;
    static Statement stmt;
    Employee emp = new Employee();
    EmployeeC empc = new EmployeeC();
    EmployeeDependent dependent = new EmployeeDependent();
    DependentC dependentc = new DependentC();
    EmployeeLog emplog = new EmployeeLog();
    EmployeeLogC emplogc = new EmployeeLogC();
    EmpTypeC emptypec = new EmpTypeC();
    EmployerC emptoc = new EmployerC();
    PositionC posc = new PositionC();
    SectionC secc = new SectionC();
    DivC divc = new DivC();
    DeptC deptc = new DeptC();
    EmpLeaveCredits elc = new EmpLeaveCredits();
    EmpLeaveCreditsC elcc = new EmpLeaveCreditsC();
    static char g;
    public static leave_credits_edit editlc;

    public employee_edit() {
        initComponents();
        cmdchange2.setVisible(false);

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);

        TableColumn idClmn3 = tbl.getColumn("empid");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);

        TableColumn idClmn2 = tbl2.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);

        TableColumn idClmn4 = tblleavecredits.getColumn("id");
        idClmn4.setMaxWidth(0);
        idClmn4.setMinWidth(0);
        idClmn4.setPreferredWidth(0);

        getRootPane().setDefaultButton(cmdsearch);
//        setComboEmpTypeModel();
//        setComboEmployerTypeModel();
//        cmbpos.addItem("--SELECT--");
//        setDeptComboModel();
//        setDivComboModel(0);
//        setSecComboModel(0);
        //setdates();
        // setTaxCode();
        txtsearch.requestFocus();
        setPersonalInfo();
        setDependent();
        setEmployement();
        setContact();
        setAccount();
        setPhoto();
        setServiceRec();
        setLeaveSetup();
    }

    boolean IsLeaveCreditSetupSet() {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT leave_type_id FROM leave_type WHERE leave_type_id NOT IN (SELECT leave_type_id FROM emp_leave_credits WHERE emp_id=" + empid + ")";

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

    boolean IsHasLeaveCreditSetupSet() {
        boolean found = false;

        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT emp_id FROM emp_leave_credits WHERE emp_id=" + empid;

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

    void setPersonalInfo() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/personal.png") + ">&nbsp</td><td>Profile<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(0, lbl);
    }

    void setDependent() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/dependent.png") + ">&nbsp</td><td>Dependent<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(1, lbl);
    }

    void setEmployement() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/employment.png") + ">&nbsp</td><td>Employment<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(2, lbl);
    }

    void setContact() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/contact.png") + ">&nbsp</td><td>Contact<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(3, lbl);
    }

    void setAccount() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/account.png") + ">&nbsp</td><td>Account<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(4, lbl);
    }

    void setPhoto() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/picture.png") + ">&nbsp</td><td>Photo<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(5, lbl);
    }

    void setServiceRec() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/servicerec.png") + ">&nbsp</td><td>Service Record<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(6, lbl);
    }

    void setLeaveSetup() {
        JLabel lbl = new JLabel("<html><table border=0 cellpadding=0 cellspacing=0><tr><td><img src=" + getClass().getResource("/images/leaveapp3.png") + ">&nbsp</td><td>Leave Credit Setup<font></td></th>");
        lbl.setIconTextGap(5);
        tab.setTabComponentAt(7, lbl);
    }

    public void setLblValues(String ntype, String nempto, String npos, String ndept, String ndiv, String nsec) {
        letype.setText(ntype);
        lempto.setText(nempto);
        lpos.setText(npos);
        ldept.setText(ndept);
        ldiv.setText(ndiv);
        lsec.setText(nsec);
    }

    public void setNewValues(int ntype, int nempto, int nposid, int nsecid) {
        ctype = ntype;
        cempto = nempto;
        cposid = nposid;
        csecid = nsecid;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmdsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        cmdadd = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtdob = new com.toedter.calendar.JDateChooser();
        txtpob = new javax.swing.JTextField();
        optmale = new javax.swing.JRadioButton();
        optfemale = new javax.swing.JRadioButton();
        txtbloodtype = new javax.swing.JTextField();
        txthouseno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtstreet = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtbrgy = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtcity = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtspouse = new javax.swing.JTextField();
        txtprovince = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtcountry = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtsuffix = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtlname = new javax.swing.JTextField();
        txtfname = new javax.swing.JTextField();
        txtempno = new javax.swing.JTextField();
        optsingle = new javax.swing.JRadioButton();
        optmarried = new javax.swing.JRadioButton();
        optwidowed = new javax.swing.JRadioButton();
        optseparated = new javax.swing.JRadioButton();
        optannulled = new javax.swing.JRadioButton();
        txtzipcode = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        cmdeditd = new javax.swing.JButton();
        cmdaddd = new javax.swing.JButton();
        cmdremoved = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        lbltaxcode = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        txttin = new javax.swing.JTextField();
        txtphic = new javax.swing.JTextField();
        txthdmf = new javax.swing.JTextField();
        txtsss = new javax.swing.JTextField();
        txtdoe = new com.toedter.calendar.JDateChooser();
        txtstep = new javax.swing.JFormattedTextField();
        chckqualified = new javax.swing.JCheckBox();
        letype = new javax.swing.JLabel();
        lempto = new javax.swing.JLabel();
        ldept = new javax.swing.JLabel();
        ldiv = new javax.swing.JLabel();
        lsec = new javax.swing.JLabel();
        lpos = new javax.swing.JLabel();
        cmdchange2 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        txtdr = new com.toedter.calendar.JDateChooser();
        txtdrn = new com.toedter.calendar.JDateChooser();
        lblsalary = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        txtcontactno = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblaccountno = new javax.swing.JLabel();
        txtcode = new javax.swing.JFormattedTextField();
        jLabel47 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        captured = new javax.swing.JLabel();
        cmdEdit = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        cmdEdit1 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblleavecredits = new javax.swing.JTable();
        cmdsetleavetypes = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtremarks = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Employees Record Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manageemp.png"))); // NOI18N

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
                "id", "Name", "Edit"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsearch))
        );

        cmdadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdadd.setMnemonic('A');
        cmdadd.setText("Save");
        cmdadd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdadd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdaddActionPerformed(evt);
            }
        });

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdcancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(cmdadd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdadd)
                    .addComponent(cmdcancel)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tab.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtdob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdob.setDateFormatString("MM/dd/yyyy");

        txtpob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        buttonGroup1.add(optmale);
        optmale.setMnemonic('M');
        optmale.setSelected(true);
        optmale.setText("Male");

        buttonGroup1.add(optfemale);
        optfemale.setMnemonic('F');
        optfemale.setText("Female");

        txtbloodtype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtbloodtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbloodtypeActionPerformed(evt);
            }
        });

        txthouseno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel12.setForeground(new java.awt.Color(204, 102, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("House No.");

        txtstreet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel14.setForeground(new java.awt.Color(204, 102, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Street");

        txtbrgy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel15.setForeground(new java.awt.Color(204, 102, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Barangay");

        txtcity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel16.setForeground(new java.awt.Color(204, 102, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("City/Municipality");

        txtspouse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtspouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtspouseActionPerformed(evt);
            }
        });

        txtprovince.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel17.setForeground(new java.awt.Color(204, 102, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Province");

        txtcountry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel18.setForeground(new java.awt.Color(204, 102, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Country");

        jLabel20.setForeground(new java.awt.Color(204, 102, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Zip Code");

        txtsuffix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel4.setForeground(new java.awt.Color(204, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Last Name");

        txtmname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel7.setForeground(new java.awt.Color(204, 102, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Suffix");

        jLabel6.setForeground(new java.awt.Color(204, 102, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Middle Name");

        jLabel5.setForeground(new java.awt.Color(204, 102, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("First Name");

        txtlname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtfname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtlname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfname)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmname)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtsuffix)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtsuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(22, 22, 22))
        );

        txtempno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        buttonGroup2.add(optsingle);
        optsingle.setMnemonic('M');
        optsingle.setSelected(true);
        optsingle.setText("Single");

        buttonGroup2.add(optmarried);
        optmarried.setMnemonic('F');
        optmarried.setText("Married");

        buttonGroup2.add(optwidowed);
        optwidowed.setMnemonic('F');
        optwidowed.setText("Widow/Widower");

        buttonGroup2.add(optseparated);
        optseparated.setMnemonic('F');
        optseparated.setText("Legally Separated");
        optseparated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optseparatedActionPerformed(evt);
            }
        });

        buttonGroup2.add(optannulled);
        optannulled.setMnemonic('F');
        optannulled.setText("Annulled");
        optannulled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optannulledActionPerformed(evt);
            }
        });

        txtzipcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        try {
            txtzipcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtzipcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpob, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtspouse, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtempno, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbloodtype, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optsingle)
                                    .addComponent(optmale))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optfemale)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(optmarried)
                                        .addGap(18, 18, 18)
                                        .addComponent(optwidowed)
                                        .addGap(18, 18, 18)
                                        .addComponent(optseparated)
                                        .addGap(18, 18, 18)
                                        .addComponent(optannulled))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtprovince, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                            .addComponent(txthouseno, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtstreet)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtbrgy)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtcity)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtcountry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtzipcode))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtempno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpob, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optmale)
                    .addComponent(optfemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optsingle)
                    .addComponent(optmarried)
                    .addComponent(optwidowed)
                    .addComponent(optseparated)
                    .addComponent(optannulled))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbloodtype, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(txthouseno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(txtstreet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtcity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtbrgy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtprovince, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcountry, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtzipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtspouse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jLabel8.setDisplayedMnemonic('f');
        jLabel8.setText("Name of Employee:");

        jLabel9.setDisplayedMnemonic('h');
        jLabel9.setText("Date of Birth:");

        jLabel10.setDisplayedMnemonic('P');
        jLabel10.setText("Place of Birth:");

        jLabel11.setText("Gender:");

        jLabel13.setDisplayedMnemonic('y');
        jLabel13.setText("Blood Type:");

        jLabel19.setDisplayedMnemonic('u');
        jLabel19.setText("Name of Spouse:");

        jLabel21.setDisplayedMnemonic('e');
        jLabel21.setText("Address:");

        jLabel23.setDisplayedMnemonic('y');
        jLabel23.setText("I.D No.:");

        jLabel34.setDisplayedMnemonic('y');
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 102, 0));
        jLabel34.setText("*");

        jLabel38.setDisplayedMnemonic('y');
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 102, 0));
        jLabel38.setText("*");

        jLabel42.setText("Civil Status:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        tab.addTab("PERSONAL INFO ", jPanel2);

        cmdeditd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editm.png"))); // NOI18N
        cmdeditd.setMnemonic('i');
        cmdeditd.setText("Edit");
        cmdeditd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdeditd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdeditd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdeditdActionPerformed(evt);
            }
        });

        cmdaddd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addm.png"))); // NOI18N
        cmdaddd.setMnemonic('d');
        cmdaddd.setText("Add");
        cmdaddd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdaddd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdaddd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdadddActionPerformed(evt);
            }
        });

        cmdremoved.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/removem.png"))); // NOI18N
        cmdremoved.setMnemonic('m');
        cmdremoved.setText("Remove");
        cmdremoved.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdremoved.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdremoved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdremovedActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "empid", "Dependent Name", "Relation", "Date of Birth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdeditd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdremoved, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(cmdaddd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(cmdaddd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdeditd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdremoved))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setForeground(new java.awt.Color(255, 102, 0));
        jLabel22.setText("Note: No need to click the Save Button");

        lbltaxcode.setForeground(new java.awt.Color(102, 0, 0));
        lbltaxcode.setText("Tax Code is D0");
        lbltaxcode.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbltaxcode, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbltaxcode)
                .addGap(47, 47, 47)
                .addComponent(jLabel22)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        tab.addTab("DEPENDENT", jPanel3);

        jLabel29.setText("Section:");

        jLabel27.setText("Department:");

        jLabel28.setText("Division:");

        jLabel30.setDisplayedMnemonic('s');
        jLabel30.setText("Plantilla Position:");

        jLabel24.setDisplayedMnemonic('f');
        jLabel24.setText("Date of Employment:");

        jLabel26.setDisplayedMnemonic('l');
        jLabel26.setText("Employed to:");

        jLabel25.setDisplayedMnemonic('o');
        jLabel25.setText("Employment Type:");

        txttin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtphic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txthdmf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtsss.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtdoe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdoe.setDateFormatString("MM/dd/yyyy");

        txtstep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtstep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtstep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstep.setText("0");

        chckqualified.setMnemonic('Q');
        chckqualified.setText("Employee is Qualified for this position");

        letype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lempto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        ldept.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        ldiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lsec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        lpos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        cmdchange2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editm.png"))); // NOI18N
        cmdchange2.setText("Change ");
        cmdchange2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdchange2ActionPerformed(evt);
            }
        });

        txtdr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdr.setDateFormatString("MM/dd/yyyy");

        txtdrn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdrn.setDateFormatString("MM/dd/yyyy");

        lblsalary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblsalary.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Php.");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(letype, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(lempto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthdmf, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttin, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdoe, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtstep, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chckqualified, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ldept, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ldiv, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lsec, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lpos, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdchange2))
                    .addComponent(txtsss, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphic, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdr, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdrn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lblsalary, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44)))
                .addGap(0, 172, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(txtdoe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(letype, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lempto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(ldept, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ldiv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lsec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lpos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdchange2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chckqualified)
                    .addComponent(txtstep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(txtsss, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtphic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthdmf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtdr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdrn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblsalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel32.setText("PHIC No.:");

        jLabel33.setText("HDMF No.:");

        jLabel31.setText("SSS No.:");

        jLabel35.setText("BIR TIN No.:");

        jLabel36.setDisplayedMnemonic('s');
        jLabel36.setText("Step:");

        jLabel40.setText("Date Retired:");

        jLabel41.setText("Date Resigned:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 102, 0));
        jLabel43.setText("BASIC SALARY:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel15);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        tab.addTab("EMPLOYMENT", jPanel4);

        jLabel37.setText("Contact No.");

        jLabel39.setText("E-mail Address.:");

        txtcontactno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtcontactno))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(txtcontactno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39))
                .addGap(40, 40, 40)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        tab.addTab("CONTACT NO", jPanel12);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("SID:");

        jLabel46.setText("PIN CODE:");

        lblaccountno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblaccountno.setForeground(new java.awt.Color(0, 102, 0));

        txtcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        try {
            txtcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel47.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(204, 102, 0));
        jLabel47.setText("4 Digit PIN Code");

        jButton1.setText("Generate Randomly");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 102, 0));
        jLabel50.setText("System Generated Unique Identification Number");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblaccountno, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel47)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 234, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(lblaccountno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        tab.addTab("ACCOUNT INFO", jPanel19);

        captured.setForeground(new java.awt.Color(255, 102, 0));
        captured.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nophoto.jpg"))); // NOI18N
        captured.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        cmdEdit.setMnemonic('C');
        cmdEdit.setText("Change");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        jLabel48.setForeground(new java.awt.Color(255, 153, 0));
        jLabel48.setText("Current Profile Picture");

        cmdEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        cmdEdit1.setMnemonic('C');
        cmdEdit1.setText("Browse");
        cmdEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEdit1ActionPerformed(evt);
            }
        });

        jLabel53.setForeground(new java.awt.Color(255, 102, 0));
        jLabel53.setText("Note: No need to click the Save Button");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addGap(426, 426, 426))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel53))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab.addTab("PHOTO", jPanel22);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 578, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 257, Short.MAX_VALUE))
        );

        tab.addTab("SERVICEREC", jPanel23);

        tblleavecredits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Leave Type", "Credits", "Used", "Edit"
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
        tblleavecredits.setColumnSelectionAllowed(true);
        tblleavecredits.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblleavecredits);
        tblleavecredits.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblleavecredits.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblleavecredits.getColumnModel().getColumn(4).setMaxWidth(50);

        cmdsetleavetypes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leaveapp6.png"))); // NOI18N
        cmdsetleavetypes.setText("SET DEFAULT VALUES");
        cmdsetleavetypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsetleavetypesActionPerformed(evt);
            }
        });

        jLabel51.setText("List of leave type with employee's credit and credit used values.");

        jLabel52.setForeground(new java.awt.Color(255, 102, 0));
        jLabel52.setText("Note: No need to click the Save Button");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdsetleavetypes, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel51)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdsetleavetypes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel52)
                .addContainerGap(17, Short.MAX_VALUE))
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

        tab.addTab("LEAVE CREDITS SETUP", jPanel26);

        txtremarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel3.setDisplayedMnemonic('L');
        jLabel3.setText("Remarks:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtremarks)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtremarks, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 687, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void setTaxCode() {
        int dc = model.getRowCount();
        taxcode = "D" + dc;
        lbltaxcode.setText("Tax Code is D" + dc);
        //System.out.println(taxcode);
    }

    public void ShowFrmNew() {
        int col = 0; //set column value to 0
        int row = tbl2.getSelectedRow(); //get value of selected value
        String id = tbl2.getValueAt(row, col).toString();
        dependent_eadd.empid = Integer.parseInt(id);
        frmNew = new dependent_eadd(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmChange() {
        frmChange = new edit_emptype(this, true);
        frmChange.setVisible(true);
    }

    public void ShowFrmBrowse() {
        frmBrowse = new browse_photo(this, true);
        frmBrowse.setVisible(true);
    }

    public void ShowFrmCapturer() {
        frmCapture = new Capturer(this, true);
        frmCapture.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new dependent_eedit(this, true);
        frmEdit.setVisible(true);
    }

    public void ShowFrmEditLC() {
        editlc = new leave_credits_edit(this, true);
        editlc.setVisible(true);
    }

    public void populateDependents(int eid) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT * FROM employee_dependent WHERE emp_id=" + eid;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl.setRowHeight(25);
            tbl.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
            // tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);

            model.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
                cnt++;
            }

            stmt.close();
            conn.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        setTaxCode();
    }

    public void insertIntoTBL(String name, String relation, String bdate) {
        try {
            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl.setRowHeight(22);
            tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl.setColumnSelectionAllowed(false);

            model.addRow(new Object[]{name, relation, bdate});
            setTaxCode();
        } catch (Exception e) {
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
            tbl2.getColumn("Edit").setCellRenderer(new ButtonRenderer(1));
            tbl2.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
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
                    poulateDatas(Integer.parseInt(id));
                    populateDependents(Integer.parseInt(id));
                    getEmploymentData(Integer.parseInt(id));
                    populateLeaveCredits();
                    setTaxCode();
                    cmdchange2.setVisible(true);
                    NumberFormat nf4 = new DecimalFormat("0000");
                    String c = nf4.format(empid);
                    lblaccountno.setText(c);
                    setPic();

                    boolean isSet = IsHasLeaveCreditSetupSet();

                    if (isSet == true) {
                        cmdsetleavetypes.setText("UPDATE NEW DEFAULT VALUES");
                    } else {
                        cmdsetleavetypes.setText("SET DEFAULT VALUES");
                    }

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
                    int row = tblleavecredits.getSelectedRow(); //get value of selected value

                    String id = tblleavecredits.getValueAt(row, col).toString();
                    leave_credits_edit.elc_id = Integer.parseInt(id);
                    ShowFrmEditLC();
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
            // tbl.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tblleavecredits.getColumn("Edit").setCellRenderer(new ButtonRenderer2(1));
            tblleavecredits.getColumn("Edit").setCellEditor(new ButtonEditor2(new JCheckBox(), 1));

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

    public void setListModel() {
//        lst.setModel(new EmployeeC().getEmployeeListModel(txtsearch.getText()));
    }

//    void setComboEmpTypeModel() {
//        cmbemptype.setModel(new EmpTypeC().getEmpTypeComboModel());
//    }
//
//    void setComboEmployerTypeModel() {
//        cmbemployer.setModel(new EmployerC().getEmployerComboModel());
//    }
//
//    void setDeptComboModel() {
//        cmbdept.setModel(new DeptC().getDeptComboModel());
//    }
//
//    void setDivComboModel(int did) {
//        cmbdiv.setModel(new DivC().getDivComboModel(did));
//    }
//    void setSecComboModel(int did) {
//        cmbsec.setModel(new SectionC().getSecComboModel(did));
//    }
    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    private void cmdaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddActionPerformed
        String lname = txtlname.getText().replace("'", " ");
        String fname = txtfname.getText().replace("'", " ");
        String mname = txtmname.getText().replace("'", " ");
        String suffix = txtsuffix.getText().replace("'", " ");
        String pob = txtpob.getText().replace("'", " ");

        if (optmale.isSelected() == true) {
            g = "M".charAt(0);
        } else if (optfemale.isSelected() == true) {
            g = "F".charAt(0);
        }

        int cs = 0;
        if (optsingle.isSelected() == true) {
            cs = 1;
        } else if (optmarried.isSelected() == true) {
            cs = 2;
        } else if (optwidowed.isSelected() == true) {
            cs = 3;
        } else if (optseparated.isSelected() == true) {
            cs = 4;
        } else if (optannulled.isSelected() == true) {
            cs = 5;
        }

        String bloodtype = txtbloodtype.getText().replace("'", " ");
        String houseno = txthouseno.getText().replace("'", " ");
        String street = txtstreet.getText().replace("'", " ");
        String brgy = txtbrgy.getText().replace("'", " ");
        String cm = txtcity.getText().replace("'", " ");
        String province = txtprovince.getText().replace("'", " ");
        String country = txtcountry.getText().replace("'", " ");
        String zcode = txtzipcode.getText().replace("'", " ");
        String spouse = txtspouse.getText().replace("'", " ");
        String city = txtcity.getText();
        String sss = txtsss.getText();
        String phic = txtphic.getText();
        String hdmf = txthdmf.getText();
        String bir = txttin.getText();
        String email = txtemail.getText();
        //int rank = Integer.parseInt(txtrank.getText());
        int step = Integer.parseInt(txtstep.getText());
        String emp_no = txtempno.getText();



        if (etype != 1) {
            rv = 0;
            qf = 0;
        } else {
            if (chckqualified.isSelected() == true) {
                qf = 1;
            } else if (chckqualified.isSelected() == false) {
                qf = 2;
            }
        }

        if (lname.isEmpty() == true || fname.isEmpty() == true || txtremarks.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Please fill-up all the required fields!");
        } else {
            //SET EMPLOYEE DATA
            emp.setLastName(lname);
            emp.setFirstName(fname);
            emp.setMiddleName(mname);
            emp.setSuffixName(suffix);
            emp.setBirthDate(txtdob.getDate());
            emp.setBirthPlace(pob);
            emp.setGender(g);
            emp.setBloodType(bloodtype);
            emp.setHouseNo(houseno);
            emp.setStreet(street);
            emp.setBarangay(brgy);
            emp.setContactNo(txtcontactno.getText());
            emp.setProvince(province);
            emp.setCountry(country);
            emp.setZipCode(zcode);
            emp.setSpouseName(spouse);
            emp.setEmploymentDate(txtdob.getDate());
            emp.setCity(city);
            emp.setSssId(sss);
            emp.setPhicId(phic);
            emp.setHdmfId(hdmf);
            emp.setTin(bir);
            emp.setEmail(email);
            emp.setSecId(csecid);
            emp.setEmpTypeId(Short.parseShort(String.valueOf(ctype)));
            emp.setEmployerId(cempto);
            emp.setStatusFlag(Short.parseShort("1"));
            //emp.setRank(Short.parseShort(String.valueOf(rank)));
            emp.setStep(Short.parseShort(String.valueOf(step)));
            emp.setPositionId(Short.parseShort(String.valueOf(cposid)));
            emp.setQualifiedFlag(Short.parseShort(String.valueOf(qf)));
            emp.setEmpNo(emp_no);
            emp.setStatusFlag(Short.parseShort("1"));
            emp.setEmpId(empid);
            emp.setCivilStatusId(cs);

//            try{
            String pcode = txtcode.getText();
            emp.setPinCode(Short.valueOf(pcode));
//            }catch(Exception e){
//                emp.setPinCode(Short.valueOf("0"));
//            }

//            if (empc.editEmployee(emp) > 0) {
            //ADD EMPLOG
            empc.editEmployee(emp);
            emplog.setLastName(lname);
            emplog.setFirstName(fname);
            emplog.setMiddleName(mname);
            emplog.setSuffixName(suffix);
            emplog.setBirthDate(txtdob.getDate());
            emplog.setBirthPlace(pob);
            emplog.setGender(g);
            emplog.setBloodType(bloodtype);
            emplog.setHouseNo(houseno);
            emplog.setStreet(street);
            emplog.setBarangay(brgy);
            emplog.setContactNo(cm);
            emplog.setProvince(province);
            emplog.setCountry(country);
            emplog.setZipCode(zcode);
            emplog.setSpouseName(spouse);
            emplog.setEmploymentDate(txtdob.getDate());
            emplog.setCity(city);
            emplog.setSssId(sss);
            emplog.setPhicId(phic);
            emplog.setHdmfId(hdmf);
            emplog.setTin(bir);
            emplog.setEmail(email);
            emplog.setSecId(csecid);
            emplog.setEmpTypeId(Short.parseShort(String.valueOf(ctype)));
            emplog.setEmployerId(cempto);
            emplog.setStatusFlag(Short.parseShort("1"));
            //emplog.setRank(Short.parseShort(String.valueOf(rank)));
            emplog.setStep(Short.parseShort(String.valueOf(step)));
            emplog.setPositionId(Short.parseShort(String.valueOf(rv)));
            emplog.setQualifiedFlag(Short.parseShort(String.valueOf(qf)));
            emplog.setRemarks(txtremarks.getText());
            emplog.setUserId(mdi.getUserID());
            emplog.setPinCode(Short.valueOf(txtcode.getText()));

            emplog.setEmpId(empid);
            emplog.setCivilStatusId(cs);

//                if (emplogc.addEmployeeLog(emplog) > 0) {
//                } else {
//                    JOptionPane.showMessageDialog(null, "Failed to save employee log");
//                }

            emplogc.addEmployeeLog(emplog);

//                this.dispose();
            JOptionPane.showMessageDialog(null, "Changes has been Successfully Saved!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Failed to save employee record");
//            }
            populateTBL();
            txtremarks.setText("");
        }

        //tab.setSelectedIndex(0);
    }//GEN-LAST:event_cmdaddActionPerformed

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmdadddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdadddActionPerformed
        try {
            int dc = model.getRowCount();
            if (dc == 4) {
                JOptionPane.showMessageDialog(this, "Only 4 dependents is allowed!");
            } else {
                ShowFrmNew();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdadddActionPerformed

    private void cmdeditdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditdActionPerformed
        try {
            int col = 0; //set column value to 0
            int row = tbl.getSelectedRow(); //get value of selected value

            //trap user incase if no row selected
            if (tbl.isRowSelected(row) != true) {
                JOptionPane.showMessageDialog(this, "Select a Record!");
            } else {
                String did = tbl.getValueAt(row, col).toString();

                dependent_eedit.empid = empid;
                dependent_eedit.edid = Integer.parseInt(did);
                ShowFrmEdit();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Select a Dependent Record!");
            e.getMessage();
        }
    }//GEN-LAST:event_cmdeditdActionPerformed

    private void cmdremovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremovedActionPerformed
        try {
            int row = tbl.getSelectedRow(); //get value of selected value

            //trap user incase if no row selected
            if (tbl.isRowSelected(row) != true) {
                JOptionPane.showMessageDialog(this, "Select a Dependent Record!");
            } else {

                String id = tbl.getValueAt(row, 0).toString();
                int i = myFunction.msgboxYesNo("Delete this current dependent?");
                if (i == 0) {
                    dependent.setEdId(Integer.parseInt(id));
                    if (dependentc.deleteDependent(dependent) > 0) {
                        populateDependents(empid);
                    } else {
                        JOptionPane.showMessageDialog(null, "Record Successfully Removed!");
                    }
                    setTaxCode();
                } else if (i == 1) {
                } else if (i == 2) {
                    this.dispose(); //exit window
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdremovedActionPerformed

    private void cmdchange2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdchange2ActionPerformed
        ShowFrmChange();
    }//GEN-LAST:event_cmdchange2ActionPerformed

    private void txtspouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtspouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtspouseActionPerformed

    private void txtbloodtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbloodtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbloodtypeActionPerformed

    private void optseparatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optseparatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optseparatedActionPerformed

    private void optannulledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optannulledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optannulledActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int pcode = myFunction.generatedRandomPINCode();
        txtcode.setText(String.valueOf(pcode));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void capturedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseReleased
    }//GEN-LAST:event_capturedMouseReleased

    private void capturedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMousePressed
    }//GEN-LAST:event_capturedMousePressed

    private void capturedMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseDragged
    }//GEN-LAST:event_capturedMouseDragged

    private void capturedMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseMoved
    }//GEN-LAST:event_capturedMouseMoved

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        if (empid == 0) {
            JOptionPane.showMessageDialog(null, "Please select an employee record!");
        } else {
            Capturer.empid = empid;
            ShowFrmCapturer();
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEdit1ActionPerformed
        ShowFrmBrowse();
    }//GEN-LAST:event_cmdEdit1ActionPerformed

    private void cmdsetleavetypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsetleavetypesActionPerformed
        if (empid == 0) {
            JOptionPane.showMessageDialog(null, "Please select an employee record!");
        } else {
            //System.out.println(empid);
            boolean isSet = IsLeaveCreditSetupSet();

            if (isSet == false) {
                JOptionPane.showMessageDialog(null, "All leave types are already set! No more new leave type/s to be set!");
            } else {
                elc.setEmpId(empid);
                if (elcc.addEmpLeaveCredits(elc) > 0) {
                    populateLeaveCredits();
                    JOptionPane.showMessageDialog(null, "Default values has been set successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save!");
                }
            }
        }

    }//GEN-LAST:event_cmdsetleavetypesActionPerformed

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

    void poulateDatas(int eid) {
        txtempno.setText(empc.GetEmpNo(eid));
        txtlname.setText(empc.GetLname(eid));
        txtfname.setText(empc.GetFname(eid));
        txtmname.setText(empc.GetMname(eid));
        txtsuffix.setText(empc.GetSuffix(eid));
        setDOBDate(eid);
        txtpob.setText(empc.GetPOB(eid));

        char g = empc.GetGender(eid).charAt(0);

        if (g == "M".charAt(0)) {
            optmale.setSelected(true);
        } else if (g == "F".charAt(0)) {
            optfemale.setSelected(true);
        }

        int cs = empc.GetCivilStatus(eid);

        if (cs == 1) {
            optsingle.setSelected(true);
        }
        if (cs == 2) {
            optmarried.setSelected(true);
        }
        if (cs == 3) {
            optwidowed.setSelected(true);
        }
        if (cs == 4) {
            optseparated.setSelected(true);
        }

        txtbloodtype.setText(empc.GetBloodType(eid));

        txthouseno.setText(empc.GetHouseNo(eid));
        txtstreet.setText(empc.GetStreet(eid));
        txtbrgy.setText(empc.GetBrgy(eid));
        txtcity.setText(empc.GetCity(eid));
        txtzipcode.setText(empc.GetZipCode(eid));
        txtprovince.setText(empc.GetProvince(eid));
        txtcountry.setText(empc.GetCountry(eid));
        txtspouse.setText(empc.GetSpouse(eid));
        txtcontactno.setText(empc.GetContactNo(eid));
        txtemail.setText(empc.GetEmail(eid));
        setDOEDate(eid);

        txtstep.setText(empc.GetStep(eid));
        txtsss.setText(empc.GetSSS(eid));
        txtphic.setText(empc.GetPHIC(eid));
        txthdmf.setText(empc.GetHDMF(eid));
        txttin.setText(empc.GetBIR(eid));
        // txtcode.setText();

        int icode = Integer.parseInt(String.valueOf(empc.GetPinCode(eid)));

        if (icode == 0) {
            txtcode.setText("1234");
        } else {
            txtcode.setText(String.valueOf(icode));
        }

        String qflag = empc.GetQF(eid);
        switch (qflag) {
            case "1":
                chckqualified.setSelected(true);
                break;
            case "2":
                chckqualified.setSelected(false);
                break;

            //String etype =;


        }

        // letype.setText(emptypec.GetEmpTypeDesc(Integer.parseInt(empc.GetEmpTypeId(eid))));
        // lempto.setText(emptoc.GetEmpto(Integer.parseInt(empc.GetEmptoId(eid))));
        // lpos.setText(posc.GetPositionDescwIcon(Integer.parseInt(empc.GetPositionId(eid))));
        //System.out.println();


    }

    void getEmploymentData(int eid) {
        ctype = Integer.parseInt(empc.GetEmpTypeId(eid));
        cempto = Integer.parseInt(empc.GetEmptoId(eid));
        cposid = Integer.parseInt(empc.GetPositionId(eid));

        if (ctype != 1) {

            //cmdchange1.setVisible(true);
            cmdchange2.setVisible(false);
            lpos.setText("NOT APPLICABLE");
        } else {

            //cmdchange1.setVisible(false);
            cmdchange2.setVisible(true);
            lpos.setText(posc.GetPositionDescwIcon(Integer.parseInt(empc.GetPositionId(eid))));
        }

        letype.setText(emptypec.GetEmpTypeDesc(Integer.parseInt(empc.GetEmpTypeId(eid))));
        letype.setText(emptypec.GetEmpTypeDesc(Integer.parseInt(empc.GetEmpTypeId(eid))));
        lempto.setText(emptoc.GetEmpto(Integer.parseInt(empc.GetEmptoId(eid))));
        csecid = Integer.parseInt(empc.GetSecId(eid));
        lsec.setText(secc.GetSecName(csecid));
        cdivid = secc.GetDivID(csecid);
        ldiv.setText(divc.GetDivName(cdivid));
        cdeptid = divc.GetDeptId(cdivid);
        ldept.setText(deptc.GetDeptName(cdeptid));

//        cflag = empc.GeteFlg(eid);

//        if (cflag==1){
//            statactive.setSelected(true);
//        }else{
//            statinactive.setSelected(true);
//        }

    }

    void setDOBDate(int eid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date theDate = null;
        try {
            theDate = sdf.parse(empc.GetDOB(eid));
        } catch (ParseException e) {
        }
        txtdob.setDate(theDate);
    }

    void setDOEDate(int eid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date theDate = null;
        try {
            theDate = sdf.parse(empc.GetEmpDate(eid));
        } catch (ParseException e) {
        }
        txtdoe.setDate(theDate);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel captured;
    private javax.swing.JCheckBox chckqualified;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdEdit1;
    private javax.swing.JButton cmdadd;
    private javax.swing.JButton cmdaddd;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdchange2;
    private javax.swing.JButton cmdeditd;
    private javax.swing.JButton cmdremoved;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JButton cmdsetleavetypes;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblaccountno;
    private javax.swing.JLabel lblsalary;
    private javax.swing.JLabel lbltaxcode;
    private javax.swing.JLabel ldept;
    private javax.swing.JLabel ldiv;
    private javax.swing.JLabel lempto;
    private javax.swing.JLabel letype;
    private javax.swing.JLabel lpos;
    private javax.swing.JLabel lsec;
    private javax.swing.JRadioButton optannulled;
    private javax.swing.JRadioButton optfemale;
    private javax.swing.JRadioButton optmale;
    private javax.swing.JRadioButton optmarried;
    private javax.swing.JRadioButton optseparated;
    private javax.swing.JRadioButton optsingle;
    private javax.swing.JRadioButton optwidowed;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbl2;
    private javax.swing.JTable tblleavecredits;
    private javax.swing.JTextField txtbloodtype;
    private javax.swing.JTextField txtbrgy;
    private javax.swing.JTextField txtcity;
    private javax.swing.JFormattedTextField txtcode;
    private javax.swing.JTextField txtcontactno;
    private javax.swing.JTextField txtcountry;
    private com.toedter.calendar.JDateChooser txtdob;
    private com.toedter.calendar.JDateChooser txtdoe;
    private com.toedter.calendar.JDateChooser txtdr;
    private com.toedter.calendar.JDateChooser txtdrn;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtempno;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txthdmf;
    private javax.swing.JTextField txthouseno;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtmname;
    private javax.swing.JTextField txtphic;
    private javax.swing.JTextField txtpob;
    private javax.swing.JTextField txtprovince;
    private javax.swing.JTextField txtremarks;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtspouse;
    private javax.swing.JTextField txtsss;
    private javax.swing.JFormattedTextField txtstep;
    private javax.swing.JTextField txtstreet;
    private javax.swing.JTextField txtsuffix;
    private javax.swing.JTextField txttin;
    private javax.swing.JFormattedTextField txtzipcode;
    // End of variables declaration//GEN-END:variables
}
