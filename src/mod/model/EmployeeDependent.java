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
@Table(name = "employee_dependent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeDependent.findAll", query = "SELECT e FROM EmployeeDependent e"),
    @NamedQuery(name = "EmployeeDependent.findByEdId", query = "SELECT e FROM EmployeeDependent e WHERE e.edId = :edId"),
    @NamedQuery(name = "EmployeeDependent.findByEmpId", query = "SELECT e FROM EmployeeDependent e WHERE e.empId = :empId"),
    @NamedQuery(name = "EmployeeDependent.findByDependentName", query = "SELECT e FROM EmployeeDependent e WHERE e.dependentName = :dependentName"),
    @NamedQuery(name = "EmployeeDependent.findByRelation", query = "SELECT e FROM EmployeeDependent e WHERE e.relation = :relation"),
    @NamedQuery(name = "EmployeeDependent.findByBirthdate", query = "SELECT e FROM EmployeeDependent e WHERE e.birthdate = :birthdate")})
public class EmployeeDependent extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ed_id", nullable = false)
    private Integer edId;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "dependent_name", length = 45)
    private String dependentName;
    @Column(name = "relation", length = 45)
    private String relation;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public EmployeeDependent() {
    }

    public EmployeeDependent(Integer edId) {
        this.edId = edId;
    }

    public Integer getEdId() {
        return edId;
    }

    public void setEdId(Integer edId) {
        this.edId = edId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getDependentName() {
        return dependentName;
    }

    public void setDependentName(String dependentName) {
        this.dependentName = dependentName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (edId != null ? edId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeDependent)) {
            return false;
        }
        EmployeeDependent other = (EmployeeDependent) object;
        if ((this.edId == null && other.edId != null) || (this.edId != null && !this.edId.equals(other.edId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmployeeDependent[ edId=" + edId + " ]";
    }
    
}
