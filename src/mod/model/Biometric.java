/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LESTER
 */
@Entity
@Table(name = "biometric")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biometric.findAll", query = "SELECT b FROM Biometric b"),
    @NamedQuery(name = "Biometric.findByDeviceId", query = "SELECT b FROM Biometric b WHERE b.deviceId = :deviceId"),
    @NamedQuery(name = "Biometric.findByDeviceName", query = "SELECT b FROM Biometric b WHERE b.deviceName = :deviceName"),
    @NamedQuery(name = "Biometric.findByIpAddress", query = "SELECT b FROM Biometric b WHERE b.ipAddress = :ipAddress"),
    @NamedQuery(name = "Biometric.findByDownloadFlag", query = "SELECT b FROM Biometric b WHERE b.downloadFlag = :downloadFlag")})
public class Biometric extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "device_id", nullable = false)
    private Short deviceId;
    @Column(name = "device_name", length = 45)
    private String deviceName;
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    @Column(name = "download_flag")
    private Short downloadFlag;

    public Biometric() {
    }

    public Biometric(Short deviceId) {
        this.deviceId = deviceId;
    }

    public Short getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Short deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Short getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(Short downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceId != null ? deviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biometric)) {
            return false;
        }
        Biometric other = (Biometric) object;
        if ((this.deviceId == null && other.deviceId != null) || (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/device.png") + ">&nbsp</td><td>" + deviceName + "<font color=#E7C30F>" + " (" + ipAddress + ")" + "<font></td></th>";
        return lbl;
    }
    
}
