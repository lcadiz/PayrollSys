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
@Table(name = "loan_ledger")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanLedger.findAll", query = "SELECT l FROM LoanLedger l"),
    @NamedQuery(name = "LoanLedger.findByLoanLedgerId", query = "SELECT l FROM LoanLedger l WHERE l.loanLedgerId = :loanLedgerId"),
    @NamedQuery(name = "LoanLedger.findByEmpLoanId", query = "SELECT l FROM LoanLedger l WHERE l.empLoanId = :empLoanId"),
    @NamedQuery(name = "LoanLedger.findByTransDate", query = "SELECT l FROM LoanLedger l WHERE l.transDate = :transDate"),
    @NamedQuery(name = "LoanLedger.findByParticular", query = "SELECT l FROM LoanLedger l WHERE l.particular = :particular"),
    @NamedQuery(name = "LoanLedger.findByDebit", query = "SELECT l FROM LoanLedger l WHERE l.debit = :debit"),
    @NamedQuery(name = "LoanLedger.findByCredit", query = "SELECT l FROM LoanLedger l WHERE l.credit = :credit"),
    @NamedQuery(name = "LoanLedger.findByPayrollPeriodId", query = "SELECT l FROM LoanLedger l WHERE l.payrollPeriodId = :payrollPeriodId")})
public class LoanLedger extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "loan_ledger_id", nullable = false)
    private Integer loanLedgerId;
    @Column(name = "emp_loan_id")
    private Integer empLoanId;
    @Column(name = "trans_date")
    @Temporal(TemporalType.DATE)
    private Date transDate;
    @Column(name = "particular", length = 200)
    private String particular;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debit", precision = 9, scale = 2)
    private BigDecimal debit;
    @Column(name = "credit", precision = 9, scale = 2)
    private BigDecimal credit;
    @Column(name = "payroll_period_id")
    private Integer payrollPeriodId;

    public LoanLedger() {
    }

    public LoanLedger(Integer loanLedgerId) {
        this.loanLedgerId = loanLedgerId;
    }

    public Integer getLoanLedgerId() {
        return loanLedgerId;
    }

    public void setLoanLedgerId(Integer loanLedgerId) {
        this.loanLedgerId = loanLedgerId;
    }

    public Integer getEmpLoanId() {
        return empLoanId;
    }

    public void setEmpLoanId(Integer empLoanId) {
        this.empLoanId = empLoanId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Integer getPayrollPeriodId() {
        return payrollPeriodId;
    }

    public void setPayrollPeriodId(Integer payrollPeriodId) {
        this.payrollPeriodId = payrollPeriodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanLedgerId != null ? loanLedgerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanLedger)) {
            return false;
        }
        LoanLedger other = (LoanLedger) object;
        if ((this.loanLedgerId == null && other.loanLedgerId != null) || (this.loanLedgerId != null && !this.loanLedgerId.equals(other.loanLedgerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.LoanLedger[ loanLedgerId=" + loanLedgerId + " ]";
    }
    
}
