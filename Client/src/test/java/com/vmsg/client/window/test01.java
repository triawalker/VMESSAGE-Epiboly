package com.vmsg.client.window;

import com.vmsg.client.thread.LoginEtc;
import org.junit.Test;

public class test01 {

    public test01(){
        connectWindow();
    }
    @Test
    public void testLoginEtc(){
        LoginEtc loginEtc=new LoginEtc();
        //loginEtc.saveinfo("888888", "111111", true);
    }
    @Test
    public void testString2Boolean(){
        String s="true";
        System.out.println(Boolean.parseBoolean(s));
    }
    @Test
    public void connectWindow(){
        ReconnectWindow connectWindow = new ReconnectWindow();
    }
}
