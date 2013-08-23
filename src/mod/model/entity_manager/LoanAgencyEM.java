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
import mod.model.LoanAgency;
import mod.model.Model;

public class LoanAgencyEM extends EntityManager {

    public LoanAgencyEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
         // throw new UnsupportedOperationException("Not supported yet.");
        LoanAgency la = (LoanAgency) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO loan_agency(agency_name) "
                    + "VALUES ('" + la.getAgencyName() +"')";

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
           // throw new UnsupportedOperationException("Not supported yet.");
        LoanAgency la = (LoanAgency) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM loan_agency WHERE loan_agency_id="+la.getLoanAgencyId();

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
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
        LoanAgency la = (LoanAgency) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE loan_agency SET agency_name='" + la.getAgencyName() + "' WHERE loan_agency_id="+la.getLoanAgencyId();

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(la.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<LoanAgency> loan_agency = new ArrayList<>();
        String query = "SELECT * FROM loan_agency ORDER BY agency_name";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanAgency list = new LoanAgency();

                    list.setLoanAgencyId(rs.getInt("loan_agency_id"));
                    list.setAgencyName(rs.getString("agency_name"));

                    loan_agency.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loan_agency;
        }
    }

    public ArrayList getById(int laid) {
        ArrayList<LoanAgency> loan_agency = new ArrayList<>();
        String query = "SELECT * FROM loan_agency WHERE loan_agency_id=" + laid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LoanAgency list = new LoanAgency();

                    list.setLoanAgencyId(rs.getInt("loan_agency_id"));
                    list.setAgencyName(rs.getString("agency_name"));

                    loan_agency.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoanAgencyEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return loan_agency;
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
