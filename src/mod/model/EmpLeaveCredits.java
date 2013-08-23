/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MIS
 */
@Entity
@Table(name = "emp_leave_credits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpLeaveCredits.findAll", query = "SELECT e FROM EmpLeaveCredits e"),
    @NamedQuery(name = "EmpLeaveCredits.findByElcId", query = "SELECT e FROM EmpLeaveCredits e WHERE e.elcId = :elcId"),
    @NamedQuery(name = "EmpLeaveCredits.findByEmpId", query = "SELECT e FROM EmpLeaveCredits e WHERE e.empId = :empId"),
    @NamedQuery(name = "EmpLeaveCredits.findByLeaveTypeId", query = "SELECT e FROM EmpLeaveCredits e WHERE e.leaveTypeId = :leaveTypeId"),
    @NamedQuery(name = "EmpLeaveCredits.findByLeaveCredits", query = "SELECT e FROM EmpLeaveCredits e WHERE e.leaveCredits = :leaveCredits"),
    @NamedQuery(name = "EmpLeaveCredits.findByCreditsUsed", query = "SELECT e FROM EmpLeaveCredits e WHERE e.creditsUsed = :creditsUsed")})
public class EmpLeaveCredits extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "elc_id", nullable = false)
    private Integer elcId;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "leave_type_id")
    private Short leaveTypeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "leave_credits", precision = 9, scale = 2)
    private BigDecimal leaveCredits;
    @Column(name = "credits_used", precision = 9, scale = 2)
    private BigDecimal creditsUsed;

    public EmpLeaveCredits() {
    }

    public EmpLeaveCredits(Integer elcId) {
        this.elcId = elcId;
    }

    public Integer getElcId() {
        return elcId;
    }

    public void setElcId(Integer elcId) {
        this.elcId = elcId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Short getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Short leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public BigDecimal getLeaveCredits() {
        return leaveCredits;
    }

    public void setLeaveCredits(BigDecimal leaveCredits) {
        this.leaveCredits = leaveCredits;
    }

    public BigDecimal getCreditsUsed() {
        return creditsUsed;
    }

    public void setCreditsUsed(BigDecimal creditsUsed) {
        this.creditsUsed = creditsUsed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elcId != null ? elcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpLeaveCredits)) {
            return false;
        }
        EmpLeaveCredits other = (EmpLeaveCredits) object;
        if ((this.elcId == null && other.elcId != null) || (this.elcId != null && !this.elcId.equals(other.elcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmpLeaveCredits[ elcId=" + elcId + " ]";
    }
    
}
