/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LESTER
 */
@Entity
@Table(name = "salary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salary.findAll", query = "SELECT s FROM Salary s"),
    @NamedQuery(name = "Salary.findBySalaryId", query = "SELECT s FROM Salary s WHERE s.salaryId = :salaryId"),
    @NamedQuery(name = "Salary.findByRankValue", query = "SELECT s FROM Salary s WHERE s.rankValue = :rankValue"),
    @NamedQuery(name = "Salary.findBySalaryBase", query = "SELECT s FROM Salary s WHERE s.salaryBase = :salaryBase"),
    @NamedQuery(name = "Salary.findByStepIncValue", query = "SELECT s FROM Salary s WHERE s.stepIncValue = :stepIncValue")})
public class Salary extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "salary_id", nullable = false)
    private Short salaryId;
    @Column(name = "rank_value")
    private Short rankValue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salary_base", precision = 18, scale = 2)
    private BigDecimal salaryBase;
    @Column(name = "step_inc_value", precision = 18, scale = 2)
    private BigDecimal stepIncValue;

    public Salary() {
    }

    public Salary(Short salaryId) {
        this.salaryId = salaryId;
    }

    public Short getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Short salaryId) {
        this.salaryId = salaryId;
    }

    public Short getRankValue() {
        return rankValue;
    }

    public void setRankValue(Short rankValue) {
        this.rankValue = rankValue;
    }

    public BigDecimal getSalaryBase() {
        return salaryBase;
    }

    public void setSalaryBase(BigDecimal salaryBase) {
        this.salaryBase = salaryBase;
    }

    public BigDecimal getStepIncValue() {
        return stepIncValue;
    }

    public void setStepIncValue(BigDecimal stepIncValue) {
        this.stepIncValue = stepIncValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaryId != null ? salaryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salary)) {
            return false;
        }
        Salary other = (Salary) object;
        if ((this.salaryId == null && other.salaryId != null) || (this.salaryId != null && !this.salaryId.equals(other.salaryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//         String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
//                + "<tr align=right><td>Rank "+ rankValue + "</td></tr></table></html>";
        return rankValue.toString();
    }
    
}
