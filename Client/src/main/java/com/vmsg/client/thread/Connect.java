package com.vmsg.client.thread;

import com.vmsg.SocketData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public class Connect {

    int num;
    public Socket socket;
    ConnectThread connectThread;
    ObjectOutputStream output;
    ObjectInputStream input;
    public boolean status = false;

    String host;
    int port;

    SocketData socketData = null;

    public Connect(String host, int port) {
        this.host = host;
        this.port = port;
        SokcetInit();
    }


    public void SokcetInit() {
        try {
            this.socket = new Socket(this.host, this.port);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            this.connectThread = new ConnectThread(this.socket, this.input);
            CompletableFuture.supplyAsync(this.connectThread, ConnectsThread.connectThreadPool).whenComplete((result, e) -> {
                status = result;
            }).exceptionally((e) -> {
                e.printStackTrace();
                return false;
            });
            status = true;
            System.out.println("连接服务器成功");
        } catch (IOException e) {
            //连接断开打开重连窗口
            MainThread.ReconnectWindow.setVisible(true);
            e.printStackTrace();
        }
    }

    public void SocketReconnet() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!SocketStatus()) {
                    System.out.println("重新连接服务器中");
                    SokcetInit();
                }
            }
        }, 3000);
    }

    public boolean SocketStatus() {
        return this.socket.isConnected();
    }

    public void Stop() {
        try {
            this.socket.close();
            this.output.close();
            this.input.close();
            this.connectThread = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendSocketData(SocketData socketData) {//调用此方法要先创建Msg之类的数据 再生成SocketData
        this.socketData = socketData;
        try {
            output.writeObject(socketData);
        } catch (IOException e) {
            MainThread.ReconnectWindow.setVisible(true);
            e.printStackTrace();
        }
    }


}
