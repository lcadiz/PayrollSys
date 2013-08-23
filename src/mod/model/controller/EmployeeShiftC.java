package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;
import mod.model.EmployeeShift;
import mod.model.EmployeeShift;
import mod.model.entity_manager.EmpTypeEM;
import mod.model.entity_manager.EmployeeShiftEM;

public class EmployeeShiftC {

    public int addEShift(EmployeeShift eshift) {
        Connection con = myDBConn.getConnection();
        EmployeeShiftEM dem = new EmployeeShiftEM(con);
        return dem.persist(eshift);
    }

//    public int editShift(EmployeeShift shift) {
//        Connection con = myDBConn.getConnection();
//        EmployeeShiftEM dem = new EmployeeShiftEM(con);
//        return dem.update(shift);
//    }
//
    public int deleteShift(EmployeeShift shift) {
        Connection con = myDBConn.getConnection();
        EmployeeShiftEM dem = new EmployeeShiftEM(con);
        return dem.delete(shift);
    }
    
     public int GetShiftId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmployeeShift> empshifts = new EmployeeShiftEM(con).getbyId(eid);

            for (EmployeeShift empshift : empshifts) {
                Value = empshift.getShiftId();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
