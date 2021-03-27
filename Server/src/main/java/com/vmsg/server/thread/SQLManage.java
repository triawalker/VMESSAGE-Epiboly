package com.vmsg.server.thread;

import com.vmsg.Login;
import com.vmsg.Register;
import com.vmsg.SocketData;
import com.vmsg.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class SQLManage {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/vmsg?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=true";
    static final String USER="root";
    //static final String PASS="jerry2000826";
    static final String PASS="Jerry.,826";

    Connection connection=null;
    public void Connect(){
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void Disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insert2user(String account, String password, String name, String ip, Date registerdate, String phone,ObjectOutputStream output){
        Connect();
        String sql= "INSERT INTO user ( account,password,name,ip,registerdate,phone ) values(?,?,?,?,?,?)";
        boolean request=false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, ip);
            statement.setDate(5, registerdate);
            statement.setString(6, phone);
            int result=statement.executeUpdate();
            request=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            request=false;
        }


        new ConsoleInfo("用户"+ip+"注册账号:"+account+"密码:"+password+(request?"成功!":"失败!"),ConsoleInfo.SERVER);

        try {
            Register register=new Register(name, account, password, request);
            SocketData socketData=new SocketData(ip, register);
            output.writeObject(socketData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Disconnect();
    }
    public int checklogin(String account, String password, String ip,ObjectOutputStream output){
        int row=0;
        Connect();
        String sql= "SELECT * FROM user";
        int request=-1;
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            String SQLaccount=null;
            String SQLpassword=null;
            while(rs.next()){
                SQLaccount=rs.getString("account");
                SQLpassword=rs.getString("password");
                if(SQLaccount.equals(account)&&SQLpassword.equals(password)){
                    request=Login.SUCCESS_LOGIN;
                    row=rs.getRow();
                    break;
                }
            }
        } catch (SQLException throwables) {
            request=Login.ERROR_SQL;
            throwables.printStackTrace();
        }

        for (Client client:MainThread.clientsThread.clientlist){
            if (client.connect&&client.clientSupplier.user!=null&&client.clientSupplier.user.account.equals(account)){
                request=Login.ALREADY_ONLINE;
            }
        }

        new ConsoleInfo("用户"+ip+"登录账号:"+account+"密码:"+password+(request==Login.SUCCESS_LOGIN?"成功!":"失败!"),ConsoleInfo.SERVER);
        try {
            Login login=new Login(account,password,request);
            SocketData socketData=new SocketData(ip, login);
            output.writeObject(socketData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Disconnect();
        return row;
    }
    public User selectuser(int row){
        User user=null;
        Connect();
        String sql="select * from user limit ?";
        if (row>0){
            try {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setInt(1, row);
                ResultSet rs=statement.executeQuery();
                int SQLid=0;
                String SQLaccount=null;
                String SQLpassword=null;
                String SQLname=null;
                String SQLip=null;
                Date SQLregisterdate=null;
                while(rs.next()){
                    SQLid=rs.getInt("id");
                    SQLaccount=rs.getString("account");
                    SQLpassword=rs.getString("password");
                    SQLname=rs.getString("name");
                    SQLip=rs.getString("ip");
                    SQLregisterdate=rs.getDate("registerdate");
                }
                user=new User(SQLid, SQLaccount, SQLpassword, SQLname, SQLip, SQLregisterdate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        Disconnect();
        return user;
    }
}
