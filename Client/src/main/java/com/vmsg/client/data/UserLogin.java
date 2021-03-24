package com.vmsg.client.data;

public class UserLogin {
    public int num;
    public String account;
    public String password;
    public boolean autologin;

    public UserLogin(int num, String account, String password, boolean autologin) {
        this.num = num;
        this.account = account;
        this.password = password;
        this.autologin = autologin;
    }

    public UserLogin() {

    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAutologin(boolean autologin) {
        this.autologin = autologin;
    }
}
