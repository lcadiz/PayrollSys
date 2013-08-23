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
@Table(name = "phic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phic.findAll", query = "SELECT p FROM Phic p"),
    @NamedQuery(name = "Phic.findByPhicId", query = "SELECT p FROM Phic p WHERE p.phicId = :phicId"),
    @NamedQuery(name = "Phic.findByRangeStart", query = "SELECT p FROM Phic p WHERE p.rangeStart = :rangeStart"),
    @NamedQuery(name = "Phic.findByRangeEnd", query = "SELECT p FROM Phic p WHERE p.rangeEnd = :rangeEnd"),
    @NamedQuery(name = "Phic.findBySalaryBase", query = "SELECT p FROM Phic p WHERE p.salaryBase = :salaryBase"),
    @NamedQuery(name = "Phic.findByTotal", query = "SELECT p FROM Phic p WHERE p.total = :total"),
    @NamedQuery(name = "Phic.findByEe", query = "SELECT p FROM Phic p WHERE p.ee = :ee"),
    @NamedQuery(name = "Phic.findByEr", query = "SELECT p FROM Phic p WHERE p.er = :er")})
public class PHIC extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "phic_id", nullable = false)
    private Short phicId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "range_start", precision = 18, scale = 2)
    private BigDecimal rangeStart;
    @Column(name = "range_end", precision = 18, scale = 2)
    private BigDecimal rangeEnd;
    @Column(name = "salary_base", precision = 18, scale = 2)
    private BigDecimal salaryBase;
    @Column(name = "total", precision = 18, scale = 2)
    private BigDecimal total;
    @Column(name = "ee", precision = 18, scale = 2)
    private BigDecimal ee;
    @Column(name = "er", precision = 18, scale = 2)
    private BigDecimal er;

    public PHIC() {
    }

    public PHIC(Short phicId) {
        this.phicId = phicId;
    }

    public Short getPhicId() {
        return phicId;
    }

    public void setPhicId(Short phicId) {
        this.phicId = phicId;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getEe() {
        return ee;
    }

    public void setEe(BigDecimal ee) {
        this.ee = ee;
    }

    public BigDecimal getEr() {
        return er;
    }

    public void setEr(BigDecimal er) {
        this.er = er;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phicId != null ? phicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PHIC)) {
            return false;
        }
        PHIC other = (PHIC) object;
        if ((this.phicId == null && other.phicId != null) || (this.phicId != null && !this.phicId.equals(other.phicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Phic[ phicId=" + phicId + " ]";
    }
    
}
