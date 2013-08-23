package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.EmployeeShift;
import mod.model.EmployeeShift;
import mod.model.Model;

public class EmployeeShiftEM extends EntityManager {

    public EmployeeShiftEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        EmployeeShift EmployeeShift = (EmployeeShift) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO employee_shift("
                    + "emp_id, "
                    + "shift_id "
                    + "start_date, "
                    + "end_date, "
                    + "user_id, "
                    + "trans_date, "
                    + "p_flag"
                    + ") "
                    + "VALUES ("
                    + "'" + EmployeeShift.getEmpId() + "',"
                    + "'" + EmployeeShift.getShiftId() + "'"
                    + "?,?,"
                    + EmployeeShift.getUserId() + ","
                    + "?,"
                    + EmployeeShift.getPFlag()
                    + ")";

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(EmployeeShift.getStartDate().getTime()));
            ps.setDate(2, new java.sql.Date(EmployeeShift.getEndDate().getTime()));
            ps.setDate(3, new java.sql.Date(EmployeeShift.getTransDate().getTime()));

        } catch (Exception ex) {
            Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        EmployeeShift empshift = (EmployeeShift) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM employee_shift "
                    + "WHERE "
                    + "emp_shift_id=" + empshift.getEmpId();

            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
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
        throw new UnsupportedOperationException("Not supported yet.");
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

    public ArrayList getbyId(int eid) {
        ArrayList<EmployeeShift> empshift = new ArrayList<>();
        String query = "SELECT * FROM employee_shift WHERE emp_id=" + eid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmployeeShift list = new EmployeeShift();

                    list.setEmpShiftId(rs.getInt("emp_shift_id"));
                    list.setShiftId(rs.getInt("shift_id"));
               
                    empshift.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return empshift;
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
