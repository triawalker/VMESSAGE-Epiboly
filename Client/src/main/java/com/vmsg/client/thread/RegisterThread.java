package com.vmsg.client.thread;

import com.vmsg.Register;
import com.vmsg.SocketData;

import javax.swing.*;

public class RegisterThread {
    Register register;
    SocketData socketData;
    public RegisterThread(Register register){
            this.register = register;
            socketData = new SocketData(WebIP.getWebip(), register);
            MainThread.connect.SendSocketData(socketData);
    }
    public RegisterThread(StringBuffer sb){
            JOptionPane.showMessageDialog(null, sb);
    }
}
