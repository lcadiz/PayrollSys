package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.LoanType;
import mod.model.entity_manager.LoanTypeEM;

public class LoanTypeC {
     public DefaultComboBoxModel getLoanTypeComboModel(int laid) {
        Connection con = null;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<LoanType> lts = new LoanTypeEM(con).getByLoanAgencyId(laid);

            model.addElement("--SELECT--");

            for (LoanType lt : lts) {

                model.addElement(lt);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
    public DefaultListModel getLoanTypeListModel(int laid) {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<LoanType> lts = new LoanTypeEM(con).getByLoanAgencyId(laid);

            for (LoanType lt : lts) {

                model.addElement(lt);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoanTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addLoanType(LoanType loantype) {
        Connection con = myDBConn.getConnection();
        LoanTypeEM dem = new LoanTypeEM(con);
        return dem.persist(loantype);
    }

    public int editLoanType(LoanType loantype) {
        Connection con = myDBConn.getConnection();
        LoanTypeEM dem = new LoanTypeEM(con);
        return dem.update(loantype);
    }

    public int deleteLoanType(LoanType loantype) {
        Connection con = myDBConn.getConnection();
        LoanTypeEM dem = new LoanTypeEM(con);
        return dem.delete(loantype);
    }

    public String GetLoanDescription(int ltid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<LoanType> LoanTypes = new LoanTypeEM(con).getById(ltid);

            for (LoanType loantype : LoanTypes) {
                Value = String.valueOf(loantype.getLoanDescription());
            }

        } catch (Exception ex) {
            Logger.getLogger(LoanTypeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
