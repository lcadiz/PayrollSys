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
@Table(name = "bir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bir.findAll", query = "SELECT b FROM Bir b"),
    @NamedQuery(name = "Bir.findByBirId", query = "SELECT b FROM Bir b WHERE b.birId = :birId"),
    @NamedQuery(name = "Bir.findByRangeStart", query = "SELECT b FROM Bir b WHERE b.rangeStart = :rangeStart"),
    @NamedQuery(name = "Bir.findByRangeEnd", query = "SELECT b FROM Bir b WHERE b.rangeEnd = :rangeEnd"),
    @NamedQuery(name = "Bir.findByTaxAddPercentage", query = "SELECT b FROM Bir b WHERE b.taxAddPercentage = :taxAddPercentage"),
    @NamedQuery(name = "Bir.findByTaxMinAmt", query = "SELECT b FROM Bir b WHERE b.taxMinAmt = :taxMinAmt")})
public class BIR extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bir_id", nullable = false)
    private Short birId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "range_start", precision = 18, scale = 2)
    private BigDecimal rangeStart;
    @Column(name = "range_end", precision = 18, scale = 2)
    private BigDecimal rangeEnd;
    @Column(name = "tax_add_percentage", precision = 18, scale = 2)
    private BigDecimal taxAddPercentage;
    @Column(name = "tax_min_amt", precision = 18, scale = 2)
    private BigDecimal taxMinAmt;

    public BIR() {
    }

    public BIR(Short birId) {
        this.birId = birId;
    }

    public Short getBirId() {
        return birId;
    }

    public void setBirId(Short birId) {
        this.birId = birId;
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

    public BigDecimal getTaxAddPercentage() {
        return taxAddPercentage;
    }

    public void setTaxAddPercentage(BigDecimal taxAddPercentage) {
        this.taxAddPercentage = taxAddPercentage;
    }

    public BigDecimal getTaxMinAmt() {
        return taxMinAmt;
    }

    public void setTaxMinAmt(BigDecimal taxMinAmt) {
        this.taxMinAmt = taxMinAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (birId != null ? birId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BIR)) {
            return false;
        }
        BIR other = (BIR) object;
        if ((this.birId == null && other.birId != null) || (this.birId != null && !this.birId.equals(other.birId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Bir[ birId=" + birId + " ]";
    }
    
}
