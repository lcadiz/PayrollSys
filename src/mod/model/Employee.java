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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpId", query = "SELECT e FROM Employee e WHERE e.empId = :empId"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByMiddleName", query = "SELECT e FROM Employee e WHERE e.middleName = :middleName"),
    @NamedQuery(name = "Employee.findBySuffixName", query = "SELECT e FROM Employee e WHERE e.suffixName = :suffixName"),
    @NamedQuery(name = "Employee.findByBirthDate", query = "SELECT e FROM Employee e WHERE e.birthDate = :birthDate"),
    @NamedQuery(name = "Employee.findByBirthPlace", query = "SELECT e FROM Employee e WHERE e.birthPlace = :birthPlace"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employee.findByCivilStatusId", query = "SELECT e FROM Employee e WHERE e.civilStatusId = :civilStatusId"),
    @NamedQuery(name = "Employee.findByBloodType", query = "SELECT e FROM Employee e WHERE e.bloodType = :bloodType"),
    @NamedQuery(name = "Employee.findBySpouseName", query = "SELECT e FROM Employee e WHERE e.spouseName = :spouseName"),
    @NamedQuery(name = "Employee.findByEmploymentDate", query = "SELECT e FROM Employee e WHERE e.employmentDate = :employmentDate"),
    @NamedQuery(name = "Employee.findByResignDate", query = "SELECT e FROM Employee e WHERE e.resignDate = :resignDate"),
    @NamedQuery(name = "Employee.findByRetireDate", query = "SELECT e FROM Employee e WHERE e.retireDate = :retireDate"),
    @NamedQuery(name = "Employee.findBySecId", query = "SELECT e FROM Employee e WHERE e.secId = :secId"),
    @NamedQuery(name = "Employee.findByPhicId", query = "SELECT e FROM Employee e WHERE e.phicId = :phicId"),
    @NamedQuery(name = "Employee.findBySssId", query = "SELECT e FROM Employee e WHERE e.sssId = :sssId"),
    @NamedQuery(name = "Employee.findByHdmfId", query = "SELECT e FROM Employee e WHERE e.hdmfId = :hdmfId"),
    @NamedQuery(name = "Employee.findByTin", query = "SELECT e FROM Employee e WHERE e.tin = :tin"),
    @NamedQuery(name = "Employee.findByHouseNo", query = "SELECT e FROM Employee e WHERE e.houseNo = :houseNo"),
    @NamedQuery(name = "Employee.findByStreet", query = "SELECT e FROM Employee e WHERE e.street = :street"),
    @NamedQuery(name = "Employee.findByBarangay", query = "SELECT e FROM Employee e WHERE e.barangay = :barangay"),
    @NamedQuery(name = "Employee.findByCity", query = "SELECT e FROM Employee e WHERE e.city = :city"),
    @NamedQuery(name = "Employee.findByZipCode", query = "SELECT e FROM Employee e WHERE e.zipCode = :zipCode"),
    @NamedQuery(name = "Employee.findByContactNo", query = "SELECT e FROM Employee e WHERE e.contactNo = :contactNo"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByEmpTypeId", query = "SELECT e FROM Employee e WHERE e.empTypeId = :empTypeId"),
    @NamedQuery(name = "Employee.findByEmployerId", query = "SELECT e FROM Employee e WHERE e.employerId = :employerId"),
    @NamedQuery(name = "Employee.findByStatusFlag", query = "SELECT e FROM Employee e WHERE e.statusFlag = :statusFlag"),
    @NamedQuery(name = "Employee.findByStep", query = "SELECT e FROM Employee e WHERE e.step = :step"),
    @NamedQuery(name = "Employee.findByPositionId", query = "SELECT e FROM Employee e WHERE e.positionId = :positionId"),
    @NamedQuery(name = "Employee.findByQualifiedFlag", query = "SELECT e FROM Employee e WHERE e.qualifiedFlag = :qualifiedFlag"),
    @NamedQuery(name = "Employee.findByProvince", query = "SELECT e FROM Employee e WHERE e.province = :province"),
    @NamedQuery(name = "Employee.findByCountry", query = "SELECT e FROM Employee e WHERE e.country = :country"),
    @NamedQuery(name = "Employee.findByEmpNo", query = "SELECT e FROM Employee e WHERE e.empNo = :empNo"),
    @NamedQuery(name = "Employee.findByPinCode", query = "SELECT e FROM Employee e WHERE e.pinCode = :pinCode"),
    @NamedQuery(name = "Employee.findByRank", query = "SELECT e FROM Employee e WHERE e.rank = :rank")})
public class Employee extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id", nullable = false)
    private Integer empId;
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
    @Column(name = "zip_code", length = 25)
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
    @Column(name = "emp_no", length = 10)
    private String empNo;
    @Column(name = "pin_code")
    private Short pinCode;
    @Column(name = "rank")
    private Short rank;

    public Employee() {
    }

    public Employee(Integer empId) {
        this.empId = empId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Short getPinCode() {
        return pinCode;
    }

    public void setPinCode(Short pinCode) {
        this.pinCode = pinCode;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mod.model.Employee[ empId=" + empId + " ]";
    }
    
}
