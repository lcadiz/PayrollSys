package mod.gui.hours_worked;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.others.myFunction;
import mod.model.PayrollPeriod;
import mod.model.controller.PayrollPeriodC;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class calculate_wh extends javax.swing.JInternalFrame {

    static Statement stmt, stmt2;
    ActionListener taskperformer;
    Timer tmr;
    static int totl;
    int totlpercen;
    int loadpercentage;
    int empid, ppid;
    int totlhwreg, totlhwsat, totlhwsun;
    Connection conn2;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellEnableRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2;
    public static String in1start, in1end, out1start, out1end, in2start, in2end, out2start, out2end;
    public static String in1, out1, in2, out2;
    PayrollPeriod payper = new PayrollPeriod();
    PayrollPeriodC payperc = new PayrollPeriodC();
    JTable dr = new JTable();

    public calculate_wh() {
        initComponents();
        setComboPPModel();


        TableColumn idClmn2 = tbl1.getColumn("id");
        idClmn2.setMaxWidth(0);
        idClmn2.setMinWidth(0);
        idClmn2.setPreferredWidth(0);
        TableColumn idClmn3 = tbl2.getColumn("id");
        idClmn3.setMaxWidth(0);
        idClmn3.setMinWidth(0);
        idClmn3.setPreferredWidth(0);

        model = (DefaultTableModel) tbl1.getModel();
        model2 = (DefaultTableModel) dr.getModel();

        CreateTmpDRTable();
    }

    void CreateTmpDRTable() {
        model2.addColumn("date");
        model2.addColumn("day");
        model2.addColumn("tlin1");
        model2.addColumn("tlout1");
        model2.addColumn("tlin2");
        model2.addColumn("tlout2");
        model2.addColumn("ot");
        model2.addColumn("totalminutes");
        model2.addColumn("mm");
        model2.addColumn("ttlogs");

        cellAlignCenterRenderer.setHorizontalAlignment(0);
        cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        cellAlignRightRenderer.setEnabled(iconable);

        dr.setRowHeight(22);
        dr.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
        dr.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenterRenderer);
        dr.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
        dr.setColumnSelectionAllowed(false);
    }

    static int GetTotalEmp() {
        int totlemp = 0;
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT count(*) FROM employee";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);


            while (rs.next()) {
                totlemp = rs.getInt(1);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(calculate_wh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totlemp;
    }

    void setComboPPModel() {
        cmbpp.setModel(new PayrollPeriodC().getPayPerComboModel(yr.getYear()));
    }

    void MakeNormal() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tbl1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tbl2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    void MakeLoading() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tbl1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tbl2.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    void CalculatePay() {
        totl = GetTotalEmp();
        load.setValue(0);
        load.setMaximum(totl);

        //int totlemp = 0;
        conn2 = myDBConn.getConnection();
        String createString;
        createString = "SELECT emp_id, CONCAT(last_name,', ',first_name,' ',COALESCE(middle_name,''),' ' ,COALESCE(suffix_name,'') ) FROM employee";
        try {
            stmt2 = conn2.createStatement();
            final ResultSet rsLoop = stmt2.executeQuery(createString);

            rsLoop.first();

            taskperformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    loadpercentage = 100 / totl;
                    if (load.getValue() != totl) {

                        try {
                            load.setValue(load.getValue() + 1);
                            totlpercen = totlpercen + loadpercentage;

                            jLabel5.setText(totlpercen + "% In Progress!. Calculating WH of " + rsLoop.getString(2));
                            empid = rsLoop.getInt(1);


                            //       System.out.println(empid);
                            populateDateRange();
                            GetShiftRanges(empid);


                            populateTLogs(in1start, in1end, 2, 1);
                            populateTLogs(out1start, out1end, 3, 2);
                            populateTLogs(in2start, in2end, 4, 1);
                            populateTLogs(out2start, out2end, 5, 2);
                            populateOT(out2);
                            CalculateNoOFLogsPerDate();
                            CalculateTotal();
                            //disp();
                            totlhwreg = 0;
                            calculateSumHoursWork();
                            insertIntoTBL(empid, rsLoop.getString(2), totlhwreg, totlhwsat, totlhwsun);

                            try {
                                rsLoop.next();
                            } catch (SQLException ex) {
                                Logger.getLogger(calculate_wh.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(calculate_wh.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        jLabel5.setText("100% Done!");
                        MakeNormal();
                        tmr.stop();
                        try {
                            stmt2.close();
                            conn2.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(calculate_wh.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };

            tmr = new Timer(200, taskperformer);
            tmr.start();



        } catch (SQLException ex) {
            Logger.getLogger(calculate_wh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertIntoTBL(int eid, String nym, int reghwreg, int reghwsat, int reghwsun) {
        try {
            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            cellAlignRightRenderer.setEnabled(iconable);

            tbl1.setRowHeight(22);
            tbl1.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            tbl1.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            tbl1.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenterRenderer);
            tbl1.getColumnModel().getColumn(4).setCellRenderer(cellAlignCenterRenderer);
            tbl1.getColumnModel().getColumn(5).setCellRenderer(cellAlignCenterRenderer);
            tbl1.getColumnModel().getColumn(6).setCellRenderer(cellAlignCenterRenderer);

            tbl1.setColumnSelectionAllowed(false);


            model.addRow(new Object[]{eid, nym, reghwreg, 0, reghwsat, reghwsun});
        } catch (Exception e) {
        }

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

    void populateDateRange() {
        Date start = payperc.GetStartDate(ppid);
        Date end = payperc.GetEndDate(ppid);

        LocalDate dateStart = new LocalDate(start);
        LocalDate dateEnd = new LocalDate(end);

        dateStart = dateStart.minusDays(1);
        dateEnd = dateEnd.minusDays(1);

        model2.setNumRows(0);
        int rc = 0;

        while (dateStart.compareTo(dateEnd) != 1) {

            dateStart = dateStart.plusDays(1);
            DateTimeFormatter fmt1 = DateTimeFormat.forPattern("EEE");
            String dd = fmt1.print(dateStart);

            DateTimeFormatter fmt3 = DateTimeFormat.forPattern("yyyy-MM-dd");
            String f = fmt3.print(dateStart);

            model2.addRow(new Object[]{f, dd, "", "", "", "", "", "", "", ""});
            //System.out.println(dd);
        }

    }

    void populateTLogs(String t1, String t2, int rwv, int flg) {
        //System.out.println(empid);
        int r = dr.getRowCount();
        //System.out.println(r);
        int c;
        c = 0;
        //System.out.println(in1start + "-" + in1end);
        while (c < r + 1) {
            try {
                String ddte = dr.getValueAt(c, 0).toString();
                //IN1
                Connection conn = myDBConn.getConnection();
                String createString;

                String condition = "";
                switch (flg) {
                    case 1:
                        condition = "MIN";
                        break;
                    case 2:
                        condition = "MAX";
                }

                createString = "SELECT " + condition + "(time(log_date_time)) FROM time_log where time(log_date_time) between time('" + t1 + "') and time('" + t2 + "')"
                        + " AND DATE(log_date_time) = '" + ddte + "' AND emp_id= " + empid;
                try {
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(createString);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

                    while (rs.next()) {
                        String ds1 = dateFormat.format(rs.getTime(1));

                        //System.out.println(ds1);
                        dr.setValueAt(ds1, c, rwv);
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

    void populateOT(String tout) {
        //System.out.println(empid);
        int r = dr.getRowCount();
        //System.out.println(r);
        int c;
        c = 0;
        //System.out.println(in1start + "-" + in1end);
        while (c < r + 1) {
            try {
                String ddte = dr.getValueAt(c, 0).toString();
                //IN1
                Connection conn = myDBConn.getConnection();
                String createString;
                createString = "SELECT MAX(time(log_date_time)) FROM time_log where time(log_date_time) > time('" + tout + "') "
                        + " AND DATE(log_date_time) = '" + ddte + "' AND emp_id= " + empid;
                try {
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(createString);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

                    while (rs.next()) {
                        String ds1 = dateFormat.format(rs.getTime(1));

                        //  System.out.println(ds1);
                        dr.setValueAt(ds1, c, 6);
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

    void disp() {


        int r = dr.getRowCount();
        int c;
        c = 0;


        while (c < r + 1) {
            try {
                String date = dr.getValueAt(c, 0).toString();
                String day = dr.getValueAt(c, 1).toString();
                String tlin1 = dr.getValueAt(c, 2).toString();
                String tlout1 = dr.getValueAt(c, 3).toString();
                String tlin2 = dr.getValueAt(c, 4).toString();
                String tlout2 = dr.getValueAt(c, 5).toString();
                String ot = dr.getValueAt(c, 6).toString();
                String hh = dr.getValueAt(c, 7).toString();
                String mm = dr.getValueAt(c, 8).toString();
                String ttlogz = dr.getValueAt(c, 9).toString();

                System.out.println(date + "-" + day + "in:" + tlin1 + "out:" + tlout1 + "in:" + tlin2 + "out:" + tlout2 + "ot:" + ot + "hh:" + hh + "mm:" + mm + "mm:" + ttlogz);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }


    }

    void calculateSumHoursWork() {
        int r = dr.getRowCount();
        int c = 0;
        totlhwsat = 0;
        totlhwsun = 0;

        while (c < r + 1) {
            try {
                String hw = dr.getValueAt(c, 7).toString();
                String day = dr.getValueAt(c, 1).toString();


                if ("Sat".equals(day)) {
                    totlhwsat = totlhwsat + Integer.parseInt(hw);
                } else if ("Sun".equals(day)) {
                    totlhwsun = totlhwsun + Integer.parseInt(hw);
                } else {
                    totlhwreg = totlhwreg + Integer.parseInt(hw);
                }


            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        // return totlhwreg;
    }

    void CalculateNoOFLogsPerDate() {
        //a=4 logs 
        //b1=2 in/out1 logs
        //b2=2 in/out2 logs
        //c=inconsistent logs (3,2 but inconsistent logs or no logs at all)

        int r = dr.getRowCount();
        int c;
        c = 0;


        while (c < r + 1) {
            try {
                String tlin1 = dr.getValueAt(c, 2).toString();
                String tlout1 = dr.getValueAt(c, 3).toString();
                String tlin2 = dr.getValueAt(c, 4).toString();
                String tlout2 = dr.getValueAt(c, 5).toString();

                int cntr = 0;
                if (!"".equals(tlin1)) {
                    cntr++;
                }
                if (!"".equals(tlout1)) {
                    cntr++;
                }
                if (!"".equals(tlin2)) {
                    cntr++;
                }
                if (!"".equals(tlout2)) {
                    cntr++;
                }

                String flg = "";

                if (cntr == 4) {
                    flg = "a";
                } else if (cntr == 2) {
                    if (!"".equals(tlin1) && !"".equals(tlout1)) {
                        flg = "b1";
                    } else if (!"".equals(tlin2) && !"".equals(tlout2)) {
                        flg = "b2";
                    } else {
                        flg = "c";
                    }
                } else if (cntr == 3) {
                    flg = "c";
                } else if (cntr == 1) {
                    flg = "c";
                } else if (cntr == 0) {
                    flg = "c";
                }


                dr.setValueAt(flg, c, 9);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }


    }

    void CalculateTotal() {
        int r = dr.getRowCount();
        int c;
        c = 0;

        while (c < r + 1) {
            try {
                String tlin1 = dr.getValueAt(c, 2).toString();
                String tlout1 = dr.getValueAt(c, 3).toString();
                String tlin2 = dr.getValueAt(c, 4).toString();
                String tlout2 = dr.getValueAt(c, 5).toString();
                String flg = dr.getValueAt(c, 9).toString();


                int io1hh = 0;
                int io1mm = 0;
                int io2hh = 0;
                int io2mm = 0;
                int u1hh = 0;
                int u2hh = 0;
                int u1mm = 0;
                int u2mm = 0;


                if ("a".equals(flg)) {
                    //CALCULATE IN/OUT1 TOTAL HOURS AND MINUTES
                    //Normal Shift Out1 Less Time Log In1 
                    io1hh = myFunction.getHourDiff(tlin1, out1);

                    if (io1hh < 4) {
                        io1mm = myFunction.getMinuteDiff(tlin1, out1);
                    }
                    if (io1hh > 4) {
                        io1hh = 4;
                    }
                    //Calculate Under Time (Normal Shift Out1 Less Time Log Out1)
                    u1hh = myFunction.getHourDiff(tlout1, out1);

                    if (u1hh < 0) {
                        u1hh = 0;
                    }

                    u1mm = myFunction.getMinuteDiff(tlout1, out1);
                    if (u1mm < 0) {
                        u1mm = 0;
                    }
                    //CALCULATE IN/OUT2 TOTAL HOURS AND MINUTES

                    //Normal Shift Out2 Less Time Log In2 
                    io2hh = myFunction.getHourDiff(tlin2, out2);

                    if (io2hh < 4) {
                        io2mm = myFunction.getMinuteDiff(tlin2, out2);
                    }
                    if (io2hh > 4) {
                        io2hh = 4;
                    }
                    //Calculate Under Time (Normal Shift Out2 Less Time Log Out2)
                    u2hh = myFunction.getHourDiff(tlout2, out2);

                    if (u2hh < 0) {
                        u2hh = 0;
                    }

                    u2mm = myFunction.getMinuteDiff(tlout2, out2);
                    if (u2mm < 0) {
                        u2mm = 0;
                    }
                } else if ("b1".equals(flg)) {
                    //CALCULATE IN/OUT1 TOTAL HOURS AND MINUTES
                    //Normal Shift Out1 Less Time Log In1 
                    io1hh = myFunction.getHourDiff(tlin1, out1);

                    if (io1hh < 4) {
                        io1mm = myFunction.getMinuteDiff(tlin1, out1);
                    }
                    if (io1hh > 4) {
                        io1hh = 4;
                    }
                    //Calculate Under Time (Normal Shift Out1 Less Time Log Out1)
                    u1hh = myFunction.getHourDiff(tlout1, out1);

                    if (u1hh < 0) {
                        u1hh = 0;
                    }

                    u1mm = myFunction.getMinuteDiff(tlout1, out1);
                    if (u1mm < 0) {
                        u1mm = 0;
                    }
                } else if ("b2".equals(flg)) {
                    //CALCULATE IN/OUT2 TOTAL HOURS AND MINUTES

                    //Normal Shift Out2 Less Time Log In2 
                    io2hh = myFunction.getHourDiff(tlin2, out2);

                    if (io2hh < 4) {
                        io2mm = myFunction.getMinuteDiff(tlin2, out2);
                    }
                    if (io2hh > 4) {
                        io2hh = 4;
                    }
                    //Calculate Under Time (Normal Shift Out2 Less Time Log Out2)
                    u2hh = myFunction.getHourDiff(tlout2, out2);

                    if (u2hh < 0) {
                        u2hh = 0;
                    }

                    u2mm = myFunction.getMinuteDiff(tlout2, out2);
                    if (u2mm < 0) {
                        u2mm = 0;
                    }
                }
                int totalhh = 0;
                int totalmm = 0;
                int totalInMinutes = 0;
                int totaluhh = 0;
                int totalumm = 0;
                int totalUnderTym = 0;


                //TOTAL MINUTES
                totalhh = io1hh + io2hh;
                totalmm = io1mm + io2mm;
                totalInMinutes = (totalhh * 60) + totalmm;
                //TOTAL UNDERTIME
                totaluhh = u1hh + u2hh;
                totalumm = u1mm + u2mm;
                totalUnderTym = (totaluhh * 60) + totalumm;

                totalInMinutes = totalInMinutes - totalUnderTym;

                dr.setValueAt(totalInMinutes, c, 7);
                //dr.setValueAt(totalUnderTym, c, 8);

                //System.out.println(date + "-" + day + ":" + tlin1 + ":" + tlout1 + ":" + tlin2 + ":" + tlout2);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbpp = new javax.swing.JComboBox();
        cmdcalculate = new javax.swing.JButton();
        yr = new com.toedter.calendar.JYearChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        load = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        cmdsave = new javax.swing.JButton();
        cmdsave1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Calculate Hours Worked");

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        cmbpp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbppActionPerformed(evt);
            }
        });

        cmdcalculate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logs.png"))); // NOI18N
        cmdcalculate.setMnemonic('A');
        cmdcalculate.setText("Calculate");
        cmdcalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcalculateActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbpp, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdcalculate))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbpp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcalculate))
        );

        jLabel1.setText("Payroll Period:");

        jLabel2.setText("Year:");

        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("0% In Progress");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name of Employee", "REG", "OTRD", "OTSAT", "OTSUN", "OTH"
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
        tbl1.setColumnSelectionAllowed(true);
        tbl1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl1);
        tbl1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl1.getColumnModel().getColumn(0).setResizable(false);
        tbl1.getColumnModel().getColumn(1).setPreferredWidth(300);

        cmdsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdsave.setMnemonic('S');
        cmdsave.setText("Save Now");
        cmdsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsaveActionPerformed(evt);
            }
        });

        cmdsave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdsave1.setMnemonic('x');
        cmdsave1.setText("Exit");
        cmdsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsave1ActionPerformed(evt);
            }
        });

        jLabel3.setDisplayedMnemonic('i');
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setLabelFor(tbl2);
        jLabel3.setText("List of Employee/s with inconsistent time logs (Found: 0)");

        jLabel4.setDisplayedMnemonic('c');
        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setLabelFor(tbl1);
        jLabel4.setText("List of Employee/s with complete time logs (Found: 0)");
        jLabel4.setToolTipText("");

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name of Employee"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl2.setColumnSelectionAllowed(true);
        tbl2.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbl2);
        tbl2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl2.getColumnModel().getColumn(0).setResizable(false);
        tbl2.getColumnModel().getColumn(1).setResizable(false);
        tbl2.getColumnModel().getColumn(1).setPreferredWidth(300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(cmdsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdsave1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdsave)
                    .addComponent(cmdsave1))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbppActionPerformed
        try {
            ppid = ((PayrollPeriod) cmbpp.getSelectedItem()).getPayrollPeriodId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbppActionPerformed

    private void cmdcalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcalculateActionPerformed
       if ("--SELECT--".equals(cmbpp.getSelectedItem().toString())){
           JOptionPane.showMessageDialog(this, "Please select a payroll period!");
       }else{    
        model.setNumRows(0);
        MakeLoading();
        loadpercentage = 0;
        totlpercen = 0;
        CalculatePay();
       }
    }//GEN-LAST:event_cmdcalculateActionPerformed

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

    private void yrVetoableChange(java.beans.PropertyChangeEvent evt) throws java.beans.PropertyVetoException {//GEN-FIRST:event_yrVetoableChange
   }//GEN-LAST:event_yrVetoableChange

    private void cmdsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdsaveActionPerformed

    private void cmdsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsave1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdsave1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbpp;
    private javax.swing.JButton cmdcalculate;
    private javax.swing.JButton cmdsave;
    private javax.swing.JButton cmdsave1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JProgressBar load;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private com.toedter.calendar.JYearChooser yr;
    // End of variables declaration//GEN-END:variables
}
