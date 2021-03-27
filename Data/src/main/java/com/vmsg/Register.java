package com.vmsg;

import java.io.Serializable;

public class Register implements Serializable {
    public String name;
    public String account;
    public String password;
    public String phone;
    public String code;
    public Boolean request;

    public Register(String name,String account,String password,boolean request){
        this.name=name;
        this.account=account;
        this.password=password;
        this.request=request;
    }

    public Register(String name, String account, String password, String phone, String code, Boolean request) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.code = code;
        this.request = request;
    }
}
