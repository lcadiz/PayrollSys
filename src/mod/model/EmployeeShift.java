/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LESTER
 */
@Entity
@Table(name = "employee_shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeShift.findAll", query = "SELECT e FROM EmployeeShift e"),
    @NamedQuery(name = "EmployeeShift.findByEmpShiftId", query = "SELECT e FROM EmployeeShift e WHERE e.empShiftId = :empShiftId"),
    @NamedQuery(name = "EmployeeShift.findByEmpId", query = "SELECT e FROM EmployeeShift e WHERE e.empId = :empId"),
    @NamedQuery(name = "EmployeeShift.findByShiftId", query = "SELECT e FROM EmployeeShift e WHERE e.shiftId = :shiftId"),
    @NamedQuery(name = "EmployeeShift.findByStartDate", query = "SELECT e FROM EmployeeShift e WHERE e.startDate = :startDate"),
    @NamedQuery(name = "EmployeeShift.findByEndDate", query = "SELECT e FROM EmployeeShift e WHERE e.endDate = :endDate"),
    @NamedQuery(name = "EmployeeShift.findByUserId", query = "SELECT e FROM EmployeeShift e WHERE e.userId = :userId"),
    @NamedQuery(name = "EmployeeShift.findByTransDate", query = "SELECT e FROM EmployeeShift e WHERE e.transDate = :transDate"),
    @NamedQuery(name = "EmployeeShift.findByPFlag", query = "SELECT e FROM EmployeeShift e WHERE e.pFlag = :pFlag")})
public class EmployeeShift extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_shift_id", nullable = false)
    private Integer empShiftId;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "shift_id")
    private Integer shiftId;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "trans_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "p_flag")
    private Short pFlag;

    public EmployeeShift() {
    }

    public EmployeeShift(Integer empShiftId) {
        this.empShiftId = empShiftId;
    }

    public Integer getEmpShiftId() {
        return empShiftId;
    }

    public void setEmpShiftId(Integer empShiftId) {
        this.empShiftId = empShiftId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Short getPFlag() {
        return pFlag;
    }

    public void setPFlag(Short pFlag) {
        this.pFlag = pFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empShiftId != null ? empShiftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeShift)) {
            return false;
        }
        EmployeeShift other = (EmployeeShift) object;
        if ((this.empShiftId == null && other.empShiftId != null) || (this.empShiftId != null && !this.empShiftId.equals(other.empShiftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmployeeShift[ empShiftId=" + empShiftId + " ]";
    }
    
}
