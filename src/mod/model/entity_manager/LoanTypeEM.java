package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.LoanType;
import mod.model.Model;

public class LoanTypeEM extends EntityManager {

    public LoanTypeEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
         // throw new UnsupportedOperationException("Not supported yet.");
        LoanType lt = (LoanType) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO loan_type(loan_description, loan_agency_id) "
                    + "VALUES ('" + lt.getLoanDescription()+ "'," + lt.getLoanAgencyId()+ ")";

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
                 // throw new UnsupportedOperationException("Not supported yet.");
        LoanType lt = (LoanType) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM loan_type WHERE loan_id="+ lt.getLoanId();

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Model model) {
                 // throw new UnsupportedOperationException("Not supported yet.");
        LoanType lt = (LoanType) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE loan_type SET loan_description='" + lt.getLoanDescription()+ "' WHERE loan_id="+lt.getLoanId();

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<LoanType> loan_type = new ArrayList<>();
        String query = "SELECT * FROM loan_type ORDER BY loan_description";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanType list = new LoanType();

                    list.setLoanId(rs.getInt("loan_id"));
                    list.setLoanAgencyId(rs.getInt("loan_agency_id"));
                    list.setLoanDescription(rs.getString("loan_description"));

                    loan_type.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loan_type;
        }
    }

    public ArrayList getById(int lid) {
        ArrayList<LoanType> loan_type = new ArrayList<>();
        String query = "SELECT * FROM loan_type WHERE loan_id=" + lid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanType list = new LoanType();

                    list.setLoanId(rs.getInt("loan_id"));
                    list.setLoanAgencyId(rs.getInt("loan_agency_id"));
                    list.setLoanDescription(rs.getString("loan_description"));

                    loan_type.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loan_type;
        }
    }

    
     public ArrayList getByLoanAgencyId(int laid) {
        ArrayList<LoanType> loan_type = new ArrayList<>();
        String query = "SELECT * FROM loan_type WHERE loan_agency_id=" + laid + " ORDER BY loan_description";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanType list = new LoanType();

                    list.setLoanId(rs.getInt("loan_id"));
                    list.setLoanAgencyId(rs.getInt("loan_agency_id"));
                    list.setLoanDescription(rs.getString("loan_description"));

                    loan_type.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanTypeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loan_type;
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
