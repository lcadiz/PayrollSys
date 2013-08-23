package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.BIR;
import mod.model.Model;

public class BIREM extends EntityManager {

    public BIREM(Connection con) {
        super(con);
    }

 @Override
    public int persist(Model model) {
        BIR bir = (BIR) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO bir (range_start, range_end, tax_min_amt, tax_add_percentage) "
                    + "VALUES ('" + bir.getRangeStart() + "','" + bir.getRangeEnd() + "','" + bir.getTaxMinAmt() + "','" + bir.getTaxAddPercentage()  + "')";

            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

     public int deleteAll(Model model) {
        BIR bir = (BIR) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM bir";
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }
    
    @Override
    public int delete(Model model) {
        BIR bir = (BIR) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM bir WHERE bir_id=" + bir.getBirId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
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
        BIR bir = (BIR) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE bir SET range_start='" + bir.getRangeStart() + "', "
                    + "range_end='" + bir.getRangeEnd() + "', "
                    + "tax_min_amt='" + bir.getTaxMinAmt()+ "', "
                    + "tax_add_percentage='" + bir.getTaxAddPercentage() + "' "
                    + "WHERE bir_id=" + bir.getBirId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BIREM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<BIR> bir = new ArrayList<>();
        String query = "SELECT * FROM bir ORDER BY bir_id";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    BIR list = new BIR();

                    list.setBirId(rs.getShort("bir_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setTaxMinAmt(rs.getBigDecimal("tax_min_amt"));
                    list.setTaxAddPercentage(rs.getBigDecimal("tax_add_percentage"));



                    bir.add(list);
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
            return bir;
        }
    }

    public ArrayList getBirId(int birid) {
        ArrayList<BIR> bir= new ArrayList<>();
        String query = "SELECT * FROM bir WHERE bir_id="+birid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    BIR list = new BIR();

                    list.setBirId(rs.getShort("bir_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setTaxMinAmt(rs.getBigDecimal("tax_min_amt"));
                    list.setTaxAddPercentage(rs.getBigDecimal("tax_add_percentage"));

                    bir.add(list);
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
            return bir;
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
