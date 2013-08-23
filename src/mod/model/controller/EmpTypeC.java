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
import mod.connection.myDBConn;
import mod.model.EmploymentType;
import mod.model.entity_manager.EmpTypeEM;

/**
 *
 * @author LESTER
 */
public class EmpTypeC {
    //LIST BOX POPULATOR

    public DefaultComboBoxModel getEmpTypeComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<EmploymentType> emptypes = new EmpTypeEM(con).get();

            model.addElement("--SELECT--");

            for (EmploymentType emptype : emptypes) {

                model.addElement(emptype);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    public DefaultComboBoxModel getEmpTypeComboModel2() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        //model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<EmploymentType> emptypes = new EmpTypeEM(con).get();

            model.addElement("--SELECT--");

            for (EmploymentType emptype : emptypes) {

                model.addElement(emptype);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    public String GetEmpTypeDesc(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmploymentType> emptypes = new EmpTypeEM(con).getbyId(eid);

            for (EmploymentType emptype : emptypes) {
                Value = String.valueOf(emptype);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
