package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Division;
import mod.model.entity_manager.DivEM;

public class DivC {

    //COMBO BOX POPULATOR
    public DefaultComboBoxModel getDivComboModel(int deptid) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Division> divs = new DivEM(con).getByDeptId(deptid);

            model.addElement("--SELECT--");

            for (Division div : divs) {
                model.addElement(div);
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
            ArrayList<Division> divs= new DivEM(con).get();

            for (Division div : divs) {

                model.addElement(div);
            }
        } catch (Exception ex) {
            Logger.getLogger(DeptC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addDivision(Division div) {
        Connection con = myDBConn.getConnection();
        DivEM dem = new DivEM(con);
        return dem.persist(div);
    }

    public int editDiv(Division div) {
        Connection con = myDBConn.getConnection();
        DivEM dem = new DivEM(con);
        return dem.update(div);
    }

    public int deleteDiv(Division div) {
        Connection con = myDBConn.getConnection();
        DivEM dem = new DivEM(con);
        return dem.delete(div);
    }
    //END OF APPENDERS
    
     public String GetDivName(int did) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Division> Divisions = new DivEM(con).getByDivId(did);

            for (Division Division : Divisions) {
                Value = String.valueOf(Division);
            }

        } catch (Exception ex) {
            Logger.getLogger(DivC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
     
      public int GetDeptId(int did) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Division> Divisions = new DivEM(con).getByDivId(did);

            for (Division Division : Divisions) {
                Value = Division.getDeptId();
            }

        } catch (Exception ex) {
            Logger.getLogger(DivC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
