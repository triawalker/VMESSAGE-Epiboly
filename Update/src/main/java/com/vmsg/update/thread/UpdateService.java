package com.vmsg.update.thread;


import com.jcraft.jsch.*;
import com.vmsg.update.window.UpdateWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ScheduledExecutorService;

public class UpdateService {

    final static int DOWNFILE=0,DOWNFILES=1;
    ChannelSftp sftp=null;
    Session sshSession=null;  //会话
    String username;
    String password;
    String host;
    String serverfilepath;
    String localfilepath;
    int post;
    final String filesep=System.getProperty("file.separator");
    static double ServerFilesize=0;
    static double LocalFileSize=0;

    public UpdateService(String username,String password,String host,int post,String serverfilepath,String localfilepath,int Downloadflag) {
        // TODO Auto-generated constructor stub
        this.username=username;
        this.password=password;
        this.host=host;
        this.post=post;
        this.serverfilepath=serverfilepath;
        this.localfilepath=localfilepath;
        LocalFileSize=0;

        if(connectSftp()){//连接服务器
            if(GetUpdateServerFiles.setSize(sftp, serverfilepath)){//获取下载文件大小
                switch (Downloadflag) {
                    case DOWNFILES:
                        if(DownloadFiles(serverfilepath, localfilepath)){//下载多文件
                            disconnectSftp();//断开服务器
                            UpdateInfoShow.MainInfo("!!!下载/更新文件任务成功!!!\n------------------------------------------");
                        }else{
                            disconnectSftp();//断开服务器
                            UpdateInfoShow.MainInfo("!!!下载/更新文件任务失败!!!\n------------------------------------------");
                        }
                        break;
                    case DOWNFILE:
                        if(DownFile(serverfilepath, localfilepath)){//下载单文件
                            disconnectSftp();//断开服务器
                            UpdateInfoShow.MainInfo("!!!下载/更新文件任务成功!!!\n------------------------------------------");
                        }else{
                            disconnectSftp();//断开服务器
                            UpdateInfoShow.MainInfo("!!!下载/更新文件任务失败!!!\n------------------------------------------");
                        }
                        break;
                }
            }else{
                disconnectSftp();//断开服务器
                UpdateInfoShow.MainInfo("!!!下载/更新文件任务失败!!!\n------------------------------------------");
            }
        }else{
            disconnectSftp();//断开服务器
            UpdateInfoShow.MainInfo("!!!下载/更新文件任务失败!!!\n------------------------------------------");
        }
    }

    public boolean connectSftp(){
        JSch jsch=new JSch();
        UpdateInfoShow.MainInfo("正在连接Vmessage SFTP服务器...");
        try {
            jsch.getSession(username, host, post);
            sshSession=jsch.getSession(username, host, post);
            sshSession.setPassword(password);
            Properties properties=new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect(30000);
            Channel channel=sshSession.openChannel("sftp");
            channel.connect(10000);
            sftp=(ChannelSftp)channel;
            UpdateInfoShow.MainInfo("与Vmessage SFTP服务器("+host+":"+post+")连接成功,开始下载文件任务...");
            return true;
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            UpdateInfoShow.MainInfo("与Vmessage SFTP服务器("+host+":"+post+")连接失败,请检测SFTP连接设置(HOST POST USER PASSWD)");
            return false;
        }
    }
    public void disconnectSftp(){
        if(this.sftp!=null){
            UpdateInfoShow.MainInfo("连接断开或超时与SFTP服务器连接关闭");
            if(this.sftp.isConnected()){
                this.sftp.disconnect();
                this.sftp=null;
            }
        }
        if(this.sshSession!=null){
            UpdateInfoShow.MainInfo("连接断开或超时与服务器的连接会话关闭");
            if(this.sshSession.isConnected()){
                this.sshSession.disconnect();
                this.sshSession=null;
            }
        }
    }

    //下载文件夹
    public  boolean DownloadFiles(String ServerFilePath,String LocalFilePath)  {
        String fileName=null;
        String SvFPath=null;
        String LcFPath=null;
        Vector<?> Serverfiles=new Vector<>();
        File file;

        try {
            sftp.cd(ServerFilePath);
            file=new File(LocalFilePath);
            if(!file.exists()){
                file.mkdirs();
            }
            Serverfiles=sftp.ls("*");
            for(Object s:Serverfiles){
                fileName=s.toString().substring(s.toString().lastIndexOf(" ")+1);
                SvFPath=ServerFilePath+"/"+fileName;
                LcFPath=LocalFilePath+filesep+fileName;
                try{
                    sftp.cd(SvFPath);
                    DownloadFiles(SvFPath, LcFPath);
                }catch (SftpException e) {
                    DownFile(SvFPath, LcFPath);
                }

            }
            return true;
        } catch (SftpException e) {
            if(DownFile(ServerFilePath, LocalFilePath)){
                return true;
            }else{
                return false;
            }
            // TODO Auto-generated catch block
        }

    }

    public boolean DownFile(String ServerFilePath,String LocalFilePath){
        String SvPath = null;
        String LcPath = null;
        FileOutputStream output;
        String FileParent;
        File file;
        try {
            UpdateInfoShow.MainInfo("正在从SFTP服务器中下载:"+ServerFilePath);
            SvPath=ServerFilePath;
            LcPath=LocalFilePath.endsWith(filesep)?LocalFilePath.substring(0, LocalFilePath.lastIndexOf(filesep)):LocalFilePath;
            FileParent=LcPath.substring(0, LocalFilePath.lastIndexOf(filesep));
            file=new File(FileParent);
            if(!file.exists()){
                file.mkdirs();
                UpdateInfoShow.MainInfo("本地创建路径文件:"+FileParent);
            }

            file=new File(LcPath);
            if(file.exists()){
                file.delete();
                UpdateInfoShow.MainInfo("删除本地原有文件:"+LcPath);
            }
            file.createNewFile();
            UpdateInfoShow.MainInfo("本地创建下载文件:"+LcPath);
            output = new FileOutputStream(file);


            sftp.get(ServerFilePath, output, new UpdateMonitor());

            output.close();
            UpdateInfoShow.MainInfo("从SFTP服务器下载文件:"+SvPath+"成功!");
            return true;
        } catch (FileNotFoundException e) {
            UpdateInfoShow.MainInfo("本地目录异常,请检查"+LcPath);
            return false;
        } catch (IOException e) {
            UpdateInfoShow.MainInfo("创建本地文件失败"+LcPath);
            return false;
        } catch (SftpException e) {
            UpdateInfoShow.MainInfo("从SFTP服务器下载文件:"+SvPath+"失败!");
            return false;
        }

    }




    static class GetUpdateServerFiles{
        public static boolean setSize(ChannelSftp sftp,String path){
            ServerFilesize=0;
            try {
                UpdateInfoShow.MainInfo("正在获取下载文件大小...");
                travelFiles(sftp,path);
                DecimalFormat df=new DecimalFormat("0.00");
                UpdateInfoShow.MainInfo("下载文件大小:"+df.format(ServerFilesize/1024/1024)+"MB");
            } catch (SftpException e) {
                // TODO Auto-generated catch block
                UpdateInfoShow.MainInfo("获取下载文件大小失败");
                return false;
            }
            return true;
        }
        public static void travelFiles(ChannelSftp sftp,String path) throws SftpException{

            try {
                Vector<?> Serverfiles=new Vector<>();
                sftp.cd(path);
                Serverfiles=sftp.ls("*");
                for(Object s:Serverfiles){
                    String fileName = s.toString().substring(s.toString().lastIndexOf(" ")+1);
                    String fileSize = cutSizeOut(s.toString());
                    String SvFPath = path+"/"+fileName;
                    //System.out.println(SvFPath);
                    try{
                        sftp.cd(SvFPath);
                        travelFiles(sftp, SvFPath);
                    }catch (SftpException e) {//若路径类型不为文件夹 下载文件大小增加
                        // TODO: handle exception
                        ServerFilesize+=Double.valueOf(fileSize);
                        //e.printStackTrace();
                    }
                }
            } catch (SftpException e) {//若路径直接导向非文件夹类文件则直接计算占用空间大小
                // TODO Auto-generated catch block
                Vector<?> Serverfiles=new Vector<>();
                String SvPath=path.substring(0,path.lastIndexOf("/"));
                String fileName=path.substring(path.lastIndexOf("/")+1);
                sftp.cd(SvPath);
                Serverfiles=sftp.ls("*");
                for(Object s:Serverfiles){
                    if(fileName.equals(s.toString().substring(s.toString().lastIndexOf(" ")+1))){
                        String fileSize = cutSizeOut(s.toString());
                        ServerFilesize+=Double.valueOf(fileSize);
                    }
                }
                //e.printStackTrace();
            }
        }

        public static String cutSizeOut(String s){//截取字符串中的文件占用空间

            String[] ss=s.split(" ");
            int i=0;
            for(String str:ss){
                if(!str.equals("")) i++;
                if(i==5) {
                    return str;
                }
            }
            return "0";
            // fileSize;
        }

    }
}




class UpdateMonitor extends Thread implements SftpProgressMonitor{
    private String src="";
    private long count = 0;     //当前接收的总字节数
    private long max = 0;       //最终文件大小
    private long time = 0;      //记录下载时间
    private boolean flag =false;//记录更新监控是否在运行
    private long percount = 0;//一秒内传输的字节


    ScheduledExecutorService executorservice;
    //当每次传输了一个数据块后，调用count方法，count方法的参数为这一次传输的数据块大小

    @Override
    public boolean count(long count) {
        //更新总进程
        UpdateService.LocalFileSize+=count;
        UpdateInfoShow.UpdateRate(UpdateService.LocalFileSize, UpdateService.ServerFilesize);
        //更新子进程
        this.count += count;
        this.percount +=count;
        UpdateInfoShow.DownloadRate(src,this.count,max,time);
        if(count>0) return true;
        return false;
    }

    //当传输结束时，调用end方法

    @Override
    public void end() {
        UpdateInfoShow.UpdateRate(0, 0);
        UpdateInfoShow.DownloadRate("",0,0,0);
        UpdateInfoShow.MainInfo(src+"下载完成,共用时:"+time+"s 平均速度:"+count/1024/time+"KB/s");
        this.flag=false;
    }

    //当文件开始传输时，调用init方法

    @Override
    public void init(int op, String src, String dest, long max) {
        if (op==SftpProgressMonitor.PUT) {
            //System.out.println("Upload file begin.");
        }else {
            //System.out.println(+"Download file begin.");
            UpdateInfoShow.DownloadRate(src,0,max,time);
        }
        this.max = max;
        this.count = 0;
        this.time = 0;
        this.src=src;
        this.flag=true;

        if(!this.isAlive()){
            start();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            if(flag){
                try {
                    DecimalFormat df=new DecimalFormat("0.0");
                    UpdateWindow.lbl_DownInfo_speed.setText(time==0?"":"速度:"+df.format(percount/1024.0)+"KB/s");
                    time+=1;
                    percount=0;
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}


class UpdateInfoShow {
    static String str1="";
    static String str2="";
    static String str3="";
    static String str4="";
    public static void MainInfo(String info){
        str1+=info+"\n";
        UpdateWindow.txt_MainInfo.setText(str1);
    }
    public static void DownloadRate(String file,long count,long max,long time){//单文件下载进程
        str2="";
        str2=file.substring(file.lastIndexOf("/")+1);
        DecimalFormat df=new DecimalFormat("0.0");
        double percent=file.equals("")&&count==0&&max==0?-1:count*100.0/max;
        str3="";
        for(int i=1;i<=percent;i++){
            str3+="|";
        }
        UpdateWindow.lbl_DownInfo_content.setText(str2);
        UpdateWindow.lbl_DownInfo_ratepic.setText(str3);
        UpdateWindow.lbl_DownInfo_ratenum.setText(percent==-1?"":df.format(percent)+"%");
        df=new DecimalFormat("0.00");
        UpdateWindow.lbl_DownInfo_ratesize.setText(percent==-1?"":"("+df.format(count/1024.0/1024.0)+"/"+df.format(max/1024.0/1024.0)+"MB)");


    }

    public static void UpdateRate(double LocalFileSize,double ServerFilesize){//更新总进程
        str4="";
        DecimalFormat df=new DecimalFormat("0.0");
        double percent=LocalFileSize==0&&ServerFilesize==0?0:LocalFileSize/ServerFilesize*100;
        for(int i=1;i<=percent;i++){
            str4+="|";
        }
        UpdateWindow.lbl_UpdateInfo_ratenum.setText(LocalFileSize==0&&ServerFilesize==0?"":df.format(percent)+"%");
        df=new DecimalFormat("0.00");
        UpdateWindow.lbl_UpdateInfo_ratesize.setText(LocalFileSize==0&&ServerFilesize==0?"":"("+df.format(LocalFileSize/1024/1024)+"/"+df.format(ServerFilesize/1024/1024)+"MB)");
        UpdateWindow.lbl_UpdateInfo_ratepic.setText(str4);
    }

    public static void Mainclear(){//信息窗口清空
        str1="";
        UpdateWindow.txt_MainInfo.setText(str1);
    }
}

