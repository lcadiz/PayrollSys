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
 * @author MIS
 */
@Entity
@Table(name = "emp_leave_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpLeaveDetails.findAll", query = "SELECT e FROM EmpLeaveDetails e"),
    @NamedQuery(name = "EmpLeaveDetails.findByEldId", query = "SELECT e FROM EmpLeaveDetails e WHERE e.eldId = :eldId"),
    @NamedQuery(name = "EmpLeaveDetails.findByLeaveAppId", query = "SELECT e FROM EmpLeaveDetails e WHERE e.leaveAppId = :leaveAppId"),
    @NamedQuery(name = "EmpLeaveDetails.findByLeaveDate", query = "SELECT e FROM EmpLeaveDetails e WHERE e.leaveDate = :leaveDate"),
    @NamedQuery(name = "EmpLeaveDetails.findByLeaveHours", query = "SELECT e FROM EmpLeaveDetails e WHERE e.leaveHours = :leaveHours"),
    @NamedQuery(name = "EmpLeaveDetails.findByEmpLeaveId", query = "SELECT e FROM EmpLeaveDetails e WHERE e.empLeaveId = :empLeaveId")})
public class EmpLeaveDetails extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eld_id", nullable = false)
    private Integer eldId;
    @Column(name = "leave_app_id")
    private Integer leaveAppId;
    @Column(name = "leave_date")
    @Temporal(TemporalType.DATE)
    private Date leaveDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "leave_hours", precision = 9, scale = 2)
    private BigDecimal leaveHours;
    @Column(name = "emp_leave_id")
    private Integer empLeaveId;

    public EmpLeaveDetails() {
    }

    public EmpLeaveDetails(Integer eldId) {
        this.eldId = eldId;
    }

    public Integer getEldId() {
        return eldId;
    }

    public void setEldId(Integer eldId) {
        this.eldId = eldId;
    }

    public Integer getLeaveAppId() {
        return leaveAppId;
    }

    public void setLeaveAppId(Integer leaveAppId) {
        this.leaveAppId = leaveAppId;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public BigDecimal getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(BigDecimal leaveHours) {
        this.leaveHours = leaveHours;
    }

    public Integer getEmpLeaveId() {
        return empLeaveId;
    }

    public void setEmpLeaveId(Integer empLeaveId) {
        this.empLeaveId = empLeaveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eldId != null ? eldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpLeaveDetails)) {
            return false;
        }
        EmpLeaveDetails other = (EmpLeaveDetails) object;
        if ((this.eldId == null && other.eldId != null) || (this.eldId != null && !this.eldId.equals(other.eldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmpLeaveDetails[ eldId=" + eldId + " ]";
    }
    
}
