package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Department;
import mod.model.entity_manager.DeptEM;

public class DeptC {

    //COMBO BOX POPULATOR
    public DefaultComboBoxModel getDeptComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Department> depts = new DeptEM(con).get();

            model.addElement("--SELECT--");

            for (Department dept : depts) {

                model.addElement(dept);
            }
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //COMBO BOX POPULATOR no image
    public DefaultComboBoxModel getDeptComboModel2() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Department> depts = new DeptEM(con).get();

            model.addElement("--SELECT--");

            for (Department dept : depts) {
                model.addElement(dept);
            }
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //LIST BOX POPULATOR
    public DefaultListModel getDeptListModel() {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Department> depts = new DeptEM(con).get();

            for (Department dept : depts) {

                model.addElement(dept);
            }
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addDept(Department dept) {
        Connection con = myDBConn.getConnection();
        DeptEM dem = new DeptEM(con);
        return dem.persist(dept);
    }

    public int editDept(Department dept) {
        Connection con = myDBConn.getConnection();
        DeptEM dem = new DeptEM(con);
        return dem.update(dept);
    }

    public int deleteDept(Department dept) {
        Connection con = myDBConn.getConnection();
        DeptEM dem = new DeptEM(con);
        return dem.delete(dept);
    }
    //END OF APPENDERS
    
    public String GetDeptName(int did) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Department> Departments = new DeptEM(con).getbyDeptId(did);

            for (Department Department : Departments) {
                Value = String.valueOf(Department);
            }

        } catch (Exception ex) {
            Logger.getLogger(DivC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
