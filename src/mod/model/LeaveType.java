/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
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
 * @author LESTER
 */
@Entity
@Table(name = "leave_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveType.findAll", query = "SELECT l FROM LeaveType l"),
    @NamedQuery(name = "LeaveType.findByLeaveTypeId", query = "SELECT l FROM LeaveType l WHERE l.leaveTypeId = :leaveTypeId"),
    @NamedQuery(name = "LeaveType.findByLeaveTypeDesc", query = "SELECT l FROM LeaveType l WHERE l.leaveTypeDesc = :leaveTypeDesc")})
public class LeaveType extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leave_type_id", nullable = false)
    private Short leaveTypeId;
    @Column(name = "leave_type_desc", length = 80)
    private String leaveTypeDesc;

    public LeaveType() {
    }

    public LeaveType(Short leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public Short getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Short leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeDesc() {
        return leaveTypeDesc;
    }

    public void setLeaveTypeDesc(String leaveTypeDesc) {
        this.leaveTypeDesc = leaveTypeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveTypeId != null ? leaveTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveType)) {
            return false;
        }
        LeaveType other = (LeaveType) object;
        if ((this.leaveTypeId == null && other.leaveTypeId != null) || (this.leaveTypeId != null && !this.leaveTypeId.equals(other.leaveTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/leavetype.png") + ">&nbsp</td><td>" + leaveTypeDesc + "<font></td></th>";
        return lbl ;
    }
    
}
