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
@Table(name = "division")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Division.findAll", query = "SELECT d FROM Division d"),
    @NamedQuery(name = "Division.findByDivId", query = "SELECT d FROM Division d WHERE d.divId = :divId"),
    @NamedQuery(name = "Division.findByDivName", query = "SELECT d FROM Division d WHERE d.divName = :divName"),
    @NamedQuery(name = "Division.findByDeptId", query = "SELECT d FROM Division d WHERE d.deptId = :deptId")})
public class Division extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "div_id", nullable = false)
    private Integer divId;
    @Column(name = "div_name", length = 150)
    private String divName;
    @Column(name = "dept_id")
    private Integer deptId;

    public Division() {
    }

    public Division(Integer divId) {
        this.divId = divId;
    }

    public Integer getDivId() {
        return divId;
    }

    public void setDivId(Integer divId) {
        this.divId = divId;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (divId != null ? divId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Division)) {
            return false;
        }
        Division other = (Division) object;
        if ((this.divId == null && other.divId != null) || (this.divId != null && !this.divId.equals(other.divId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/div.png") + ">&nbsp</td><td>" + divName + "<font></td></th>";
        return lbl ;
    }
    
}
