/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mod.model.Model;
import mod.model.YearHoliday;

/**
 *
 * @author LESTER
 */
public class YearHolidayEM extends EntityManager {

    public YearHolidayEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        YearHoliday yholiday = (YearHoliday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String fdate = dateFormat.format(yholiday.getTransDate());

            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String yhdate = dateFormat1.format(yholiday.getHolidayDate());

            String query = "INSERT INTO year_holiday (year, holiday_id, holiday_date, user_id, trans_date) "
                    + "VALUES (" + yholiday.getYear() + "," + yholiday.getHolidayId() + ",'" + yhdate + "'," + yholiday.getUserId() + ",'" + fdate + "'" + ")";

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(holiday.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        YearHoliday yholiday = (YearHoliday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String query = "DELETE FROM year_holiday WHERE year_holiday_id=" + yholiday.getYearHolidayId();

            ps = getConnection().prepareStatement(query);
            //ps.setDate(1, new java.sql.Date(holiday.getTransDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
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
        YearHoliday yholiday = (YearHoliday) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String query = "UPDATE year_holiday SET holiday_date=? WHERE year_holiday_id=" + yholiday.getYearHolidayId();

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(yholiday.getHolidayDate().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    public ArrayList getByYhId(int yhid) {
        ArrayList<YearHoliday> yholiday = new ArrayList<>();
        String query = "SELECT * FROM year_holiday WHERE year_holiday_id=" + yhid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    YearHoliday list = new YearHoliday();

                    list.setYearHolidayId(rs.getInt("year_holiday_id"));
                    list.setYear(rs.getInt("year"));
                    list.setHolidayId(rs.getInt("holiday_id"));
                    list.setHolidayDate(rs.getDate("holiday_date"));

                    yholiday.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return yholiday;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<YearHoliday> yholiday = new ArrayList<>();
        String query = "SELECT * FROM year_holiday ORDER BY holiday_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    YearHoliday list = new YearHoliday();

                    list.setYearHolidayId(rs.getInt("year_holiday_id"));
                    list.setYear(rs.getInt("year"));
                    list.setHolidayId(rs.getInt("holiday_id"));
                    list.setHolidayDate(rs.getDate("holiday_date"));

                    yholiday.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(YearHolidayEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return yholiday;
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
