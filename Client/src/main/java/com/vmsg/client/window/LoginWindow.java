/*
 * Created by JFormDesigner on Wed Sep 02 12:25:17 CST 2020
 */

package com.vmsg.client.window;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.vmsg.Login;
import com.vmsg.client.data.UserLogin;
import com.vmsg.client.thread.LoginThread;
import com.vmsg.client.thread.MainThread;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;

/**
 * @author JERRY
 */
public class LoginWindow extends JFrame {

    public LoginWindow() {
        initComponents();
    }

    private void btn_loginActionPerformed(ActionEvent e) {
        // TODO add your code here
        Login login=new Login(combox_account.getSelectedItem().toString(), txt_password.getText(),Login.NOT_LOGIN);
        LoginThread loginThread=new LoginThread(login);
    }

    private void btn_registerActionPerformed(ActionEvent e) {
        // TODO add your code here
        MainThread.registerWindow=new RegisterWindow();
    }

    private void checkBox1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void checkBox2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void txt_passwordKeyTyped(KeyEvent e) {
        // TODO add your code here

        if(txt_password.getText().length()>=30){
            e.consume();
        }else{
            String s= String.valueOf(e.getKeyChar());
            byte[] bytes=s.getBytes();
            for(byte b: bytes){
                if(b>=(byte)0x81&&b<=(byte)0xfe)
                    e.consume();
            }
        }
    }

    private void combox_accountItemStateChanged(ItemEvent e) {
        // TODO add your code here

        String EditerStr=combox_account.getSelectedItem().toString();
        Boolean flag=false;
        for (UserLogin ul:MainThread.UserLoginlist){
            if (ul.account.equals(EditerStr)){
                flag=true;
                break;
            }else{
                txt_password.setText("");
                ckbox_savepwd.setSelected(false);
                ckbox_autologin.setSelected(false);
            }
        }
        if (flag) {
            txt_password.setText(MainThread.UserLoginlist.get(combox_account.getSelectedIndex()).password);
            ckbox_savepwd.setSelected(MainThread.UserLoginlist.get(combox_account.getSelectedIndex()).password.length() > 0 ? true : false);
            ckbox_autologin.setSelected(MainThread.UserLoginlist.get(combox_account.getSelectedIndex()).autologin);
        }
    }

    private void ckbox_autologinStateChanged(ChangeEvent e) {
        // TODO add your code here
        ckbox_savepwd.setSelected(ckbox_autologin.isSelected()?true:ckbox_savepwd.isSelected());
    }

    private void thisKeyReleased(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            btn_login.doClick();
        }
    }








    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        combox_account = new JComboBox();
        label3 = new JLabel();
        txt_password = new JPasswordField();
        panel1 = new JPanel();
        ckbox_savepwd = new JCheckBox();
        ckbox_autologin = new JCheckBox();
        panel8 = new JPanel();
        panel9 = new JPanel();
        btn_login = new JButton();
        btn_register = new JButton();

        //======== this ========
        setTitle("VMSG\u767b\u5f55\u7cfb\u7edf");
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setVisible(true);
        setMinimumSize(new Dimension(486, 327));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                thisKeyReleased(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout(3, 0));

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout());
            }
            panel3.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new BorderLayout());

                //======== panel2 ========
                {
                    panel2.setLayout(new FormLayout(
                        "default:grow(0.1), right:default:grow(0.05), default:grow(0.85), default:grow(0.1)",
                        "17dlu:grow(0.4), $lgap, default:grow(0.4), $lgap, default:grow(0.2)"));

                    //---- label2 ----
                    label2.setText("\u8d26\u53f7\uff1a");
                    label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    panel2.add(label2, CC.xy(2, 1));

                    //---- combox_account ----
                    combox_account.setEditable(true);
                    combox_account.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    combox_account.addItemListener(e -> combox_accountItemStateChanged(e));
                    panel2.add(combox_account, CC.xy(3, 1));

                    //---- label3 ----
                    label3.setText("\u5bc6\u7801\uff1a");
                    label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    panel2.add(label3, CC.xy(2, 3));

                    //---- txt_password ----
                    txt_password.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            txt_passwordKeyTyped(e);
                        }
                    });
                    panel2.add(txt_password, CC.xy(3, 3));

                    //======== panel1 ========
                    {
                        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

                        //---- ckbox_savepwd ----
                        ckbox_savepwd.setText("\u8bb0\u4f4f\u5bc6\u7801");
                        ckbox_savepwd.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        ckbox_savepwd.addActionListener(e -> checkBox1ActionPerformed(e));
                        panel1.add(ckbox_savepwd);

                        //---- ckbox_autologin ----
                        ckbox_autologin.setText("\u81ea\u52a8\u767b\u5f55");
                        ckbox_autologin.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                        ckbox_autologin.addChangeListener(e -> ckbox_autologinStateChanged(e));
                        panel1.add(ckbox_autologin);
                    }
                    panel2.add(panel1, CC.xy(3, 5));
                }
                panel5.add(panel2, BorderLayout.CENTER);
            }
            panel3.add(panel5);

            //======== panel8 ========
            {
                panel8.setLayout(new BorderLayout());

                //======== panel9 ========
                {
                    panel9.setLayout(new FormLayout(
                        "center:default:grow(0.2), default:grow(0.7), center:default:grow(0.1)",
                        "2*(default, $lgap), default"));

                    //---- btn_login ----
                    btn_login.setText("\u767b\u5f55");
                    btn_login.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    btn_login.addActionListener(e -> btn_loginActionPerformed(e));
                    panel9.add(btn_login, CC.xy(2, 1));

                    //---- btn_register ----
                    btn_register.setText("\u6ce8\u518c");
                    btn_register.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                    btn_register.addActionListener(e -> btn_registerActionPerformed(e));
                    panel9.add(btn_register, CC.xy(2, 3));
                }
                panel8.add(panel9, BorderLayout.CENTER);
            }
            panel3.add(panel8);
        }
        contentPane.add(panel3, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel2;
    private JLabel label2;
    public JComboBox combox_account;
    private JLabel label3;
    public JPasswordField txt_password;
    private JPanel panel1;
    public JCheckBox ckbox_savepwd;
    public JCheckBox ckbox_autologin;
    private JPanel panel8;
    private JPanel panel9;
    public JButton btn_login;
    public JButton btn_register;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
