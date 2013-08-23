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
 * @author LESTER
 */
@Entity
@Table(name = "sss")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sss.findAll", query = "SELECT s FROM Sss s"),
    @NamedQuery(name = "Sss.findBySssId", query = "SELECT s FROM Sss s WHERE s.sssId = :sssId"),
    @NamedQuery(name = "Sss.findByRangeStart", query = "SELECT s FROM Sss s WHERE s.rangeStart = :rangeStart"),
    @NamedQuery(name = "Sss.findByRangeEnd", query = "SELECT s FROM Sss s WHERE s.rangeEnd = :rangeEnd"),
    @NamedQuery(name = "Sss.findBySalaryBase", query = "SELECT s FROM Sss s WHERE s.salaryBase = :salaryBase"),
    @NamedQuery(name = "Sss.findByEr", query = "SELECT s FROM Sss s WHERE s.er = :er"),
    @NamedQuery(name = "Sss.findByEe", query = "SELECT s FROM Sss s WHERE s.ee = :ee"),
    @NamedQuery(name = "Sss.findByEc", query = "SELECT s FROM Sss s WHERE s.ec = :ec")})
public class SSS extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sss_id", nullable = false)
    private Short sssId;
    //@Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "range_start", precision = 18, scale = 2)
    private BigDecimal rangeStart;
    @Column(name = "range_end", precision = 18, scale = 2)
    private BigDecimal rangeEnd;
    @Column(name = "salary_base", precision = 18, scale = 2)
    private BigDecimal salaryBase;
    @Column(name = "er", precision = 9, scale = 2)
    private BigDecimal er;
    @Column(name = "ee", precision = 9, scale = 2)
    private BigDecimal ee;
    @Column(name = "ec", precision = 9, scale = 2)
    private BigDecimal ec;

    public SSS() {
    }

    public SSS(Short sssId) {
        this.sssId = sssId;
    }

    public Short getSssId() {
        return sssId;
    }

    public void setSssId(Short sssId) {
        this.sssId = sssId;
    }

    public BigDecimal getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(BigDecimal rangeStart) {
        this.rangeStart = rangeStart;
    }

    public BigDecimal getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(BigDecimal rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public BigDecimal getSalaryBase() {
        return salaryBase;
    }

    public void setSalaryBase(BigDecimal salaryBase) {
        this.salaryBase = salaryBase;
    }

    public BigDecimal getEr() {
        return er;
    }

    public void setEr(BigDecimal er) {
        this.er = er;
    }

    public BigDecimal getEe() {
        return ee;
    }

    public void setEe(BigDecimal ee) {
        this.ee = ee;
    }

    public BigDecimal getEc() {
        return ec;
    }

    public void setEc(BigDecimal ec) {
        this.ec = ec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sssId != null ? sssId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SSS)) {
            return false;
        }
        SSS other = (SSS) object;
        if ((this.sssId == null && other.sssId != null) || (this.sssId != null && !this.sssId.equals(other.sssId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Sss[ sssId=" + sssId + " ]";
    }
    
}
