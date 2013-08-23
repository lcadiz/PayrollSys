/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Table(name = "payroll_period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PayrollPeriod.findAll", query = "SELECT p FROM PayrollPeriod p"),
    @NamedQuery(name = "PayrollPeriod.findByPayrollPeriodId", query = "SELECT p FROM PayrollPeriod p WHERE p.payrollPeriodId = :payrollPeriodId"),
    @NamedQuery(name = "PayrollPeriod.findByPayrollPeriodDesc", query = "SELECT p FROM PayrollPeriod p WHERE p.payrollPeriodDesc = :payrollPeriodDesc"),
    @NamedQuery(name = "PayrollPeriod.findByStartDate", query = "SELECT p FROM PayrollPeriod p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "PayrollPeriod.findByEndDate", query = "SELECT p FROM PayrollPeriod p WHERE p.endDate = :endDate")})
public class PayrollPeriod extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payroll_period_id", nullable = false)
    private Integer payrollPeriodId;
    @Column(name = "payroll_period_desc", length = 45)
    private String payrollPeriodDesc;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public PayrollPeriod() {
    }

    public PayrollPeriod(Integer payrollPeriodId) {
        this.payrollPeriodId = payrollPeriodId;
    }

    public Integer getPayrollPeriodId() {
        return payrollPeriodId;
    }

    public void setPayrollPeriodId(Integer payrollPeriodId) {
        this.payrollPeriodId = payrollPeriodId;
    }

    public String getPayrollPeriodDesc() {
        return payrollPeriodDesc;
    }

    public void setPayrollPeriodDesc(String payrollPeriodDesc) {
        this.payrollPeriodDesc = payrollPeriodDesc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payrollPeriodId != null ? payrollPeriodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayrollPeriod)) {
            return false;
        }
        PayrollPeriod other = (PayrollPeriod) object;
        if ((this.payrollPeriodId == null && other.payrollPeriodId != null) || (this.payrollPeriodId != null && !this.payrollPeriodId.equals(other.payrollPeriodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String lbl = null;
        Date sDate = startDate;
        
        DateFormat sdateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String start = sdateFormat.format(sDate);

        Date eDate = endDate;
        
        DateFormat edateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String end = edateFormat.format(eDate);
        
        lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/payperiod.png") + "></td>&nbsp<td><b>" + payrollPeriodDesc.toUpperCase() + "</b><br>" + "From <font color=#59DF00><b>" + start + "</b></font> to <font color=#FF0000><b>" + end + "</b></font></td></th>";


        return lbl;
    }
}
