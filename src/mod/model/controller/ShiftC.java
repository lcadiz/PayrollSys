package mod.model.controller;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.Shift;
import mod.model.entity_manager.ShiftEM;

public class ShiftC {

    public DefaultTableModel getShiftTableModel(JTable tbl) {
        Connection con = null;

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        DefaultTableCellRenderer AlignRight = new DefaultTableCellRenderer();
        DefaultTableCellRenderer AlignCenter = new DefaultTableCellRenderer();

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);

        AlignCenter.setHorizontalAlignment(0);
        AlignRight.setHorizontalAlignment(SwingConstants.RIGHT);

        tbl.setRowHeight(60);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(250);
        // tbl.getColumnModel().getColumn(2).setCellRenderer(AlignCenter);
        //tbl.getColumnModel().getColumn(3).setCellRenderer(AlignCenter);
        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).get();

            int rc = 1;
            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

            for (Shift shift : Shifts) {
                Object[] newCol = new Object[4];

                int i = 0;
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/shiftmini.png") + ">&nbsp</td><td>" + shift.getShiftDesc() + "<font></td>";

                String time1 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(shift.getInTime1()) + "</b></td><td width=20></td><td>Range is Between <font color=#59DF00><b>" + dateFormat.format(shift.getIn1LogStart()) + "</b></font> and <font color=#59DF00><b>" + dateFormat.format(shift.getIn1LogEnd()) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(shift.getOutTime1()) + "</b></td><td width=20></td><td>Range is Between <font color=#FF0000><b>" + dateFormat.format(shift.getOut1LogStart()) + "</b></font> and <font color=#FF0000><b>" + dateFormat.format(shift.getOut1LogEnd()) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                String time2 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(shift.getInTime2()) + "</b></td><td width=20></td><td>Range is Between <font color=#59DF00><b>" + dateFormat.format(shift.getIn2LogStart()) + "</b></font> and <font color=#59DF00><b>" + dateFormat.format(shift.getIn2LogEnd()) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(shift.getOutTime2()) + "</b></td><td width=20></td><td>Range is Between <font color=#FF0000><b>" + dateFormat.format(shift.getOut2LogStart()) + "</b></font> and <font color=#FF0000><b>" + dateFormat.format(shift.getOut2LogEnd()) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                newCol[i++] = shift.getShiftId();
                newCol[i++] = lbl;
                newCol[i++] = time1;
                newCol[i++] = time2;

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    
    public DefaultTableModel getAssignShiftTableModel(JTable tbl) {
        Connection con = null;

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        DefaultTableCellRenderer AlignRight = new DefaultTableCellRenderer();
        DefaultTableCellRenderer AlignCenter = new DefaultTableCellRenderer();

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);

        AlignCenter.setHorizontalAlignment(0);
        AlignRight.setHorizontalAlignment(SwingConstants.RIGHT);

        tbl.setRowHeight(60);
        //tbl.getColumnModel().getColumn(2).setPreferredWidth(250);
        //tbl.getColumnModel().getColumn(3).setPreferredWidth(250);
        tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
         tbl.getColumnModel().getColumn(2).setCellRenderer(AlignCenter);
         tbl.getColumnModel().getColumn(3).setCellRenderer(AlignCenter);
        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).get();

            int rc = 1;
            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

            for (Shift shift : Shifts) {
                Object[] newCol = new Object[4];

                int i = 0;
                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/shiftmini.png") + ">&nbsp</td><td>" + shift.getShiftDesc() + "<font></td>";

                String time1 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(shift.getInTime1()) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(shift.getOutTime1()) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                String time2 = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift.png") + ">&nbsp</td><td>Time-in:</td><td font color=#59DF00><b>" + dateFormat.format(shift.getInTime2()) + "</b></td></tr>"
                        //+ "<tr font color=#E7C30F><td colspan=3><b>Time In Range is Between "+dateFormat.format(shift.getIn1LogStart())+" to "+dateFormat.format(shift.getIn1LogEnd())+ "</b></td></tr>"
                        + "<tr><td width=20><img src=" + getClass().getResource("/images/timeshift2.png") + ">&nbsp</td><td>Time-Out:</td><td font color=#FF0000><b>" + dateFormat.format(shift.getOutTime2()) + "</b></td></tr>"
                        + "</table>"
                        + "</html>";

                newCol[i++] = shift.getShiftId();
                newCol[i++] = lbl;
                newCol[i++] = time1;
                newCol[i++] = time2;

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    public int addShift(Shift shift) {
        Connection con = myDBConn.getConnection();
        ShiftEM dem = new ShiftEM(con);
        return dem.persist(shift);
    }

    public int editShift(Shift shift) {
        Connection con = myDBConn.getConnection();
        ShiftEM dem = new ShiftEM(con);
        return dem.update(shift);
    }

    public int deleteShift(Shift shift) {
        Connection con = myDBConn.getConnection();
        ShiftEM dem = new ShiftEM(con);
        return dem.delete(shift);
    }
    
    public String getShiftDesc(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getShiftDesc());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getInTime1(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getInTime1());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOutTime1(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOutTime1());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getIn1LogStart(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getIn1LogStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getIn1LogEnd(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getIn1LogEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOut1LogStart(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOut1LogStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOut1LogEnd(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOut1LogEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getInTime2(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getInTime2());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOutTime2(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOutTime2());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getIn2LogStart(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getIn2LogStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getIn2LogEnd(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getIn2LogEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOut2LogStart(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOut2LogStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String getOut2LogEnd(int sid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Shift> Shifts = new ShiftEM(con).getById(sid);

            for (Shift Shift : Shifts) {
                Value = String.valueOf(Shift.getOut2LogEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(ShiftC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    
    
}
