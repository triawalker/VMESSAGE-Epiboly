package com.vmsg.server.thread;

import com.vmsg.server.IMService.com.netease.Controller.PhoneCodeController;

import java.io.IOException;

public class Test01 {
    public static void main(String[] args) throws IOException {
        PhoneCodeController phoneCodeController = new PhoneCodeController("17681988026");
        System.out.println("code="+phoneCodeController.getCode());
        System.out.println("phone="+phoneCodeController.getPhone());
    }
}
