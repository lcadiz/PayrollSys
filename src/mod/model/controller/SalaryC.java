/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
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
import mod.model.Position;
import mod.model.Salary;
import mod.model.entity_manager.DivEM;
import mod.model.entity_manager.PositionEM;
import mod.model.entity_manager.SalaryEM;
import mod.others.myFunction;

/**
 *
 * @author LESTER
 */
public class SalaryC {
    //TABLE POPULATOR

    public DefaultTableModel getSalaryTableModel(JTable tbl) {
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
        tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(1).setMaxWidth(100);
        tbl.getColumnModel().getColumn(2).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(3).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(4).setCellRenderer(AlignRight);

        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).get();

            int rc = 1;

            for (Salary salary : salarys) {
                Object[] newCol = new Object[5];

                int i = 0;
                newCol[i++] = salary.getSalaryId();
                newCol[i++] = rc;
                newCol[i++] = salary.getRankValue().toString();
                newCol[i++] = myFunction.amountFormat2(salary.getSalaryBase().toString());
                newCol[i++] = myFunction.amountFormat2(salary.getStepIncValue().toString());

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(SalaryC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
        //COMBO BOX POPULATOR
    public DefaultComboBoxModel getSalaryComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).get();

            model.addElement("--SELECT--");

            for (Salary salary : salarys) {
                model.addElement(salary);
            }
           // model.setSelectedItem("Rank 13");
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
          //COMBO BOX POPULATOR
    public DefaultComboBoxModel getSalaryComboModelonEdit(String sid) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).get();

            model.addElement("--SELECT--");

            for (Salary salary : salarys) {
                model.addElement(salary);
            }
            String rv=GetRankValue(Integer.parseInt(sid));
            model.setSelectedItem(rv);
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    

    //APPENDERS
    public int addSalary(Salary salary) {
        Connection con = myDBConn.getConnection();
        SalaryEM dem = new SalaryEM(con);
        return dem.persist(salary);
    }

    public int editSalary(Salary salary) {
        Connection con = myDBConn.getConnection();
        SalaryEM dem = new SalaryEM(con);
        return dem.update(salary);
    }

    public int deleteSalary(Salary salary) {
        Connection con = myDBConn.getConnection();
        SalaryEM dem = new SalaryEM(con);
        return dem.delete(salary);
    }

    public int deleteAll(Salary salary) {
        Connection con = myDBConn.getConnection();
        SalaryEM dem = new SalaryEM(con);
        return dem.deleteAll(salary);
    }

    public String GetRankValue(int salaryid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).getSalaryId(salaryid);

            for (Salary salary : salarys) {
                Value = String.valueOf(salary.getRankValue());
            }

        } catch (Exception ex) {
            Logger.getLogger(SalaryC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetStepIncValue(int salaryid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).getSalaryId(salaryid);

            for (Salary salary : salarys) {
                Value = String.valueOf(salary.getStepIncValue());
            }

        } catch (Exception ex) {
            Logger.getLogger(SalaryC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetMaxSalaryBase(int salaryid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Salary> salarys = new SalaryEM(con).getSalaryId(salaryid);

            for (Salary salary : salarys) {
                Value = String.valueOf(salary.getSalaryBase());
            }

        } catch (Exception ex) {
            Logger.getLogger(SalaryC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
