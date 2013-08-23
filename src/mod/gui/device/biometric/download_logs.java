package mod.gui.device.biometric;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import mod.connection.myDBConn;
import mod.model.controller.BiometricC;
import mod.others.myFunction;

public class download_logs extends javax.swing.JInternalFrame {

    ActionListener taskperformer;
    Timer tmr, tmr2, tmr3;
    BiometricC devicec = new BiometricC();
    static Statement stmt;

    public download_logs() {
        initComponents();
        cmdrefresh.setEnabled(false);
        cmddownload.setEnabled(false);
        cmdcancel.setVisible(false);
        setTableModels();
        setPinging();
        lblcap.setText("Please wait while the program is pinging all the devices!");
        MakeLoading();
    }

    void MakeNormal() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        tbl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    void MakeLoading() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tbl.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    void testPing() {
        int r = tbl.getRowCount();
        int c;
        c = 0;

        while (c < r + 1) {
            try {
                String did = tbl.getValueAt(c, 0).toString();
                String ipadd = devicec.GetIpAddress(Integer.parseInt(did));

                boolean pip = myFunction.pingIP(ipadd);

                if (pip == true) {

                    String success = ""
                            + "<html><table border=0 cellpadding=0 cellspacing=3>"
                            + "<tr><td></td><td font color=#59DF00><b>Success</b></font></tr>"
                            + "</table>"
                            + "</html>";

                    tbl.setValueAt(success, c, 3);
                } else if (pip == false) {
                    String failed = ""
                            + "<html><table border=0 cellpadding=0 cellspacing=3>"
                            + "<tr><td></td><td font color=#FF0000><b>Failed</b></font></tr>"
                            + "</table>"
                            + "</html>";
                    tbl.setValueAt(failed, c, 3);
                }
                //System.out.println(ipadd);

            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
    }

    void resetSelection() {
        int r = tbl.getRowCount();
        int c;
        c = 0;

        while (c < r + 1) {
            try {
                tbl.setValueAt(false, c, 1);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
    }

    int countPlus() {
        int r = tbl.getRowCount();
        int c;
        c = 0;

        int cntr = 0;

        while (c < r + 1) {
            try {
                String stat = tbl.getValueAt(c, 1).toString();

                if ("true".equals(stat)) {
                    cntr++;
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
        return cntr;
    }

    void setPinging() {
        int r = tbl.getRowCount();
        int c;
        c = 0;

        while (c < r + 1) {
            try {
                String pinging = ""
                        + "<html><table border=0 cellpadding=0 cellspacing=3>"
                        + "<tr><td></td><td font color=blue><b>Pinging...</b></font></tr>"
                        + "</table>"
                        + "</html>";
                tbl.setValueAt(pinging, c, 3);
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;
        }
    }

    void setTableModels() {
        tbl.setModel(new BiometricC().getBiometricTableModel(tbl));
    }

    void go_motion() {
        taskperformer = new ActionListener() {

            int x = 1;

            public void actionPerformed(ActionEvent evt) {
                lbl.setIcon(new ImageIcon(getClass().getResource("/images/dloads/" + x + ".png")));
                lbl.revalidate();
                x++;
                if (x == 9) {
                    x = x - 8;
                }

            }
        };

        tmr = new Timer(500, taskperformer);
        tmr.start();
    }

    void load() {
        taskperformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                testPing();
                tmr2.stop();
                cmdrefresh.setEnabled(true);
                cmddownload.setEnabled(true);

                MakeNormal();
                lblcap.setText("");
            }
        };

        tmr2 = new Timer(100, taskperformer);
        tmr2.start();
    }

    void execute_download() {
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("biometric/GetLog.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
        download();
    }

    void download() {
        taskperformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                boolean flg = getDownloadStatus();
                Scanner scanSTR;
                        try {
                            scanSTR = new Scanner(new File("biometric/logs.txt"));
                            console.setText("");
                            while (scanSTR.hasNextLine()) {
                                String Str = scanSTR.nextLine();
                                console.append(Str + "\n");
                            }
                        } catch (FileNotFoundException ex) {
                            ex.getStackTrace();
                        }
                if (flg == true) {
                    cmdcancel.setEnabled(false);
                    cmddownload.setEnabled(true);
                    tmr.stop();
                    lbl.setIcon(new ImageIcon(getClass().getResource("/images/dloads/1.png")));
                    resetSelection();
                    tmr3.stop();
                    MakeNormal();
                    JOptionPane.showMessageDialog(null, "Download Done!");
                }
            }
        };

        tmr3 = new Timer(100, taskperformer);
        tmr3.start();
    }

    boolean getDownloadStatus() {
        Boolean isFinish = false;
        int flg = 0;
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "SELECT flag FROM download_indicator WHERE indicator_id=1";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);
            while (rs.next()) {
                flg = rs.getInt("flag");
            }


            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Populating List!");
        }

        if (flg == 1) {
            isFinish = false;
        } else if (flg == 0) {
            isFinish = true;
        }

        return isFinish;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdrefresh = new javax.swing.JButton();
        cmddownload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        lbl = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmdexit = new javax.swing.JButton();
        lblcap = new javax.swing.JLabel();
        cmdcancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Download Attendance Time Logs");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "", "Biometric Device", "Ping Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(0).setResizable(false);
        tbl.getColumnModel().getColumn(2).setResizable(false);

        cmdrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        cmdrefresh.setText("Refresh Ping Status");
        cmdrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdrefreshActionPerformed(evt);
            }
        });

        cmddownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download2.png"))); // NOI18N
        cmddownload.setText("Download Now");
        cmddownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmddownloadActionPerformed(evt);
            }
        });

        jLabel1.setText("Select a biometric device you want to download the time logs.");

        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setColumns(20);
        console.setForeground(new java.awt.Color(255, 255, 255));
        console.setRows(5);
        jScrollPane2.setViewportView(console);

        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dloads/1.png"))); // NOI18N

        jLabel3.setText("Download Status Console");

        cmdexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cmdexit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        lblcap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcap.setForeground(new java.awt.Color(0, 0, 153));
        lblcap.setText("jLabel2");

        cmdcancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcap, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdrefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmddownload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdexit, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdrefresh)
                    .addComponent(cmddownload)
                    .addComponent(cmdexit)
                    .addComponent(cmdcancel))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmddownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmddownloadActionPerformed
        int ci = countPlus();

        if (ci == 0) {
            JOptionPane.showMessageDialog(null, "No Selected Biometric Device!");
        } else {
            go_motion();
            UpdateDownloadFlag(1);
            cmdcancel.setEnabled(true);
            cmddownload.setEnabled(false);
            UpdateDownloadFlag(1);
            MakeLoading();
            execute_download();
        }
    }//GEN-LAST:event_cmddownloadActionPerformed

    public static void UpdateDownloadFlag(int flg) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "UPDATE download_indicator "
                + " SET flag=" + flg;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefreshActionPerformed
        cmdrefresh.setEnabled(false);
        cmddownload.setEnabled(false);
        cmdcancel.setEnabled(false);
        lblcap.setText("Please wait while the program is pinging all the devices!");
        MakeLoading();
        setPinging();
        load();
    }//GEN-LAST:event_cmdrefreshActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        //try {
        // Thread.sleep(5000);
        load();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(download_logs.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_formInternalFrameOpened

    private void tblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseReleased
        checkstat();

    }//GEN-LAST:event_tblMouseReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
    }//GEN-LAST:event_tblMouseClicked

    private void tblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMousePressed
    }//GEN-LAST:event_tblMousePressed

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        cmdcancel.setEnabled(false);
        cmddownload.setEnabled(true);
        tmr.stop();
        lbl.setIcon(new ImageIcon(getClass().getResource("/images/dloads/1.png")));
        resetSelection();
    }//GEN-LAST:event_cmdcancelActionPerformed

    void checkstat() {
        int col = 3; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value
        String stat = tbl.getValueAt(row, col).toString();

        String failed = ""
                + "<html><table border=0 cellpadding=0 cellspacing=3>"
                + "<tr><td></td><td font color=#FF0000><b>Failed</b></font></tr>"
                + "</table>"
                + "</html>";

        if (failed.equals(stat)) {
            tbl.setValueAt(false, row, 1);
            JOptionPane.showMessageDialog(null, "The device is unreachable! ");
        } else {
            RefreshStats();
        }

        //JOptionPane.showMessageDialog(null, stat);
    }

    public void RefreshStats() {
        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        String did = tbl.getValueAt(row, col).toString();
        String stat = tbl.getValueAt(row, 1).toString();

        //JOptionPane.showMessageDialog(this, stat);

        int intstat = 1;

        if ("true".equals(stat)) {
            intstat = 1;
        } else if ("false".equals(stat)) {
            intstat = 0;
        }

        UpdateFlag(intstat, Integer.parseInt(did));
    }

    public static void UpdateFlag(int stat, int did) {
        Connection conn = myDBConn.getConnection();
        String createString;
        createString = "UPDATE biometric "
                + " SET download_flag=" + stat + " WHERE device_id=" + did;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton cmddownload;
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdrefresh;
    private javax.swing.JTextArea console;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblcap;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
