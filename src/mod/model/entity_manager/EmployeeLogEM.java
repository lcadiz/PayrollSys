/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.entity_manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.EmployeeLog;
import mod.model.Model;

/**
 *
 * @author LESTER
 */
public class EmployeeLogEM extends EntityManager {

    public EmployeeLogEM(Connection con) {
        super(con);
    }

    @Override
    public int persist(Model model) {
        EmployeeLog emplog = (EmployeeLog) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO employee_log("
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
                    + "remarks,"
                    + "user_id,"
                    + "emp_id, "
                    + "civil_status_id, "
                    + "pin_code " 
                    + ") "
                    + "VALUES"
                    + "("
                    + "'" + emplog.getLastName() + "',"
                    + "'" + emplog.getFirstName() + "',"
                    + "'" + emplog.getMiddleName() + "',"
                    + "'" + emplog.getSuffixName() + "',"
                    + "?," //Date of Birth
                    + "'" + emplog.getBirthPlace() + "',"
                    + "'" + emplog.getGender() + "',"
                    + "'" + emplog.getBloodType() + "',"
                    + "'" + emplog.getSpouseName() + "',"
                    + "?," //Employement Date
                    //                    + "'" + emplog.getResignDate() + "',"
                    //                    + "'" + emplog.getRetireDate() + "',"
                    + "'" + emplog.getSecId() + "',"
                    + "'" + emplog.getPhicId() + "',"
                    + "'" + emplog.getSssId() + "',"
                    + "'" + emplog.getHdmfId() + "',"
                    + "'" + emplog.getTin() + "',"
                    + "'" + emplog.getHouseNo() + "',"
                    + "'" + emplog.getStreet() + "',"
                    + "'" + emplog.getBarangay() + "',"
                    + "'" + emplog.getCity() + "',"
                    + "'" + emplog.getZipCode() + "',"
                    + "'" + emplog.getContactNo() + "',"
                    + "'" + emplog.getEmail() + "',"
                    + "'" + emplog.getEmpTypeId() + "',"
                    + "'" + emplog.getEmployerId() + "', "
                    + "'" + emplog.getStatusFlag() + "', "
                    + "'" + emplog.getStep() + "', "
                    + "'" + emplog.getPositionId() + "', "
                    + "'" + emplog.getQualifiedFlag() + "', "
                    + "'" + emplog.getProvince() + "', "
                    + "'" + emplog.getCountry() + "',"
                    + "'" + emplog.getRemarks() + "',"
                    + "'" + emplog.getUserId() + "',"
                    + "'" + emplog.getEmpId() + "', "
                    + "'" + emplog.getCivilStatusId() + "', "
                    + " " + emplog.getPinCode() + " "
                    + ")";


            ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(emplog.getBirthDate().getTime())); //Dateof Birth
            ps.setDate(2, new java.sql.Date(emplog.getEmploymentDate().getTime())); //Employement Date
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                throw new RuntimeException("Can't find most recent insert we just entered");
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeLogEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeLogEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeLogEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(int entityId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList get(Object filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Model get(Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
