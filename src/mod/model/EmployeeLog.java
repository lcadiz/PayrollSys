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
@Table(name = "employee_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeLog.findAll", query = "SELECT e FROM EmployeeLog e"),
    @NamedQuery(name = "EmployeeLog.findByLogId", query = "SELECT e FROM EmployeeLog e WHERE e.logId = :logId"),
    @NamedQuery(name = "EmployeeLog.findByLastName", query = "SELECT e FROM EmployeeLog e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "EmployeeLog.findByFirstName", query = "SELECT e FROM EmployeeLog e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "EmployeeLog.findByMiddleName", query = "SELECT e FROM EmployeeLog e WHERE e.middleName = :middleName"),
    @NamedQuery(name = "EmployeeLog.findBySuffixName", query = "SELECT e FROM EmployeeLog e WHERE e.suffixName = :suffixName"),
    @NamedQuery(name = "EmployeeLog.findByBirthDate", query = "SELECT e FROM EmployeeLog e WHERE e.birthDate = :birthDate"),
    @NamedQuery(name = "EmployeeLog.findByBirthPlace", query = "SELECT e FROM EmployeeLog e WHERE e.birthPlace = :birthPlace"),
    @NamedQuery(name = "EmployeeLog.findByGender", query = "SELECT e FROM EmployeeLog e WHERE e.gender = :gender"),
    @NamedQuery(name = "EmployeeLog.findByCivilStatusId", query = "SELECT e FROM EmployeeLog e WHERE e.civilStatusId = :civilStatusId"),
    @NamedQuery(name = "EmployeeLog.findByBloodType", query = "SELECT e FROM EmployeeLog e WHERE e.bloodType = :bloodType"),
    @NamedQuery(name = "EmployeeLog.findBySpouseName", query = "SELECT e FROM EmployeeLog e WHERE e.spouseName = :spouseName"),
    @NamedQuery(name = "EmployeeLog.findByEmploymentDate", query = "SELECT e FROM EmployeeLog e WHERE e.employmentDate = :employmentDate"),
    @NamedQuery(name = "EmployeeLog.findByResignDate", query = "SELECT e FROM EmployeeLog e WHERE e.resignDate = :resignDate"),
    @NamedQuery(name = "EmployeeLog.findByRetireDate", query = "SELECT e FROM EmployeeLog e WHERE e.retireDate = :retireDate"),
    @NamedQuery(name = "EmployeeLog.findBySecId", query = "SELECT e FROM EmployeeLog e WHERE e.secId = :secId"),
    @NamedQuery(name = "EmployeeLog.findByPhicId", query = "SELECT e FROM EmployeeLog e WHERE e.phicId = :phicId"),
    @NamedQuery(name = "EmployeeLog.findBySssId", query = "SELECT e FROM EmployeeLog e WHERE e.sssId = :sssId"),
    @NamedQuery(name = "EmployeeLog.findByHdmfId", query = "SELECT e FROM EmployeeLog e WHERE e.hdmfId = :hdmfId"),
    @NamedQuery(name = "EmployeeLog.findByTin", query = "SELECT e FROM EmployeeLog e WHERE e.tin = :tin"),
    @NamedQuery(name = "EmployeeLog.findByHouseNo", query = "SELECT e FROM EmployeeLog e WHERE e.houseNo = :houseNo"),
    @NamedQuery(name = "EmployeeLog.findByStreet", query = "SELECT e FROM EmployeeLog e WHERE e.street = :street"),
    @NamedQuery(name = "EmployeeLog.findByBarangay", query = "SELECT e FROM EmployeeLog e WHERE e.barangay = :barangay"),
    @NamedQuery(name = "EmployeeLog.findByCity", query = "SELECT e FROM EmployeeLog e WHERE e.city = :city"),
    @NamedQuery(name = "EmployeeLog.findByZipCode", query = "SELECT e FROM EmployeeLog e WHERE e.zipCode = :zipCode"),
    @NamedQuery(name = "EmployeeLog.findByContactNo", query = "SELECT e FROM EmployeeLog e WHERE e.contactNo = :contactNo"),
    @NamedQuery(name = "EmployeeLog.findByEmail", query = "SELECT e FROM EmployeeLog e WHERE e.email = :email"),
    @NamedQuery(name = "EmployeeLog.findByEmpTypeId", query = "SELECT e FROM EmployeeLog e WHERE e.empTypeId = :empTypeId"),
    @NamedQuery(name = "EmployeeLog.findByEmployerId", query = "SELECT e FROM EmployeeLog e WHERE e.employerId = :employerId"),
    @NamedQuery(name = "EmployeeLog.findByStatusFlag", query = "SELECT e FROM EmployeeLog e WHERE e.statusFlag = :statusFlag"),
    @NamedQuery(name = "EmployeeLog.findByStep", query = "SELECT e FROM EmployeeLog e WHERE e.step = :step"),
    @NamedQuery(name = "EmployeeLog.findByPositionId", query = "SELECT e FROM EmployeeLog e WHERE e.positionId = :positionId"),
    @NamedQuery(name = "EmployeeLog.findByQualifiedFlag", query = "SELECT e FROM EmployeeLog e WHERE e.qualifiedFlag = :qualifiedFlag"),
    @NamedQuery(name = "EmployeeLog.findByProvince", query = "SELECT e FROM EmployeeLog e WHERE e.province = :province"),
    @NamedQuery(name = "EmployeeLog.findByCountry", query = "SELECT e FROM EmployeeLog e WHERE e.country = :country"),
    @NamedQuery(name = "EmployeeLog.findByRemarks", query = "SELECT e FROM EmployeeLog e WHERE e.remarks = :remarks"),
    @NamedQuery(name = "EmployeeLog.findByUserId", query = "SELECT e FROM EmployeeLog e WHERE e.userId = :userId"),
    @NamedQuery(name = "EmployeeLog.findByEmpId", query = "SELECT e FROM EmployeeLog e WHERE e.empId = :empId"),
    @NamedQuery(name = "EmployeeLog.findByPinCode", query = "SELECT e FROM EmployeeLog e WHERE e.pinCode = :pinCode"),
    @NamedQuery(name = "EmployeeLog.findByRank", query = "SELECT e FROM EmployeeLog e WHERE e.rank = :rank")})
public class EmployeeLog extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id", nullable = false)
    private Integer logId;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "middle_name", length = 45)
    private String middleName;
    @Column(name = "suffix_name", length = 45)
    private String suffixName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "birth_place", length = 150)
    private String birthPlace;
    @Column(name = "gender")
    private Character gender;
    @Column(name = "civil_status_id")
    private Integer civilStatusId;
    @Column(name = "blood_type", length = 5)
    private String bloodType;
    @Column(name = "spouse_name", length = 45)
    private String spouseName;
    @Column(name = "employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;
    @Column(name = "resign_date")
    @Temporal(TemporalType.DATE)
    private Date resignDate;
    @Column(name = "retire_date")
    @Temporal(TemporalType.DATE)
    private Date retireDate;
    @Column(name = "sec_id")
    private Integer secId;
    @Column(name = "phic_id", length = 20)
    private String phicId;
    @Column(name = "sss_id", length = 20)
    private String sssId;
    @Column(name = "hdmf_id", length = 20)
    private String hdmfId;
    @Column(name = "tin", length = 20)
    private String tin;
    @Column(name = "house_no", length = 20)
    private String houseNo;
    @Column(name = "street", length = 45)
    private String street;
    @Column(name = "barangay", length = 45)
    private String barangay;
    @Column(name = "city", length = 45)
    private String city;
    @Column(name = "zip_code", length = 4)
    private String zipCode;
    @Column(name = "contact_no", length = 45)
    private String contactNo;
    @Column(name = "email", length = 80)
    private String email;
    @Column(name = "emp_type_id")
    private Short empTypeId;
    @Column(name = "employer_id")
    private Integer employerId;
    @Column(name = "status_flag")
    private Short statusFlag;
    @Column(name = "step")
    private Short step;
    @Column(name = "position_id")
    private Short positionId;
    @Column(name = "qualified_flag")
    private Short qualifiedFlag;
    @Column(name = "province", length = 150)
    private String province;
    @Column(name = "country", length = 45)
    private String country;
    @Column(name = "remarks", length = 150)
    private String remarks;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "pin_code")
    private Short pinCode;
    @Column(name = "rank")
    private Integer rank;

    public EmployeeLog() {
    }

    public EmployeeLog(Integer logId) {
        this.logId = logId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getCivilStatusId() {
        return civilStatusId;
    }

    public void setCivilStatusId(Integer civilStatusId) {
        this.civilStatusId = civilStatusId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public Date getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public String getPhicId() {
        return phicId;
    }

    public void setPhicId(String phicId) {
        this.phicId = phicId;
    }

    public String getSssId() {
        return sssId;
    }

    public void setSssId(String sssId) {
        this.sssId = sssId;
    }

    public String getHdmfId() {
        return hdmfId;
    }

    public void setHdmfId(String hdmfId) {
        this.hdmfId = hdmfId;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Short empTypeId) {
        this.empTypeId = empTypeId;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Short getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Short statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Short getStep() {
        return step;
    }

    public void setStep(Short step) {
        this.step = step;
    }

    public Short getPositionId() {
        return positionId;
    }

    public void setPositionId(Short positionId) {
        this.positionId = positionId;
    }

    public Short getQualifiedFlag() {
        return qualifiedFlag;
    }

    public void setQualifiedFlag(Short qualifiedFlag) {
        this.qualifiedFlag = qualifiedFlag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Short getPinCode() {
        return pinCode;
    }

    public void setPinCode(Short pinCode) {
        this.pinCode = pinCode;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeLog)) {
            return false;
        }
        EmployeeLog other = (EmployeeLog) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.EmployeeLog[ logId=" + logId + " ]";
    }
}
