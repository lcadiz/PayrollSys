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
import mod.model.EmpLeaveCredits;
import mod.model.Model;

/**
 *
 * @author MIS
 */
public class EmpLeaveCreditsEM extends EntityManager {

    public EmpLeaveCreditsEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        EmpLeaveCredits empleavecredits = (EmpLeaveCredits) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO emp_leave_credits(emp_id, leave_type_id, leave_credits, credits_used) "
                    + " (SELECT " + empleavecredits.getEmpId() + ", leave_type_id, 0, 0  FROM leave_type WHERE leave_type_id NOT IN (SELECT leave_type_id FROM emp_leave_credits WHERE emp_id="+empleavecredits.getEmpId()+")) ";

            ps = getConnection().prepareStatement(query);
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
           EmpLeaveCredits empleavecredits = (EmpLeaveCredits) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE emp_leave_credits "
                    + "SET leave_credits='"+empleavecredits.getLeaveCredits() +"',credits_used='"+empleavecredits.getCreditsUsed()+"' "
                    + "WHERE elc_id=" + empleavecredits.getElcId();
            ps = getConnection().prepareStatement(query);
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
    
    public ArrayList getbyId(int elcid) {
        ArrayList<EmpLeaveCredits> empleavecredits = new ArrayList<>();
        String query = "SELECT * FROM emp_leave_credits WHERE elc_id=" + elcid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmpLeaveCredits list = new EmpLeaveCredits();

                    list.setElcId(rs.getInt("elc_id"));
                    list.setEmpId(rs.getInt("emp_id"));
                    list.setLeaveTypeId(rs.getShort("leave_type_id"));
                    list.setLeaveCredits(rs.getBigDecimal("leave_credits"));
                    list.setCreditsUsed(rs.getBigDecimal("credits_used"));
                    list.setEmpId(rs.getInt("emp_id"));

                    empleavecredits.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return empleavecredits;
        }
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
