package com.vmsg;

import java.io.Serializable;

public class PhoneCode implements Serializable {
    public String phone;

    public PhoneCode(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
