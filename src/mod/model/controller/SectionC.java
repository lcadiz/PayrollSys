/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import mod.connection.myDBConn;
import mod.model.Section;
import mod.model.entity_manager.SectionEM;

/**
 *
 * @author LESTER
 */
public class SectionC {
      //COMBO BOX POPULATOR
    public DefaultComboBoxModel getSecComboModel(int divid) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Section> secs = new SectionEM(con).getByDivId(divid);

            model.addElement("--SELECT--");

            for (Section sec : secs) {
                model.addElement(sec);
            }
        } catch (Exception ex) {
            Logger.getLogger(SectionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
     public String GetSecName(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Section> sections = new SectionEM(con).getBySecId(sid);

            for (Section section : sections) {
                Value = String.valueOf(section);
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
     
     public int GetDivID(int sid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Section> sections = new SectionEM(con).getBySecId(sid);

            for (Section section : sections) {
                Value = section.getDivId();
            }

        } catch (Exception ex) {
            Logger.getLogger(SectionC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
