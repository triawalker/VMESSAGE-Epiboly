package com.vmsg.server.thread;

import org.junit.Test;

import java.sql.*;

public class SQLManageTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/vmsg?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER="root";
    static final String PASS="jerry2000826";

    @Test
    public void testConnect(){
        Connection connection=null;
        Statement statement=null;
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            System.out.println("连接数据库");
            connection= DriverManager.getConnection(DB_URL,USER,PASS);

            //执行查询

            System.out.println("实例化Statement对象...");
            statement=connection.createStatement();
            String sql;
            sql="SELECT id, account,password FROM user";
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String account = resultSet.getString("account");
                String password = resultSet.getString("password");
                System.out.println("id:"+id+" account="+account+" password="+password);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
