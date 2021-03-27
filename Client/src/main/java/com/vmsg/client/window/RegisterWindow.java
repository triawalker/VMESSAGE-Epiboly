/*
 * Created by JFormDesigner on Wed Sep 02 12:25:31 CST 2020
 */

package com.vmsg.client.window;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.vmsg.PhoneCode;
import com.vmsg.Register;
import com.vmsg.SocketData;
import com.vmsg.client.thread.MainThread;
import com.vmsg.client.thread.RegisterThread;
import com.vmsg.client.thread.WebIP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author JERRY
 */
public class RegisterWindow extends JFrame {
    public RegisterWindow() {
        initComponents();
    }

    private void btn_regActionPerformed() {
        // TODO add your code here
        StringBuffer sb=new StringBuffer();
        Boolean flag=true;
        Register register;
        if(txt_name.getText().length()<1){
            sb=new StringBuffer("注册失败:");
            sb.append("\n>昵称长度小于1");
            flag=false;
        }
        if (txt_password.getText().length()<6){
            if (flag) sb=new StringBuffer("注册失败:");
            sb.append("\n>密码长度小于6");
            flag=false;
        }
        if(txt_account.getText().length()<8){
            if (flag) sb=new StringBuffer("注册失败:");
            sb.append("\n>账号长度小于8");
            flag=false;
        }
        if(!txt_password2.getText().equals(txt_password.getText())){
            if (flag) sb=new StringBuffer("注册失败:");
            sb.append("\n>两次输入密码不相同");
            flag=false;
        }

        if(flag){
            register=new Register(txt_name.getText(),txt_account.getText(),txt_password.getText(),txt_phone.getText(),txt_Code.getText(),false);
            new RegisterThread(register);
        }else{
            new RegisterThread(sb);
        }


    }

    private void txt_nameKeyTyped(KeyEvent e) {
        // TODO add your code here
        if(txt_name.getText().toString().length()>=10){
            e.consume();
            lbl_reginfo.setText("*昵称长度不得超过10,且长度至少为1");
        }
    }

    private void txt_accountKeyTyped(KeyEvent e) {
        // TODO add your code here
        if(txt_account.getText().length()>=30){
            e.consume();
            lbl_reginfo.setText("*账号长度不得超过30,且长度至少为8");
        }else{
            String s= String.valueOf(e.getKeyChar());
            byte[] bytes=s.getBytes();
            for(byte b: bytes){
                if(b>=(byte)0x81&&b<=(byte)0xfe){
                    e.consume();
                    lbl_reginfo.setText("*账号不得包含中文字符");
                }
            }
        }
    }

    private void txt_passwordKeyTyped(KeyEvent e) {
        // TODO add your code here
        if(txt_password.getText().length()>=30){
            e.consume();
            lbl_reginfo.setText("*密码长度不得超过30,且长度至少为6");
        }else{
            String s= String.valueOf(e.getKeyChar());
            byte[] bytes=s.getBytes();
            for(byte b: bytes){
                if(b>=(byte)0x81&&b<=(byte)0xfe){
                    e.consume();
                    lbl_reginfo.setText("*密码不得包含中文字符");
                }
            }
        }
        if(!txt_password.getText().equals(txt_password2.getText())){
            lbl_reginfo.setText("*输入两次密码不相同");
        }else{
            lbl_reginfo.setText("");
        }
    }

    private void txt_passwordKeyReleased(KeyEvent e) {
        // TODO add your code here
        if(txt_password.getText().length()>=30){
            e.consume();
            lbl_reginfo.setText("*密码长度不得超过30,且长度至少为6");
        }else{
            String s= String.valueOf(e.getKeyChar());
            byte[] bytes=s.getBytes();
            for(byte b: bytes){
                if(b>=(byte)0x81&&b<=(byte)0xfe){
                    e.consume();
                    lbl_reginfo.setText("*密码不得包含中文字符");
                }
            }
        }
        if(!txt_password.getText().equals(txt_password2.getText())){
            lbl_reginfo.setText("*输入两次密码不相同");
        }else{
            lbl_reginfo.setText("");
        }
    }

    private void thisKeyReleased(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            btn_reg.doClick();
        }
    }

    private void btn_sendCodeActionPerformed(ActionEvent e) {
        // TODO add your code here
        PhoneCode phoneCode = new PhoneCode(txt_phone.getText());
        SocketData socketData = new SocketData(WebIP.getWebip(), phoneCode);
        MainThread.connect.SendSocketData(socketData);
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        txt_name = new JTextField();
        label2 = new JLabel();
        txt_account = new JTextField();
        label3 = new JLabel();
        txt_password = new JPasswordField();
        label4 = new JLabel();
        txt_password2 = new JPasswordField();
        label5 = new JLabel();
        panel3 = new JPanel();
        txt_phone = new JTextField();
        btn_sendCode = new JButton();
        label6 = new JLabel();
        txt_Code = new JTextField();
        lbl_reginfo = new JLabel();
        panel2 = new JPanel();
        btn_reg = new JButton();

        //======== this ========
        setTitle("VMSG\u8d26\u53f7\u6ce8\u518c");
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(445, 340));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                thisKeyReleased(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "right:default:grow(0.05), $rgap, default:grow(0.9), $rgap, default:grow(0.05)",
                "8*(default, $lgap), default"));

            //---- label1 ----
            label1.setText("\u6635\u79f0\uff1a");
            label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label1, CC.xy(1, 3));

            //---- txt_name ----
            txt_name.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            txt_name.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    txt_nameKeyTyped(e);
                }
            });
            panel1.add(txt_name, CC.xy(3, 3));

            //---- label2 ----
            label2.setText("\u8d26\u53f7\uff1a");
            label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label2, CC.xy(1, 5));

            //---- txt_account ----
            txt_account.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            txt_account.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    txt_accountKeyTyped(e);
                }
            });
            panel1.add(txt_account, CC.xy(3, 5));

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");
            label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label3, CC.xy(1, 7));

            //---- txt_password ----
            txt_password.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            txt_password.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txt_passwordKeyReleased(e);
                }
            });
            panel1.add(txt_password, CC.xy(3, 7));

            //---- label4 ----
            label4.setText("\u91cd\u590d\u5bc6\u7801\uff1a");
            label4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label4, CC.xy(1, 9));

            //---- txt_password2 ----
            txt_password2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            txt_password2.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txt_passwordKeyReleased(e);
                }
            });
            panel1.add(txt_password2, CC.xy(3, 9));

            //---- label5 ----
            label5.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
            label5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label5, CC.xy(1, 11));

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout());

                //---- txt_phone ----
                txt_phone.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                panel3.add(txt_phone, BorderLayout.CENTER);

                //---- btn_sendCode ----
                btn_sendCode.setText("\u53d1\u9001\u9a8c\u8bc1\u7801");
                btn_sendCode.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
                btn_sendCode.addActionListener(e -> btn_sendCodeActionPerformed(e));
                panel3.add(btn_sendCode, BorderLayout.EAST);
            }
            panel1.add(panel3, CC.xy(3, 11));

            //---- label6 ----
            label6.setText("\u9a8c\u8bc1\u7801\uff1a");
            label6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(label6, CC.xy(1, 13));

            //---- txt_Code ----
            txt_Code.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(txt_Code, CC.xy(3, 13));

            //---- lbl_reginfo ----
            lbl_reginfo.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
            panel1.add(lbl_reginfo, CC.xy(3, 15));

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- btn_reg ----
                btn_reg.setText("\u6ce8\u518c");
                btn_reg.addActionListener(e -> btn_regActionPerformed());
                panel2.add(btn_reg);
            }
            panel1.add(panel2, CC.xy(3, 17));
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField txt_name;
    private JLabel label2;
    private JTextField txt_account;
    private JLabel label3;
    private JPasswordField txt_password;
    private JLabel label4;
    private JPasswordField txt_password2;
    private JLabel label5;
    private JPanel panel3;
    private JTextField txt_phone;
    private JButton btn_sendCode;
    private JLabel label6;
    private JTextField txt_Code;
    private JLabel lbl_reginfo;
    private JPanel panel2;
    private JButton btn_reg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
