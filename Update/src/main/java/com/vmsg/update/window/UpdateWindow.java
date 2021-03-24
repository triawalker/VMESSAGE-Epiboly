/*
 * Created by JFormDesigner on Sun Aug 30 17:52:31 CST 2020
 */

package com.vmsg.update.window;

import javax.swing.border.*;
import com.vmsg.update.thread.MainThread;
import com.vmsg.update.thread.UpdateEtc;
import com.vmsg.update.thread.UpdateEtcThread;
import com.vmsg.update.thread.UpdateThread;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * @author JERRY
 */
public class UpdateWindow extends JFrame {
    public UpdateWindow() {
        initComponents();
    }

    private void btn_beginActionPerformed(ActionEvent e) {
        // TODO add your code here
        UpdateThread updateThread=new UpdateThread();

    }

    private void menu_etc_localActionPerformed(ActionEvent e) {
        // TODO add your code here
        UpdateEtcThread updateEtcThread=new UpdateEtcThread(UpdateEtcThread.ETCFROMLOCAL);
    }

    private void menu_etc_serverActionPerformed(ActionEvent e) {
        // TODO add your code here
        UpdateEtcThread updateEtcThread=new UpdateEtcThread(UpdateEtcThread.ETCFROMSERVER);
    }

    private void menu_etc_alterActionPerformed(ActionEvent e) {
        // TODO add your code here
        UpdateEtcAlterWindow updateEtcAlterWindow=null;
        UpdateEtc updateEtc=new UpdateEtc();
        updateEtc.read_UpadteEtc();
        if (updateEtcAlterWindow==null||!updateEtcAlterWindow.isVisible()){
            updateEtcAlterWindow=new UpdateEtcAlterWindow();
            updateEtcAlterWindow.txt_flp.setText(MainThread.etcLocalFilePath);
            updateEtcAlterWindow.txt_fsp.setText(MainThread.etcServerFilePath);
            updateEtcAlterWindow.txt_host.setText(MainThread.etcHOST);
            updateEtcAlterWindow.txt_port.setText(MainThread.etcPOST+"");
        }
    }

    private void menu_window_logclearActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu_etc = new JMenu();
        menu_etc_local = new JMenuItem();
        menu_etc_server = new JMenuItem();
        menu_etc_alter = new JMenuItem();
        menu_window = new JMenu();
        menu_window_logclear = new JMenuItem();
        panel1 = new JPanel();
        panel2 = new JPanel();
        btn_begin = new JButton();
        panel_info = new JPanel();
        scrollPane1 = new JScrollPane();
        txt_MainInfo = new JTextArea();
        panel3 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        lbl_DownInfo_title = new JLabel();
        lbl_DownInfo_content = new JLabel();
        panel7 = new JPanel();
        lbl_DownInfo_speed = new JLabel();
        panel4 = new JPanel();
        panel8 = new JPanel();
        lbl_DownInfo_ratetitle = new JLabel();
        lbl_DownInfo_ratepic = new JLabel();
        lbl_DownInfo_ratenum = new JLabel();
        panel9 = new JPanel();
        lbl_DownInfo_ratesize = new JLabel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        lbl_UpdateInfo_ratesize = new JLabel();
        panel12 = new JPanel();
        lbl_UpdateInfo_ratetitle = new JLabel();
        lbl_UpdateInfo_ratepic = new JLabel();
        lbl_UpdateInfo_ratenum = new JLabel();

        //======== this ========
        setTitle("VMSG\u66f4\u65b0\u7a0b\u5e8f   built by triawalker");
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setMinimumSize(new Dimension(784, 507));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu_etc ========
            {
                menu_etc.setText("\u914d\u7f6e\u6587\u4ef6");
                menu_etc.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- menu_etc_local ----
                menu_etc_local.setText("\u672c\u5730\u751f\u6210\u914d\u7f6e\u6587\u4ef6");
                menu_etc_local.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                menu_etc_local.addActionListener(e -> menu_etc_localActionPerformed(e));
                menu_etc.add(menu_etc_local);

                //---- menu_etc_server ----
                menu_etc_server.setText("\u8bfb\u53d6\u670d\u52a1\u5668\u66f4\u65b0\u914d\u7f6e\u6587\u4ef6");
                menu_etc_server.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                menu_etc_server.addActionListener(e -> menu_etc_serverActionPerformed(e));
                menu_etc.add(menu_etc_server);

                //---- menu_etc_alter ----
                menu_etc_alter.setText("\u4fee\u6539\u914d\u7f6e\u6587\u4ef6");
                menu_etc_alter.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                menu_etc_alter.addActionListener(e -> menu_etc_alterActionPerformed(e));
                menu_etc.add(menu_etc_alter);
            }
            menuBar1.add(menu_etc);

            //======== menu_window ========
            {
                menu_window.setText("\u7a97\u53e3");
                menu_window.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));

                //---- menu_window_logclear ----
                menu_window_logclear.setText("\u4e3b\u4fe1\u606f\u7a97\u53e3\u6e05\u7a7a");
                menu_window_logclear.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                menu_window_logclear.addActionListener(e -> menu_window_logclearActionPerformed(e));
                menu_window.add(menu_window_logclear);
            }
            menuBar1.add(menu_window);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== panel2 ========
            {
                panel2.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
                panel2.setLayout(new FlowLayout());
                ((FlowLayout)panel2.getLayout()).setAlignOnBaseline(true);

                //---- btn_begin ----
                btn_begin.setText("\u5f00     \u59cb");
                btn_begin.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                btn_begin.setBackground(SystemColor.control);
                btn_begin.addActionListener(e -> btn_beginActionPerformed(e));
                panel2.add(btn_begin);
            }
            panel1.add(panel2, BorderLayout.EAST);

            //======== panel_info ========
            {
                panel_info.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
                panel_info.setLayout(new BorderLayout());

                //======== scrollPane1 ========
                {
                    scrollPane1.setBorder(null);

                    //---- txt_MainInfo ----
                    txt_MainInfo.setEditable(false);
                    scrollPane1.setViewportView(txt_MainInfo);
                }
                panel_info.add(scrollPane1, BorderLayout.CENTER);

                //======== panel3 ========
                {
                    panel3.setBorder(new MatteBorder(1, 0, 0, 0, Color.black));
                    panel3.setLayout(new BorderLayout());

                    //======== panel5 ========
                    {
                        panel5.setLayout(new BorderLayout());

                        //======== panel6 ========
                        {
                            panel6.setLayout(new FlowLayout());

                            //---- lbl_DownInfo_title ----
                            lbl_DownInfo_title.setText("\u6b63\u5728\u4e0b\u8f7d:");
                            lbl_DownInfo_title.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel6.add(lbl_DownInfo_title);

                            //---- lbl_DownInfo_content ----
                            lbl_DownInfo_content.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel6.add(lbl_DownInfo_content);
                        }
                        panel5.add(panel6, BorderLayout.WEST);

                        //======== panel7 ========
                        {
                            panel7.setLayout(new FlowLayout());

                            //---- lbl_DownInfo_speed ----
                            lbl_DownInfo_speed.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel7.add(lbl_DownInfo_speed);
                        }
                        panel5.add(panel7, BorderLayout.EAST);
                    }
                    panel3.add(panel5, BorderLayout.NORTH);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new BorderLayout());

                        //======== panel8 ========
                        {
                            panel8.setLayout(new FlowLayout(FlowLayout.LEFT));

                            //---- lbl_DownInfo_ratetitle ----
                            lbl_DownInfo_ratetitle.setText("\u8fdb\u5ea6:");
                            lbl_DownInfo_ratetitle.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel8.add(lbl_DownInfo_ratetitle);

                            //---- lbl_DownInfo_ratepic ----
                            lbl_DownInfo_ratepic.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel8.add(lbl_DownInfo_ratepic);

                            //---- lbl_DownInfo_ratenum ----
                            lbl_DownInfo_ratenum.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel8.add(lbl_DownInfo_ratenum);
                        }
                        panel4.add(panel8, BorderLayout.CENTER);

                        //======== panel9 ========
                        {
                            panel9.setLayout(new BorderLayout());

                            //---- lbl_DownInfo_ratesize ----
                            lbl_DownInfo_ratesize.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                            panel9.add(lbl_DownInfo_ratesize, BorderLayout.CENTER);
                        }
                        panel4.add(panel9, BorderLayout.EAST);
                    }
                    panel3.add(panel4, BorderLayout.CENTER);
                }
                panel_info.add(panel3, BorderLayout.SOUTH);

                //======== panel10 ========
                {
                    panel10.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
                    panel10.setLayout(new BorderLayout());

                    //======== panel11 ========
                    {
                        panel11.setLayout(new FlowLayout(FlowLayout.RIGHT));

                        //---- lbl_UpdateInfo_ratesize ----
                        lbl_UpdateInfo_ratesize.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        panel11.add(lbl_UpdateInfo_ratesize);
                    }
                    panel10.add(panel11, BorderLayout.EAST);

                    //======== panel12 ========
                    {
                        panel12.setLayout(new FlowLayout(FlowLayout.LEFT));

                        //---- lbl_UpdateInfo_ratetitle ----
                        lbl_UpdateInfo_ratetitle.setText("\u603b\u8fdb\u5ea6:");
                        lbl_UpdateInfo_ratetitle.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        panel12.add(lbl_UpdateInfo_ratetitle);

                        //---- lbl_UpdateInfo_ratepic ----
                        lbl_UpdateInfo_ratepic.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        panel12.add(lbl_UpdateInfo_ratepic);

                        //---- lbl_UpdateInfo_ratenum ----
                        lbl_UpdateInfo_ratenum.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        panel12.add(lbl_UpdateInfo_ratenum);
                    }
                    panel10.add(panel12, BorderLayout.WEST);
                }
                panel_info.add(panel10, BorderLayout.NORTH);
            }
            panel1.add(panel_info, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu_etc;
    private JMenuItem menu_etc_local;
    private JMenuItem menu_etc_server;
    private JMenuItem menu_etc_alter;
    private JMenu menu_window;
    private JMenuItem menu_window_logclear;
    private JPanel panel1;
    private JPanel panel2;
    private JButton btn_begin;
    private JPanel panel_info;
    private JScrollPane scrollPane1;
    public static JTextArea txt_MainInfo;
    private JPanel panel3;
    private JPanel panel5;
    private JPanel panel6;
    private JLabel lbl_DownInfo_title;
    public static JLabel lbl_DownInfo_content;
    private JPanel panel7;
    public static JLabel lbl_DownInfo_speed;
    private JPanel panel4;
    private JPanel panel8;
    public static JLabel lbl_DownInfo_ratetitle;
    public static JLabel lbl_DownInfo_ratepic;
    public static JLabel lbl_DownInfo_ratenum;
    private JPanel panel9;
    public static JLabel lbl_DownInfo_ratesize;
    private JPanel panel10;
    private JPanel panel11;
    public static JLabel lbl_UpdateInfo_ratesize;
    private JPanel panel12;
    private JLabel lbl_UpdateInfo_ratetitle;
    public static JLabel lbl_UpdateInfo_ratepic;
    public static JLabel lbl_UpdateInfo_ratenum;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
