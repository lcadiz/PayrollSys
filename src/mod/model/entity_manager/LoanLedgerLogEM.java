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
import mod.model.LoanLedgerLog;
import mod.model.Model;

public class LoanLedgerLogEM extends EntityManager {

    public LoanLedgerLogEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        LoanLedgerLog loanledgerlog = (LoanLedgerLog) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO loan_ledger_log(trans_date, notification, user_id, emp_loan_id, due_date, monthly) "
                    + "VALUES (?,'" + loanledgerlog.getNotification()+ "'," + loanledgerlog.getUserId() + "," + loanledgerlog.getEmpLoanId() + ",?,'"+loanledgerlog.getMonthly()+"')";

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(loanledgerlog.getTransDate().getTime()));
            ps.setDate(2, new java.sql.Date(loanledgerlog.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList getById(int elid) {
        ArrayList<LoanLedgerLog> loanledgerlog = new ArrayList<>();
        String query = "SELECT * FROM loan_ledger_log WHERE emp_loan_id=" + elid + " ORDER BY trans_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanLedgerLog list = new LoanLedgerLog();

                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setLoanLedgerLogId(rs.getInt("loan_ledger_id"));
                    list.setNotification(rs.getString("notification"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setUserId(rs.getInt("user_id"));
                    list.setDueDate(rs.getDate("due_date"));
                    list.setMonthly(rs.getBigDecimal("monthly"));

                    loanledgerlog.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerLogEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loanledgerlog;
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
