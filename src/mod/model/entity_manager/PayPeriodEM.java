/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod.model.entity_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.model.Model;
import mod.model.PayrollPeriod;

/**
 *
 * @author LESTER
 */
public class PayPeriodEM extends EntityManager {

    public PayPeriodEM(Connection con) {
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
        PayrollPeriod pp = (PayrollPeriod) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO payroll_period(payroll_period_desc, start_date, end_date) "
                    + "VALUES ('" + pp.getPayrollPeriodDesc() + "',?,?)";

            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(pp.getStartDate().getTime())); //date start
            ps.setDate(2, new java.sql.Date(pp.getEndDate().getTime())); //date end
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    public int deleteAll(Model model) {
        PayrollPeriod pp = (PayrollPeriod) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM payroll_period";
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public int delete(Model model) {
        PayrollPeriod pp = (PayrollPeriod) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM payroll_preiod WHERE pp_id=" + pp.getPayrollPeriodId();
            ps = getConnection().prepareStatement(query);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
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
        PayrollPeriod pp = (PayrollPeriod) model;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE payroll_period SET payroll_period_desc='" + pp.getPayrollPeriodDesc() + "', "
                    + "start_date=?, "
                    + "end_date=? "
                    + "WHERE payroll_period_id=" + pp.getPayrollPeriodId();
            ps = getConnection().prepareStatement(query);
            ps.setDate(1, new java.sql.Date(pp.getStartDate().getTime())); //date start
            ps.setDate(2, new java.sql.Date(pp.getEndDate().getTime())); //date end
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
            getConnection().rollback();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PayPeriodEM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }

    @Override
    public ArrayList get() {
        ArrayList<PayrollPeriod> pp = new ArrayList<>();
        String query = "SELECT * FROM payroll_period ORDER BY start_date ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    PayrollPeriod list = new PayrollPeriod();

                    list.setPayrollPeriodId(rs.getInt("payroll_period_id"));
                    list.setPayrollPeriodDesc(rs.getString("payroll_period_desc"));
                    list.setStartDate(rs.getDate("start_date"));
                    list.setEndDate(rs.getDate("end_date"));
                    pp.add(list);
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
            return pp;
        }
    }

    public ArrayList getByYear(int yr) {
        ArrayList<PayrollPeriod> pp = new ArrayList<>();
        String query = "SELECT * FROM payroll_period WHERE YEAR(start_date)=" + yr + " ORDER BY start_date ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    PayrollPeriod list = new PayrollPeriod();

                    list.setPayrollPeriodId(rs.getInt("payroll_period_id"));
                    list.setPayrollPeriodDesc(rs.getString("payroll_period_desc"));
                    list.setStartDate(rs.getDate("start_date"));
                    list.setEndDate(rs.getDate("end_date"));
                    pp.add(list);
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
            return pp;
        }
    }

    public ArrayList getPPId(int ppid) {
        ArrayList<PayrollPeriod> pp = new ArrayList<>();
        String query = "SELECT * FROM payroll_period WHERE payroll_period_id=" + ppid +" ORDER BY start_date";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                do {
                    PayrollPeriod list = new PayrollPeriod();

                    list.setPayrollPeriodId(rs.getInt("payroll_period_id"));
                    list.setPayrollPeriodDesc(rs.getString("payroll_period_desc"));
                    list.setStartDate(rs.getDate("start_date"));
                    list.setEndDate(rs.getDate("end_date"));

                    pp.add(list);
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
            return pp;
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
