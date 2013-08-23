/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;
import mod.model.EmpLeaveCredits;
import mod.model.entity_manager.EmpLeaveCreditsEM;

/**
 *
 * @author MIS
 */
public class EmpLeaveCreditsC {
    //APPENDERS

    public int addEmpLeaveCredits(EmpLeaveCredits empleavecredits) {
        Connection con = myDBConn.getConnection();
        EmpLeaveCreditsEM dem = new EmpLeaveCreditsEM(con);
        return dem.persist(empleavecredits);
    }

    public int editEmpLeaveCredits(EmpLeaveCredits empleavecredits) {
        Connection con = myDBConn.getConnection();
        EmpLeaveCreditsEM dem = new EmpLeaveCreditsEM(con);
        return dem.update(empleavecredits);
    }

    public int deleteEmpLeaveCredits(EmpLeaveCredits empleavecredits) {
        Connection con = myDBConn.getConnection();
        EmpLeaveCreditsEM dem = new EmpLeaveCreditsEM(con);
        return dem.delete(empleavecredits);
    }
    //END OF APPENDERS
    

    

    public String GetLeaveCredits(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmpLeaveCredits> elc = new EmpLeaveCreditsEM(con).getbyId(eid);

            for (EmpLeaveCredits EmpLeaveCredits : elc) {
                Value = String.valueOf(EmpLeaveCredits.getLeaveCredits());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    public String GetCreditsUsed(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<EmpLeaveCredits> elc = new EmpLeaveCreditsEM(con).getbyId(eid);

            for (EmpLeaveCredits EmpLeaveCredits : elc) {
                Value = String.valueOf(EmpLeaveCredits.getCreditsUsed());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpLeaveCreditsC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
