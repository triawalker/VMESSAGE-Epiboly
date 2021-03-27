package com.vmsg.server.thread;

import com.vmsg.SocketData;
import com.vmsg.User;
import com.vmsg.server.IMService.com.netease.Controller.PhoneCodeController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ClientsThread extends Thread {

    public static final int SOCKETHOST=19000;

    int corePoolSize;
    int maximumPoolSize;
    int blockingQueueSize;
    long keepAliveTime;
    TimeUnit unit;
    static ThreadPoolExecutor clientThreadPool;
    int count=0;

    Socket clientsocket;
    ServerSocket serversocket=null;

    Client client;
    public static List<Client> clientlist;

    Boolean flag=true;
    public ClientsThread(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,int blockingQueueSize) {
        // TODO Auto-generated constructor stub
        this.corePoolSize=corePoolSize;
        this.maximumPoolSize=maximumPoolSize;
        this.keepAliveTime=keepAliveTime;
        this.unit=unit;
        this.blockingQueueSize=blockingQueueSize;
        clientlist=new ArrayList<>();
        new ConsoleInfo("-------------<<<<<客户机连接主线程正在创建>>>>>-------------", ConsoleInfo.NORMAL);
        clientThreadPool=new ThreadPoolExecutor(
                this.corePoolSize,
                this.maximumPoolSize,
                this.keepAliveTime,
                this.unit,
                new ArrayBlockingQueue<>(blockingQueueSize));
        new ConsoleInfo("核心线程大小:"+this.corePoolSize,ConsoleInfo.NORMAL);
        new ConsoleInfo("线程池最大线程数:"+this.maximumPoolSize, ConsoleInfo.NORMAL);
        new ConsoleInfo("空闲线程线程存活时间:"+this.keepAliveTime+" "+this.unit, ConsoleInfo.NORMAL);
        new ConsoleInfo("工作队列长度:"+this.blockingQueueSize, ConsoleInfo.NORMAL);
        new ConsoleInfo("-------------<<<<<客户机连接主线程创建完成>>>>>-------------",ConsoleInfo.NORMAL);
        start();
    }



    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        new ConsoleInfo("-------------<<<<<客户机连接主线程正启动中>>>>>-------------", ConsoleInfo.NORMAL);
        try {
            serversocket=new ServerSocket(SOCKETHOST);
            new ConsoleInfo("服务器主机("+InetAddress.getLocalHost().getHostAddress()+"):"+ InetAddress.getLocalHost().getHostName(),ConsoleInfo.NORMAL);
            new ConsoleInfo("Socket服务器端口监听端口:"+SOCKETHOST, ConsoleInfo.NORMAL);
            new ConsoleInfo("服务器启动时间:"+ TimeThread.getTime(), ConsoleInfo.NORMAL);
            new ConsoleInfo("-------------<<<<<客户机连接主线程启动成功>>>>>-------------", ConsoleInfo.NORMAL);
            while(flag){
                try {
                    //----------------------
                    clientsocket=serversocket.accept();
                    //----------------------
                    new ConsoleInfo("IP:"+clientsocket.getInetAddress().getHostAddress()+"于"+TimeThread.getTime()+"登录VMsg服务器", ConsoleInfo.SERVER);
                    count++;
                    client=new Client(clientsocket, clientThreadPool);
                    client.num=count;
                    new ConsoleInfo("为IP:"+clientsocket.getInetAddress().getHostAddress()+"用户开启第"+count+"个线程", ConsoleInfo.SERVER);
                    clientlist.add(client);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    if(flag)new ConsoleInfo("用户调用Socket出现错误,服务器Socket已经关闭",ConsoleInfo.SERVERERROR);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            new ConsoleInfo("Socket服务器端口19000被占用",ConsoleInfo.SERVERERROR);
            new ConsoleInfo("-------------<<<<<客户机连接主线程启动失败>>>>>-------------",ConsoleInfo.NORMAL);
        }
    }

    public void Stop(){
        try {
            flag=false;
            serversocket.close();
            new ConsoleInfo("服务器Socket端口监听关闭",ConsoleInfo.SERVER);
            interrupt();
            new ConsoleInfo("服务器客户机连接主线程关闭", ConsoleInfo.SERVER);
            for(Client c:clientlist){
                c.clientSupplier.input.close();
            }
            clientlist=null;
            clientThreadPool.shutdownNow();
            new ConsoleInfo("用户线程全部关闭", ConsoleInfo.SERVER);
            new ConsoleInfo("用户线程池关闭", ConsoleInfo.SERVER);
            new ConsoleInfo("服务器客户机连接线程关闭", ConsoleInfo.SERVER);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            new ConsoleInfo("服务器Socket已经关闭,无法继续关闭",ConsoleInfo.SERVERERROR);
        }
    }
    public static List<Client> getClientList(){
        return clientlist;
    }
}

class Client{//客户线程数据存储类
    int num;
    ClientSupplier clientSupplier;
    Boolean connect=true;
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket clientsocket;
    ThreadPoolExecutor clientThreadPool;
    User user;


    public Client(Socket clientsocket,ThreadPoolExecutor clientThreadPool)  {
        // TODO Auto-generated constructor stub
        this.clientsocket=clientsocket;
        this.clientThreadPool=clientThreadPool;
        try {
            output=new ObjectOutputStream(clientsocket.getOutputStream());
            input=new ObjectInputStream(clientsocket.getInputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            new ConsoleInfo("IP:"+clientsocket.getInetAddress().getHostAddress()+"输出流连接Socket出现问题" ,ConsoleInfo.CLIENTERROR);
        }
        clientSupplier =new ClientSupplier(this.clientsocket,this.input,this.output);
        CompletableFuture.supplyAsync(clientSupplier, this.clientThreadPool).whenComplete((result, e)->{
            connect=result;
            if(!connect) new ConsoleInfo("IP:"+this.clientsocket.getInetAddress().getHostAddress()+"断开与服务器连接",ConsoleInfo.SERVER);
        }).exceptionally((e) -> {
            String str="";
            StackTraceElement[] traceElements=e.getStackTrace();
            for(StackTraceElement trace: traceElements){
                str+=trace+"\n";
            }
            new ConsoleInfo("supplyAsync异常:\n", ConsoleInfo.SERVERERROR);
            return false;
        });
    }
    public Boolean getConnect(){
        return connect;
    }
}



class ClientSupplier implements Supplier<Boolean> {

    Socket clientsocket=null;
    ObjectInputStream input;
    ObjectOutputStream output;
    SocketData socketData;
    User user;
    String code = null;

    LoginThread loginThread;
    RegisterThread registerThread;
    HandMsgThread handMsgThread;

    boolean flag=true;
    public ClientSupplier(Socket clientsocket,ObjectInputStream input,ObjectOutputStream output) {
        // TODO Auto-generated constructor stub
        this.clientsocket=clientsocket;
        this.input=input;
        this.output=output;
    }
    @Override
    public Boolean get() {
        // TODO Auto-generated method stub
        while(flag){
            try {
                socketData=(SocketData) input.readObject();

                if(socketData.login!=null){
                    loginThread=new LoginThread(this.socketData,this.output);
                    this.user=loginThread.getLoginUser();
                }else if(socketData.register!=null){
                    registerThread=new RegisterThread(this.socketData,this.code, this.output);
                }else if(socketData.msg!=null){
                    socketData.msg.time = TimeThread.getTime();
                    handMsgThread = new HandMsgThread(ClientsThread.getClientList(), socketData);
                }else if (socketData.phoneCode!=null){
                    PhoneCodeController phoneCodeController = new PhoneCodeController(socketData.phoneCode.getPhone());
                    this.code = phoneCodeController.getCode();
                }

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                new ConsoleInfo("IP:"+clientsocket.getInetAddress().getHostAddress()+"获取输入信息错误", ConsoleInfo.CLIENTERROR);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return false;
            }
        }
        return false;
    }
}

