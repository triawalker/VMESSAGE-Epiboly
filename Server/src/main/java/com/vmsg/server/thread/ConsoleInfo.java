package com.vmsg.server.thread;

public class ConsoleInfo {

    public static final int NORMAL=0;
    public static final int SERVER=1;
    public static final int SERVERERROR=2;
    public static final int CLIENT=3;
    public static final int CLIENTERROR=4;
    public static final int MESSAGE=5;
    public static final int COMMAND=6;


    String headinfo="";
    String endinfo="";

    public ConsoleInfo(String headinfo,int type) {
        // TODO Auto-generated constructor stub
        this.headinfo=headinfo;
        this.endinfo=endinfo;
        switch (type) {
            case NORMAL:
                Normal();
                break;
            case SERVER:
                Server();
                break;
            case SERVERERROR:
                ServerError();
                break;
            case CLIENT:
                Client();
                break;
            case CLIENTERROR:
                ClientError();
                break;
            case MESSAGE:
                Message();
                break;
            case COMMAND:
                Command();
                break;
        }
    }


    private void Normal() {
        System.out.println(headinfo);
    }
    private void Server(){
        System.out.println(TimeThread.getTime()+"[SERVER]"+headinfo);
    }
    private void ServerError(){
        System.out.println(TimeThread.getTime()+"[SERVERERROR]"+headinfo);
    }
    private void Client() {
        System.out.println(TimeThread.getTime()+"[CLIENT]"+headinfo);
    }
    private void ClientError() {
        System.out.println(TimeThread.getTime()+"[CLIENTERROR]"+headinfo);
    }
    private void Message() {
        System.out.println("\n<MESSAGE>\n"+headinfo);
        System.out.println("</MESSAGE>");
    }
    private void Command() {
        // TODO Auto-generated method stub
        System.out.println(TimeThread.getTime()+"[COMMAND]"+headinfo);
    }

}

