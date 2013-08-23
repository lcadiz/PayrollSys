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
 * @author Lester
 */
@Entity
@Table(name = "download_indicator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DownloadIndicator.findAll", query = "SELECT d FROM DownloadIndicator d"),
    @NamedQuery(name = "DownloadIndicator.findByIndicatorId", query = "SELECT d FROM DownloadIndicator d WHERE d.indicatorId = :indicatorId"),
    @NamedQuery(name = "DownloadIndicator.findByFlag", query = "SELECT d FROM DownloadIndicator d WHERE d.flag = :flag")})
public class DownloadIndicator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "indicator_id", nullable = false)
    private Integer indicatorId;
    @Column(name = "flag", length = 255)
    private String flag;

    public DownloadIndicator() {
    }

    public DownloadIndicator(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indicatorId != null ? indicatorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DownloadIndicator)) {
            return false;
        }
        DownloadIndicator other = (DownloadIndicator) object;
        if ((this.indicatorId == null && other.indicatorId != null) || (this.indicatorId != null && !this.indicatorId.equals(other.indicatorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.DownloadIndicator[ indicatorId=" + indicatorId + " ]";
    }
    
}
