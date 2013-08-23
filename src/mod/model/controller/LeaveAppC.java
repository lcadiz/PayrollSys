package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;
import mod.model.EmpLeaveCredits;
import mod.model.LeaveApp;
import mod.model.entity_manager.EmpLeaveCreditsEM;
import mod.model.entity_manager.LeaveAppEM;

public class LeaveAppC {
    //APPENDERS

    public int addLeaveApp(LeaveApp leaveapp) {
        Connection con = myDBConn.getConnection();
        LeaveAppEM dem = new LeaveAppEM(con);
        return dem.persist(leaveapp);
    }

    public int editLeaveApp(LeaveApp leaveapp) {
        Connection con = myDBConn.getConnection();
        LeaveAppEM dem = new LeaveAppEM(con);
        return dem.update(leaveapp);
    }

    public int deleteLeaveApp(LeaveApp leaveapp) {
        Connection con = myDBConn.getConnection();
        LeaveAppEM dem = new LeaveAppEM(con);
        return dem.delete(leaveapp);
    }

    public int GetLeaveTypeId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LeaveApp> leaveapps = new LeaveAppEM(con).getById2(eid);

            for (LeaveApp leaveapp : leaveapps) {
                Value = leaveapp.getLeaveTypeId();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int GetEmpId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LeaveApp> leaveapps = new LeaveAppEM(con).getById2(eid);

            for (LeaveApp leaveapp : leaveapps) {
                Value = leaveapp.getEmpId();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int GetRApprovedId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LeaveApp> leaveapps = new LeaveAppEM(con).getById2(eid);

            for (LeaveApp leaveapp : leaveapps) {
                Value = leaveapp.getRApprovedBy();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int GetApprovedId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LeaveApp> leaveapps = new LeaveAppEM(con).getById2(eid);

            for (LeaveApp leaveapp : leaveapps) {
                Value = leaveapp.getApprovedBy();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    public int GetRApprovedFlg(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LeaveApp> leaveapps = new LeaveAppEM(con).getById2(eid);

            for (LeaveApp leaveapp : leaveapps) {
                Value = leaveapp.getRApproveFlag();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
