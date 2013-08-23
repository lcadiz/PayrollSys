package mod.others;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import mod.connection.myDBConn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class myReports {

    public static void rptBoot() throws FileNotFoundException, IOException {
        try {
            JasperReport jasperReport;
            JasperPrint jPrint;

            //parameters
            HashMap parameters = new HashMap();


            jasperReport = JasperCompileManager.compileReport("rpt/rptboot.jrxml");

            jPrint = JasperFillManager.fillReport(jasperReport, parameters, myDBConn.getConnection());

            JasperViewer Viewer = new JasperViewer(jPrint, false);
            Viewer.setExtendedState(Viewer.getExtendedState() | Viewer.MAXIMIZED_BOTH);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;

        }
    }

    public static void rptLeaveApp(String leave_type, int id, String dept, String shift, String dm, String gm, double credits, double bal) throws FileNotFoundException, IOException {
        try {
            JasperReport jasperReport;
            JasperPrint jPrint;

            //parameters
            HashMap parameters = new HashMap();
            parameters.put("leavetype", leave_type);
            parameters.put("id", id);
            parameters.put("dept", dept);
            parameters.put("shift", shift);
            parameters.put("dm", dm);
            parameters.put("gm", gm);
            parameters.put("credits", credits);
            parameters.put("bal", bal);

            jasperReport = JasperCompileManager.compileReport("rpt/rptLeaveApp.jrxml");

            jPrint = JasperFillManager.fillReport(jasperReport, parameters, myDBConn.getConnection());

            JasperViewer Viewer = new JasperViewer(jPrint, false);
            //JasperExportManager.exportReportToPdf(jPrint);
            Viewer.setTitle("Leave Application Report");
            Viewer.setExtendedState(Viewer.getExtendedState() | Viewer.MAXIMIZED_BOTH);
            Viewer.setVisible(true);
//            OutputStream output = new FileOutputStream(new File("rpt/rpt0001.pdf"));
//            JasperExportManager.exportReportToPdfStream(jPrint, output);
//
//            output.flush();
//            output.close();
//
//            try {
//                File pdfFile = new File("rpt/rpt0001.pdf");
//                if (pdfFile.exists()) {
//
//                    if (Desktop.isDesktopSupported()) {
//                        Desktop.getDesktop().open(pdfFile);
//                    } else {
//                    }
//                } else {
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;

        }

    }//
}
