/*
 * To change this teldlate, choose Tools | Teldlates
 * and open the teldlate in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
import mod.connection.myDBConn;
import mod.model.EmpLeaveDetails;
import mod.model.entity_manager.EmpLeaveDetailsEM;

/**
 *
 * @author MIS
 */
public class EmpLeaveDetailsC {
       //APPENDERS
    public int addEmpLeaveDetails(EmpLeaveDetails eld) {
        Connection con = myDBConn.getConnection();
        EmpLeaveDetailsEM dem = new EmpLeaveDetailsEM(con);
        return dem.persist(eld);
    }

    public int editEmpLeaveDetails(EmpLeaveDetails eld) {
        Connection con = myDBConn.getConnection();
        EmpLeaveDetailsEM dem = new EmpLeaveDetailsEM(con);
        return dem.update(eld);
    }

    public int deleteEmpLeaveDetails(EmpLeaveDetails eld) {
        Connection con = myDBConn.getConnection();
        EmpLeaveDetailsEM dem = new EmpLeaveDetailsEM(con);
        return dem.delete(eld);
    }
    //END OF APPENDERS

}
