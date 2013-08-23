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
import mod.model.Model;
import mod.model.Section;

/**
 *
 * @author LESTER
 */
public class SectionEM extends EntityManager {

    public SectionEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList getByDivId(int divid) {
        ArrayList<Section> sec = new ArrayList<>();
        String query = "SELECT * FROM section WHERE div_id=" + divid + " ORDER BY sec_name ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Section list = new Section();

                    list.setSecId(rs.getInt("sec_id"));
                    list.setSecName(rs.getString("sec_name"));
 
                    sec.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return sec;
        }
    }
    
    public ArrayList getBySecId(int secid) {
        ArrayList<Section> sec = new ArrayList<>();
        String query = "SELECT * FROM section WHERE sec_id=" + secid + " ORDER BY sec_name ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Section list = new Section();

                    list.setSecId(rs.getInt("sec_id"));
                    list.setSecName(rs.getString("sec_name"));
                    list.setDivId(rs.getInt("div_id"));
 
                    sec.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SectionEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return sec;
        }
    }

    
    
    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
