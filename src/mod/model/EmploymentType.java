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
@Table(name = "employment_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmploymentType.findAll", query = "SELECT e FROM EmploymentType e"),
    @NamedQuery(name = "EmploymentType.findByEmpTypeId", query = "SELECT e FROM EmploymentType e WHERE e.empTypeId = :empTypeId"),
    @NamedQuery(name = "EmploymentType.findByTypeDesc", query = "SELECT e FROM EmploymentType e WHERE e.typeDesc = :typeDesc")})
public class EmploymentType extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_type_id", nullable = false)
    private Integer empTypeId;
    @Column(name = "type_desc", length = 60)
    private String typeDesc;

    public EmploymentType() {
    }

    public EmploymentType(Integer empTypeId) {
        this.empTypeId = empTypeId;
    }

    public Integer getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Integer empTypeId) {
        this.empTypeId = empTypeId;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empTypeId != null ? empTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploymentType)) {
            return false;
        }
        EmploymentType other = (EmploymentType) object;
        if ((this.empTypeId == null && other.empTypeId != null) || (this.empTypeId != null && !this.empTypeId.equals(other.empTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
           String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/emps.png") + ">&nbsp</td><td>" + typeDesc + "<font></td></th>";
        return lbl;
    }
    
}
