package com.server;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class test {
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/vmsg?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//    static final String USER="root";
//    static final String PASS="jerry2000826";

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/vmsg?useSSL=true&serverTimezone=GMT";
    static String USER;
    static String PASS;

    static Connection connection=null;

    public static void Connect(){

        System.out.print("输入账号:");
        Scanner scanner=new Scanner(System.in);
        USER=scanner.nextLine();
        System.out.print("输入密码:");
        scanner=new Scanner(System.in);
        PASS=scanner.nextLine();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void clipboard(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
    }

    public static void main(String[] args) {
        Connect();
    }
}
