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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Shift;
import mod.model.Model;

/**
 *
 * @author LESTER
 */
public class ShiftEM extends EntityManager {

    public ShiftEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        // throw new UnsupportedOperationException("Not supported yet.");
        Shift shift = (Shift) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO Shift("
                    + "shift_desc, "
                    + "in_time1, "
                    + "out_time1, "
                    + "in1_log_start, "
                    + "in1_log_end, "
                    + "out1_log_start, "
                    + "out1_log_end, "
                    + "in_time2, "
                    + "out_time2, "
                    + "in2_log_start, "
                    + "in2_log_end, "
                    + "out2_log_start, "
                    + "out2_log_end) "
                    + "VALUES ('" + shift.getShiftDesc() + "',"
                    + "?,?,?,?,?,?,?,?,?,?,?,?)";

            ps = getConnection().prepareStatement(query);
            ps.setTime(1, new java.sql.Time(shift.getInTime1().getTime()));
            ps.setTime(2, new java.sql.Time(shift.getOutTime1().getTime()));
            ps.setTime(3, new java.sql.Time(shift.getIn1LogStart().getTime()));
            ps.setTime(4, new java.sql.Time(shift.getIn1LogEnd().getTime()));
            ps.setTime(5, new java.sql.Time(shift.getOut1LogStart().getTime()));
            ps.setTime(6, new java.sql.Time(shift.getOut1LogEnd().getTime()));
            ps.setTime(7, new java.sql.Time(shift.getInTime2().getTime()));
            ps.setTime(8, new java.sql.Time(shift.getOutTime2().getTime()));
            ps.setTime(9, new java.sql.Time(shift.getIn2LogStart().getTime()));
            ps.setTime(10, new java.sql.Time(shift.getIn2LogEnd().getTime()));
            ps.setTime(11, new java.sql.Time(shift.getOut2LogStart().getTime()));
            ps.setTime(12, new java.sql.Time(shift.getOut2LogEnd().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
         // throw new UnsupportedOperationException("Not supported yet.");
        Shift shift = (Shift) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM shift "
                    + "WHERE shift_id="+ shift.getShiftId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
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
        // throw new UnsupportedOperationException("Not supported yet.");
        Shift shift = (Shift) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE shift SET "
                    + "shift_desc='" + shift.getShiftDesc() + "',"
                    + "in_time1=?, "
                    + "out_time1=?, "
                    + "in1_log_start=?, "
                    + "in1_log_end=?, "
                    + "out1_log_start=?, "
                    + "out1_log_end=?, "
                    + "in_time2=?, "
                    + "out_time2=?, "
                    + "in2_log_start=?, "
                    + "in2_log_end=?, "
                    + "out2_log_start=?, "
                    + "out2_log_end=? "
                    + "WHERE shift_id="+ shift.getShiftId();
                   

            ps = getConnection().prepareStatement(query);
            ps.setTime(1, new java.sql.Time(shift.getInTime1().getTime()));
            ps.setTime(2, new java.sql.Time(shift.getOutTime1().getTime()));
            ps.setTime(3, new java.sql.Time(shift.getIn1LogStart().getTime()));
            ps.setTime(4, new java.sql.Time(shift.getIn1LogEnd().getTime()));
            ps.setTime(5, new java.sql.Time(shift.getOut1LogStart().getTime()));
            ps.setTime(6, new java.sql.Time(shift.getOut1LogEnd().getTime()));
            ps.setTime(7, new java.sql.Time(shift.getInTime2().getTime()));
            ps.setTime(8, new java.sql.Time(shift.getOutTime2().getTime()));
            ps.setTime(9, new java.sql.Time(shift.getIn2LogStart().getTime()));
            ps.setTime(10, new java.sql.Time(shift.getIn2LogEnd().getTime()));
            ps.setTime(11, new java.sql.Time(shift.getOut2LogStart().getTime()));
            ps.setTime(12, new java.sql.Time(shift.getOut2LogEnd().getTime()));
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShiftEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {

        ArrayList<Shift> Shift = new ArrayList<>();
        String query = "SELECT * FROM Shift ORDER BY Shift_desc";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {

                do {
                    Shift list = new Shift();

                    list.setShiftId(rs.getInt("shift_id"));
                    list.setShiftDesc(rs.getString("shift_desc"));
                    list.setInTime1(rs.getTime("in_time1"));
                    list.setOutTime1(rs.getTime("out_time1"));
                    list.setIn1LogStart(rs.getTime("in1_log_start"));
                    list.setIn1LogEnd(rs.getTime("in1_log_end"));
                    list.setOut1LogStart(rs.getTime("out1_log_start"));
                    list.setOut1LogEnd(rs.getTime("out1_log_end"));
                    list.setInTime2(rs.getTime("in_time2"));
                    list.setOutTime2(rs.getTime("out_time2"));
                    list.setIn2LogStart(rs.getTime("in2_log_start"));
                    list.setIn2LogEnd(rs.getTime("in2_log_end"));
                    list.setOut2LogStart(rs.getTime("out2_log_start"));
                    list.setOut2LogEnd(rs.getTime("out2_log_end"));

                    Shift.add(list);
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
            return Shift;
        }
    }

    public ArrayList getById(int sid){

        ArrayList<Shift> Shift = new ArrayList<>();
        String query = "SELECT * FROM Shift WHERE shift_id="+sid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {

                do {
                    Shift list = new Shift();

                    list.setShiftId(rs.getInt("shift_id"));
                    list.setShiftDesc(rs.getString("shift_desc"));
                    list.setInTime1(rs.getTime("in_time1"));
                    list.setOutTime1(rs.getTime("out_time1"));
                    list.setIn1LogStart(rs.getTime("in1_log_start"));
                    list.setIn1LogEnd(rs.getTime("in1_log_end"));
                    list.setOut1LogStart(rs.getTime("out1_log_start"));
                    list.setOut1LogEnd(rs.getTime("out1_log_end"));
                    list.setInTime2(rs.getTime("in_time2"));
                    list.setOutTime2(rs.getTime("out_time2"));
                    list.setIn2LogStart(rs.getTime("in2_log_start"));
                    list.setIn2LogEnd(rs.getTime("in2_log_end"));
                    list.setOut2LogStart(rs.getTime("out2_log_start"));
                    list.setOut2LogEnd(rs.getTime("out2_log_end"));

                    Shift.add(list);
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
            return Shift;
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
