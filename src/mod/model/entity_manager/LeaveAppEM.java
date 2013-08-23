package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Model;
import mod.model.LeaveApp;

public class LeaveAppEM extends EntityManager {

    public LeaveAppEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        LeaveApp leaveapp = (LeaveApp) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String nwdate = dateFormat.format(leaveapp.getTransDate());


        try {
            String query = "INSERT INTO leave_app (leave_type_id, emp_id, start_date_time, end_date_time, leave_days, credits_used, reason, trans_date, r_approved_by, approved_by, status_id, r_approve_flag, approve_flag) "
                    + "VALUES ("+leaveapp.getLeaveTypeId()+"," + leaveapp.getEmpId() + ",?,?,'" + leaveapp.getLeaveDays() + "','" + leaveapp.getCreditsUsed() + "','" + leaveapp.getReason() + "','" + nwdate + "','" + leaveapp.getRApprovedBy() + "','" + leaveapp.getApprovedBy() + "'," + leaveapp.getStatusId() + ",0,0)";

            ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(leaveapp.getStartDateTime().getTime())); //date start
            ps.setDate(2, new java.sql.Date(leaveapp.getEndDateTime().getTime())); //date end
            // ps.setDate(3, new java.sql.Date(leaveapp.getTransDate().getTime())); //date end
            result = ps.executeUpdate();
            
                        rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                throw new RuntimeException("Can't find most recent insert we just entered");
            }

        } catch (Exception ex) {
            Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }

    }

    @Override
    public int delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList get() {
        ArrayList<LeaveApp> leaveapp = new ArrayList<>();
        String query = "SELECT * FROM leave_app WHERE status_id=0 ORDER BY trans_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LeaveApp list = new LeaveApp();

                    list.setLeaveAppId(rs.getInt("leave_app_id"));
                    list.setEmpId(rs.getInt("emp_id"));
                    list.setStartDateTime(rs.getDate("start_date_time"));
                    list.setEndDateTime(rs.getDate("end_date_time"));
                    list.setLeaveDays(rs.getBigDecimal("leave_days"));
                    list.setCreditsUsed(rs.getBigDecimal("credits_used"));
                    list.setReason(rs.getString("reason"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setRApprovedBy(rs.getInt("r_approved_by"));
                    list.setApprovedBy(rs.getInt("approved_by"));
                    list.setStatusId(rs.getShort("status_id"));
                    list.setRApproveFlag(rs.getShort("r_approve_flag"));
                    list.setApproveFlag(rs.getShort("approve_flag"));

                    leaveapp.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return leaveapp;
        }
    }

    public ArrayList getById(int statid) {
        ArrayList<LeaveApp> leaveapp = new ArrayList<>();
        String query = "SELECT * FROM leave_app WHERE status_id=0 ORDER BY trans_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LeaveApp list = new LeaveApp();

                    list.setLeaveAppId(rs.getInt("leave_app_id"));
                    list.setEmpId(rs.getInt("emp_id"));
                    list.setStartDateTime(rs.getDate("start_date_time"));
                    list.setEndDateTime(rs.getDate("end_date_time"));
                    list.setLeaveDays(rs.getBigDecimal("leave_days"));
                    list.setCreditsUsed(rs.getBigDecimal("credits_used"));
                    list.setReason(rs.getString("reason"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setRApprovedBy(rs.getInt("r_approved_by"));
                    list.setApprovedBy(rs.getInt("approved_by"));
                    list.setStatusId(rs.getShort("status_id"));
                    list.setStatusId(rs.getShort("remarks"));
                    leaveapp.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return leaveapp;
        }
    }

    public ArrayList getById2(int laid) {
        ArrayList<LeaveApp> leaveapp = new ArrayList<>();
        String query = "SELECT * FROM leave_app WHERE leave_app_id="+laid;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    LeaveApp list = new LeaveApp();

                    list.setLeaveAppId(rs.getInt("leave_app_id"));
                    list.setEmpId(rs.getInt("emp_id"));
                    list.setLeaveTypeId(rs.getShort("leave_type_id"));
                    list.setStartDateTime(rs.getDate("start_date_time"));
                    list.setEndDateTime(rs.getDate("end_date_time"));
                    list.setLeaveDays(rs.getBigDecimal("leave_days"));
                    list.setCreditsUsed(rs.getBigDecimal("credits_used"));
                    list.setReason(rs.getString("reason"));
                    list.setTransDate(rs.getDate("trans_date"));
                    list.setRApprovedBy(rs.getInt("r_approved_by"));
                    list.setApprovedBy(rs.getInt("approved_by"));
                    list.setStatusId(rs.getShort("status_id"));
                    list.setRApproveFlag(rs.getShort("r_approve_flag"));
                    list.setApproveFlag(rs.getShort("approve_flag"));

                    leaveapp.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LeaveAppEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return leaveapp;
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
