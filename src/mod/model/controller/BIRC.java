/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mod.model.BIR;
import mod.model.entity_manager.BIREM;
import mod.others.myFunction;

/**
 *
 * @author LESTER
 */
public class BIRC {
       //TABLE POPULATOR
    public DefaultTableModel getBIRTableModel(JTable tbl) {
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



        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<BIR> birs = new BIREM(con).get();

            int rc = 1;

            for (BIR bir : birs) {
                Object[] newCol = new Object[6];

                int i = 0;
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(bir.getRangeStart().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";

                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(bir.getRangeEnd().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";

                newCol[i++] = bir.getBirId();
                newCol[i++] = rc;
                newCol[i++] = start;
                newCol[i++] = end;
                newCol[i++] = myFunction.amountFormat2(bir.getTaxMinAmt().toString());
                newCol[i++] = myFunction.amountFormat2(bir.getTaxAddPercentage().toString());



                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(BIRC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addBIR(BIR bir) {
        Connection con = myDBConn.getConnection();
        BIREM dem = new BIREM(con);
        return dem.persist(bir);
    }

    public int editBIR(BIR bir) {
        Connection con = myDBConn.getConnection();
        BIREM dem = new BIREM(con);
        return dem.update(bir);
    }

    public int deleteBIR(BIR bir) {
        Connection con = myDBConn.getConnection();
        BIREM dem = new BIREM(con);
        return dem.delete(bir);
    }

    public int deleteAll(BIR bir) {
        Connection con = myDBConn.getConnection();
        BIREM dem = new BIREM(con);
        return dem.deleteAll(bir);
    }

    public String GetRangeStart(int birid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<BIR> birs = new BIREM(con).getBirId(birid);

            for (BIR bir : birs) {
                Value = String.valueOf(bir.getRangeStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(BIRC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetRangeEnd(int birid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<BIR> birs = new BIREM(con).getBirId(birid);

            for (BIR bir : birs) {
                Value = String.valueOf(bir.getRangeEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(BIRC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetTaxMinAmt(int birid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<BIR> birs = new BIREM(con).getBirId(birid);

            for (BIR bir : birs) {
                Value = String.valueOf(bir.getTaxMinAmt());
            }

        } catch (Exception ex) {
            Logger.getLogger(BIRC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetTaxAddPercentage(int birid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<BIR> birs = new BIREM(con).getBirId(birid);

            for (BIR bir : birs) {
                Value = String.valueOf(bir.getTaxAddPercentage());
            }

        } catch (Exception ex) {
            Logger.getLogger(BIRC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

}
