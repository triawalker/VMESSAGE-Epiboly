package com.vmsg;

import java.io.Serializable;

public class Msg implements Serializable {
    public String name;
    public String info;
    public String time;

    public Msg(String name,String info,String time){
        this.name=name;
        this.info=info;
        this.time=time;
    }
    public String Server2getMsg(){
        String msg=name+":"+"	"+time+"\nSAY:"+info;
        return msg;
    }
    public String Client2getMsg(){
        String msg=name+":"+"               "+time+"\n"+info+"\n-------------\n";
        return msg;
    }
}
