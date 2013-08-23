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
import mod.model.HDMF;
import mod.model.entity_manager.HDMFEM;
import mod.others.myFunction;

public class HDMFC {

    //TABLE POPULATOR
    public DefaultTableModel getHDMFTableModel(JTable tbl) {
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
            ArrayList<HDMF> hdmfs = new HDMFEM(con).get();

            int rc = 1;

            for (HDMF hdmf : hdmfs) {
                Object[] newCol = new Object[8];

                int i = 0;
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(hdmf.getRangeStart().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";

                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(hdmf.getRangeEnd().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";

                newCol[i++] = hdmf.getHdmfId();
                newCol[i++] = rc;
                newCol[i++] = start;
                newCol[i++] = end;
                newCol[i++] = myFunction.amountFormat2(hdmf.getMaxSalaryBase().toString());
                newCol[i++] = myFunction.amountFormat2(hdmf.getTotal().toString());
                newCol[i++] = myFunction.amountFormat2(hdmf.getEe().toString());
                newCol[i++] = myFunction.amountFormat2(hdmf.getEr().toString());


                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addHDMF(HDMF hdmf) {
        Connection con = myDBConn.getConnection();
        HDMFEM dem = new HDMFEM(con);
        return dem.persist(hdmf);
    }

    public int editHDMF(HDMF hdmf) {
        Connection con = myDBConn.getConnection();
        HDMFEM dem = new HDMFEM(con);
        return dem.update(hdmf);
    }

    public int deleteHDMF(HDMF hdmf) {
        Connection con = myDBConn.getConnection();
        HDMFEM dem = new HDMFEM(con);
        return dem.delete(hdmf);
    }

    public int deleteAll(HDMF hdmf) {
        Connection con = myDBConn.getConnection();
        HDMFEM dem = new HDMFEM(con);
        return dem.deleteAll(hdmf);
    }

    public String GetRangeStart(int hdmfid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getRangeStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetRangeEnd(int hdmfid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getRangeEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetMaxSalaryBase(int hdmfid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getMaxSalaryBase());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEE(int hdmfid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getEe());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetER(int hdmfid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getEr());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetTotal(int hdmfid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<HDMF> hdmfs = new HDMFEM(con).getHdmfId(hdmfid);

            for (HDMF hdmf : hdmfs) {
                Value = String.valueOf(hdmf.getTotal());
            }

        } catch (Exception ex) {
            Logger.getLogger(HDMFC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
//    END OF APPENDERS
}
