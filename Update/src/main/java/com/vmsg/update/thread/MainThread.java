package com.vmsg.update.thread;

import com.vmsg.update.window.UpdateWindow;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainThread {
    public static UpdateWindow updateWindow;
    public static final String SERVERHOST="ssw.xzzpig.com";
    public static final String USERNAME="UpdateClient";
    public static final String PASSWORD="123456";
    public static final String filesep=System.getProperty("file.separator");
    public static final String ServerEtcPath="/home/UpdateClient/ClientEtc/Update.properties";
    public static final String LocalEtcPath="."+filesep+"etc"+filesep+"Update.properties";
    public static final String ServerFilePath="/home/UpdateClient/VMsg_Client";
    public static final String	LocalFilePath="."+filesep+"VMsg";
    public static String DEFAULTHOST;
    public static final int DEFAULTPOST=1426;

    public static String etcLocalFilePath="";
    public static int etcPOST;
    public static String etcServerFilePath="";
    public static String etcHOST="";

    public static void main(String[] args) {
        try {//解析域名
            InetAddress[] addresses = InetAddress.getAllByName(SERVERHOST);
            addresses = InetAddress.getAllByName(SERVERHOST);
            for (InetAddress addr : addresses) {
                DEFAULTHOST=addr.getHostAddress();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        start();
    }
    public static void start(){
       updateWindow=new UpdateWindow();
    }



}
