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
@Table(name = "hdmf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hdmf.findAll", query = "SELECT h FROM Hdmf h"),
    @NamedQuery(name = "Hdmf.findByHdmfId", query = "SELECT h FROM Hdmf h WHERE h.hdmfId = :hdmfId"),
    @NamedQuery(name = "Hdmf.findByRangeStart", query = "SELECT h FROM Hdmf h WHERE h.rangeStart = :rangeStart"),
    @NamedQuery(name = "Hdmf.findByRangeEnd", query = "SELECT h FROM Hdmf h WHERE h.rangeEnd = :rangeEnd"),
    @NamedQuery(name = "Hdmf.findByMaxSalaryBase", query = "SELECT h FROM Hdmf h WHERE h.maxSalaryBase = :maxSalaryBase"),
    @NamedQuery(name = "Hdmf.findByTotal", query = "SELECT h FROM Hdmf h WHERE h.total = :total"),
    @NamedQuery(name = "Hdmf.findByEe", query = "SELECT h FROM Hdmf h WHERE h.ee = :ee"),
    @NamedQuery(name = "Hdmf.findByEr", query = "SELECT h FROM Hdmf h WHERE h.er = :er")})
public class HDMF extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hdmf_id", nullable = false)
    private Short hdmfId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "range_start", precision = 18, scale = 2)
    private BigDecimal rangeStart;
    @Column(name = "range_end", precision = 18, scale = 2)
    private BigDecimal rangeEnd;
    @Column(name = "max_salary_base", precision = 18, scale = 2)
    private BigDecimal maxSalaryBase;
    @Column(name = "total", precision = 18, scale = 2)
    private BigDecimal total;
    @Column(name = "ee", precision = 18, scale = 2)
    private BigDecimal ee;
    @Column(name = "er", precision = 18, scale = 2)
    private BigDecimal er;

    public HDMF() {
    }

    public HDMF(Short hdmfId) {
        this.hdmfId = hdmfId;
    }

    public Short getHdmfId() {
        return hdmfId;
    }

    public void setHdmfId(Short hdmfId) {
        this.hdmfId = hdmfId;
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

    public BigDecimal getMaxSalaryBase() {
        return maxSalaryBase;
    }

    public void setMaxSalaryBase(BigDecimal maxSalaryBase) {
        this.maxSalaryBase = maxSalaryBase;
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
        hash += (hdmfId != null ? hdmfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HDMF)) {
            return false;
        }
        HDMF other = (HDMF) object;
        if ((this.hdmfId == null && other.hdmfId != null) || (this.hdmfId != null && !this.hdmfId.equals(other.hdmfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Hdmf[ hdmfId=" + hdmfId + " ]";
    }
    
}
