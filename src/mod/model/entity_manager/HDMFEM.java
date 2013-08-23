package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.HDMF;
import mod.model.Model;

public class HDMFEM extends EntityManager {

    public HDMFEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        HDMF hdmf = (HDMF) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO hdmf (range_start, range_end, max_salary_base, ee, er, total) "
                    + "VALUES ('" + hdmf.getRangeStart() + "','" + hdmf.getRangeEnd() + "','" + hdmf.getMaxSalaryBase() + "','" + hdmf.getEe() + "','" + hdmf.getEr() + "','" + hdmf.getTotal() + "')";

            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    public int deleteAll(Model model) {
        HDMF hdmf = (HDMF) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM hdmf";
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }
    
    @Override
    public int delete(Model model) {
        HDMF hdmf = (HDMF) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM hdmf WHERE hdmf_id=" + hdmf.getHdmfId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
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
        HDMF hdmf = (HDMF) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE hdmf SET range_start='" + hdmf.getRangeStart() + "', "
                    + "range_end='" + hdmf.getRangeEnd() + "', "
                    + "max_salary_base='" + hdmf.getMaxSalaryBase() + "', "
                    + "ee='" + hdmf.getEe() + "', "
                    + "er='" + hdmf.getEr() + "', "
                    + "total='" + hdmf.getTotal() + "' "
                    + "WHERE hdmf_id=" + hdmf.getHdmfId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HDMFEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<HDMF> hdmf = new ArrayList<>();
        String query = "SELECT * FROM hdmf ORDER BY hdmf_id";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    HDMF list = new HDMF();

                    list.setHdmfId(rs.getShort("hdmf_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setMaxSalaryBase(rs.getBigDecimal("max_salary_base"));
                    list.setTotal(rs.getBigDecimal("total"));
                    list.setEe(rs.getBigDecimal("ee"));
                    list.setEr(rs.getBigDecimal("er"));


                    hdmf.add(list);
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
            return hdmf;
        }
    }

    public ArrayList getHdmfId(int hdmfid) {
        ArrayList<HDMF> hdmf= new ArrayList<>();
        String query = "SELECT * FROM hdmf WHERE hdmf_id="+hdmfid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    HDMF list = new HDMF();

                    list.setHdmfId(rs.getShort("hdmf_id"));
                    list.setRangeStart(rs.getBigDecimal("range_start"));
                    list.setRangeEnd(rs.getBigDecimal("range_end"));
                    list.setMaxSalaryBase(rs.getBigDecimal("max_salary_base"));
                    list.setEe(rs.getBigDecimal("ee"));
                    list.setEr(rs.getBigDecimal("er"));
                    list.setTotal(rs.getBigDecimal("total"));

                    hdmf.add(list);
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
            return hdmf;
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