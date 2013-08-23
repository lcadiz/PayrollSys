package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import mod.connection.myDBConn;
import mod.model.LeaveType;
import mod.model.entity_manager.LeaveTypeEM;

public class LeaveTypeC {
        //COMBO BOX POPULATOR
    public DefaultComboBoxModel getLeaveTypeComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<LeaveType> leavetypes = new LeaveTypeEM(con).get();

            model.addElement("--SELECT--");

            for (LeaveType leavetype : leavetypes) {

                model.addElement(leavetype);
            }
        } catch (Exception ex) {
            Logger.getLogger(LeaveTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
}
