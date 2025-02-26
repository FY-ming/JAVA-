package JAVA_EX_work;

import java.sql.SQLException;
import java.util.*;

public class DataProcessing {
    static Hashtable<String, User> users;

    static {//静态代码块，在程序开始时执行
        users = new Hashtable<String, User>();
        users.put("jack", new Operator("jack","123","operator"));
        users.put("rose", new Browser("rose","123","browser"));
        users.put("kate", new Administrator("kate","123","administrator"));
    }
    //返回users容器中对应的同名用户
    public static User searchUser(String name){
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }
    //返回users容器中对应的同名同密码用户
    public static User search(String name, String password){
        if (users.containsKey(name)) {
            User temp =users.get(name);
            if ((temp.getPassword()).equals(password))
                return temp;
        }
        return null;
    }
    //返回user容器中全部数据
    public static Enumeration<User> getAllUser(){

        Enumeration<User> e  = users.elements();
        return e;
    }

    //更新users容器中数据的身份
    public static boolean update(String name, String password, String role){
        User user;
        if (users.containsKey(name)) {
            if (role.equalsIgnoreCase("administrator"))//不区分大小写
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
    //向users容器中插入一个新的数据
    public static boolean insert(String name, String password, String role){
        User user;
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
    // 删除users容器中数据
    public static boolean delete(String name){

        if (users.containsKey(name)){
            users.remove(name);
            return true;
        }else
            return false;

    }



}

