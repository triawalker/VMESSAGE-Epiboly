package com.vmsg;


import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    public int id;
    public String account;
    public String password;
    public String name;
    public String ip;
    public Date registerdate;
    public User(int id,String account,String password,String name,String ip,Date registerdate){
        this.id=id;
        this.account=account;
        this.password=password;
        this.name=name;
        this.ip=ip;
        this.registerdate=registerdate;
    }
}
