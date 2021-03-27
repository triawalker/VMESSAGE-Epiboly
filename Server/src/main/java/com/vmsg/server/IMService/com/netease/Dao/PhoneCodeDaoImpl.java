package com.vmsg.server.IMService.com.netease.Dao;

public class PhoneCodeDaoImpl implements PhoneCodeDao {

    private String phone = null;
    private String code = null;

    public PhoneCodeDaoImpl(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    public PhoneCodeDaoImpl(String phone) {
        this.phone = phone;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPhone() {
        return phone;
    }
}
