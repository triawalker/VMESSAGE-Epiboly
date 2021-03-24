package com.vmsg.server.thread;

import com.vmsg.Msg;
import com.vmsg.SocketData;

import java.util.Scanner;

public class CommandThread extends Thread{

    Boolean flag=true;
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        Scanner scanner=new Scanner(System.in);
        while(flag){
            String s=scanner.nextLine();
            if(s.length()>0){
                s=s.substring(0, 1).equals("/")?s:"/"+s; //确定是否为命令如果是直接进行命令判断,如果不是则加上命令符号
                s=LowerCaps(s);//将命令小写
                new ConsoleInfo(s, ConsoleInfo.COMMAND);
                CommandSelect(s);
            }else{
                new ConsoleInfo("",ConsoleInfo.NORMAL);
            }
        }
    }

    public CommandThread() {
        // TODO Auto-generated constructor stub
        start();
    }
    private String LowerCaps(String s){
        int blank=s.indexOf(" ")==-1?s.length()-1:s.indexOf(" ");
        String command=s.substring(0, blank+1).toLowerCase();
        String parameters=s.substring(blank+1);
        return parameters.length()>0?command+parameters:command;

    }
    private void CommandSelect(String s){ //选择指令
        int blank=s.indexOf(" ")==-1?s.length()-1:s.indexOf(" ");
        switch(s.substring(0, blank+1).replace(" ", "")){
            case "/help":
                CommandHelp(s);
                break;
            case "/say":
                CommandSay(s);
                break;
            case "/stop":
                CommandStop(s);
                break;
            case "/start":
                CommandStart(s);
                break;
            case "/restart":
                CommandRestart(s);
                break;
            case "/shutdown":
                CommandShutdown(s);
                break;
            case "/user":
                CommandUser(s);
                break;
        }
    }

    private void CommandUser(String s) {
        // TODO Auto-generated method stub
        String parameter=s.substring(s.indexOf(" ")+1).replace(" ", "");
        if(parameter.substring(0,1).equals("-")){
            new ConsoleInfo("全部在线用户:"+ ClientsThread.clientThreadPool.getActiveCount()+"人", ConsoleInfo.SERVER);
            if(ClientsThread.getClientList().size()>0){
                for(Client c:ClientsThread.getClientList()){
                    if(c.connect){
                        if(parameter.indexOf("o")>0||parameter.indexOf("O")>0||parameter.indexOf("a")>0||parameter.indexOf("A")>0){
                            new ConsoleInfo(c.clientSupplier.user.ip+"	ONLINE", ConsoleInfo.SERVER);
                        }
                    }else{
                        if(parameter.indexOf("d")>0||parameter.indexOf("D")>0||parameter.indexOf("a")>0||parameter.indexOf("A")>0){
                            new ConsoleInfo(c.clientSupplier.user.ip+"	DISCONNECT", ConsoleInfo.SERVER);
                        }
                    }
                }
            }else{
                new ConsoleInfo("当前无用户在线!", ConsoleInfo.SERVER);
            }
        }
    }

    private void CommandShutdown(String s) {
        // TODO Auto-generated method stub
        MainThread.Shutdown();
    }

    private void CommandHelp(String s) {
        // TODO Auto-generated method stub

    }

    private void CommandSay(String s) {
        // TODO Auto-generated method stub
        String info = s.substring(s.indexOf(" ")+1);
        SocketData socketData=new SocketData("localhost",new Msg("管理员",info, TimeThread.getTime()));
        HandMsgThread handMsgThread=new HandMsgThread(ClientsThread.getClientList(),socketData);
    }

    private void CommandStop(String s) {
        // TODO Auto-generated method stub
        MainThread.Stop();
    }

    private void CommandStart(String s) {
        // TODO Auto-generated method stub
        MainThread.Start();
    }

    private void CommandRestart(String s) {
        // TODO Auto-generated method stub
        MainThread.Restart();
    }

    public void Stop(){
        flag=false;
        interrupt();
    }
}
