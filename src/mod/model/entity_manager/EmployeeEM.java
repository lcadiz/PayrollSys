package mod.model.entity_manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Employee;
import mod.model.Model;


public class EmployeeEM extends EntityManager {

    public EmployeeEM(Connection con) {
        super(con);
    }

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int persist(Model model) {
        Employee employee = (Employee) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO employee("
                    + "last_name,"
                    + "first_name,"
                    + "middle_name,"
                    + "suffix_name,"
                    + "birth_date,"
                    + "birth_place,"
                    + "gender,"
                    + "blood_type,"
                    + "spouse_name,"
                    + "employment_date,"
                    //                    + "resign_date,"
                    //                    + "retire_date,"
                    + "sec_id,"
                    + "phic_id,"
                    + "sss_id,"
                    + "hdmf_id,"
                    + "tin,"
                    + "house_no,"
                    + "street,"
                    + "barangay,"
                    + "city,"
                    + "zip_code,"
                    + "contact_no,"
                    + "email,"
                    + "emp_type_id,"
                    + "employer_id,"
                    + "status_flag, "
                    + "step, "
                    + "position_id, "
                    + "qualified_flag, "
                    + "province, "
                    + "country, "
                    + "emp_no, "
                    + "civil_status_id "
                    + ") "
                    + "VALUES"
                    + "("
                    + "'" + employee.getLastName() + "',"
                    + "'" + employee.getFirstName() + "',"
                    + "'" + employee.getMiddleName() + "',"
                    + "'" + employee.getSuffixName() + "',"
                    + "?," //Date of Birth
                    + "'" + employee.getBirthPlace() + "',"
                    + "'" + employee.getGender() + "',"
                    + "'" + employee.getBloodType() + "',"
                    + "'" + employee.getSpouseName() + "',"
                    + "?," //Employement Date
                    //                    + "'" + employee.getResignDate() + "',"
                    //                    + "'" + employee.getRetireDate() + "',"
                    + "'" + employee.getSecId() + "',"
                    + "'" + employee.getPhicId() + "',"
                    + "'" + employee.getSssId() + "',"
                    + "'" + employee.getHdmfId() + "',"
                    + "'" + employee.getTin() + "',"
                    + "'" + employee.getHouseNo() + "',"
                    + "'" + employee.getStreet() + "',"
                    + "'" + employee.getBarangay() + "',"
                    + "'" + employee.getCity() + "',"
                    + "'" + employee.getZipCode() + "',"
                    + "'" + employee.getContactNo() + "',"
                    + "'" + employee.getEmail() + "',"
                    + "'" + employee.getEmpTypeId() + "',"
                    + "'" + employee.getEmployerId() + "', "
                    + "'" + employee.getStatusFlag() + "', "
                    + "'" + employee.getStep() + "', "
                    + "'" + employee.getPositionId() + "', "
                    + "'" + employee.getQualifiedFlag() + "', "
                    + "'" + employee.getProvince() + "', "
                    + "'" + employee.getCountry() + "',"
                    + "'" + employee.getEmpNo() + "', "
                    + "'" + employee.getCivilStatusId()+ "'"
                    + ")";


            ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(employee.getBirthDate().getTime())); //Dateof Birth
            ps.setDate(2, new java.sql.Date(employee.getEmploymentDate().getTime())); //Employement Date
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                throw new RuntimeException("Can't find most recent insert we just entered");
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        Employee employee = (Employee) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM employee "
                    + "WHERE "
                    + "employee_id=" + employee.getEmpId();

            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        Employee employee = (Employee) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE employee SET "
                    + "last_name='" + employee.getLastName() + "',"
                    + "first_name='" + employee.getFirstName() + "',"
                    + "middle_name='" + employee.getMiddleName() + "',"
                    + "suffix_name='" + employee.getSuffixName() + "',"
                    + "birth_date=?,"
                    + "birth_place='" + employee.getBirthPlace() + "',"
                    + "gender='" + employee.getGender() + "',"
                    + "blood_type='" + employee.getBloodType() + "',"
                    + "spouse_name='" + employee.getSpouseName() + "',"
                    + "employment_date=?,"
                    //                    + "resign_date,"
                    //                    + "retire_date,"
                    + "sec_id='" + employee.getSecId() + "',"
                    + "phic_id='" + employee.getPhicId() + "',"
                    + "sss_id='" + employee.getSssId() + "',"
                    + "hdmf_id='" + employee.getHdmfId() + "',"
                    + "tin='" + employee.getTin() + "',"
                    + "house_no='" + employee.getHouseNo() + "',"
                    + "street='" + employee.getStreet() + "',"
                    + "barangay='" + employee.getBarangay() + "',"
                    + "city='" + employee.getCity() + "',"
                    + "zip_code='" + employee.getZipCode() + "',"
                    + "contact_no='" + employee.getContactNo() + "',"
                    + "email='" + employee.getEmail() + "',"
                    + "emp_type_id='" + employee.getEmpTypeId() + "',"
                    + "employer_id='" + employee.getEmployerId() + "', "
                    + "status_flag='" + employee.getStatusFlag() + "', "
                    + "step='" + employee.getStep() + "', "
                    + "position_id='" + employee.getPositionId() + "', "
                    + "qualified_flag='" + employee.getQualifiedFlag() + "', "
                    + "province='" + employee.getProvince() + "', "
                    + "country='" + employee.getCountry() + "',"
                    + "emp_no='" + employee.getEmpNo() + "', "
                    + "civil_status_id='" + employee.getCivilStatusId() + "', "
                    + "pin_code=" + employee.getPinCode() + " "
                    + "WHERE emp_id=" + employee.getEmpId();

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(employee.getBirthDate().getTime())); //Dateof Birth
            ps.setDate(2, new java.sql.Date(employee.getEmploymentDate().getTime())); //Employement Date
            ps.executeUpdate();


        } catch (Exception ex) {
            Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    public ArrayList getbyEmployee(String nym) {

        ArrayList<Employee> employee = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE CONCAT(last_name,', ',first_name,' ',middle_name,' ',COALESCE(suffix_name,'')) LIKE '%" + nym + "%' ORDER BY last_name, first_name";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {

                do {
                    Employee list = new Employee();


                    char genderChar = rs.getString("gender").charAt(0);

                    list.setEmpId(rs.getInt("emp_id"));
                    list.setLastName(rs.getString("last_name"));
                    list.setFirstName(rs.getString("first_name"));
                    list.setMiddleName(rs.getString("middle_name"));
                    list.setSuffixName(rs.getString("suffix_name"));
                    list.setBirthDate(rs.getDate("birth_date"));
                    list.setBirthPlace(rs.getString("birth_place"));
                    list.setGender(genderChar);
                    list.setBloodType(rs.getString("blood_type"));
                    list.setSpouseName(rs.getString("spouse_name"));
                    list.setEmploymentDate(rs.getDate("employment_date"));
                    list.setResignDate(rs.getDate("resign_date"));
                    list.setRetireDate(rs.getDate("retire_date"));
                    list.setSecId(rs.getInt("sec_id"));
                    list.setPhicId(rs.getString("phic_id"));
                    list.setSssId(rs.getString("sss_id"));
                    list.setHdmfId(rs.getString("hdmf_id"));
                    list.setTin(rs.getString("tin"));
                    list.setHouseNo(rs.getString("house_no"));
                    list.setStreet(rs.getString("street"));
                    list.setBarangay(rs.getString("barangay"));
                    list.setCity(rs.getString("city"));
                    list.setZipCode(rs.getString("zip_code"));
                    list.setContactNo(rs.getString("contact_no"));
                    list.setEmail(rs.getString("email"));
                    list.setEmpTypeId(rs.getShort("emp_type_id"));
                    list.setEmployerId(rs.getInt("employer_id"));
                    list.setStatusFlag(rs.getShort("status_flag"));
                    list.setCivilStatusId(rs.getInt("civil_status_id"));

                    employee.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return employee;
        }
    }

    public ArrayList getbyEmpId(int EmpId) {

        ArrayList<Employee> employee = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE emp_id=" + EmpId;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {

                do {
                    Employee list = new Employee();


                    char genderChar = rs.getString("gender").charAt(0);

                    list.setEmpNo(rs.getString("emp_no"));
                    list.setEmpId(rs.getInt("emp_id"));
                    list.setLastName(rs.getString("last_name"));
                    list.setFirstName(rs.getString("first_name"));
                    list.setMiddleName(rs.getString("middle_name"));
                    list.setSuffixName(rs.getString("suffix_name"));
                    list.setBirthDate(rs.getDate("birth_date"));
                    list.setBirthPlace(rs.getString("birth_place"));
                    list.setGender(genderChar);
                    list.setBloodType(rs.getString("blood_type"));
                    list.setSpouseName(rs.getString("spouse_name"));
                    list.setEmploymentDate(rs.getDate("employment_date"));
                    list.setResignDate(rs.getDate("resign_date"));
                    list.setRetireDate(rs.getDate("retire_date"));
                    list.setSecId(rs.getInt("sec_id"));
                    list.setPhicId(rs.getString("phic_id"));
                    list.setSssId(rs.getString("sss_id"));
                    list.setHdmfId(rs.getString("hdmf_id"));
                    list.setTin(rs.getString("tin"));
                    list.setHouseNo(rs.getString("house_no"));
                    list.setStreet(rs.getString("street"));
                    list.setBarangay(rs.getString("barangay"));
                    list.setCity(rs.getString("city"));
                    list.setZipCode(rs.getString("zip_code"));
                    list.setContactNo(rs.getString("contact_no"));
                    list.setEmail(rs.getString("email"));
                    list.setEmpTypeId(rs.getShort("emp_type_id"));
                    list.setEmployerId(rs.getInt("employer_id"));
                    list.setStatusFlag(rs.getShort("status_flag"));
                    list.setProvince(rs.getString("province"));
                    list.setCountry(rs.getString("country"));
                    list.setStep(rs.getShort("step"));
                    list.setQualifiedFlag(rs.getShort("qualified_flag"));
                    list.setPositionId(rs.getShort("position_id"));
                    list.setCivilStatusId(rs.getInt("civil_status_id"));
                    list.setPinCode(rs.getShort("pin_code"));
                    
                    employee.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return employee;
        }
    }

    @Override
    public ArrayList get() {

        ArrayList<Employee> employee = new ArrayList<>();
        String query = "SELECT * FROM employee ORDER BY employee_name";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {

                do {
                    Employee list = new Employee();


                    char genderChar = rs.getString("gender").charAt(0);

                    list.setEmpId(rs.getInt("emp_id"));
                    list.setLastName(rs.getString("last_name"));
                    list.setFirstName(rs.getString("first_name"));
                    list.setMiddleName(rs.getString("middle_name"));
                    list.setSuffixName(rs.getString("suffix_name"));
                    list.setBirthDate(rs.getDate("birth_date"));
                    list.setBirthPlace(rs.getString("birth_place"));
                    list.setGender(genderChar);
                    list.setBloodType(rs.getString("blood_type"));
                    list.setSpouseName(rs.getString("spouse_name"));
                    list.setEmploymentDate(rs.getDate("employement_date"));
                    list.setResignDate(rs.getDate("resign_date"));
                    list.setRetireDate(rs.getDate("retire_date"));
                    list.setSecId(rs.getInt("sec_id"));
                    list.setPhicId(rs.getString("phic_id"));
                    list.setSssId(rs.getString("sss_id"));
                    list.setHdmfId(rs.getString("hdmf_id"));
                    list.setTin(rs.getString("tin"));
                    list.setHouseNo(rs.getString("house_no"));
                    list.setStreet(rs.getString("street"));
                    list.setBarangay(rs.getString("barangay"));
                    list.setCity(rs.getString("city"));
                    list.setZipCode(rs.getString("zip_code"));
                    list.setContactNo(rs.getString("contact_no"));
                    list.setEmail(rs.getString("email"));
                    list.setEmpTypeId(rs.getShort("emp_type_id"));
                    list.setEmployerId(rs.getInt("employer_id"));
                    list.setStatusFlag(rs.getShort("status_flag"));
                    list.setCivilStatusId(rs.getInt("civil_status_id"));

                    employee.add(list);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DeptEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return employee;
        }
    }

    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
