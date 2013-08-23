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
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s"),
    @NamedQuery(name = "Shift.findByShiftId", query = "SELECT s FROM Shift s WHERE s.shiftId = :shiftId"),
    @NamedQuery(name = "Shift.findByShiftDesc", query = "SELECT s FROM Shift s WHERE s.shiftDesc = :shiftDesc"),
    @NamedQuery(name = "Shift.findByInTime1", query = "SELECT s FROM Shift s WHERE s.inTime1 = :inTime1"),
    @NamedQuery(name = "Shift.findByOutTime1", query = "SELECT s FROM Shift s WHERE s.outTime1 = :outTime1"),
    @NamedQuery(name = "Shift.findByIn1LogStart", query = "SELECT s FROM Shift s WHERE s.in1LogStart = :in1LogStart"),
    @NamedQuery(name = "Shift.findByIn1LogEnd", query = "SELECT s FROM Shift s WHERE s.in1LogEnd = :in1LogEnd"),
    @NamedQuery(name = "Shift.findByOut1LogStart", query = "SELECT s FROM Shift s WHERE s.out1LogStart = :out1LogStart"),
    @NamedQuery(name = "Shift.findByOut1LogEnd", query = "SELECT s FROM Shift s WHERE s.out1LogEnd = :out1LogEnd"),
    @NamedQuery(name = "Shift.findByInTime2", query = "SELECT s FROM Shift s WHERE s.inTime2 = :inTime2"),
    @NamedQuery(name = "Shift.findByOutTime2", query = "SELECT s FROM Shift s WHERE s.outTime2 = :outTime2"),
    @NamedQuery(name = "Shift.findByIn2LogStart", query = "SELECT s FROM Shift s WHERE s.in2LogStart = :in2LogStart"),
    @NamedQuery(name = "Shift.findByIn2LogEnd", query = "SELECT s FROM Shift s WHERE s.in2LogEnd = :in2LogEnd"),
    @NamedQuery(name = "Shift.findByOut2LogStart", query = "SELECT s FROM Shift s WHERE s.out2LogStart = :out2LogStart"),
    @NamedQuery(name = "Shift.findByOut2LogEnd", query = "SELECT s FROM Shift s WHERE s.out2LogEnd = :out2LogEnd")})
public class Shift extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shift_id", nullable = false)
    private Integer shiftId;
    @Column(name = "shift_desc", length = 45)
    private String shiftDesc;
    @Column(name = "in_time1")
    @Temporal(TemporalType.TIME)
    private Date inTime1;
    @Column(name = "out_time1")
    @Temporal(TemporalType.TIME)
    private Date outTime1;
    @Column(name = "in1_log_start")
    @Temporal(TemporalType.TIME)
    private Date in1LogStart;
    @Column(name = "in1_log_end")
    @Temporal(TemporalType.TIME)
    private Date in1LogEnd;
    @Column(name = "out1_log_start")
    @Temporal(TemporalType.TIME)
    private Date out1LogStart;
    @Column(name = "out1_log_end")
    @Temporal(TemporalType.TIME)
    private Date out1LogEnd;
    @Column(name = "in_time2")
    @Temporal(TemporalType.TIME)
    private Date inTime2;
    @Column(name = "out_time2")
    @Temporal(TemporalType.TIME)
    private Date outTime2;
    @Column(name = "in2_log_start")
    @Temporal(TemporalType.TIME)
    private Date in2LogStart;
    @Column(name = "in2_log_end")
    @Temporal(TemporalType.TIME)
    private Date in2LogEnd;
    @Column(name = "out2_log_start")
    @Temporal(TemporalType.TIME)
    private Date out2LogStart;
    @Column(name = "out2_log_end")
    @Temporal(TemporalType.TIME)
    private Date out2LogEnd;

    public Shift() {
    }

    public Shift(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftDesc() {
        return shiftDesc;
    }

    public void setShiftDesc(String shiftDesc) {
        this.shiftDesc = shiftDesc;
    }

    public Date getInTime1() {
        return inTime1;
    }

    public void setInTime1(Date inTime1) {
        this.inTime1 = inTime1;
    }

    public Date getOutTime1() {
        return outTime1;
    }

    public void setOutTime1(Date outTime1) {
        this.outTime1 = outTime1;
    }

    public Date getIn1LogStart() {
        return in1LogStart;
    }

    public void setIn1LogStart(Date in1LogStart) {
        this.in1LogStart = in1LogStart;
    }

    public Date getIn1LogEnd() {
        return in1LogEnd;
    }

    public void setIn1LogEnd(Date in1LogEnd) {
        this.in1LogEnd = in1LogEnd;
    }

    public Date getOut1LogStart() {
        return out1LogStart;
    }

    public void setOut1LogStart(Date out1LogStart) {
        this.out1LogStart = out1LogStart;
    }

    public Date getOut1LogEnd() {
        return out1LogEnd;
    }

    public void setOut1LogEnd(Date out1LogEnd) {
        this.out1LogEnd = out1LogEnd;
    }

    public Date getInTime2() {
        return inTime2;
    }

    public void setInTime2(Date inTime2) {
        this.inTime2 = inTime2;
    }

    public Date getOutTime2() {
        return outTime2;
    }

    public void setOutTime2(Date outTime2) {
        this.outTime2 = outTime2;
    }

    public Date getIn2LogStart() {
        return in2LogStart;
    }

    public void setIn2LogStart(Date in2LogStart) {
        this.in2LogStart = in2LogStart;
    }

    public Date getIn2LogEnd() {
        return in2LogEnd;
    }

    public void setIn2LogEnd(Date in2LogEnd) {
        this.in2LogEnd = in2LogEnd;
    }

    public Date getOut2LogStart() {
        return out2LogStart;
    }

    public void setOut2LogStart(Date out2LogStart) {
        this.out2LogStart = out2LogStart;
    }

    public Date getOut2LogEnd() {
        return out2LogEnd;
    }

    public void setOut2LogEnd(Date out2LogEnd) {
        this.out2LogEnd = out2LogEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftId != null ? shiftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftId == null && other.shiftId != null) || (this.shiftId != null && !this.shiftId.equals(other.shiftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Shift[ shiftId=" + shiftId + " ]";
    }
    
}
