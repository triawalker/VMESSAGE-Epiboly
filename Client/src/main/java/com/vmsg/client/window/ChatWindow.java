/*
 * Created by JFormDesigner on Sun Aug 30 15:40:52 CST 2020
 */

package com.vmsg.client.window;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author JERRY
 */
public class ChatWindow extends JFrame {
    public ChatWindow() {
        initComponents();
    }

    private void txt_MsgSendKeyReleased(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            btn_send.doClick();
        }else if(e.getKeyCode()==KeyEvent.VK_ENTER){

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        txt_MsgLog = new JTextArea();
        panel4 = new JPanel();
        scrollPane2 = new JScrollPane();
        txt_MsgSend = new JTextArea();
        btn_send = new JButton();
        panel2 = new JPanel();

        //======== this ========
        setTitle("VMessage(v0.0.1)Built by TriAwalker");
        setMinimumSize(new Dimension(500, 350));
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- txt_MsgLog ----
                txt_MsgLog.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                txt_MsgLog.setEditable(false);
                txt_MsgLog.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
                scrollPane1.setViewportView(txt_MsgLog);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());

            //======== scrollPane2 ========
            {

                //---- txt_MsgSend ----
                txt_MsgSend.setRows(3);
                txt_MsgSend.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                txt_MsgSend.setBorder(new MatteBorder(0, 1, 1, 1, Color.black));
                txt_MsgSend.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        txt_MsgSendKeyReleased(e);
                    }
                });
                scrollPane2.setViewportView(txt_MsgSend);
            }
            panel4.add(scrollPane2, BorderLayout.CENTER);

            //---- btn_send ----
            btn_send.setText("\u53d1     \u9001");
            btn_send.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            btn_send.setBackground(SystemColor.control);
            panel4.add(btn_send, BorderLayout.EAST);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
            }
            panel4.add(panel2, BorderLayout.NORTH);
        }
        contentPane.add(panel4, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JScrollPane scrollPane1;
    public JTextArea txt_MsgLog;
    private JPanel panel4;
    private JScrollPane scrollPane2;
    public JTextArea txt_MsgSend;
    public JButton btn_send;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
