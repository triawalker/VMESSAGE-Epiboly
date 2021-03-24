package com.vmsg.client.thread;

import com.vmsg.client.data.UserLogin;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginEtc {

    File file;

    List<UserLogin> UserLoginlist=new ArrayList<>();

    public LoginEtc(){

    }
    public void createfile(){
        try {
            file=new File("."+MainThread.filesep+"etc"+MainThread.filesep+"login");
            if (!file.exists()) file.mkdirs();
            file=new File(MainThread.LoginEtcPath);
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveinfo(String account,String password,Boolean pwdsave,Boolean autologin){

        try {
            readinfo();
            createfile();
            Element root=DocumentHelper.createElement("users");
            Document document=DocumentHelper.createDocument(root);
            initAutologin(autologin);//初始化自动登录，使其xml记录中只能有一组数据自动登录
            if(userExist(account)<0){
                UserLoginlist.add(new UserLogin(userNum(), account, pwdsave ? password : "",autologin));
            }else{
                UserLoginlist.get(userExist(account)).password = pwdsave ? password : "";
                UserLoginlist.get(userExist(account)).autologin = autologin;
            }
            for(UserLogin ul: UserLoginlist) {
                //添加子节点
                Element Parent_num = root.addElement("user");
                Parent_num.addAttribute("num", ul.num + "");

                Element Kid_account = Parent_num.addElement("account");
                Kid_account.setText(ul.account);

                Element Kid_password = Parent_num.addElement("password");
                Kid_password.setText(ul.password);

                Element Kid_autologin = Parent_num.addElement("autologin");
                Kid_autologin.setText(String.valueOf(ul.autologin));

            }

            //添加
            XMLWriter xmlWriter = new XMLWriter();
            xmlWriter.write(document);
            //创建文件格式
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            //设定编码
            outputFormat.setEncoding("UTF-8");
            XMLWriter xmlWriter1 = new XMLWriter(new FileOutputStream(MainThread.LoginEtcPath), outputFormat);
            xmlWriter1.write(document);
            xmlWriter1.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<UserLogin> readinfo(){
        file=new File(MainThread.LoginEtcPath);
        if(file.exists()) {
            try {
                SAXReader saxReader = new SAXReader();
                InputStream inputStream = new FileInputStream(file);
                Document document = saxReader.read(inputStream);
                Element root = document.getRootElement();
                Iterator iterator = root.elementIterator();
                while (iterator.hasNext()) {
                    UserLogin userLogin=new UserLogin();
                    Element element = (Element) iterator.next();
                    List<Attribute> attributeList = element.attributes();
                    for (Attribute attribute : attributeList) {
                        //System.out.println(attribute.getName() + "=" + attribute.getValue());
                        userLogin.setNum(Integer.valueOf(attribute.getValue()));
                    }
                    Iterator childIterator = element.elementIterator();
                    while (childIterator.hasNext()) {
                        Element child = (Element) childIterator.next();
                        //System.out.println(child.getName() + "=" + child.getStringValue());
                        switch (child.getName()){
                            case "account":
                                userLogin.setAccount(child.getStringValue());
                                break;
                            case "password":
                                userLogin.setPassword(child.getStringValue());
                                break;
                            case "autologin":
                                userLogin.setAutologin(Boolean.parseBoolean(child.getStringValue()));
                                break;
                        }
                    }
                    UserLoginlist.add(userLogin);
                }
                //System.out.println("----------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return UserLoginlist;
    }

    public int userExist(String account){
        int i=-1;
        for(UserLogin ul:UserLoginlist){
            i++;
            if (ul.account.equals(account)) return i;
        }
        return -1;
    }

    public int userNum(){
        int num=0;
        int max=-1;
        for (UserLogin ul: UserLoginlist){
            ul.setNum(num++);
        }
        return num;
    }
    public void initAutologin(boolean autologin){
        if (autologin) {
            for (UserLogin ul : UserLoginlist) {
                ul.setAutologin(false);
            }
        }
    }
}

