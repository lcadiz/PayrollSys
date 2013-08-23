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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.EmployeeLoan;
import mod.model.Model;

/**
 *
 * @author LESTER
 */
public class EmpLoanEM extends EntityManager {

    public EmpLoanEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        EmployeeLoan emploan = (EmployeeLoan) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO employee_loan(loan_id, particular, term, loan_date, due_date, amount, monthly, emp_id) "
                    + "VALUES (" + emploan.getLoanId() + ",'" + emploan.getParticular() + "'," + emploan.getTerm() + ",?,?,'" + emploan.getAmount() + "','" + emploan.getMonthly() + "'," + emploan.getEmpId() + ")";

            ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(emploan.getLoanDate().getTime()));
            ps.setDate(2, new java.sql.Date(emploan.getDueDate().getTime()));
            result = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                throw new RuntimeException("Can't find most recent insert we just entered");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
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
        // throw new UnsupportedOperationException("Not supported yet.");
        EmployeeLoan emploan = (EmployeeLoan) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE employee_loan SET due_date =?, monthly='"+emploan.getMonthly()+"' WHERE emp_loan_id="+emploan.getEmpLoanId();

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(emploan.getDueDate().getTime()));
            result = ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EmpLoanEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
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
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<EmployeeLoan> emploan = new ArrayList<>();
        String query = "SELECT * FROM employee_loan";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmployeeLoan list = new EmployeeLoan();

                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setLoanId(rs.getInt("loan_id"));
                    list.setParticular(rs.getString("particular"));
                    list.setTerm(rs.getShort("term"));
                    list.setLoanDate(rs.getDate("loan_date"));
                    list.setDueDate(rs.getDate("due_date"));
                    list.setAmount(rs.getBigDecimal("amount"));
                    list.setMonthly(rs.getBigDecimal("monthly"));
                    list.setEmpId(rs.getInt("emp_id"));

                    emploan.add(list);
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
            return emploan;
        }
    }

    public ArrayList getbyId(int eid, int lid) {
        ArrayList<EmployeeLoan> emploan = new ArrayList<>();
        String query = "SELECT * FROM employee_loan WHERE emp_id=" + eid + " AND loan_id=" + lid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmployeeLoan list = new EmployeeLoan();

                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setLoanId(rs.getInt("loan_id"));
                    list.setParticular(rs.getString("particular"));
                    list.setTerm(rs.getShort("term"));
                    list.setLoanDate(rs.getDate("loan_date"));
                    list.setDueDate(rs.getDate("due_date"));
                    list.setAmount(rs.getBigDecimal("amount"));
                    list.setMonthly(rs.getBigDecimal("monthly"));
                    list.setEmpId(rs.getInt("emp_id"));

                    emploan.add(list);
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
            return emploan;
        }
    }

    public ArrayList getbyId2(int elid) {
        ArrayList<EmployeeLoan> emploan = new ArrayList<>();
        String query = "SELECT * FROM employee_loan WHERE emp_loan_id=" + elid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    EmployeeLoan list = new EmployeeLoan();

                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setLoanId(rs.getInt("loan_id"));
                    list.setParticular(rs.getString("particular"));
                    list.setTerm(rs.getShort("term"));
                    list.setLoanDate(rs.getDate("loan_date"));
                    list.setDueDate(rs.getDate("due_date"));
                    list.setAmount(rs.getBigDecimal("amount"));
                    list.setMonthly(rs.getBigDecimal("monthly"));
                    list.setEmpId(rs.getInt("emp_id"));

                    emploan.add(list);
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
            return emploan;
        }
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
