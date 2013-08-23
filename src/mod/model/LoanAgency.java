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
@Table(name = "loan_agency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanAgency.findAll", query = "SELECT l FROM LoanAgency l"),
    @NamedQuery(name = "LoanAgency.findByLoanAgencyId", query = "SELECT l FROM LoanAgency l WHERE l.loanAgencyId = :loanAgencyId"),
    @NamedQuery(name = "LoanAgency.findByAgencyName", query = "SELECT l FROM LoanAgency l WHERE l.agencyName = :agencyName")})
public class LoanAgency extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loan_agency_id", nullable = false)
    private Integer loanAgencyId;
    @Column(name = "agency_name", length = 45)
    private String agencyName;

    public LoanAgency() {
    }

    public LoanAgency(Integer loanAgencyId) {
        this.loanAgencyId = loanAgencyId;
    }

    public Integer getLoanAgencyId() {
        return loanAgencyId;
    }

    public void setLoanAgencyId(Integer loanAgencyId) {
        this.loanAgencyId = loanAgencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanAgencyId != null ? loanAgencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanAgency)) {
            return false;
        }
        LoanAgency other = (LoanAgency) object;
        if ((this.loanAgencyId == null && other.loanAgencyId != null) || (this.loanAgencyId != null && !this.loanAgencyId.equals(other.loanAgencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/loanagency.png") + ">&nbsp</td><td>" + agencyName + "<font></td></th>";
        return lbl ;
    }
    
}
