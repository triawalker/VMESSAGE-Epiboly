package com.vmsg;


import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    public int id;
    public String account;
    public String password;
    public String name;
    public String ip;
    public String phone;
    public String code;
    public Date registerdate;
    public User(int id,String account,String password,String name,String ip,Date registerdate){
        this.id=id;
        this.account=account;
        this.password=password;
        this.name=name;
        this.ip=ip;
        this.registerdate=registerdate;
    }

    public User(int id, String account, String password, String name, String ip, String phone, String code, Date registerdate) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.ip = ip;
        this.phone = phone;
        this.code = code;
        this.registerdate = registerdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }
}
