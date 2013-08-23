/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;
import mod.model.Holiday;
import mod.model.YearHoliday;
import mod.model.entity_manager.HolidayEM;
import mod.model.entity_manager.YearHolidayEM;

/**
 *
 * @author LESTER
 */
public class YearHolidayC {
    //APPENDERS

    public int addyHoliday(YearHoliday holiday) {
        Connection con = myDBConn.getConnection();
        YearHolidayEM dem = new YearHolidayEM(con);
        return dem.persist(holiday);
    }

    public int edityHoliday(YearHoliday holiday) {
        Connection con = myDBConn.getConnection();
        YearHolidayEM dem = new YearHolidayEM(con);
        return dem.update(holiday);
    }

    public int deleteyHoliday(YearHoliday holiday) {
        Connection con = myDBConn.getConnection();
        YearHolidayEM dem = new YearHolidayEM(con);
        return dem.delete(holiday);
    }

    public Date GetDateValue(int yhid) {
        Date Value = null;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<YearHoliday> yHolidays = new YearHolidayEM(con).getByYhId(yhid);

            for (YearHoliday yholiday : yHolidays) {
                Value = yholiday.getHolidayDate();
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
