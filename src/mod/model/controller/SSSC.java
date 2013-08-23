package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.SSS;
import mod.model.entity_manager.SSSEM;
import mod.others.myFunction;

public class SSSC {

    //TABLE POPULATOR
    public DefaultTableModel getSSSTableModel(JTable tbl) {
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

        tbl.setRowHeight(28);
        tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(1).setMaxWidth(100);
        tbl.getColumnModel().getColumn(2).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(3).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(4).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(5).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(6).setCellRenderer(AlignRight);
        tbl.getColumnModel().getColumn(7).setCellRenderer(AlignRight);


        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).get();

            int rc = 1;

            for (SSS sss : SSSs) {
                Object[] newCol = new Object[8];

                int i = 0;
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(sss.getRangeStart().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";

                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(sss.getRangeEnd().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";

                newCol[i++] = sss.getSssId();
                newCol[i++] = rc;
                newCol[i++] = start;
                newCol[i++] = end;
                newCol[i++] = myFunction.amountFormat2(sss.getSalaryBase().toString());
                newCol[i++] = myFunction.amountFormat2(sss.getEe().toString());
                newCol[i++] = myFunction.amountFormat2(sss.getEr().toString());
                newCol[i++] = myFunction.amountFormat2(sss.getEc().toString());

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    //APPENDERS

    public int addSSS(SSS sss) {
        Connection con = myDBConn.getConnection();
        SSSEM dem = new SSSEM(con);
        return dem.persist(sss);
    }

    public int editSSS(SSS sss) {
        Connection con = myDBConn.getConnection();
        SSSEM dem = new SSSEM(con);
        return dem.update(sss);
    }

    public int deleteSSS(SSS sss) {
        Connection con = myDBConn.getConnection();
        SSSEM dem = new SSSEM(con);
        return dem.delete(sss);
    }

    public int deleteAll(SSS sss) {
        Connection con = myDBConn.getConnection();
        SSSEM dem = new SSSEM(con);
        return dem.deleteAll(sss);
    }

    public String GetRangeStart(int sssid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getRangeStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetRangeEnd(int sssid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getRangeEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSalaryBase(int sssid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getSalaryBase());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEE(int sssid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getEe());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetER(int sssid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getEr());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEC(int sssid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<SSS> SSSs = new SSSEM(con).getSssId(sssid);

            for (SSS sss : SSSs) {
                Value = String.valueOf(sss.getEc());
            }

        } catch (Exception ex) {
            Logger.getLogger(SSSC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
    //END OF APPENDERS
}
