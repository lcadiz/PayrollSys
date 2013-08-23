package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Holiday;
import mod.model.entity_manager.HolidayEM;

public class HolidayC {
    //LIST BOX POPULATOR

    public DefaultListModel getHolidayListModel() {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Holiday> holidays = new HolidayEM(con).get();

            for (Holiday holiday : holidays) {

                model.addElement(holiday);
            }
        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addHoliday(Holiday holiday) {
        Connection con = myDBConn.getConnection();
        HolidayEM dem = new HolidayEM(con);
        return dem.persist(holiday);
    }

    public int editHoliday(Holiday holiday) {
        Connection con = myDBConn.getConnection();
        HolidayEM dem = new HolidayEM(con);
        return dem.update(holiday);
    }

    public int deleteHoliday(Holiday holiday) {
        Connection con = myDBConn.getConnection();
        HolidayEM dem = new HolidayEM(con);
        return dem.delete(holiday);
    }

    public String GetHoildayName(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Holiday> Holidays = new HolidayEM(con).getById(eid);

            for (Holiday holiday : Holidays) {
                Value = String.valueOf(holiday.getHolidayName());
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int getDefaultMonth(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Holiday> Holidays = new HolidayEM(con).getById(eid);

            for (Holiday holiday : Holidays) {
                Value = holiday.getDefaultMonth();
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int getDefaultDay(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Holiday> Holidays = new HolidayEM(con).getById(eid);

            for (Holiday holiday : Holidays) {
                Value = holiday.getDefaultDay();
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int getPayCompId(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Holiday> Holidays = new HolidayEM(con).getById(eid);

            for (Holiday holiday : Holidays) {
                Value = holiday.getPayCompId();
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public int getCFlag(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Holiday> Holidays = new HolidayEM(con).getById(eid);

            for (Holiday holiday : Holidays) {
                Value = holiday.getConstantFlag();
            }

        } catch (Exception ex) {
            Logger.getLogger(HolidayC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
