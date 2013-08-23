package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.LoanLedger;
import mod.model.entity_manager.LoanLedgerEM;
import mod.others.myFunction;

public class LoanLedgerC {

    public DefaultTableModel getLoanLedgerTableModel(JTable tbl, int elid) {
        Connection con = null;

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        DefaultTableCellRenderer AlignRight = new DefaultTableCellRenderer();
        DefaultTableCellRenderer AlignCenter = new DefaultTableCellRenderer();

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);

        AlignCenter.setHorizontalAlignment(0);
        AlignRight.setHorizontalAlignment(SwingConstants.RIGHT);

        tbl.setRowHeight(28);
        tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(3).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(4).setCellRenderer(AlignRight);
        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<LoanLedger> LoanLedgers = new LoanLedgerEM(con).getById(elid);

            int rc = 1;

            for (LoanLedger loanledger : LoanLedgers) {
                Object[] newCol = new Object[5];

                int i = 0;
                String particular = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/ledgermini.png") + "></td>&nbsp<td >" + loanledger.getParticular().toString() + "</td></th>";

                newCol[i++] = loanledger.getLoanLedgerId();
                newCol[i++] = loanledger.getTransDate();
                newCol[i++] = particular;
                newCol[i++] = myFunction.amountFormat2(loanledger.getDebit().toString());
                newCol[i++] = myFunction.amountFormat2(loanledger.getCredit().toString());
              
             

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(LoanLedgerC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addLoanLedger(LoanLedger loanledger) {
        Connection con = myDBConn.getConnection();
        LoanLedgerEM dem = new LoanLedgerEM(con);
        return dem.persist(loanledger);
    }

    public int editLoanLedger(LoanLedger loanledger) {
        Connection con = myDBConn.getConnection();
        LoanLedgerEM dem = new LoanLedgerEM(con);
        return dem.update(loanledger);
    }

    public int deleteLoanLedger(LoanLedger loanledger) {
        Connection con = myDBConn.getConnection();
        LoanLedgerEM dem = new LoanLedgerEM(con);
        return dem.delete(loanledger);
    }
}
