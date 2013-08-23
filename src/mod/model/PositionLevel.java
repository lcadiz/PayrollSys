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
@Table(name = "position_level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionLevel.findAll", query = "SELECT p FROM PositionLevel p"),
    @NamedQuery(name = "PositionLevel.findByPosLevelId", query = "SELECT p FROM PositionLevel p WHERE p.posLevelId = :posLevelId"),
    @NamedQuery(name = "PositionLevel.findByDescription", query = "SELECT p FROM PositionLevel p WHERE p.description = :description")})
public class PositionLevel extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pos_level_id", nullable = false)
    private Integer posLevelId;
    @Column(name = "description", length = 45)
    private String description;

    public PositionLevel() {
    }

    public PositionLevel(Integer posLevelId) {
        this.posLevelId = posLevelId;
    }

    public Integer getPosLevelId() {
        return posLevelId;
    }

    public void setPosLevelId(Integer posLevelId) {
        this.posLevelId = posLevelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posLevelId != null ? posLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PositionLevel)) {
            return false;
        }
        PositionLevel other = (PositionLevel) object;
        if ((this.posLevelId == null && other.posLevelId != null) || (this.posLevelId != null && !this.posLevelId.equals(other.posLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/poslevel.png") + ">&nbsp</td><td>" + description + "<font color=orange>" + " (Level " + posLevelId + ")" + "<font></td></th>";
        return lbl;
    }
}
