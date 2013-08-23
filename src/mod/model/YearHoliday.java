/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LESTER
 */
@Entity
@Table(name = "year_holiday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "YearHoliday.findAll", query = "SELECT y FROM YearHoliday y"),
    @NamedQuery(name = "YearHoliday.findByYearHolidayId", query = "SELECT y FROM YearHoliday y WHERE y.yearHolidayId = :yearHolidayId"),
    @NamedQuery(name = "YearHoliday.findByYear", query = "SELECT y FROM YearHoliday y WHERE y.year = :year"),
    @NamedQuery(name = "YearHoliday.findByHolidayId", query = "SELECT y FROM YearHoliday y WHERE y.holidayId = :holidayId"),
    @NamedQuery(name = "YearHoliday.findByHolidayDate", query = "SELECT y FROM YearHoliday y WHERE y.holidayDate = :holidayDate"),
    @NamedQuery(name = "YearHoliday.findByUserId", query = "SELECT y FROM YearHoliday y WHERE y.userId = :userId"),
    @NamedQuery(name = "YearHoliday.findByTransDate", query = "SELECT y FROM YearHoliday y WHERE y.transDate = :transDate")})
public class YearHoliday extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "year_holiday_id", nullable = false)
    private Integer yearHolidayId;
    @Column(name = "year")
    private Integer year;
    @Column(name = "holiday_id")
    private Integer holidayId;
    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date holidayDate;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "trans_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;

    public YearHoliday() {
    }

    public YearHoliday(Integer yearHolidayId) {
        this.yearHolidayId = yearHolidayId;
    }

    public Integer getYearHolidayId() {
        return yearHolidayId;
    }

    public void setYearHolidayId(Integer yearHolidayId) {
        this.yearHolidayId = yearHolidayId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearHolidayId != null ? yearHolidayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YearHoliday)) {
            return false;
        }
        YearHoliday other = (YearHoliday) object;
        if ((this.yearHolidayId == null && other.yearHolidayId != null) || (this.yearHolidayId != null && !this.yearHolidayId.equals(other.yearHolidayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.YearHoliday[ yearHolidayId=" + yearHolidayId + " ]";
    }
    
}
