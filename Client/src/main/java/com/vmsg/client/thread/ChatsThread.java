package com.vmsg.client.thread;

import com.vmsg.Msg;
import com.vmsg.SocketData;
import com.vmsg.client.window.ChatWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatsThread{//启动多聊天线程

    Chat chat;
    static List<Chat> chatlist;

    public ChatsThread() {
        // TODO Auto-generated constructor stub
        chatlist=new ArrayList<>();
    }

    public void addChat(){
        chat=new Chat();
        chatlist.add(chat);
    }

}
class Chat implements ActionListener {

    ChatWindow chatWindow;

    SocketData socketData;
    Msg msg;

    public Chat(){
        chatWindow=new ChatWindow();
        chatWindow.btn_send.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        msg=new Msg(MainThread.user.name, chatWindow.txt_MsgSend.getText(), "");
        socketData=new SocketData(WebIP.getWebip(), msg);
        chatWindow.txt_MsgSend.setText("");
        MainThread.connect.SendSocketData(socketData);
    }
}
