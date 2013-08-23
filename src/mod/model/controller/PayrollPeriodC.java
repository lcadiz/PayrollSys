package mod.model.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.PayrollPeriod;
import mod.model.entity_manager.PayPeriodEM;

public class PayrollPeriodC {

    //COMBO BOX POPULATOR
    public DefaultComboBoxModel getPayPerComboModel(int yr) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<PayrollPeriod> paypers = new PayPeriodEM(con).getByYear(yr);

            model.addElement("--SELECT--");

            for (PayrollPeriod payper : paypers) {

                model.addElement(payper);
            }
        } catch (Exception ex) {
            Logger.getLogger(PayrollPeriodC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //TABLE POPULATOR
    public DefaultTableModel getPayrollPeriodTableModel(JTable tbl, int yr) {
        Connection con = null;

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        DefaultTableCellRenderer AlignRight = new DefaultTableCellRenderer();
        DefaultTableCellRenderer AlignCenter = new DefaultTableCellRenderer();

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);

        AlignCenter.setHorizontalAlignment(0);
        AlignRight.setHorizontalAlignment(SwingConstants.RIGHT);

        tbl.setRowHeight(28);
        //tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbl.getColumnModel().getColumn(2).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(3).setCellRenderer(AlignCenter);

        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<PayrollPeriod> PayrollPeriods = new PayPeriodEM(con).getByYear(yr);

            int rc = 1;

            for (PayrollPeriod Payroll_Period : PayrollPeriods) {
                Object[] newCol = new Object[4];

                int i = 0;
                String desc = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/ppmini.png") + "></td>&nbsp<td>" + Payroll_Period.getPayrollPeriodDesc() + "</td></th>";

                newCol[i++] = Payroll_Period.getPayrollPeriodId();
                newCol[i++] = desc;
                newCol[i++] = Payroll_Period.getStartDate();
                newCol[i++] = Payroll_Period.getEndDate();

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(PayrollPeriodC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    //APPENDERS

    public int addPayrollPeriod(PayrollPeriod Payroll_Period) {
        Connection con = myDBConn.getConnection();
        PayPeriodEM dem = new PayPeriodEM(con);
        return dem.persist(Payroll_Period);
    }

    public int editPayrollPeriod(PayrollPeriod Payroll_Period) {
        Connection con = myDBConn.getConnection();
        PayPeriodEM dem = new PayPeriodEM(con);
        return dem.update(Payroll_Period);
    }

    public int deletePayrollPeriod(PayrollPeriod Payroll_Period) {
        Connection con = myDBConn.getConnection();
        PayPeriodEM dem = new PayPeriodEM(con);
        return dem.delete(Payroll_Period);
    }

    public int deleteAll(PayrollPeriod Payroll_Period) {
        Connection con = myDBConn.getConnection();
        PayPeriodEM dem = new PayPeriodEM(con);
        return dem.deleteAll(Payroll_Period);
    }

    // END OF APPENDERS
    public Date GetStartDate(int ppid) {
        Date Value = null;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PayrollPeriod> PayrollPeriods = new PayPeriodEM(con).getPPId(ppid);

            for (PayrollPeriod PayrollPeriod : PayrollPeriods) {
                Value = new java.sql.Date(PayrollPeriod.getStartDate().getTime());
            }

        } catch (Exception ex) {
            Logger.getLogger(PayrollPeriodC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public Date GetEndDate(int ppid) {
        Date Value = null;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PayrollPeriod> PayrollPeriods = new PayPeriodEM(con).getPPId(ppid);

            for (PayrollPeriod PayrollPeriod : PayrollPeriods) {
                Value = new java.sql.Date(PayrollPeriod.getEndDate().getTime());
            }

        } catch (Exception ex) {
            Logger.getLogger(PayrollPeriodC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetDesc(int ppid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PayrollPeriod> PayrollPeriods = new PayPeriodEM(con).getPPId(ppid);

            for (PayrollPeriod PayrollPeriod : PayrollPeriods) {
                Value = String.valueOf(PayrollPeriod.getPayrollPeriodDesc());
            }

        } catch (Exception ex) {
            Logger.getLogger(PayrollPeriodC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
