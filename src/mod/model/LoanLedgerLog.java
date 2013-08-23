/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LESTER
 */
@Entity
@Table(name = "loan_ledger_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanLedgerLog.findAll", query = "SELECT l FROM LoanLedgerLog l"),
    @NamedQuery(name = "LoanLedgerLog.findByLoanLedgerLogId", query = "SELECT l FROM LoanLedgerLog l WHERE l.loanLedgerLogId = :loanLedgerLogId"),
    @NamedQuery(name = "LoanLedgerLog.findByNotification", query = "SELECT l FROM LoanLedgerLog l WHERE l.notification = :notification"),
    @NamedQuery(name = "LoanLedgerLog.findByTransDate", query = "SELECT l FROM LoanLedgerLog l WHERE l.transDate = :transDate"),
    @NamedQuery(name = "LoanLedgerLog.findByUserId", query = "SELECT l FROM LoanLedgerLog l WHERE l.userId = :userId"),
    @NamedQuery(name = "LoanLedgerLog.findByEmpLoanId", query = "SELECT l FROM LoanLedgerLog l WHERE l.empLoanId = :empLoanId"),
    @NamedQuery(name = "LoanLedgerLog.findByMonthly", query = "SELECT l FROM LoanLedgerLog l WHERE l.monthly = :monthly"),
    @NamedQuery(name = "LoanLedgerLog.findByDueDate", query = "SELECT l FROM LoanLedgerLog l WHERE l.dueDate = :dueDate")})
public class LoanLedgerLog  extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loan_ledger_log_id", nullable = false)
    private Integer loanLedgerLogId;
    @Column(name = "notification", length = 1000)
    private String notification;
    @Column(name = "trans_date")
    @Temporal(TemporalType.DATE)
    private Date transDate;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "emp_loan_id")
    private Integer empLoanId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monthly", precision = 9, scale = 2)
    private BigDecimal monthly;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    public LoanLedgerLog() {
    }

    public LoanLedgerLog(Integer loanLedgerLogId) {
        this.loanLedgerLogId = loanLedgerLogId;
    }

    public Integer getLoanLedgerLogId() {
        return loanLedgerLogId;
    }

    public void setLoanLedgerLogId(Integer loanLedgerLogId) {
        this.loanLedgerLogId = loanLedgerLogId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmpLoanId() {
        return empLoanId;
    }

    public void setEmpLoanId(Integer empLoanId) {
        this.empLoanId = empLoanId;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanLedgerLogId != null ? loanLedgerLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanLedgerLog)) {
            return false;
        }
        LoanLedgerLog other = (LoanLedgerLog) object;
        if ((this.loanLedgerLogId == null && other.loanLedgerLogId != null) || (this.loanLedgerLogId != null && !this.loanLedgerLogId.equals(other.loanLedgerLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.LoanLedgerLog[ loanLedgerLogId=" + loanLedgerLogId + " ]";
    }
    
}
