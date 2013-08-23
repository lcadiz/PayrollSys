package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Holiday;
import mod.model.Model;

public class HolidayEM extends EntityManager {

    public HolidayEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        Holiday holiday = (Holiday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String fdate = dateFormat.format(holiday.getTransDate());

            String query = "INSERT INTO holiday(holiday_name, default_month, default_day, pay_comp_id, user_id, trans_date, constant_flag) "
                    + "VALUES ('" + holiday.getHolidayName() + "'," + holiday.getDefaultMonth() + "," + holiday.getDefaultDay() + "," + holiday.getPayCompId() + "," + holiday.getUserId() + ",'" + fdate + "'," + holiday.getConstantFlag() + ")";

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(holiday.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        Holiday holiday = (Holiday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM holiday "
                    + " WHERE holiday_id=" + holiday.getHolidayId();
            ps = getConnection().prepareStatement(query);

            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
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
        Holiday holiday = (Holiday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String fdate = dateFormat.format(holiday.getTransDate());

            String query = "UPDATE holiday SET "
                    + "holiday_name='" + holiday.getHolidayName() + "',"
                    + "default_month=" + holiday.getDefaultMonth() + ", "
                    + "default_day=" + holiday.getDefaultDay() + ", "
                    + "pay_comp_id=" + holiday.getPayCompId() + ", "
                    + "user_id=" + holiday.getUserId() + ", "
                    + "trans_date='" + fdate + "', "
                    + "constant_flag=" + holiday.getConstantFlag()
                    + " WHERE holiday_id=" + holiday.getHolidayId();
            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(holiday.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<Holiday> holiday = new ArrayList<>();
        String query = "SELECT * FROM holiday ORDER BY default_month, default_day";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Holiday list = new Holiday();

                    list.setHolidayId(rs.getInt("holiday_id"));
                    list.setHolidayName(rs.getString("holiday_name"));
                    list.setDefaultMonth(rs.getShort("default_month"));
                    list.setDefaultDay(rs.getShort("default_day"));
                    list.setPayCompId(rs.getInt("pay_comp_id"));
                    list.setConstantFlag(rs.getShort("constant_flag"));

                    holiday.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return holiday;
        }
    }

    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList getById(int hid) {
        ArrayList<Holiday> holiday = new ArrayList<>();
        String query = "SELECT * FROM holiday WHERE holiday_id=" + hid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    Holiday list = new Holiday();

                    list.setHolidayId(rs.getInt("holiday_id"));
                    list.setHolidayName(rs.getString("holiday_name"));
                    list.setDefaultMonth(rs.getShort("default_month"));
                    list.setDefaultDay(rs.getShort("default_day"));
                    list.setPayCompId(rs.getInt("pay_comp_id"));
                    list.setConstantFlag(rs.getShort("constant_flag"));

                    holiday.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return holiday;
        }
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
