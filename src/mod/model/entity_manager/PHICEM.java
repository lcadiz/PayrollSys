package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Model;
import mod.model.PHIC;

public class PHICEM extends EntityManager {

    
    public PHICEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        PHIC phic = (PHIC) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO phic (range_start, range_end, salary_base, ee, er, total) "
                    + "VALUES ('" + phic.getRangeStart() + "','" + phic.getRangeEnd() + "','" + phic.getSalaryBase() + "','" + phic.getEe() + "','" + phic.getEr() + "','" + phic.getTotal() + "')";

            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

     public int deleteAll(Model model) {
        PHIC phic = (PHIC) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM phic";
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }
    
    @Override
    public int delete(Model model) {
        PHIC phic = (PHIC) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM phic WHERE phic_id=" + phic.getPhicId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
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
        PHIC phic = (PHIC) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE phic SET range_start='" + phic.getRangeStart() + "', "
                    + "range_end='" + phic.getRangeEnd() + "', "
                    + "salary_base='" + phic.getSalaryBase() + "', "
                    + "ee='" + phic.getEe() + "', "
                    + "er='" + phic.getEr() + "', "
                    + "total='" + phic.getTotal() + "' "
                    + "WHERE phic_id=" + phic.getPhicId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PHICEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<PHIC> phic = new ArrayList<>();
        String query = "SELECT * FROM phic ORDER BY phic_id";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    PHIC list = new PHIC();

                    list.setPhicId(rs.getShort("phic_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setSalaryBase(rs.getBigDecimal("salary_base"));
                    list.setTotal(rs.getBigDecimal("total"));
                    list.setEe(rs.getBigDecimal("ee"));
                    list.setEr(rs.getBigDecimal("er"));


                    phic.add(list);
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
            return phic;
        }
    }

    public ArrayList getPhicId(int phicid) {
        ArrayList<PHIC> phic= new ArrayList<>();
        String query = "SELECT * FROM phic WHERE phic_id="+phicid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    PHIC list = new PHIC();

                    list.setPhicId(rs.getShort("phic_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setSalaryBase(rs.getBigDecimal("salary_base"));
                    list.setEe(rs.getBigDecimal("ee"));
                    list.setEr(rs.getBigDecimal("er"));
                    list.setTotal(rs.getBigDecimal("total"));

                    phic.add(list);
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
            return phic;
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
