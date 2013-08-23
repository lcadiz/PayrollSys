/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;
import mod.model.EmployeeLoan;
import mod.model.entity_manager.EmpLoanEM;

/**
 *
 * @author LESTER
 */
public class EmployeeLoanC {
    //APPENDERS

    public int addEmployeeLoan(EmployeeLoan emploan) {
        Connection con = myDBConn.getConnection();
        EmpLoanEM dem = new EmpLoanEM(con);
        return dem.persist(emploan);
    }

    public int editEmployeeLoan(EmployeeLoan emploan) {
        Connection con = myDBConn.getConnection();
        EmpLoanEM dem = new EmpLoanEM(con);
        return dem.update(emploan);
    }

    public int deleteEmployeeLoan(EmployeeLoan emploan) {
        Connection con = myDBConn.getConnection();
        EmpLoanEM dem = new EmpLoanEM(con);
        return dem.delete(emploan);
    }

    public Date GetDueDate(int elid) {
        Date Value = null;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeLoan> els = new EmpLoanEM(con).getbyId2(elid);

            for (EmployeeLoan el : els) {
                Value = new java.sql.Date(el.getDueDate().getTime());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeLoanC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetMonhtly(int elid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeLoan> els = new EmpLoanEM(con).getbyId2(elid);

            for (EmployeeLoan el : els) {
                Value = String.valueOf(el.getMonthly());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeLoanC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
