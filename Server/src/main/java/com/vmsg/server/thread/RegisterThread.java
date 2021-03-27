package com.vmsg.server.thread;

import com.vmsg.Register;
import com.vmsg.SocketData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;


public class RegisterThread {
    SocketData socketData;
    Register register;

    String account;
    String password;
    String name;
    String phone;
    String code;
    String ip;
    Date registerdate;

    ObjectOutputStream output;

    SQLManage sqlManage=new SQLManage();

    public RegisterThread(SocketData socketData,String code, ObjectOutputStream output) throws IOException {
        this.socketData=socketData;
        this.register=socketData.register;
        this.account=socketData.register.account;
        this.password=socketData.register.password;
        this.name=socketData.register.name;
        this.phone=socketData.register.phone;
        this.code=socketData.register.code; //用户获取的输入的code
        this.ip=socketData.getWebip();
        this.registerdate=new Date(new java.util.Date().getTime());
        this.output=output;
        System.out.println("userCode:"+this.code+"|serverCode:"+code);
        if (this.code .equals(code)){
            sqlManage.insert2user(this.account, this.password, this.name,this.ip, this.registerdate,this.phone,output);
        }else{
            Register register = new Register(this.name, this.account, this.password, this.phone, this.code, false);
            output.writeObject(new SocketData(this.ip, register));
        }
    }

}
