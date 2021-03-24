package com.vmsg.server.thread;

import com.vmsg.Login;
import com.vmsg.SocketData;
import com.vmsg.User;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class LoginThread {

    SocketData socketData;
    ObjectOutputStream output;
    Login login;

    String account;
    String password;
    String ip;
    User user;
    int row;
    int request;

    SQLManage sqlManage=new SQLManage();

    public LoginThread(SocketData socketData, ObjectOutputStream output){
        this.socketData=socketData;
        this.login=socketData.login;
        this.account=this.login.account;
        this.password=this.login.password;
        this.request=this.login.request;
        this.ip=socketData.getWebip();
        this.output=output;
        row=sqlManage.checklogin(this.account, this.password, this.ip, this.output);
    }
    public User getLoginUser(){

        user=sqlManage.selectuser(row);

        try {
            this.output.writeObject(new SocketData(user));
        } catch (IOException e) {
            new ConsoleInfo("输出User信息失败", ConsoleInfo.SERVERERROR);
            e.printStackTrace();
        }

        return user;
    }
}
