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
@Table(name = "employee_loan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeLoan.findAll", query = "SELECT e FROM EmployeeLoan e"),
    @NamedQuery(name = "EmployeeLoan.findByEmpLoanId", query = "SELECT e FROM EmployeeLoan e WHERE e.empLoanId = :empLoanId"),
    @NamedQuery(name = "EmployeeLoan.findByLoanId", query = "SELECT e FROM EmployeeLoan e WHERE e.loanId = :loanId"),
    @NamedQuery(name = "EmployeeLoan.findByParticular", query = "SELECT e FROM EmployeeLoan e WHERE e.particular = :particular"),
    @NamedQuery(name = "EmployeeLoan.findByTerm", query = "SELECT e FROM EmployeeLoan e WHERE e.term = :term"),
    @NamedQuery(name = "EmployeeLoan.findByLoanDate", query = "SELECT e FROM EmployeeLoan e WHERE e.loanDate = :loanDate"),
    @NamedQuery(name = "EmployeeLoan.findByDueDate", query = "SELECT e FROM EmployeeLoan e WHERE e.dueDate = :dueDate"),
    @NamedQuery(name = "EmployeeLoan.findByAmount", query = "SELECT e FROM EmployeeLoan e WHERE e.amount = :amount"),
    @NamedQuery(name = "EmployeeLoan.findByMonthly", query = "SELECT e FROM EmployeeLoan e WHERE e.monthly = :monthly"),
    @NamedQuery(name = "EmployeeLoan.findByEmpId", query = "SELECT e FROM EmployeeLoan e WHERE e.empId = :empId")})
public class EmployeeLoan extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_loan_id", nullable = false)
    private Integer empLoanId;
    @Column(name = "loan_id")
    private Integer loanId;
    @Column(name = "particular", length = 45)
    private String particular;
    @Column(name = "term")
    private Short term;
    @Column(name = "loan_date")
    @Temporal(TemporalType.DATE)
    private Date loanDate;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 18, scale = 2)
    private BigDecimal amount;
    @Column(name = "monthly", precision = 18, scale = 2)
    private BigDecimal monthly;
    @Column(name = "emp_id")
    private Integer empId;

    public EmployeeLoan() {
    }

    public EmployeeLoan(Integer empLoanId) {
        this.empLoanId = empLoanId;
    }

    public Integer getEmpLoanId() {
        return empLoanId;
    }

    public void setEmpLoanId(Integer empLoanId) {
        this.empLoanId = empLoanId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public Short getTerm() {
        return term;
    }

    public void setTerm(Short term) {
        this.term = term;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empLoanId != null ? empLoanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeLoan)) {
            return false;
        }
        EmployeeLoan other = (EmployeeLoan) object;
        if ((this.empLoanId == null && other.empLoanId != null) || (this.empLoanId != null && !this.empLoanId.equals(other.empLoanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmployeeLoan[ empLoanId=" + empLoanId + " ]";
    }
    
}
