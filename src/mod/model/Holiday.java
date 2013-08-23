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
@Table(name = "holiday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holiday.findAll", query = "SELECT h FROM Holiday h"),
    @NamedQuery(name = "Holiday.findByHolidayId", query = "SELECT h FROM Holiday h WHERE h.holidayId = :holidayId"),
    @NamedQuery(name = "Holiday.findByHolidayName", query = "SELECT h FROM Holiday h WHERE h.holidayName = :holidayName"),
    @NamedQuery(name = "Holiday.findByDefaultMonth", query = "SELECT h FROM Holiday h WHERE h.defaultMonth = :defaultMonth"),
    @NamedQuery(name = "Holiday.findByDefaultDay", query = "SELECT h FROM Holiday h WHERE h.defaultDay = :defaultDay"),
    @NamedQuery(name = "Holiday.findByPayCompId", query = "SELECT h FROM Holiday h WHERE h.payCompId = :payCompId"),
    @NamedQuery(name = "Holiday.findByUserId", query = "SELECT h FROM Holiday h WHERE h.userId = :userId"),
    @NamedQuery(name = "Holiday.findByTransDate", query = "SELECT h FROM Holiday h WHERE h.transDate = :transDate"),
    @NamedQuery(name = "Holiday.findByConstantFlag", query = "SELECT h FROM Holiday h WHERE h.constantFlag = :constantFlag")})
public class Holiday extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "holiday_id", nullable = false)
    private Integer holidayId;
    @Column(name = "holiday_name", length = 45)
    private String holidayName;
    @Column(name = "default_month")
    private Short defaultMonth;
    @Column(name = "default_day")
    private Short defaultDay;
    @Column(name = "pay_comp_id")
    private Integer payCompId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "trans_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Column(name = "constant_flag")
    private Short constantFlag;

    public Holiday() {
    }

    public Holiday(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Short getDefaultMonth() {
        return defaultMonth;
    }

    public void setDefaultMonth(Short defaultMonth) {
        this.defaultMonth = defaultMonth;
    }

    public Short getDefaultDay() {
        return defaultDay;
    }

    public void setDefaultDay(Short defaultDay) {
        this.defaultDay = defaultDay;
    }

    public Integer getPayCompId() {
        return payCompId;
    }

    public void setPayCompId(Integer payCompId) {
        this.payCompId = payCompId;
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

    public Short getConstantFlag() {
        return constantFlag;
    }

    public void setConstantFlag(Short constantFlag) {
        this.constantFlag = constantFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (holidayId != null ? holidayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Holiday)) {
            return false;
        }
        Holiday other = (Holiday) object;
        if ((this.holidayId == null && other.holidayId != null) || (this.holidayId != null && !this.holidayId.equals(other.holidayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String monthname = null;
        switch (defaultMonth) {
            case 1:
                monthname = "JANUARY";
                break;
            case 2:
                monthname = "FEBRUARY";
                break;
            case 3:
                monthname = "MARCH";
                break;
            case 4:
                monthname = "APRIL";
                break;
            case 5:
                monthname = "MAY";
                break;
            case 6:
                monthname = "JUNE";
                break;
            case 7:
                monthname = "JULY";
                break;
            case 8:
                monthname = "AUGUST";
                break;
            case 9:
                monthname = "SEPTEMBER";
                break;
            case 10:
                monthname = "OCTOBER";
                break;
            case 11:
                monthname = "NOVEMBER";
                break;
            case 12:
                monthname = "DECEMBER";
                break;
        }

        String dayfactor = null;

        if (defaultDay == 1) {
            dayfactor = "st";
        } else if (defaultDay == 2) {
            dayfactor = "nd";
        } else if (defaultDay == 3) {
            dayfactor = "rd";
        } else {
            dayfactor = "th";
        }

        String type = null;
        String color = null;
        switch (payCompId) {
            case 8:
                type = "Regular";
                color = "blue";
                break;
            case 7:
                type = "Special";
                color = "red";
                break;
        }

        String cf = null;

        switch (constantFlag) {
            case 1:
                cf = "Constant";
                break;
            case 2:
                cf = "Changeable";
                break;
        }

        String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/holiday.png") + "></td><td><b>" + holidayName + "</b><font color="+color+">" + " (" + type + " holiday and date is " + cf + ") " + "<br>" + "<font color=#E7C30F>" + defaultDay + dayfactor + " day of " + monthname + "</font></td></th>";

        return lbl;
    }
}
