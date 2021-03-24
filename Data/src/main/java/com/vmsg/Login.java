package com.vmsg;

import java.io.Serializable;

public class Login implements Serializable {
    public String account;
    public String password;
    public int request;
    public static final int SUCCESS_LOGIN=0;
    public static final int ERROR_PASSWORD=1;
    public static final int ALREADY_ONLINE=2;
    public static final int ERROR_SQL=3;
    public static final int NOT_LOGIN=4;
    public Login(String account,String password,int request){
        this.account=account;
        this.password=password;
        this.request=request;
    }

    public void setRequest(int request) {
        this.request = request;
    }
}
