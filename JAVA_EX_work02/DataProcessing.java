package JAVA_EX_work02;

import java.util.*;
import java.sql.*;

public  class DataProcessing {
    private static boolean connectToDB=false;

    static Hashtable<String, User> users;

    static {
        users = new Hashtable<String, User>();
        users.put("jack", new Operator("jack","123","operator"));
        users.put("rose", new Browser("rose","123","browser"));
        users.put("kate", new Administrator("kate","123","administrator"));
        Init();
    }

    public static  void Init(){
        // 通过随机数更新数据库状态
        if (Math.random()>0.2)
            connectToDB = true;
        else
            connectToDB = false;

    }

    public static User searchUser(String name) throws SQLException{
        //返回users容器中对应的同名用户
        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException( "未连接至数据库！" );
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    public static User search(String name, String password) throws SQLException {
        //返回users容器中对应的同名同密码用户
        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }

        if (users.containsKey(name)) {
            User temp =users.get(name);
            if ((temp.getPassword()).equals(password))
                return temp;
        }
        return null;
    }

    public static Enumeration<User> getAllUser() throws SQLException{
        //返回user容器中全部数据
        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }

        Enumeration<User> e  = users.elements();
        return e;
    }



    public static boolean update(String name, String password, String role) throws SQLException{
        //更新users容器中数据的身份
        User user;
        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }

        if (users.containsKey(name)) {
            if (role.equalsIgnoreCase("administrator"))
                user = new Administrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);
            users.put(name, user);
            return true;
        }else
            return false;
    }

    public static boolean insert(String name, String password, String role) throws SQLException{
        //向users容器中插入一个新的数据
        User user;

        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }

        if (users.containsKey(name))
            return false;
        else{
            if (role.equalsIgnoreCase("administrator"))
                user = new Administrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);
            users.put(name, user);
            return true;
        }
    }

    public static boolean delete(String name) throws SQLException{
        // 删除users容器中数据
        if ( !connectToDB ) {
            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("数据库查询错误！");
            throw new SQLException("数据库查询错误！");
        }

        if (users.containsKey(name)){
            users.remove(name);
            return true;
        }else
            return false;

    }

    public void disconnectFromDB() {
        if ( connectToDB ){
            // close Statement and Connection
            try{
                if (Math.random()>0.5)
                {
                    System.out.println("断开数据库连接失败！");
                    throw new SQLException( "断开数据库连接失败！" );
                }

            }catch ( SQLException sqlException ){
                sqlException.printStackTrace();
            }finally{
                connectToDB = false;
            }
        }
    }

}


