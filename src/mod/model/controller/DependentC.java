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
import mod.model.EmployeeDependent;
import mod.model.entity_manager.DependentEM;

/**
 *
 * @author LESTER
 */
public class DependentC {
    //APPENDERS

    public int addDependent(EmployeeDependent emp) {
        Connection con = myDBConn.getConnection();
        DependentEM dem = new DependentEM(con);
        return dem.persist(emp);
    }

    public int editDependent(EmployeeDependent emp) {
        Connection con = myDBConn.getConnection();
        DependentEM dem = new DependentEM(con);
        return dem.update(emp);
    }
    
        public int deleteDependent(EmployeeDependent emp) {
        Connection con = myDBConn.getConnection();
        DependentEM dem = new DependentEM(con);
        return dem.delete(emp);
    }

    public String GetEdId(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeDependent> Dependents = new DependentEM(con).getbyEdId(eid);

            for (EmployeeDependent dependent : Dependents) {
                Value = String.valueOf(dependent.getEdId());
            }

        } catch (Exception ex) {
            Logger.getLogger(DependentC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
        public String GetDependentName(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeDependent> Dependents = new DependentEM(con).getbyEdId(eid);

            for (EmployeeDependent dependent : Dependents) {
                Value = String.valueOf(dependent.getDependentName());
            }

        } catch (Exception ex) {
            Logger.getLogger(DependentC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
        
            public String GetRelation(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeDependent> Dependents = new DependentEM(con).getbyEdId(eid);

            for (EmployeeDependent dependent : Dependents) {
                Value = String.valueOf(dependent.getRelation());
            }

        } catch (Exception ex) {
            Logger.getLogger(DependentC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
            
                public Date GetBdate(int eid) {
        Date Value=null;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeDependent> Dependents = new DependentEM(con).getbyEdId(eid);

            for (EmployeeDependent dependent : Dependents) {
                Value =new java.sql.Date(dependent.getBirthdate().getTime());
            }

        } catch (Exception ex) {
            Logger.getLogger(DependentC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
