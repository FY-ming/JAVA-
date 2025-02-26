package JAVA_EX_work03;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;

public  class DataProcessing {
    private static boolean connectToDB=false;

    static Hashtable<String, User> users;
    static Hashtable<String, Doc> docs;

    static {
        users = new Hashtable<String, User>();
        users.put("jack", new Operator("jack","123","operator"));
        users.put("rose", new Browser("rose","123","browser"));
        users.put("kate", new Administrator("kate","123","administrator"));
        //Init();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// 捕捉系统启动时的时间戳

        docs = new Hashtable<String,Doc>();
        docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","E:\\JAVA_EX\\uploadfile\\Doc.java"));
    }

    public static  void Init(){
        // 通过随机数更新数据库状态
//        if (Math.random()>0.2)
//            connectToDB = true;
//        else
//            connectToDB = false;

    }
    public static Doc searchDoc(String ID) throws SQLException {
        // 通过档案号查找文件
        if (docs.containsKey(ID)) {
            Doc temp =docs.get(ID);
            return temp;
        }
        return null;
    }

    public static Enumeration<Doc> getAllDocs() throws SQLException{
        // 获得docs中全部文件信息
        Enumeration<Doc> e  = docs.elements();
        return e;
    }

    public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
        // 向doc中插入新数据
        Doc doc;

        if (docs.containsKey(ID))
            return false;
        else{
            doc = new Doc(ID,creator,timestamp,description,filename);
            docs.put(ID, doc);
            return true;
        }
    }

    public static User searchUser(String name) throws SQLException{
        //返回users容器中对应的同名用户
//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException( "未连接至数据库！" );
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    public static User search(String name, String password) throws SQLException {
        //返回users容器中对应的同名同密码用户
//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException("未连接至数据库！");
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

        if (users.containsKey(name)) {
            User temp =users.get(name);
            if ((temp.getPassword()).equals(password))
                return temp;
        }
        return null;
    }

    public static Enumeration<User> getAllUser() throws SQLException{
        //返回user容器中全部数据
//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException("未连接至数据库！");
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

        Enumeration<User> e  = users.elements();
        return e;
    }



    public static boolean update(String name, String password, String role) throws SQLException{
        //更新users容器中数据的身份
        User user;
//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException("未连接至数据库！");
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

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

//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException("未连接至数据库！");
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

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
//        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
//            throw new SQLException("未连接至数据库！");
//        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

        if (users.containsKey(name)){
            users.remove(name);
            return true;
        }else
            return false;

    }

    public void disconnectFromDB() {
        if ( connectToDB ){
            // close Statement and Connection
//            try{
//                if (Math.random()>0.5)
//                {
//                    System.out.println("断开数据库连接失败！");
//                    throw new SQLException( "断开数据库连接失败！" );
//                }
//
//            }catch ( SQLException sqlException ){
//                sqlException.printStackTrace();
//            }finally{
//                connectToDB = false;
//            }
            connectToDB = false;
        }
    }

}


