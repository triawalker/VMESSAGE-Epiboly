package com.vmsg.server.thread;

import com.vmsg.SocketData;

import java.io.IOException;
import java.util.List;

public class HandMsgThread extends Thread{
    List<Client> clientlist;
    SocketData socketData;
    public HandMsgThread(List<Client> clientlist, SocketData socketData) {
        // TODO Auto-generated constructor stub
        this.clientlist=clientlist;
        this.socketData=socketData;
        start();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub

        super.run();
        new ConsoleInfo("(" + this.socketData.getWebip() + ")" + this.socketData.msg.Server2getMsg(), ConsoleInfo.MESSAGE);
        if(clientlist!=null){
            for(Client client:clientlist){
                if(client.connect){
                    try {
                        client.output.writeObject(socketData);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        new ConsoleInfo("IP:"+client.clientsocket.getInetAddress().toString().replace("/", "")+"输出信息错误", ConsoleInfo.CLIENTERROR);
                    }
                }
            }
        }
    }
}

