package com.vmsg.server.thread;

import com.vmsg.Register;
import com.vmsg.SocketData;

import java.io.ObjectOutputStream;
import java.sql.Date;


public class RegisterThread {
    SocketData socketData;
    Register register;

    String account;
    String password;
    String name;
    String ip;
    Date registerdate;

    ObjectOutputStream output;

    SQLManage sqlManage=new SQLManage();

    public RegisterThread(SocketData socketData, ObjectOutputStream output){
        this.socketData=socketData;
        this.register=socketData.register;
        this.account=socketData.register.account;
        this.password=socketData.register.password;
        this.name=socketData.register.name;
        this.ip=socketData.getWebip();
        this.registerdate=new Date(new java.util.Date().getTime());
        this.output=output;
        sqlManage.insert2user(this.account, this.password, this.name,this.ip, this.registerdate,output);
    }

}
