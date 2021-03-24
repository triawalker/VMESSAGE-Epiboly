package com.vmsg.server.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeThread extends Thread{

    SimpleDateFormat df;
    Boolean flag=true;
    static String time;

    public TimeThread() {
        // TODO Auto-generated constructor stub
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        start();
        new ConsoleInfo("时间线程运行中...", ConsoleInfo.SERVER);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        while(flag){
            time=df.format(new Date());
        }
    }

    public static String  getTime(){
        return time;
    }
    public void Stop(){
        flag=false;
        interrupted();
        new ConsoleInfo("时间线程关闭", ConsoleInfo.SERVER);
    }
}
