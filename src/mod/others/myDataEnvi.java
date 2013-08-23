package mod.others;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;

public class myDataEnvi {

    static Statement stmt;

    public static void add_trans(int id, int type) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "INSERT INTO transaction(user_id, trans_type) "
                + "VALUES (" + id + "," + type + ")";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static int get_trans_id() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT IFNULL(MAX(trans_id),0) AS fld FROM transaction";
        int TransID = 0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                TransID = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return TransID;
    }

    public static int get_stock_trans_id() {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT IFNULL(MAX(stock_trans_id),0) AS fld FROM stock_trans";
        int STID = 0;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                STID = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return STID;
    }

    public static String get_soh(int id) {
        //get the current item stock on hand
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT item_soh FROM item WHERE item_id=" + id;
        String soh = "";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                soh = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return soh;
    }

    public static void add_qty(int id, String qty) {
        double cur_soh = Double.parseDouble(get_soh(id));
        double qty_add = Double.parseDouble(qty);
        double new_soh = cur_soh + qty_add;
        edit_qty(id, String.valueOf(new_soh));
    }

    public static void minus_qty(int id, String qty) {
        double cur_soh = Double.parseDouble(get_soh(id));
        double qty_add = Double.parseDouble(qty);
        double new_soh = cur_soh - qty_add;
        edit_qty(id, String.valueOf(new_soh));
    }

    public static void edit_qty(int id, String qty) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "UPDATE item SET item_soh='" + qty + "' "
                + "WHERE item_id=" + id;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void insert_stock_log(int itemid, String qty, String bal, int transid, String cost, String ldate) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "INSERT INTO stock_log("
                + "item_id, "
                + "quantity, "
                + "balance, "
                + "trans_id, "
                + "unit_cost, "
                + "log_date "
                + ") "
                + "VALUES (" + itemid + ",'" + qty + "','" + bal + "'," + transid + ",'" + cost + "','" + ldate + "')";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static int set_new_trans(int id, int type) {
        add_trans(id, type);
        int transid = get_trans_id();
        return transid;
    }

    public static void main(String[] args) {
    }
}
