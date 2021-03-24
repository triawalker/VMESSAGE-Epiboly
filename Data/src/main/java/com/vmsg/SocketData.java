package com.vmsg;


import java.io.Serializable;

public class SocketData implements Serializable {
    public String webip;
    public Msg msg;
    public Register register;
    public Login login;
    public User user;
    public boolean connectStatus;

    public SocketData(String webip,Msg msg){
        this.webip=webip;
        this.msg=msg;
    }
    public SocketData(String webip,Register register){
        this.webip=webip;
        this.register=register;
    }
    public SocketData(String webip,Login login){
        this.webip=webip;
        this.login=login;
    }
    public SocketData(User user){
        this.user=user;
    }
    public SocketData(boolean connectStatus){
        this.connectStatus=connectStatus;
    }

    public String getWebip(){
        return webip;
    }
    public Msg getMsg(){
        return msg;
    }
    public Register getRegister() { return register; }
    public Login getLogin(){ return login; }
}
