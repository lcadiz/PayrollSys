package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Position;
import mod.model.entity_manager.PositionEM;

public class PositionC {
    //COMBO BOX POPULATOR

    public DefaultComboBoxModel getPositionComboModel(int secid) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getbySection(secid);

            model.addElement("--SELECT--");

            for (Position position : positions) {

                model.addElement(position);
            }
        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //LIST BOX POPULATOR
    public DefaultListModel getPositionListModel(int secid) {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getbySection(secid);

            for (Position position : positions) {

                model.addElement(position);
            }
        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    


    //APPENDERS
    public int addPosition(Position position) {
        Connection con = myDBConn.getConnection();
        PositionEM dem = new PositionEM(con);
        return dem.persist(position);
    }

    public int editPosition(Position position) {
        Connection con = myDBConn.getConnection();
        PositionEM dem = new PositionEM(con);
        return dem.update(position);
    }

    public int deletePosition(Position position) {
        Connection con = myDBConn.getConnection();
        PositionEM dem = new PositionEM(con);
        return dem.delete(position);
    }
    //END OF APPENDERS

    public String GetPositionDesc(int posid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getPositonId(posid);

            for (Position position : positions) {
                Value = String.valueOf(position.getPositionDesc());
            }

        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetPositionDescwIcon(int posid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getPositonId(posid);

            for (Position position : positions) {
                Value = String.valueOf(position);
            }

        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSalaryID(int posid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getPositonId(posid);

            for (Position position : positions) {
                Value = String.valueOf(position.getSalaryId());
            }

        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    
     public int GetPositionLevel(int posid) {
        int Value =0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Position> positions = new PositionEM(con).getPositonId(posid);

            for (Position position : positions) {
                Value = position.getPosLevelId();
            }

        } catch (Exception ex) {
            Logger.getLogger(PositionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
