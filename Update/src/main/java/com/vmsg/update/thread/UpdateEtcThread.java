package com.vmsg.update.thread;

public class UpdateEtcThread extends Thread{  //调取配置文件线程
    UpdateEtc update_Etc;
    static int select=-1;
    public static final int ETCFROMLOCAL=0;
    public static final int ETCFROMSERVER=1;
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        switch (select) {
            case ETCFROMLOCAL:
                update_Etc=new UpdateEtc();
                update_Etc.reload_from_local();
                break;
            case ETCFROMSERVER:
                update_Etc=new UpdateEtc();
                update_Etc.reload_from_server();
                break;
        }
    }
    public UpdateEtcThread(int select){
        this.select=select;
        start();
    }
}
