package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import mod.connection.myDBConn;
import mod.model.PositionLevel;
import mod.model.entity_manager.PositionLevelEM;

public class PositionLevelC {
        public DefaultComboBoxModel getPositionLevelComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<PositionLevel> positionlevels = new PositionLevelEM(con).get();

            model.addElement("--SELECT--");

            for (PositionLevel positionlevel : positionlevels) {

                model.addElement(positionlevel);
            }
        } catch (Exception ex) {
            Logger.getLogger(PositionLevelC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
}
