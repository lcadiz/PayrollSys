package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Division;
import mod.model.Model;

public class DivEM extends EntityManager {

    //public static int deptid;
    public DivEM(Connection con) {
        super(con);
    }

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int persist(Model model) {
        Division div = (Division) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO division(div_name, dept_id) "
                + "VALUES ('" + div.getDivName() + "'," + div.getDeptId() + ")";

        try {
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
        Division div = (Division) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM division "
                    + "WHERE "
                    + "div_id=" + div.getDeptId();

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
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        Division div = (Division) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE division "
                    + "SET "
                    + "div_name='" + div.getDivName() + "', "
                    + "WHERE "
                    + "div_id=" + div.getDeptId();

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

    public ArrayList getByDeptId(int deptid) {
        ArrayList<Division> divn = new ArrayList<>();
        String query = "SELECT * FROM division WHERE dept_id=" + deptid + " ORDER BY div_name ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Division list = new Division();

                    list.setDivId(rs.getInt("div_id"));
                    list.setDivName(rs.getString("div_name"));
                    list.setDeptId(rs.getInt("dept_id"));

                    divn.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DivEM.class.getName()).log(Level.SEVERE, null, ex);
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
            return divn;
        }
    }
    
     public ArrayList getByDivId(int did) {
        ArrayList<Division> divn = new ArrayList<>();
        String query = "SELECT * FROM division WHERE div_id=" + did + " ORDER BY div_name ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Division list = new Division();

                    list.setDivId(rs.getInt("div_id"));
                    list.setDivName(rs.getString("div_name"));
                    list.setDeptId(rs.getInt("dept_id"));

                    divn.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DivEM.class.getName()).log(Level.SEVERE, null, ex);
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
            return divn;
        }
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
}
