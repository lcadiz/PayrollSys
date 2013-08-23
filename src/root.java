
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mod.others.myReports;
import mod.user.login;
import org.apache.log4j.PropertyConfigurator;

public class root {

    static Logger logger ;

    public static void main(String[] args) throws IOException {
        //PropertyConfigurator.configure("log4j.properties");
       


        try {
//            UIManager.put("nimbusBase", new Color(153,255,153));
//            UIManager.put("nimbusBlueGrey", new Color(153,255,153));
//            UIManager.put("control", new Color(153,255,153));


            Painter<JComponent> painter = new Painter<JComponent>() {
                @Override
                public void paint(Graphics2D g, JComponent c, int w, int h) {
                    g.setColor(new Color(0, 153, 153));
                    g.fillRect(0, 0, w, h);
                }
            };
            UIManager.put("DesktopPane[Enabled].backgroundPainter", painter);

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(root.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(root.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(root.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(root.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        //LAUNCH LOGIN
        login frm = new login();
        frm.setIconImage(Toolkit.getDefaultToolkit().getImage("ico.png"));
        //frm.setExtendedState(frm.getExtendedState() | frm.MAXIMIZED_BOTH); //set JFrame maximized
        frm.setVisible(true);



        try {
            myReports.rptBoot();
        } catch (Exception e) {
        }


    }
}
