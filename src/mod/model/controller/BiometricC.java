package mod.model.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mod.connection.myDBConn;
import mod.model.Biometric;
import mod.model.entity_manager.BiometricEM;

public class BiometricC {
    //TABLE POPULATOR

    public DefaultTableModel getBiometricTableModel(JTable tbl) {
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

        tbl.setRowHeight(45);
        //tbl.getColumnModel().getColumn(1).setCellRenderer(AlignCenter);
        tbl.getColumnModel().getColumn(1).setMaxWidth(50);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbl.getColumnModel().getColumn(3).setCellRenderer(AlignCenter);

        model.setNumRows(0);


        try {
            con = myDBConn.getConnection();
            ArrayList<Biometric> Biometrics = new BiometricEM(con).get();

            int rc = 1;

            for (Biometric biometric : Biometrics) {
                Object[] newCol = new Object[4];

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                + "<tr><td><img src=" + getClass().getResource("/images/biometric.png") + "></td><td>" + biometric.getDeviceName() + "<br>" + "<font color=#E7C30F>IP Address: " + biometric.getIpAddress() + "</font></td></th>";

                
                int i = 0;
                newCol[i++] = biometric.getDeviceId();
                newCol[i++] = false;
                newCol[i++] = lbl;
                newCol[i++] = "";
 

                model.addRow(newCol);
                rc++;
            }

        } catch (Exception ex) {
            Logger.getLogger(BiometricC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }
    public DefaultListModel getBiometricListModel() {
        Connection con = null;
        DefaultListModel model = new DefaultListModel();
        model.removeAllElements();
        try {
            con = myDBConn.getConnection();
            ArrayList<Biometric> biometrics = new BiometricEM(con).get();

            for (Biometric biometric : biometrics) {

                model.addElement(biometric);
            }
        } catch (Exception ex) {
            Logger.getLogger(BiometricC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return model;
        }
    }

    //APPENDERS
    public int addBiometric(Biometric biometric) {
        Connection con = myDBConn.getConnection();
        BiometricEM dem = new BiometricEM(con);
        return dem.persist(biometric);
    }

    public int editBiometric(Biometric biometric) {
        Connection con = myDBConn.getConnection();
        BiometricEM dem = new BiometricEM(con);
        return dem.update(biometric);
    }

    public int deleteBiometric(Biometric biometric) {
        Connection con = myDBConn.getConnection();
        BiometricEM dem = new BiometricEM(con);
        return dem.delete(biometric);
    }

    public String GetDeviceName(int biometricid) {
        String Value = "";

        try {
            Connection con = myDBConn.getConnection();
            ArrayList<Biometric> Biometrics = new BiometricEM(con).getDeviceId(biometricid);

            for (Biometric biometric : Biometrics) {
                Value = String.valueOf(biometric.getDeviceName());
            }

        } catch (Exception ex) {
            Logger.getLogger(BiometricC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    public String GetIpAddress(int biometricid) {
        String Value = "";

        try {

            Connection con = myDBConn.getConnection();
            ArrayList<Biometric> Biometrics = new BiometricEM(con).getDeviceId(biometricid);

            for (Biometric biometric : Biometrics) {
                Value = String.valueOf(biometric.getIpAddress());
            }

        } catch (Exception ex) {
            Logger.getLogger(BiometricC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return Value;
        }
    }

    //END OF APPENDERS
}
