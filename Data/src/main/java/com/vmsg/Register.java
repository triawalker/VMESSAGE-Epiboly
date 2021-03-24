package com.vmsg;

import java.io.Serializable;

public class Register implements Serializable {
    public String name;
    public String account;
    public String password;
    public Boolean request;
    public Register(String name,String account,String password,boolean request){
        this.name=name;
        this.account=account;
        this.password=password;
        this.request=request;
    }
}
