package mod.others;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mod.connection.myDBConn;

public class paths {

    static String path;

    public static String getPicPathConfig() {

        String filePath = System.getProperty("user.dir") + "\\config.properties";

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException ex) {
            Logger.getLogger(myDBConn.class.getName()).log(Level.SEVERE, null, ex);
        }

        path = properties.getProperty("picpathconfig");
        return path;
    }
    
    
    public static void main(String[] args) {
           
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date1, date2;
       
            
       try {
             date1 =myFunction.GetSystemNowDate();
             date2 =df.parse("08/19/2013");
             System.out.print(date1);
             System.out.print(date2);
             
             int res = date1.compareTo(date2);
             System.out.print(res);
        } catch (ParseException ex) {
            Logger.getLogger(paths.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
    }
}
