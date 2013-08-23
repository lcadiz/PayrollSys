package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.LoanLedger;
import mod.model.Model;

public class LoanLedgerEM extends EntityManager {

    public LoanLedgerEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
           // throw new UnsupportedOperationException("Not supported yet.");
        LoanLedger loanledger = (LoanLedger) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO loan_ledger(trans_date, particular, debit, credit, emp_loan_id, payroll_period_id) "
                    + "VALUES (?,'" + loanledger.getParticular() +"','" + loanledger.getDebit() +"','" + loanledger.getCredit() +"'," + loanledger.getEmpLoanId() + "," + loanledger.getPayrollPeriodId()+")";

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(loanledger.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
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
         ArrayList<LoanLedger> loanledger = new ArrayList<>();
        String query = "SELECT * FROM loanledger ORDER BY agency_name";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanLedger list = new LoanLedger();

                    list.setLoanLedgerId(rs.getInt("loan_ledger_id"));
                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setParticular(rs.getString("particular"));
                    list.setDebit(rs.getBigDecimal("debit"));
                    list.setCredit(rs.getBigDecimal("credit"));
                    list.setPayrollPeriodId(rs.getInt("payroll_period_id"));

                    loanledger.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loanledger;
        }
    }
    
    
       public ArrayList getById(int elid) {
         ArrayList<LoanLedger> loanledger = new ArrayList<>();
        String query = "SELECT * FROM loan_ledger WHERE emp_loan_id="+elid+" ORDER BY trans_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanLedger list = new LoanLedger();

                    list.setLoanLedgerId(rs.getInt("loan_ledger_id"));
                    list.setEmpLoanId(rs.getInt("emp_loan_id"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setParticular(rs.getString("particular"));
                    list.setDebit(rs.getBigDecimal("debit"));
                    list.setCredit(rs.getBigDecimal("credit"));
                    list.setPayrollPeriodId(rs.getInt("payroll_period_id"));

                    loanledger.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanLedgerEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loanledger;
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
