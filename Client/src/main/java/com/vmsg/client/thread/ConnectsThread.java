package com.vmsg.client.thread;

import com.vmsg.SocketData;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ConnectsThread {//负责端口连接和端口传输数据和获取数据


    int corePoolSize;
    int maximumPoolSize;
    int blockingQueueSize;
    long keepAliveTime;
    TimeUnit unit;
    static ThreadPoolExecutor connectThreadPool;


    Connect connect;

    static List<Connect>  Connectlist;

    String host;
    int port;


    boolean flag =true;


    public ConnectsThread(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int blockingQueueSize) {//在main程序中直接运行一次就生成socket
        this.corePoolSize=corePoolSize;
        this.maximumPoolSize=maximumPoolSize;
        this.keepAliveTime=keepAliveTime;
        this.unit=unit;
        this.blockingQueueSize=blockingQueueSize;
        Connectlist=new ArrayList<>();
        connectThreadPool=new ThreadPoolExecutor(
                this.corePoolSize,
                this.maximumPoolSize,
                this.keepAliveTime,
                this.unit,
                new ArrayBlockingQueue<>(blockingQueueSize));
    }
    public Connect addConnect(String host,int port){
        this.host=host;
        this.port=port;
        connect=new Connect(this.host,this.port);
        Connectlist.add(connect);
        return connect;
    }
    public void Stop(){
        for(Connect c:Connectlist){
            c.Stop();
        }
        Connectlist=null;
        connectThreadPool.shutdownNow();
    }
}

class ConnectThread implements Supplier<Boolean> {

    ObjectInputStream input;

    Socket socket;

    SocketData socketData;
    boolean flag = true;

    public ConnectThread(Socket socket, ObjectInputStream input) {
        this.socket = socket;
        this.input = input;
    }

    @Override
    public Boolean get() {
        // TODO Auto-generated method stub
        while (flag) {
            try {
                socketData = (SocketData) input.readObject();
                if (socketData.login != null) {
                    if (socketData.login.request == socketData.login.SUCCESS_LOGIN) {//登录成功
                        MainThread.chatsThread = new ChatsThread();
                        MainThread.chatsThread.addChat();
                        LoginEtc loginEtc = new LoginEtc();
                        loginEtc.saveinfo(socketData.login.account, socketData.login.password, MainThread.loginWindow.ckbox_savepwd.isSelected(), MainThread.loginWindow.ckbox_autologin.isSelected());
                        MainThread.loginWindow.dispose();
                    } else if (socketData.login.request == socketData.login.ERROR_PASSWORD) {
                        JOptionPane.showMessageDialog(null, "登录失败,请检查账号密码!");
                    } else if (socketData.login.request == socketData.login.ALREADY_ONLINE) {
                        JOptionPane.showMessageDialog(null, "账号已在线,请勿重复登录!");
                    } else if (socketData.login.request == socketData.login.ERROR_SQL) {
                        JOptionPane.showMessageDialog(null, "SQL数据访问错误,请联系管理员!");
                    }
                } else if (socketData.register != null) {
                    if (socketData.register.request) {
                        JOptionPane.showMessageDialog(null, "注册成功");
                        MainThread.registerWindow.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败,可能账号已存在");
                    }
                } else if (socketData.msg != null) {
                    MainThread.chatsThread.chat.chatWindow.txt_MsgLog.append(socketData.msg.Client2getMsg());
                } else if (socketData.user != null) {
                    MainThread.user = socketData.user;
                }

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                MainThread.ReconnectWindow.setVisible(true);
                return false;
            }
        }
        return false;
    }
}