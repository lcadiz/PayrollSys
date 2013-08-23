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
@Table(name = "leave_app")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveApp.findAll", query = "SELECT l FROM LeaveApp l"),
    @NamedQuery(name = "LeaveApp.findByLeaveAppId", query = "SELECT l FROM LeaveApp l WHERE l.leaveAppId = :leaveAppId"),
    @NamedQuery(name = "LeaveApp.findByEmpId", query = "SELECT l FROM LeaveApp l WHERE l.empId = :empId"),
    @NamedQuery(name = "LeaveApp.findByLeaveTypeId", query = "SELECT l FROM LeaveApp l WHERE l.leaveTypeId = :leaveTypeId"),
    @NamedQuery(name = "LeaveApp.findByStartDateTime", query = "SELECT l FROM LeaveApp l WHERE l.startDateTime = :startDateTime"),
    @NamedQuery(name = "LeaveApp.findByEndDateTime", query = "SELECT l FROM LeaveApp l WHERE l.endDateTime = :endDateTime"),
    @NamedQuery(name = "LeaveApp.findByLeaveDays", query = "SELECT l FROM LeaveApp l WHERE l.leaveDays = :leaveDays"),
    @NamedQuery(name = "LeaveApp.findByCreditsUsed", query = "SELECT l FROM LeaveApp l WHERE l.creditsUsed = :creditsUsed"),
    @NamedQuery(name = "LeaveApp.findByReason", query = "SELECT l FROM LeaveApp l WHERE l.reason = :reason"),
    @NamedQuery(name = "LeaveApp.findByTransDate", query = "SELECT l FROM LeaveApp l WHERE l.transDate = :transDate"),
    @NamedQuery(name = "LeaveApp.findByRApprovedBy", query = "SELECT l FROM LeaveApp l WHERE l.rApprovedBy = :rApprovedBy"),
    @NamedQuery(name = "LeaveApp.findByApprovedBy", query = "SELECT l FROM LeaveApp l WHERE l.approvedBy = :approvedBy"),
    @NamedQuery(name = "LeaveApp.findByStatusId", query = "SELECT l FROM LeaveApp l WHERE l.statusId = :statusId"),
    @NamedQuery(name = "LeaveApp.findByRApproveFlag", query = "SELECT l FROM LeaveApp l WHERE l.rApproveFlag = :rApproveFlag"),
    @NamedQuery(name = "LeaveApp.findByApproveFlag", query = "SELECT l FROM LeaveApp l WHERE l.approveFlag = :approveFlag"),
    @NamedQuery(name = "LeaveApp.findByRemarks", query = "SELECT l FROM LeaveApp l WHERE l.remarks = :remarks")})
public class LeaveApp extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leave_app_id", nullable = false)
    private Integer leaveAppId;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "leave_type_id")
    private Short leaveTypeId;
    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "leave_days", precision = 9, scale = 2)
    private BigDecimal leaveDays;
    @Column(name = "credits_used", precision = 9, scale = 2)
    private BigDecimal creditsUsed;
    @Column(name = "reason", length = 500)
    private String reason;
    @Column(name = "trans_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "r_approved_by")
    private Integer rApprovedBy;
    @Column(name = "approved_by")
    private Integer approvedBy;
    @Column(name = "status_id")
    private Short statusId;
    @Column(name = "r_approve_flag")
    private Short rApproveFlag;
    @Column(name = "approve_flag")
    private Short approveFlag;
    @Column(name = "remarks", length = 500)
    private String remarks;

    public LeaveApp() {
    }

    public LeaveApp(Integer leaveAppId) {
        this.leaveAppId = leaveAppId;
    }

    public Integer getLeaveAppId() {
        return leaveAppId;
    }

    public void setLeaveAppId(Integer leaveAppId) {
        this.leaveAppId = leaveAppId;
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public BigDecimal getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(BigDecimal leaveDays) {
        this.leaveDays = leaveDays;
    }

    public BigDecimal getCreditsUsed() {
        return creditsUsed;
    }

    public void setCreditsUsed(BigDecimal creditsUsed) {
        this.creditsUsed = creditsUsed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Integer getRApprovedBy() {
        return rApprovedBy;
    }

    public void setRApprovedBy(Integer rApprovedBy) {
        this.rApprovedBy = rApprovedBy;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Short getStatusId() {
        return statusId;
    }

    public void setStatusId(Short statusId) {
        this.statusId = statusId;
    }

    public Short getRApproveFlag() {
        return rApproveFlag;
    }

    public void setRApproveFlag(Short rApproveFlag) {
        this.rApproveFlag = rApproveFlag;
    }

    public Short getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(Short approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveAppId != null ? leaveAppId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveApp)) {
            return false;
        }
        LeaveApp other = (LeaveApp) object;
        if ((this.leaveAppId == null && other.leaveAppId != null) || (this.leaveAppId != null && !this.leaveAppId.equals(other.leaveAppId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.LeaveApp[ leaveAppId=" + leaveAppId + " ]";
    }
    
}
