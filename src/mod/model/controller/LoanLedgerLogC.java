
package mod.model.controller;

import java.sql.Connection;
import mod.connection.myDBConn;
import mod.model.LoanLedgerLog;
import mod.model.entity_manager.LoanLedgerLogEM;


public class LoanLedgerLogC {
        //APPENDERS
    public int addLoanLedgerLog(LoanLedgerLog loanledgerlog) {
        Connection con = myDBConn.getConnection();
        LoanLedgerLogEM dem = new LoanLedgerLogEM(con);
        return dem.persist(loanledgerlog);
    }

    public int editLoanLedgerLog(LoanLedgerLog loanledgerlog) {
        Connection con = myDBConn.getConnection();
        LoanLedgerLogEM dem = new LoanLedgerLogEM(con);
        return dem.update(loanledgerlog);
    }

    public int deleteLoanLedgerLog(LoanLedgerLog loanledgerlog) {
        Connection con = myDBConn.getConnection();
        LoanLedgerLogEM dem = new LoanLedgerLogEM(con);
        return dem.delete(loanledgerlog);
    }
}
