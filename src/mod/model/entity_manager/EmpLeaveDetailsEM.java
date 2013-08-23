/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.EmpLeaveDetails;

import mod.model.Model;

/**
 *
 * @author MIS
 */
public class EmpLeaveDetailsEM extends EntityManager {

    public EmpLeaveDetailsEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        EmpLeaveDetails eld = (EmpLeaveDetails) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO emp_leave_details(leave_app_id, leave_date, leave_hours, emp_leave_id) "
                + "VALUES (" + eld.getLeaveAppId() + ",?," + eld.getLeaveHours() + ",0)";

        try {
            ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(eld.getLeaveDate().getTime()));
            result = ps.executeUpdate();


        } catch (Exception ex) {
            Logger.getLogger(DivEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DivEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DivEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model get(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
