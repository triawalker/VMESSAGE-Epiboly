package com.vmsg.server.thread;

import java.util.concurrent.TimeUnit;

public class MainThread {

    static TimeThread timeThread;
    static ClientsThread clientsThread;
    static CommandThread commandThread;
    public static void main(String[] args) {
        commandThread=new CommandThread();//指令线程,不能加入关闭重启命令中防止无法正常运行
        timeThread=new TimeThread();//时间线程,不能加入关闭重启命令中防止无法正常运行
        Start();
    }
    public static void Start(){
        new ConsoleInfo("VMessage服务器正在开启中...", ConsoleInfo.SERVER);//特殊输出形式
        clientsThread=new ClientsThread(10, 30, 30, TimeUnit.MINUTES,10);
    }
    public static void Stop(){
        clientsThread.Stop();
    }
    public static void Restart(){
        Stop();
        Start();
    }
    public static void Shutdown(){
        System.exit(0);
    }
}
