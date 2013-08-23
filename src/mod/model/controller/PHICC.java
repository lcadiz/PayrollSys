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
import mod.model.PHIC;
import mod.model.entity_manager.PHICEM;
import mod.others.myFunction;

public class PHICC {
    
    //TABLE POPULATOR
    public DefaultTableModel getPHICTableModel(JTable tbl) {
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
            ArrayList<PHIC> phics = new PHICEM(con).get();

            int rc = 1;

            for (PHIC phic : phics) {
                Object[] newCol = new Object[8];

                int i = 0;
                String start = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(phic.getRangeStart().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/innn.png") + "></td></th>";

                String end = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td >" + myFunction.amountFormat2(phic.getRangeEnd().toString()) + "</td>&nbsp<td><img src=" + getClass().getResource("/images/outtt.png") + "></td></th>";

                newCol[i++] = phic.getPhicId();
                newCol[i++] = rc;
                newCol[i++] = start;
                newCol[i++] = end;
                newCol[i++] = myFunction.amountFormat2(phic.getSalaryBase().toString());
                newCol[i++] = myFunction.amountFormat2(phic.getTotal().toString());
                newCol[i++] = myFunction.amountFormat2(phic.getEe().toString());
                newCol[i++] = myFunction.amountFormat2(phic.getEr().toString());


                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addPHIC(PHIC phic) {
        Connection con = myDBConn.getConnection();
        PHICEM dem = new PHICEM(con);
        return dem.persist(phic);
    }

    public int editPHIC(PHIC phic) {
        Connection con = myDBConn.getConnection();
        PHICEM dem = new PHICEM(con);
        return dem.update(phic);
    }

    public int deletePHIC(PHIC phic) {
        Connection con = myDBConn.getConnection();
        PHICEM dem = new PHICEM(con);
        return dem.delete(phic);
    }

    public int deleteAll(PHIC phic) {
        Connection con = myDBConn.getConnection();
        PHICEM dem = new PHICEM(con);
        return dem.deleteAll(phic);
    }
    public String GetRangeStart(int phicid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getRangeStart());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetRangeEnd(int phicid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getRangeEnd());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetSalaryBase(int phicid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getSalaryBase());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetEE(int phicid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getEe());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetER(int phicid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getEr());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetTotal(int phicid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<PHIC> phics = new PHICEM(con).getPhicId(phicid);

            for (PHIC phic : phics) {
                Value = String.valueOf(phic.getTotal());
            }

        } catch (Exception ex) {
            Logger.getLogger(PHICC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }
}
