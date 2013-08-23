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
@Table(name = "loan_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanType.findAll", query = "SELECT l FROM LoanType l"),
    @NamedQuery(name = "LoanType.findByLoanId", query = "SELECT l FROM LoanType l WHERE l.loanId = :loanId"),
    @NamedQuery(name = "LoanType.findByLoanDescription", query = "SELECT l FROM LoanType l WHERE l.loanDescription = :loanDescription"),
    @NamedQuery(name = "LoanType.findByLoanAgencyId", query = "SELECT l FROM LoanType l WHERE l.loanAgencyId = :loanAgencyId")})
public class LoanType extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loan_id", nullable = false)
    private Integer loanId;
    @Column(name = "loan_description", length = 45)
    private String loanDescription;
    @Column(name = "loan_agency_id")
    private Integer loanAgencyId;

    public LoanType() {
    }

    public LoanType(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    public Integer getLoanAgencyId() {
        return loanAgencyId;
    }

    public void setLoanAgencyId(Integer loanAgencyId) {
        this.loanAgencyId = loanAgencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanId != null ? loanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanType)) {
            return false;
        }
        LoanType other = (LoanType) object;
        if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/emploans.png") + ">&nbsp</td><td>" + loanDescription + "<font></td></th>";
        return lbl ;
    }
    
}
