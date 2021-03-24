package com.vmsg.client.window;

import com.vmsg.client.thread.ConnectsThread;
import com.vmsg.client.thread.MainThread;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class MainThreadtest {

    public static final String SERVERHOST="ssw.xzzpig.com";
    public static String DEFAULTHOST;
    public static int DEAFULTMSGPORT;


    @Test
    public void testlocal(){
        DEFAULTHOST="127.0.0.1";
        DEAFULTMSGPORT=19000;
        MainThread.connectsThread=new ConnectsThread(10, 30, 30, TimeUnit.MINUTES,10);
        MainThread.connect=MainThread.connectsThread.addConnect(DEFAULTHOST, DEAFULTMSGPORT);
        MainThread.loginWindow=new LoginWindow();
    }
    @Test
    public void testserver(){
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
        MainThread.connectsThread=new ConnectsThread(10, 30, 30, TimeUnit.MINUTES,10);
        MainThread.connect= MainThread.connectsThread.addConnect(DEFAULTHOST, DEAFULTMSGPORT);
        MainThread.loginWindow=new LoginWindow();
    }
}
