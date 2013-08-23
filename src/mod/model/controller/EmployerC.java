package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Employer;
import mod.model.entity_manager.EmployerEM;

public class EmployerC {
    //COMBO BOX POPULATOR

    public DefaultComboBoxModel getEmployerComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Employer> employers = new EmployerEM(con).get();

            model.addElement("--SELECT--");

            for (Employer employer : employers) {

                model.addElement(employer);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployerC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //LIST BOX POPULATOR
    public DefaultListModel getEmployerListModel() {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Employer> employers = new EmployerEM(con).get();

            for (Employer employer : employers) {

                model.addElement(employer);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployerC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addEmployer(Employer employer) {
        Connection con = myDBConn.getConnection();
        EmployerEM dem = new EmployerEM(con);
        return dem.persist(employer);
    }

    public int editEmployer(Employer employer) {
        Connection con = myDBConn.getConnection();
        EmployerEM dem = new EmployerEM(con);
        return dem.update(employer);
    }

    public int deleteEmployer(Employer employer) {
        Connection con = myDBConn.getConnection();
        EmployerEM dem = new EmployerEM(con);
        return dem.delete(employer);
    }
    //END OF APPENDERS

    public String GetEmpto(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employer> employers = new EmployerEM(con).getbyId(eid);

            for (Employer employer : employers) {
                Value = String.valueOf(employer);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
