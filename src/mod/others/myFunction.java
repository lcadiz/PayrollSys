package mod.others;

import java.awt.Component;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class myFunction {

    static Statement stmt;
    
    public static String getSystemInfo(){
        String sys=null;
        boolean is64bit = false;
        if (System.getProperty("os.name").contains("Windows")) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
        }
        //System.out.println(is64bit);
        
        if(is64bit==true){
            sys ="x64-based PC";
        }else{
            sys ="x86-based PC";
        }
        
        return sys;
    }

    public static Date GetSystemNowDate() {
        Date NowDate = null;
        
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT CURDATE() AS NowDate";
        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
               NowDate = rs.getDate(1);
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return NowDate;
    }
    
     public static Time GetSystemNowTime() {
        Time NowTime = null;
        
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT CURTIME() AS NowDate";
        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
               NowTime = rs.getTime(1);
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return NowTime;
    }

     public static Date getSystemNowDateTime(){
         Date NowDate =null;
         String DateStr = GetSystemNowDate().toString() + " " +GetSystemNowTime().toString();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        try {
            NowDate = dateFormat.parse(DateStr);
        } catch (ParseException ex) {
            Logger.getLogger(myFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
         return NowDate;
     }
     
    public static int generatedRandomPINCode() {
        int aStart = 1111;
        int aEnd = 9999;
        Random random = new Random();
        Random aRandom = random;

        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);
        return randomNumber;
    }

    public static int getMinuteDiff(String startTime, String endTime) {
        int diff = 0;

        final DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");

        DateTime timeOne = format.parseDateTime(startTime);
        DateTime timeTwo = format.parseDateTime(endTime);

        final Period period = new Period(timeOne, timeTwo);

        //System.out.print(period.getDays()+" days, ");
        //System.out.print(period.getHours()+" hours, ");
        //System.out.print(period.getMinutes()+" minutes, ");
        // System.out.print(period.getSeconds()+" seconds.");

        diff = period.getMinutes();
        return diff;
    }

    public static int getHourDiff(String startTime, String endTime) {
        int diff = 0;

        final DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");

        DateTime timeOne = format.parseDateTime(startTime);
        DateTime timeTwo = format.parseDateTime(endTime);

        final Period period = new Period(timeOne, timeTwo);

        //System.out.print(period.getDays()+" days, ");
        //System.out.print(period.getHours()+" hours, ");
        //System.out.print(period.getMinutes()+" minutes, ");
        // System.out.print(period.getSeconds()+" seconds.");

        diff = period.getHours();
        return diff;
    }

    public static boolean pingIP(String ip) {
        boolean result = false;

        String ipAddress = ip;

        try {
            InetAddress inet = InetAddress.getByName(ipAddress);
            //System.out.println("Sending Ping Request to " + ipAddress);

            boolean status = inet.isReachable(3000); //Timeout = 5000 milli seconds

            if (status) {
                result = true;
            } else {
                result = false;
            }
        } catch (UnknownHostException e) {
            System.err.println("Host does not exists");
        } catch (IOException e) {
            System.err.println("Error in reaching the Host");
        }

        return result;
    }

    public static String mysqlformatdate(String seldate) {
        String yy = myFunction.getValYear(seldate);
        String mm = myFunction.getValMonth(seldate);
        String dd = myFunction.getValDay(seldate);
        String ddate = yy + "-" + mm + "-" + dd;

        return ddate;
    }

    public static String getValYear(String myDate) {
        String output = null;
        try {
            DateFormat formatter, formatter2;
            Date datein;
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            datein = (Date) formatter.parse(myDate);
            formatter2 = new SimpleDateFormat("yyyy");
            output = formatter2.format(datein);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return output;
    }

    public static String getValMonth(String myDate) {
        String output = null;
        try {
            DateFormat formatter, formatter2;
            Date datein;
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            datein = (Date) formatter.parse(myDate);
            formatter2 = new SimpleDateFormat("MM");
            output = formatter2.format(datein);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return output;
    }

    public static String getValDay(String myDate) {
        String output = null;
        try {
            DateFormat formatter, formatter2;
            Date datein;
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            datein = (Date) formatter.parse(myDate);
            formatter2 = new SimpleDateFormat("dd");
            output = formatter2.format(datein);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return output;
    }

    public static String convertMD5(String pass) {
        byte[] defaultBytes = pass.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }
            pass = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {
        }

        return pass;


    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$#-" + n + "s", s);
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "The date you provided is in an invalid date format.");
            return false;
        }
        if (!sdf.format(testDate).equals(date)) {
            JOptionPane.showMessageDialog(null, "The date that you provided is invalid.");
            return false;
        }
        return true;
    }

//    public static Date dateformatter3(String wat) {
//        Format formatter;
//        Date output = null;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        Date date;
//        try {
//            date = dateFormat.parse(wat);
//            formatter = new SimpleDateFormat("yyyy-MM-dd");
//            formatter.format(date);
//            output=date;
//        } catch (ParseException ex) {
//            Logger.getLogger(myFunctions.class.getName()).log(Level.SEVERE, null, ex);
//        }
//return output;
//    }
    public static String getDatewTym() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date getDatewTymDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
        Date date = new Date();
        String fdate = dateFormat.format(date);
        Date finaldate = null;
        try {
            finaldate = dateFormat.parse(fdate);
        } catch (ParseException ex) {
            Logger.getLogger(myFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finaldate;
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDate2() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss a");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDate3() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getdateN() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String gettimeN() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getcurtime() {
        String ctime = "";
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT CURTIME();";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                ctime = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }


        return ctime;
    }

    public static String getYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getMonth() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static int msgOPT(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert", JOptionPane.YES_NO_CANCEL_OPTION);
        return result;
    }

    public static int msgboxYesNoCancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert", JOptionPane.YES_NO_CANCEL_OPTION);
        return result;
    }

    public static int msgboxYesNo(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert", JOptionPane.YES_NO_OPTION);
        return result;
    }

    public static String amountFormat2(String amnt) {
        DecimalFormat money = new DecimalFormat("#,##0.00");
        double aDouble = Double.parseDouble(amnt);
        String output = money.format(aDouble);
        return output;
    }

    public static String amountFormat(double amnt) {
        DecimalFormat money = new DecimalFormat("#,##0.00");
        double aDouble = amnt;
        String output = money.format(aDouble);
        return output;
    }

    public static String amountFormat3(String amnt) {
        DecimalFormat money = new DecimalFormat("0.0");
        double aDouble = Double.parseDouble(amnt);
        String output = money.format(aDouble);
        return output;
    }

    public static String amountFormat4(BigDecimal amnt) {
        DecimalFormat money = new DecimalFormat("0.0");
        //double aDouble = Double.parseDouble(String.valueOf(amnt));
        String output = money.format(amnt);
        return output;
    }

    public static String dateformatter(String wat) {
        Format formatter;
        String output = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
        Date date;
        try {
            date = dateFormat.parse(wat);
            formatter = new SimpleDateFormat("MM/dd/yy");
            output = formatter.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(myFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    public static String dateformatter2(String wat) {
        Format formatter;
        String output = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        try {
            date = dateFormat.parse(wat);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            output = formatter.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(myFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    public static String dateformatter3(String wat) {
        Format formatter;
        String output = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Date date;
        try {
            date = dateFormat.parse(wat);
            formatter = new SimpleDateFormat("hh:mm a");
            output = formatter.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(myFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    public static int getMaxDayofTheMonth(int month) {
        GregorianCalendar calendar = new GregorianCalendar();

        // convert the year and month to integers
        int yearInt = Integer.parseInt(getYear());
        int monthInt = month;

        // adjust the month for a zero based index
        monthInt = monthInt - 1;

        // set the date of the calendar to the date provided
        calendar.set(yearInt, monthInt, 1);

        int dayInt = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return dayInt;
    }

    public static void main(String[] args) {
        //int x=generatedRandomPINCode();
        
        System.out.println(getSystemNowDateTime());
    }
}
