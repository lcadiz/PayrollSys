/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.EmploymentType;
import mod.model.Model;

/**
 *
 * @author LESTER
 */
public class EmpTypeEM extends EntityManager {

    public EmpTypeEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList get() {
        ArrayList<EmploymentType> emptype = new ArrayList<>();
        String query = "SELECT * FROM employment_type ORDER BY type_desc";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmploymentType list = new EmploymentType();

                    list.setEmpTypeId(rs.getInt("emp_type_id"));
                    list.setTypeDesc(rs.getString("type_desc"));

                    emptype.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return emptype;
        }
    }

    public ArrayList getbyId(int eid) {
        ArrayList<EmploymentType> emptype = new ArrayList<>();
        String query = "SELECT * FROM employment_type WHERE emp_type_id="+eid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmploymentType list = new EmploymentType();

                    list.setEmpTypeId(rs.getInt("emp_type_id"));
                    list.setTypeDesc(rs.getString("type_desc"));

                    emptype.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return emptype;
        }
    }
    
    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
