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
import mod.model.EmployeeDependent;
import mod.model.Model;

/**
 *
 * @author LESTER
 */
public class DependentEM extends EntityManager {

    public DependentEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        EmployeeDependent dependent = (EmployeeDependent) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO employee_dependent(emp_id, dependent_name, relation, birthdate) "
                    + "VALUES (" + dependent.getEmpId() + ",'" + dependent.getDependentName() + "','" + dependent.getRelation() + "',?)";

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(dependent.getBirthdate().getTime()));

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
        EmployeeDependent dependent = (EmployeeDependent) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM employee_dependent "
                    + "WHERE ed_id=" + dependent.getEdId();
                  

            ps = getConnection().prepareStatement(query);
           // ps.setDate(1, new java.sql.Date(dependent.getBirthdate().getTime()));

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
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        EmployeeDependent dependent = (EmployeeDependent) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE employee_dependent "
                    + "SET emp_id='" + dependent.getEmpId() + "', "
                    + "dependent_name='" + dependent.getDependentName() + "', relation='"+dependent.getRelation()+"', birthdate=?"
                    + "WHERE ed_id="+dependent.getEdId();
                   

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(dependent.getBirthdate().getTime()));

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
    public ArrayList get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getbyEdId(int edid) {

        ArrayList<EmployeeDependent> dept = new ArrayList<>();
        String query = "SELECT * FROM employee_dependent WHERE ed_id="+edid+" ORDER BY dependent_name";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmployeeDependent list = new EmployeeDependent();

                    list.setEdId(rs.getInt("ed_id"));
                    list.setDependentName(rs.getString("dependent_name"));
                    list.setRelation(rs.getString("relation"));
                    list.setBirthdate(rs.getDate("birthdate"));

                    dept.add(list);
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
            return dept;
        }
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
