package com.vmsg.client.thread;

import com.vmsg.Login;
import com.vmsg.SocketData;

public class LoginThread {
    Login login;
    SocketData socketData;
    public LoginThread(Login login){
        this.login=login;
        socketData=new SocketData(WebIP.getWebip(), login);
        MainThread.connect.SendSocketData(socketData);
    }

}
