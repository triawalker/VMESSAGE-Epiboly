package com.vmsg.server.IMService.com.netease.Service;

import com.vmsg.server.IMService.com.netease.Dao.PhoneCodeDao;

public class PhoneCodeServiceImpl implements PhoneCodeService{

    private PhoneCodeDao phoneCodeDao;

    public void setPhoneCodeDao(PhoneCodeDao phoneCodeDao) {
        this.phoneCodeDao = phoneCodeDao;
    }


    @Override
    public String getPhone() {
        return phoneCodeDao.getPhone();
    }

    @Override
    public String getCode() {
        return phoneCodeDao.getCode();
    }
}
