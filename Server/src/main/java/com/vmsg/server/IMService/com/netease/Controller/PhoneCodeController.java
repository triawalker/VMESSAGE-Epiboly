package com.vmsg.server.IMService.com.netease.Controller;

import com.vmsg.server.IMService.com.netease.Dao.PhoneCodeDao;
import com.vmsg.server.IMService.com.netease.Dao.PhoneCodeDaoImpl;
import com.vmsg.server.IMService.com.netease.Service.PhoneCodeService;
import com.vmsg.server.IMService.com.netease.Service.PhoneCodeServiceImpl;
import com.vmsg.server.IMService.com.netease.code.SendCode;

import java.io.IOException;

public class PhoneCodeController {

    PhoneCodeService phoneCodeServie = new PhoneCodeServiceImpl();
    SendCode sendCode;


    public PhoneCodeController(String phone) throws IOException {
        sendCode = new SendCode(phone);
        setCodeDao(phone, sendCode.getCode());
    }

    public void setCodeDao(String phone, String code) {
        PhoneCodeDao phoneCodeDao = new PhoneCodeDaoImpl(phone, code);
        ((PhoneCodeServiceImpl)phoneCodeServie).setPhoneCodeDao(phoneCodeDao);
    }

    public String getPhone() {
        return phoneCodeServie.getPhone();
    }

    public String getCode() {
        return phoneCodeServie.getCode();
    }
}
