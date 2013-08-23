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
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId"),
    @NamedQuery(name = "Position.findByPositionDesc", query = "SELECT p FROM Position p WHERE p.positionDesc = :positionDesc"),
    @NamedQuery(name = "Position.findBySalaryId", query = "SELECT p FROM Position p WHERE p.salaryId = :salaryId"),
    @NamedQuery(name = "Position.findBySecId", query = "SELECT p FROM Position p WHERE p.secId = :secId"),
    @NamedQuery(name = "Position.findByPosLevelId", query = "SELECT p FROM Position p WHERE p.posLevelId = :posLevelId")})
public class Position extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_id", nullable = false)
    private Short positionId;
    @Column(name = "position_desc", length = 350)
    private String positionDesc;
    @Column(name = "salary_id")
    private Short salaryId;
    @Column(name = "sec_id")
    private Integer secId;
    @Column(name = "pos_level_id")
    private Short posLevelId;

    public Position() {
    }

    public Position(Short positionId) {
        this.positionId = positionId;
    }

    public Short getPositionId() {
        return positionId;
    }

    public void setPositionId(Short positionId) {
        this.positionId = positionId;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    public Short getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Short salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public Short getPosLevelId() {
        return posLevelId;
    }

    public void setPosLevelId(Short posLevelId) {
        this.posLevelId = posLevelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
                   String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/position.png") + ">&nbsp</td><td>" + positionDesc + "<font></td></th>";
        return lbl ;
    }
    
}
