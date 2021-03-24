package com.vmsg.update.thread;

import java.io.*;
import java.util.Properties;

public class UpdateEtc {
    UpdateService updateService;

    public void Update_Etc() {
        // TODO Auto-generated constructor stub

    }

    public static void alter_UpdateEtc(String lfp, int post, String sfp, String host){

        try {
            File file;
            UpdateInfoShow.MainInfo("正在修改配置文件");
            Properties prop=new Properties();
            file=new File("."+MainThread.filesep+"etc");
            if(!file.exists()) file.mkdirs();
            file=new File("."+MainThread.filesep+"etc"+MainThread.filesep+"Update.properties");
            FileOutputStream output=new FileOutputStream(file);
            prop.setProperty("POST", post+"");
            prop.setProperty("HOST", host);
            prop.setProperty("ServerFilePath", sfp);
            prop.setProperty("LocalFilePath", lfp);
            prop.store(output, "UpdateEtc");
            output.close();
            UpdateInfoShow.MainInfo("配置文件修改成功");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void read_UpadteEtc(){
        try {
            File file=new File(MainThread.LocalEtcPath);
            Properties prop=new Properties();
            UpdateInfoShow.MainInfo("正在读取更新配置文件信息");
            if(!file.exists()){
                UpdateInfoShow.MainInfo("配置文件不存在");
                reload_from_local();
            }
            InputStream input=new BufferedInputStream(new FileInputStream(file));
            prop.load(input);
            MainThread.etcLocalFilePath=prop.getProperty("LocalFilePath");
            MainThread.etcHOST=prop.getProperty("HOST");
            MainThread.etcServerFilePath=prop.getProperty("ServerFilePath");
            MainThread.etcPOST=Integer.valueOf(prop.getProperty("POST"));
            input.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void reload_from_server(){   //从服务器中读取配置文件
        UpdateInfoShow.MainInfo("正在从服务器中下载更新配置文件");
        updateService=new UpdateService(
                MainThread.USERNAME,
                MainThread.PASSWORD,
                MainThread.DEFAULTHOST,
                MainThread.DEFAULTPOST,
                MainThread.ServerEtcPath,
                MainThread.LocalEtcPath,
                UpdateService.DOWNFILE);
    }
    public void reload_from_local(){
        try {
            File file;
            UpdateInfoShow.MainInfo("正在本地中生成更新配置文件");
            Properties prop=new Properties();
            file=new File("."+MainThread.filesep+"etc");
            if(!file.exists()) file.mkdirs();
            file=new File("."+MainThread.filesep+"etc"+MainThread.filesep+"Update.properties");
            FileOutputStream output=new FileOutputStream(file);
            prop.setProperty("POST", MainThread.DEFAULTPOST+"");
            prop.setProperty("HOST", MainThread.DEFAULTHOST);
            prop.setProperty("ServerFilePath", MainThread.ServerFilePath);
            prop.setProperty("LocalFilePath", MainThread.LocalFilePath);
            prop.store(output, "UpdateEtc");
            output.close();
            UpdateInfoShow.MainInfo("生成本地更新配置文件在同级目录的etc文件夹中");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
