package mod.model.controller;
import java.sql.Connection;
import mod.connection.myDBConn;
import mod.model.EmployeeLog;
import mod.model.entity_manager.EmployeeLogEM;

public class EmployeeLogC {
        //APPENDERS
    public int addEmployeeLog(EmployeeLog emp) {
        Connection con = myDBConn.getConnection();
        EmployeeLogEM dem = new EmployeeLogEM(con);
        return dem.persist(emp);
    }

    public int editEmployeeLog(EmployeeLog emp) {
        Connection con = myDBConn.getConnection();
        EmployeeLogEM dem = new EmployeeLogEM(con);
        return dem.update(emp);
    }

    public int deleteEmployeeLog(EmployeeLog emp) {
        Connection con = myDBConn.getConnection();
        EmployeeLogEM dem = new EmployeeLogEM(con);
        return dem.delete(emp);
    }
    //END OF APPENDERS
}
