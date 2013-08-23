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
@Table(name = "employer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employer.findAll", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "Employer.findByEmployerId", query = "SELECT e FROM Employer e WHERE e.employerId = :employerId"),
    @NamedQuery(name = "Employer.findByEmployerName", query = "SELECT e FROM Employer e WHERE e.employerName = :employerName")})
public class Employer extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employer_id", nullable = false)
    private Integer employerId;
    @Column(name = "employer_name", length = 150)
    private String employerName;

    public Employer() {
    }

    public Employer(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employerId != null ? employerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employer)) {
            return false;
        }
        Employer other = (Employer) object;
        if ((this.employerId == null && other.employerId != null) || (this.employerId != null && !this.employerId.equals(other.employerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/employer2.png") + ">&nbsp</td><td>" + employerName +"<font></td></th>";
        return lbl;
    }
    
}
