/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.gui.employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import mod.connection.myDBConn;
import mod.main.mdi;
import mod.model.*;
import mod.model.controller.*;
import mod.others.myFunction;

public class employee_add extends javax.swing.JInternalFrame {

    static int deptid, divid, secid, etype, eto, rv, qf;
    static String nowDate = myFunction.getDate();
    public static dependent_add frmNew;
    public static dependent_edit frmEdit;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    public static String taxcode;
    static Statement stmt;
    Employee emp = new Employee();
    EmployeeC empc = new EmployeeC();
    EmployeeDependent dependent = new EmployeeDependent();
    DependentC dependentc = new DependentC();
    EmployeeLog emplog = new EmployeeLog();
    EmployeeLogC emplogc = new EmployeeLogC();
    static char g;

    public employee_add() {
        initComponents();
        model = (DefaultTableModel) tbl.getModel();
        setComboEmpTypeModel();
        setComboEmployerTypeModel();
        cmbpos.addItem("--SELECT--");
        setDeptComboModel();
        setDivComboModel(0);
        setSecComboModel(0);
        setdates();
        setTaxCode();
    }

    void setTaxCode() {
        int dc = model.getRowCount();
        taxcode = "D" + dc;
        lbltaxcode.setText("Tax Code is D" + dc);
        //System.out.println(taxcode);
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

    public String getDName(int selrow) {
        String dname = model.getValueAt(selrow, 0).toString();
        return dname;
    }

    public String getDRelation(int selrow) {
        String dname = model.getValueAt(selrow, 1).toString();
        return dname;
    }

    public String getDBdate(int selrow) {
        String dname = model.getValueAt(selrow, 2).toString();
        return dname;
    }

    public void setvalueatTBL(String name, String relation, String bdate, int selrow) {
        try {

            tbl.setValueAt(name, selrow, 0);
            tbl.setValueAt(relation, selrow, 1);
            tbl.setValueAt(bdate, selrow, 2);

        } catch (Exception e) {
        }
    }

    public void ShowFrmNew() {
        frmNew = new dependent_add(this, true);
        frmNew.setVisible(true);
    }

    public void ShowFrmEdit() {
        frmEdit = new dependent_edit(this, true);
        frmEdit.setVisible(true);
    }

    void setdates() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date theDate = null;
        try {
            theDate = sdf.parse(nowDate);
        } catch (ParseException e) {
        }
        //txtdate.setDateFormatString(nowDate);
        txtdob.setDate(theDate);
        txtdoe.setDate(theDate);
    }

    void setComboEmpTypeModel() {
        cmbemptype.setModel(new EmpTypeC().getEmpTypeComboModel());
    }

    void setComboEmployerTypeModel() {
        cmbemployer.setModel(new EmployerC().getEmployerComboModel());
    }

    void setComboPositionModel() {
        cmbpos.setModel(new PositionC().getPositionComboModel(secid));
    }

    void setDeptComboModel() {
        cmbdept.setModel(new DeptC().getDeptComboModel());
    }

    void setDivComboModel(int did) {
        cmbdiv.setModel(new DivC().getDivComboModel(did));
    }

    void setSecComboModel(int did) {
        cmbsec.setModel(new SectionC().getSecComboModel(did));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gender = new javax.swing.ButtonGroup();
        civilstat = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtdoe = new com.toedter.calendar.JDateChooser();
        cmbemptype = new javax.swing.JComboBox();
        cmbemployer = new javax.swing.JComboBox();
        cmbpos = new javax.swing.JComboBox();
        cmbdept = new javax.swing.JComboBox();
        cmbdiv = new javax.swing.JComboBox();
        cmbsec = new javax.swing.JComboBox();
        txtsss = new javax.swing.JTextField();
        txtphic = new javax.swing.JTextField();
        txthdmf = new javax.swing.JTextField();
        txttin = new javax.swing.JTextField();
        r = new javax.swing.JPanel();
        l2 = new javax.swing.JLabel();
        chckqualified = new javax.swing.JCheckBox();
        txtstep = new javax.swing.JFormattedTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtcontactno = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtremarks = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdaddd = new javax.swing.JButton();
        cmdeditd = new javax.swing.JButton();
        cmdremoved = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        lbltaxcode = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtsuffix = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cmdadd = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Add New Employee");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addemp.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 0));
        jLabel22.setText("EMPLOYMENT INFORMATION");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 0));
        jLabel11.setText("PERSONAL INFORMATION");

        txtdoe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdoe.setDateFormatString("MM/dd/yyyy");

        cmbemptype.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbemptype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbemptypeActionPerformed(evt);
            }
        });

        cmbemployer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbemployer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbemployerActionPerformed(evt);
            }
        });

        cmbpos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbpos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbposActionPerformed(evt);
            }
        });

        cmbdept.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdeptActionPerformed(evt);
            }
        });

        cmbdiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbdiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdivActionPerformed(evt);
            }
        });

        cmbsec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120)));
        cmbsec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsecActionPerformed(evt);
            }
        });

        txtsss.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtphic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txthdmf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txttin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        l2.setText("STEP:");

        chckqualified.setMnemonic('Q');
        chckqualified.setSelected(true);
        chckqualified.setText("Employee is Qualified for this position");

        txtstep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtstep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtstep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstep.setText("0");

        javax.swing.GroupLayout rLayout = new javax.swing.GroupLayout(r);
        r.setLayout(rLayout);
        rLayout.setHorizontalGroup(
            rLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtstep, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chckqualified, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rLayout.setVerticalGroup(
            rLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chckqualified)
                    .addComponent(l2)
                    .addComponent(txtstep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsss, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphic, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthdmf, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttin, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdoe, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbemptype, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbemployer, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbsec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbdept, 0, 411, Short.MAX_VALUE)
                        .addComponent(cmbdiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cmbpos, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(891, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtdoe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbemptype, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbemployer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbdiv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbsec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbpos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(txtsss, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtphic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthdmf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jLabel23.setDisplayedMnemonic('f');
        jLabel23.setLabelFor(txtdoe);
        jLabel23.setText("Date of Employment:");

        jLabel24.setDisplayedMnemonic('o');
        jLabel24.setLabelFor(cmbemptype);
        jLabel24.setText("Employment Type:");

        jLabel25.setDisplayedMnemonic('l');
        jLabel25.setLabelFor(cmbemployer);
        jLabel25.setText("Employed to:");

        jLabel26.setDisplayedMnemonic('s');
        jLabel26.setLabelFor(cmbpos);
        jLabel26.setText("Plantilla Position:");

        jLabel27.setText("Department:");

        jLabel28.setText("Division:");

        jLabel29.setText("Section:");

        jLabel30.setText("SSS No.:");

        jLabel31.setText("PHIC No.:");

        jLabel32.setText("HDMF No.:");

        jLabel33.setText("BIR TIN No.:");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 0));
        jLabel35.setText("CONTACT INFORMATION");

        txtcontactno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtcontactno))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtcontactno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel36.setText("Contact No.");

        jLabel37.setText("E-mail Address.:");

        jLabel40.setDisplayedMnemonic('y');
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 102, 0));
        jLabel40.setLabelFor(txtbloodtype);
        jLabel40.setText("*");
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel41.setDisplayedMnemonic('y');
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 102, 0));
        jLabel41.setLabelFor(txtbloodtype);
        jLabel41.setText("*");

        jLabel42.setDisplayedMnemonic('y');
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 102, 0));
        jLabel42.setLabelFor(txtbloodtype);
        jLabel42.setText("*");

        jLabel43.setDisplayedMnemonic('y');
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 102, 0));
        jLabel43.setLabelFor(txtbloodtype);
        jLabel43.setText("*");

        jLabel44.setDisplayedMnemonic('y');
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 102, 0));
        jLabel44.setLabelFor(txtbloodtype);
        jLabel44.setText("*");

        jLabel45.setDisplayedMnemonic('y');
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 102, 0));
        jLabel45.setLabelFor(txtbloodtype);
        jLabel45.setText("*");

        jLabel39.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 102, 0));
        jLabel39.setText("OTHERS");

        txtremarks.setText("New Employee Record");
        txtremarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel46.setText("Remarks:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37))
                                .addGap(40, 40, 40)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(75, 75, 75)
                                .addComponent(txtremarks, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel41)))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtremarks, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        txtdob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtdob.setDateFormatString("MM/dd/yyyy");

        txtpob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        gender.add(optmale);
        optmale.setMnemonic('M');
        optmale.setSelected(true);
        optmale.setText("Male");

        gender.add(optfemale);
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

        txtprovince.setText("NEGROS ORIENTAL");
        txtprovince.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel17.setForeground(new java.awt.Color(204, 102, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Province");

        txtcountry.setText("PHILIPPINES");
        txtcountry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel18.setForeground(new java.awt.Color(204, 102, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Country");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dependent Name", "Relation", "Date of Birth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(200);

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

        jLabel20.setForeground(new java.awt.Color(204, 102, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Zip Code");

        lbltaxcode.setForeground(new java.awt.Color(102, 0, 0));
        lbltaxcode.setLabelFor(tbl);
        lbltaxcode.setText("Tax Code is D0");
        lbltaxcode.setToolTipText("");

        txtsuffix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel1.setForeground(new java.awt.Color(204, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Last Name");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtlname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfname)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmname)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtsuffix)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtsuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(22, 22, 22))
        );

        txtempno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        civilstat.add(optsingle);
        optsingle.setMnemonic('M');
        optsingle.setSelected(true);
        optsingle.setText("Single");

        civilstat.add(optmarried);
        optmarried.setMnemonic('F');
        optmarried.setText("Married");

        civilstat.add(optwidowed);
        optwidowed.setMnemonic('F');
        optwidowed.setText("Widowed");

        civilstat.add(optseparated);
        optseparated.setMnemonic('F');
        optseparated.setText("Separated");
        optseparated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optseparatedActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdremoved, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdeditd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdaddd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbltaxcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpob, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtspouse, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtempno, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbloodtype, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtprovince, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                .addComponent(txthouseno, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtstreet)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(optsingle)
                                            .addComponent(optmale))
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(optfemale)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(optmarried)
                                                .addGap(18, 18, 18)
                                                .addComponent(optwidowed)
                                                .addGap(18, 18, 18)
                                                .addComponent(optseparated)))))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtbrgy, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtcity)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtcountry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtzipcode)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(optannulled)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtempno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpob, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optmale)
                    .addComponent(optfemale))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optsingle)
                    .addComponent(optmarried)
                    .addComponent(optwidowed)
                    .addComponent(optseparated)
                    .addComponent(optannulled))
                .addGap(14, 14, 14)
                .addComponent(txtbloodtype, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txthouseno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtstreet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtcity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtbrgy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtprovince, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcountry, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtzipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(txtspouse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmdaddd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdeditd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdremoved))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltaxcode))
        );

        jLabel2.setDisplayedMnemonic('f');
        jLabel2.setLabelFor(txtlname);
        jLabel2.setText("Name of Employee:");

        jLabel3.setDisplayedMnemonic('h');
        jLabel3.setLabelFor(txtdob);
        jLabel3.setText("Date of Birth:");

        jLabel4.setDisplayedMnemonic('P');
        jLabel4.setLabelFor(txtpob);
        jLabel4.setText("Place of Birth:");

        jLabel8.setText("Gender:");

        jLabel9.setDisplayedMnemonic('y');
        jLabel9.setLabelFor(txtbloodtype);
        jLabel9.setText("Blood Type:");

        jLabel10.setDisplayedMnemonic('u');
        jLabel10.setLabelFor(txtspouse);
        jLabel10.setText("Name of Spouse:");

        jLabel13.setDisplayedMnemonic('e');
        jLabel13.setLabelFor(txthouseno);
        jLabel13.setText("Address:");

        jLabel19.setDisplayedMnemonic('t');
        jLabel19.setLabelFor(tbl);
        jLabel19.setText("Dependents:");
        jLabel19.setToolTipText("");

        jLabel21.setDisplayedMnemonic('y');
        jLabel21.setLabelFor(txtbloodtype);
        jLabel21.setText("I.D No.:");

        jLabel34.setDisplayedMnemonic('y');
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 102, 0));
        jLabel34.setLabelFor(txtbloodtype);
        jLabel34.setText("*");

        jLabel38.setDisplayedMnemonic('y');
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 102, 0));
        jLabel38.setLabelFor(txtbloodtype);
        jLabel38.setText("*");

        jLabel47.setText("Civil Status:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel13)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        cmdadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        cmdadd.setMnemonic('A');
        cmdadd.setText("Add");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdcancel, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdadd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdcancel)
                .addContainerGap(530, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdaddActionPerformed
        String lname = txtlname.getText().replace("'", " ").toUpperCase();
        String fname = txtfname.getText().replace("'", " ").toUpperCase();
        String mname = txtmname.getText().replace("'", " ").toUpperCase();
        String suffix = txtsuffix.getText().replace("'", " ").toUpperCase();
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

        if (lname.isEmpty() == true || fname.isEmpty() == true || cmbsec.getSelectedItem() == "--SELECT--" || cmbemptype.getSelectedItem() == "--SELECT--" || cmbemployer.getSelectedItem() == "--SELECT--" || txtremarks.getText().isEmpty() == true) {
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
            emp.setSecId(secid);
            emp.setEmpTypeId(Short.parseShort(String.valueOf(etype)));
            emp.setEmployerId(eto);
            emp.setStatusFlag(Short.parseShort("1"));
            //emp.setRank(Short.parseShort(String.valueOf(rank)));
            emp.setStep(Short.parseShort(String.valueOf(step)));
            emp.setPositionId(Short.parseShort(String.valueOf(rv)));
            emp.setQualifiedFlag(Short.parseShort(String.valueOf(qf)));
            emp.setEmpNo(emp_no);
            emp.setCivilStatusId(cs);


            int lastid = empc.addEmployee(emp);
            if (lastid > 0) {
                addDependents(lastid); //add all dependents
                //ADD EMPLOG
                emplog.setEmpId(lastid);
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
                emplog.setContactNo(txtcontactno.getText());
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
                emplog.setSecId(secid);
                emplog.setEmpTypeId(Short.parseShort(String.valueOf(etype)));
                emplog.setEmployerId(eto);
                emplog.setStatusFlag(Short.parseShort("1"));
                //emplog.setRank(Short.parseShort(String.valueOf(rank)));
                emplog.setStep(Short.parseShort(String.valueOf(step)));
                emplog.setPositionId(Short.parseShort(String.valueOf(rv)));
                emplog.setQualifiedFlag(Short.parseShort(String.valueOf(qf)));
                emplog.setRemarks(txtremarks.getText());
                emplog.setUserId(mdi.getUserID());
                emplog.setCivilStatusId(cs);

                if (emplogc.addEmployeeLog(emplog) > 0) {
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save employee log");
                }

                this.dispose();
                JOptionPane.showMessageDialog(null, "New Employee Record Successfully Added!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save employee record");
            }


        }
    }//GEN-LAST:event_cmdaddActionPerformed

    public void addDependents(int eid) {
        int r = tbl.getRowCount();
        int c;
        c = 0;

        if (r != 0) {
            while (c < r + 1) {
                try {
                    String dnym = tbl.getValueAt(c, 0).toString();
                    String drelation = tbl.getValueAt(c, 1).toString();
                    String dbdate = tbl.getValueAt(c, 2).toString();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date bdate = dateFormat.parse(dbdate);

                    dependent.setEmpId(eid);
                    dependent.setDependentName(dnym);
                    dependent.setRelation(drelation);
                    dependent.setBirthdate(bdate);

                    if (dependentc.addDependent(dependent) > 0) {
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to save Dependent");
                    }

                } catch (Exception e) {
                    e.getStackTrace();
                }
                c++;
            }
        }
    }

    public static int getLastID() {
        int id = 0;
        Connection conn = myDBConn.getConnection();
        String createString;
        //createString = "SELECT MAX(emp_id) FROM employee";
        createString = "SELECT last_insert_id()";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                id = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return id;
    }

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void cmbdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdeptActionPerformed
        deptid = 0;
        try {
            int id = deptid = ((Department) cmbdept.getSelectedItem()).getDeptId();
            setDivComboModel(id);
            //JOptionPane.showMessageDialog(null,  deptid);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cmbdeptActionPerformed

    private void cmbsecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsecActionPerformed
        secid = 0;
        try {
            secid = ((Section) cmbsec.getSelectedItem()).getSecId();
            setComboPositionModel();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbsecActionPerformed

    private void cmbdivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdivActionPerformed
        divid = 0;
        try {
            int id = divid = ((Division) cmbdiv.getSelectedItem()).getDivId();
            setSecComboModel(id);
            //JOptionPane.showMessageDialog(null,  deptid);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbdivActionPerformed

    private void cmbemptypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbemptypeActionPerformed
        try {
            etype = ((EmploymentType) cmbemptype.getSelectedItem()).getEmpTypeId();
            // JOptionPane.showMessageDialog(null,  etype);
            if (etype > 1) {
                cmbpos.removeAllItems();
//            String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
//                        + "<tr><td><img src=" + getClass().getResource("/images/na.png") + ">&nbsp</td><td>NOT APPLICABLE<font></td></th>";
                cmbpos.addItem("NOT APPLICABLE");
                cmbpos.setEnabled(false);
                //    txtrank.setEnabled(false);
                txtstep.setEnabled(false);
                // l1.setEnabled(false);
                l2.setEnabled(false);
                chckqualified.setEnabled(false);
                cmdaddd.setEnabled(false);
                model.setNumRows(0);
            } else {
                cmbpos.setEnabled(true);
                setComboPositionModel();
                chckqualified.setEnabled(true);
                //  txtrank.setEnabled(true);
                txtstep.setEnabled(true);
                //  l1.setEnabled(true);
                l2.setEnabled(true);
                cmdaddd.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbemptypeActionPerformed

    private void cmdadddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdadddActionPerformed
        int dc = model.getRowCount();
        if (dc == 4) {
            JOptionPane.showMessageDialog(this, "Only 4 dependents is allowed!");
        } else {
            ShowFrmNew();
        }

    }//GEN-LAST:event_cmdadddActionPerformed
    private void cmdeditdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdeditdActionPerformed
        try {
            int row = tbl.getSelectedRow(); //get value of selected value

            //trap user incase if no row selected
            if (tbl.isRowSelected(row) != true) {
                JOptionPane.showMessageDialog(this, "Select a Range!");
            } else {

                dependent_edit.selrow = row;
                ShowFrmEdit();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Select a Dependent Record!");
        }
    }//GEN-LAST:event_cmdeditdActionPerformed

    private void cmdremovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdremovedActionPerformed
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "Select a Dependent Record!");
        } else {
            int i = myFunction.msgboxYesNo("Delete this current dependent?");
            if (i == 0) {
                model.removeRow(row);
                setTaxCode();
            } else if (i == 1) {
            } else if (i == 2) {
                this.dispose(); //exit window
            }

        }
    }//GEN-LAST:event_cmdremovedActionPerformed

    public static String getRankValue(int bid) {
        String totl = "";
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT count(voter_id) FROM vms_db.voter WHERE brgy_id=" + bid;



        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                totl = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }


        return totl;
    }

    private void cmbposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbposActionPerformed

        try {

            rv = ((Position) cmbpos.getSelectedItem()).getPositionId();
            //txtrank.setText(String.valueOf(rv));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbposActionPerformed

    private void cmbemployerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbemployerActionPerformed
        try {
            eto = ((Employer) cmbemployer.getSelectedItem()).getEmployerId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbemployerActionPerformed

    private void txtbloodtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbloodtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbloodtypeActionPerformed

    private void optseparatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optseparatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optseparatedActionPerformed

    private void optannulledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optannulledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optannulledActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chckqualified;
    private javax.swing.ButtonGroup civilstat;
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JComboBox cmbdiv;
    private javax.swing.JComboBox cmbemployer;
    private javax.swing.JComboBox cmbemptype;
    private javax.swing.JComboBox cmbpos;
    private javax.swing.JComboBox cmbsec;
    private javax.swing.JButton cmdadd;
    private javax.swing.JButton cmdaddd;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmdeditd;
    private javax.swing.JButton cmdremoved;
    private javax.swing.ButtonGroup gender;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel lbltaxcode;
    private javax.swing.JRadioButton optannulled;
    private javax.swing.JRadioButton optfemale;
    private javax.swing.JRadioButton optmale;
    private javax.swing.JRadioButton optmarried;
    private javax.swing.JRadioButton optseparated;
    private javax.swing.JRadioButton optsingle;
    private javax.swing.JRadioButton optwidowed;
    private javax.swing.JPanel r;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtbloodtype;
    private javax.swing.JTextField txtbrgy;
    private javax.swing.JTextField txtcity;
    private javax.swing.JTextField txtcontactno;
    private javax.swing.JTextField txtcountry;
    private com.toedter.calendar.JDateChooser txtdob;
    private com.toedter.calendar.JDateChooser txtdoe;
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
    private javax.swing.JTextField txtspouse;
    private javax.swing.JTextField txtsss;
    private javax.swing.JFormattedTextField txtstep;
    private javax.swing.JTextField txtstreet;
    private javax.swing.JTextField txtsuffix;
    private javax.swing.JTextField txttin;
    private javax.swing.JFormattedTextField txtzipcode;
    // End of variables declaration//GEN-END:variables
}
