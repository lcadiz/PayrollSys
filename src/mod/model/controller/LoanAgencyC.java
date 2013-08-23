package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.LoanAgency;
import mod.model.entity_manager.LoanAgencyEM;

public class LoanAgencyC {

     public DefaultComboBoxModel getLoanAgencyComboModel() {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<LoanAgency> las = new LoanAgencyEM(con).get();

            model.addElement("--SELECT--");

            for (LoanAgency la : las) {

                model.addElement(la);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
    public DefaultListModel getLoanAgencyListModel() {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<LoanAgency> loanagencys = new LoanAgencyEM(con).get();

            for (LoanAgency loanagency : loanagencys) {

                model.addElement(loanagency);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addLoanAgency(LoanAgency loanagency) {
        Connection con = myDBConn.getConnection();
        LoanAgencyEM dem = new LoanAgencyEM(con);
        return dem.persist(loanagency);
    }

    public int editLoanAgency(LoanAgency loanagency) {
        Connection con = myDBConn.getConnection();
        LoanAgencyEM dem = new LoanAgencyEM(con);
        return dem.update(loanagency);
    }

    public int deleteLoanAgency(LoanAgency loanagency) {
        Connection con = myDBConn.getConnection();
        LoanAgencyEM dem = new LoanAgencyEM(con);
        return dem.delete(loanagency);
    }

    public String GetAgencyName(int laid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LoanAgency> LoanAgencys = new LoanAgencyEM(con).getById(laid);

            for (LoanAgency loanagency : LoanAgencys) {
                Value = String.valueOf(loanagency.getAgencyName());
            }

        } catch (Exception ex) {
            Logger.getLogger(LoanAgencyC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
