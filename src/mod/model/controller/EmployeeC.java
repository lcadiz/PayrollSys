package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import mod.connection.myDBConn;
import mod.model.Employee;
import mod.model.entity_manager.EmployeeEM;

public class EmployeeC {

    //LIST BOX POPULATOR
    public DefaultListModel getEmployeeListModel(String nym) {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Employee> employee = new EmployeeEM(con).getbyEmployee(nym);

            for (Employee Employee : employee) {

                model.addElement(Employee);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addEmployee(Employee emp) {
        Connection con = myDBConn.getConnection();
        EmployeeEM dem = new EmployeeEM(con);
        return dem.persist(emp);
    }

    public int editEmployee(Employee emp) {
        Connection con = myDBConn.getConnection();
        EmployeeEM dem = new EmployeeEM(con);
        return dem.update(emp);
    }

    public int deleteEmployee(Employee emp) {
        Connection con = myDBConn.getConnection();
        EmployeeEM dem = new EmployeeEM(con);
        return dem.delete(emp);
    }
    //END OF APPENDERS

    public String GetEmpNo(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getEmpNo());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetLname(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getLastName());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetFname(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getFirstName());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetMname(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getMiddleName());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSuffix(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getSuffixName());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetDOB(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(new java.sql.Date(Employee.getBirthDate().getTime()));
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetPOB(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getBirthPlace());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetGender(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getGender());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    
    public int GetCivilStatus(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = Employee.getCivilStatusId();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetBloodType(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getBloodType());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetHouseNo(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getHouseNo());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetStreet(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getStreet());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetBrgy(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getBarangay());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetCity(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getCity());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetZipCode(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getZipCode());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetProvince(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getProvince());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetCountry(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getCountry());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSpouse(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getSpouseName());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetContactNo(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getContactNo());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEmail(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getEmail());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEmpDate(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(new java.sql.Date(Employee.getEmploymentDate().getTime()));
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetStep(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getStep());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSSS(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getSssId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetPHIC(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getPhicId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetHDMF(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getHdmfId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetBIR(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getTin());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetQF(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getQualifiedFlag());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEmpTypeId(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getEmpTypeId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEmptoId(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getEmployerId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetPositionId(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getPositionId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    public String GetSecId(int eid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = String.valueOf(Employee.getSecId());
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    public int GeteFlg(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = Employee.getStatusFlag();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    public int GetPinCode(int eid) {
        int Value = 0;

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Employee> Employees = new EmployeeEM(con).getbyEmpId(eid);

            for (Employee Employee : Employees) {
                Value = Employee.getPinCode();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
