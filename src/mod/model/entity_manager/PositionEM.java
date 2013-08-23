package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Model;
import mod.model.Position;

public class PositionEM extends EntityManager {

    public PositionEM(Connection con) {
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
        Position position = (Position) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO position (position_desc,salary_id, sec_id, pos_level_id) "
                    + " VALUES('" + position.getPositionDesc() + "'," + position.getSalaryId() + "," + position.getSecId() + "," + position.getPosLevelId() + ")";

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
        Position position = (Position) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM position "
                    + "WHERE "
                    + "position_id=" + position.getPositionId();

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
        Position position = (Position) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE position "
                    + "SET "
                    + "position_desc='" + position.getPositionDesc() + "', "
                    + "salary_id='" + position.getSalaryId() + "', "
                    + "pos_level_id=" + position.getPosLevelId() + " "
                    + "WHERE "
                    + "position_id=" + position.getPositionId();

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
    public ArrayList get() {

        ArrayList<Position> position = new ArrayList<>();
        String query = "SELECT * FROM position ORDER BY position_desc";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Position list = new Position();

                    list.setPositionId(rs.getShort("position_id"));
                    list.setPositionDesc(rs.getString("position_desc"));
                    list.setSalaryId(rs.getShort("salary_id"));
                    list.setPosLevelId(rs.getShort("pos_level_id"));

                    position.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return position;
        }
    }

    public ArrayList getbySection(int secid) {

        ArrayList<Position> position = new ArrayList<>();
        String query = "SELECT * FROM position WHERE sec_id=" + secid + " ORDER BY position_desc";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Position list = new Position();

                    list.setPositionId(rs.getShort("position_id"));
                    list.setPositionDesc(rs.getString("position_desc"));
                    list.setSalaryId(rs.getShort("salary_id"));
                    //list.setRankValue(rs.getShort("rank_value"));
                    list.setPosLevelId(rs.getShort("pos_level_id"));

                    position.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PositionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return position;
        }
    }

    public ArrayList getPositonId(int posid) {
        ArrayList<Position> position = new ArrayList<>();
        String query = "SELECT * FROM position WHERE position_id=" + posid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Position list = new Position();

                    list.setPositionId(rs.getShort("position_id"));
                    list.setPositionDesc(rs.getString("position_desc"));
                    list.setSalaryId(rs.getShort("salary_id"));
                    list.setPosLevelId(rs.getShort("pos_level_id"));


                    position.add(list);
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
            return position;
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
}
