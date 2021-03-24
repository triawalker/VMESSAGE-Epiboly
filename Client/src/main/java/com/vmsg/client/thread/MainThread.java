package com.vmsg.client.thread;

import com.vmsg.Login;
import com.vmsg.User;
import com.vmsg.client.data.UserLogin;
import com.vmsg.client.window.ReconnectWindow;
import com.vmsg.client.window.LoginWindow;
import com.vmsg.client.window.RegisterWindow;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainThread {

    public static final String filesep=System.getProperty("file.separator");
    public static final String LoginEtcPath="."+filesep+"etc"+filesep+"login"+filesep+"UserLogin.xml";

    public static final String SERVERHOST="ssw.xzzpig.com";
    public static String DEFAULTHOST;
    public static int DEAFULTMSGPORT;

    //public static final String DEFAULTHOST="192.168.1.123";
    //public static final int DEAFULTMSGPORT=19000;

    public static ConnectsThread connectsThread;
    public static Connect connect;
    static ChatsThread chatsThread;
    public static LoginWindow loginWindow;
    public static RegisterWindow registerWindow;
    public static ReconnectWindow ReconnectWindow = new ReconnectWindow();
    public static LoginEtc loginEtc;
    public static LoginThread loginThread;


    public static User user;

    public static List<UserLogin> UserLoginlist=new ArrayList<>();
    public static String[] AccountList;
    public static ComboBoxModel cbm;

    public MainThread() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        initConnect(0);// 参数为0s是本地服务器 参数为1是远程服务器 ！！！！注意修改为0
        start();
    }
    public static void start(){

        connectsThread = new ConnectsThread(10, 30, 30, TimeUnit.MINUTES, 10);
        connect = connectsThread.addConnect(DEFAULTHOST, DEAFULTMSGPORT);
        //登录窗口初始化
        initLogin();
    }

    public static void initConnect(int i){
        if(i==0) {//连接本地服务器
            DEFAULTHOST="127.0.0.1";
            DEAFULTMSGPORT=19000;
        }else if(i==1){//连接SSW服务器
            try {//解析域名
                InetAddress[] addresses = InetAddress.getAllByName(SERVERHOST);
                addresses = InetAddress.getAllByName(SERVERHOST);
                for (InetAddress addr : addresses) {
                    DEFAULTHOST=addr.getHostAddress();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DEAFULTMSGPORT=8026;
        }
    }


    public static void initLogin(){
        loginWindow = new LoginWindow();
        loginEtc = new LoginEtc();
        UserLoginlist = loginEtc.readinfo();
        AccountList=new String[UserLoginlist.size()];
        for (UserLogin ul: UserLoginlist){
            AccountList[ul.num]=ul.account;
        }
        cbm=new DefaultComboBoxModel(AccountList);
        loginWindow.combox_account.setModel(cbm);

        if(cbm.getSize()>0) {
            loginWindow.txt_password.setText(MainThread.UserLoginlist.get(loginWindow.combox_account.getSelectedIndex()).password);
            loginWindow.ckbox_savepwd.setSelected(MainThread.UserLoginlist.get(loginWindow.combox_account.getSelectedIndex()).password.length() > 0 ? true : false);
            loginWindow.ckbox_autologin.setSelected(MainThread.UserLoginlist.get(loginWindow.combox_account.getSelectedIndex()).autologin);
        }

        for (UserLogin ul: UserLoginlist){
            if (ul.autologin){
                Login login=new Login(ul.account, ul.password,Login.NOT_LOGIN);
                loginThread=new LoginThread(login);
            }
        }
    }
}

