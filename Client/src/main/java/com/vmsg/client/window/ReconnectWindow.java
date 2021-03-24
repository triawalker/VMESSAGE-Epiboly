/*
 * Created by JFormDesigner on Sat Mar 06 16:07:15 CST 2021
 */

package com.vmsg.client.window;

import com.vmsg.client.thread.MainThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author JERRY
 */
public class ReconnectWindow extends JFrame {
    public ReconnectWindow() {
        initComponents();
    }

    private void btn_connectActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (!MainThread.connect.status){
            MainThread.connect = MainThread.connectsThread.addConnect(MainThread.DEFAULTHOST, MainThread.DEAFULTMSGPORT);
        }
        if (MainThread.connect.status){
            lbl_info.setText("  与服务器连接成功,请关闭!");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        btn_connect = new JButton();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        lbl_info = new JLabel();

        //======== this ========
        setTitle("VMSG\u8fde\u63a5\u670d\u52a1\u5668");
        setMinimumSize(new Dimension(414, 115));
        setAlwaysOnTop(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- btn_connect ----
            btn_connect.setText("\u91cd\u8fde");
            btn_connect.addActionListener(e -> btn_connectActionPerformed(e));
            panel1.add(btn_connect);
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());
        }
        contentPane.add(panel2, BorderLayout.WEST);

        //======== panel3 ========
        {
            panel3.setLayout(new BorderLayout());
        }
        contentPane.add(panel3, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setLayout(new GridLayout());

            //---- lbl_info ----
            lbl_info.setText("   \u73b0\u4e0e\u670d\u52a1\u5668\u65ad\u5f00\u8fde\u63a5\u3002");
            lbl_info.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel4.add(lbl_info);
        }
        contentPane.add(panel4, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton btn_connect;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel lbl_info;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
