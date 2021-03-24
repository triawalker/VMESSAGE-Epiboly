package com.vmsg.update.thread;

public class UpdateThread extends Thread{ //更新总线程
    UpdateService updateService;
    UpdateEtc update_Etc;
    //UpdateRateThread updateRateThread;
    public UpdateThread(){
        start();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        update_Etc=new UpdateEtc();
        update_Etc.read_UpadteEtc();//读取配置文件
        //开始更新服务
        updateService=new UpdateService(
                MainThread.USERNAME,
                MainThread.PASSWORD,
                MainThread.etcHOST,
                MainThread.etcPOST,
                MainThread.etcServerFilePath,
                MainThread.etcLocalFilePath,
                UpdateService.DOWNFILES);

        interrupt();
    }

}
