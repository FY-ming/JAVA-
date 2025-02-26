package JAVA_EX_work06;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public  class DataProcessing {
    private static boolean connectToDB=false;

    public static Hashtable<String, User> users;
    static Hashtable<String, Doc> docs;

    static {
        try {
            users = DataProcessing.users();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        users.put("jack", new Operator("jack","123","operator"));
//        users.put("rose", new Browser("rose","123","browser"));
//        users.put("kate", new Administrator("kate","123","administrator"));
        //Init();

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// 捕捉系统启动时的时间戳

        try {
            docs = DataProcessing.docs();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","E:\\JAVA_EX\\uploadfile\\Doc.java"));
    }

    public  static  void  init(){
        connectToDB = DataProcessing.connectToDB() ;
    }

    public static Doc searchDoc(String ID) throws SQLException {
        // 通过档案号查找文件
        if  (!connectToDB) {
            throw  new  SQLException("未连接至数据库！");
        }
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
        if  (!connectToDB) {
            throw  new  SQLException("未连接至数据库！");
        }
        if (docs.containsKey(ID))
            return false;
        else{
            doc = new Doc(ID,creator,timestamp,description,filename);
            docs.put(ID, doc);
            int result = 0;
            Connection connection;
            PreparedStatement preparedStatementstatement;
            String driverName = "com.mysql.jdbc.Driver"; // 加载数据库驱动类
            String url = "jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
            String use = "root"; // 数据库用户
            String pwd = "200511@fym.com";
            try {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, use, pwd); // 建立数据库连接
                String sql = "insert into doc_info (ID,creator,timestamp,description,filename) values(?,?,?,?,?)";
                preparedStatementstatement = connection.prepareStatement(sql);
                preparedStatementstatement.setString(1,ID);
                preparedStatementstatement.setString(2,creator);
                preparedStatementstatement.setTimestamp(3,timestamp);
                preparedStatementstatement.setString(4,description);
                preparedStatementstatement.setString(5,filename);
                result = preparedStatementstatement.executeUpdate();
                preparedStatementstatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                System.out.println("数据驱动错误");
            } catch (SQLException e) {
                System.out.println("数据库错误");
            }
            return result == 1;
        }
    }

    public static User searchUser(String name) throws SQLException{
        //返回users容器中对应的同名用户
        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
            throw new SQLException( "未连接至数据库！" );
        }
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
        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
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
        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
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
        if ( !connectToDB ) {
//            System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
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

            Connection connection;
            PreparedStatement preparedStatementstatement;
            String driverName = "com.mysql.jdbc.Driver"; // 加载数据库驱动类
            String url = "jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
            String use = "root"; // 数据库用户
            String pwd = "200511@fym.com";
            try {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, use, pwd); // 建立数据库连接
                String sql = "UPDATE user_info SET password = ?, role = ? WHERE username = ?";
                preparedStatementstatement = connection.prepareStatement(sql);
                preparedStatementstatement.setString(1,password);
                preparedStatementstatement.setString(2,role);
                preparedStatementstatement.setString(3,name);
                preparedStatementstatement.executeUpdate();//更新至数据库
                preparedStatementstatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                System.out.println("数据驱动错误");
            } catch (SQLException e) {
                System.out.println("数据库错误");
            }
            return true;
        }else
            return false;
    }

    public static boolean insert(String name, String password, String role) throws SQLException{
        //向users容器中插入一个新的数据
        User user;

        if ( !connectToDB ) {
            //System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
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
            int result = 0;
            Connection connection;
            PreparedStatement preparedStatementstatement;
            String driverName = "com.mysql.jdbc.Driver"; // 加载数据库驱动类
            String url = "jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
            String use = "root"; // 数据库用户
            String pwd = "200511@fym.com";
            try {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, use, pwd); // 建立数据库连接
                String sql = "insert into user_info (username,password,role) values(?,?,?)";
                preparedStatementstatement = connection.prepareStatement(sql);
                preparedStatementstatement.setString(1,name);
                preparedStatementstatement.setString(2,password);
                preparedStatementstatement.setString(3,role);
                result = preparedStatementstatement.executeUpdate();
                preparedStatementstatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                System.out.println("数据驱动错误");
            } catch (SQLException e) {
                System.out.println("数据库错误");
            }
            return result == 1;
        }
    }

    public static boolean delete(String name) throws SQLException{
        // 删除users容器中数据
        if ( !connectToDB ) {
            //System.out.println("未连接至数据库！");
            throw new SQLException("未连接至数据库！");
        }
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("数据库查询错误！");
//            throw new SQLException("数据库查询错误！");
//        }

        if (users.containsKey(name)){
            Connection connection;
            PreparedStatement preparedStatementstatement;
            String driverName = "com.mysql.jdbc.Driver"; // 加载数据库驱动类
            String url = "jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
            String use = "root"; // 数据库用户
            String pwd = "200511@fym.com";
            try {
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, use, pwd); // 建立数据库连接
                String sql = "delete FROM user_info WHERE username = ?";
                preparedStatementstatement = connection.prepareStatement(sql);
                preparedStatementstatement.setString(1,name);
                preparedStatementstatement.executeUpdate();
                preparedStatementstatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                System.out.println("数据驱动错误");
            } catch (SQLException e) {
                System.out.println("数据库错误");
            }
            users.remove(name);
            return  true ;
        }else  {
            return  false ;
        }
    }

    //建立数据库链接
    static boolean connectToDB(){
        String driverName="com.mysql.jdbc.Driver"; // 加载数据库驱动类
        String url="jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
        String user="root"; // 数据库用户
        String password="200511@fym.com";
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(connection != null)
            return true;
        return false;
    }
    //从数据库中获取用户信息
    static  Hashtable<String,User> users() throws SQLException, ClassNotFoundException {
        init();
        String driverName="com.mysql.jdbc.Driver"; // 加载数据库驱动类
        String url="jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
        String use="root"; // 数据库用户
        String pwd="200511@fym.com";
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, use, pwd);
        if(!connectToDB)
            throw  new  SQLException("未连接至数据库！");
        Hashtable<String,User> userHashtable = new Hashtable<>();
        Statement statement  = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY );
        ResultSet resultSet;
        String sql="select * from user_info";
        resultSet = statement.executeQuery(sql);
        while  (resultSet.next()){
            String username=resultSet.getString("username");
            String password=resultSet.getString("password");
            String role=resultSet.getString("role");
            User user = null;
            switch(role){
                case"administrator":
                    user = new Administrator(username,password,role);
                    break;
                case "browser":
                    user = new Browser(username,password,role);
                    break;
                case "operator":
                    user = new Operator(username,password,role);
            }
            userHashtable.put(username,user);
        }
        resultSet.close();
        statement.close();
        return userHashtable;
    }

    //从数据库获得用户档案
    static Hashtable<String,Doc> docs() throws SQLException, ClassNotFoundException {
        init();
        String driverName="com.mysql.jdbc.Driver"; // 加载数据库驱动类
        String url="jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
        String use="root"; // 数据库用户
        String pwd="200511@fym.com";
        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, use, pwd);
        if(!connectToDB)
            throw  new  SQLException("未连接至数据库！");
        Statement statement  = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY );
        ResultSet resultSet;
        String sql="select * from doc_info";
        resultSet = statement.executeQuery(sql);
        Hashtable<String, Doc> docHashtable = new Hashtable<>();
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String creator = resultSet.getString("creator");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            String description = resultSet.getString("description");
            String filename = resultSet.getString("filename");
            docHashtable.put(id,new Doc(id,creator,timestamp,description,filename));
        }
        return docHashtable;
    }

    /**
     * TODO 关闭数据库连接
     *
     * @param
     * @return void
     * @throws
     */
    public  static  void  disconnectFromDataBase() {
        if  (connectToDB){
            String driverName="com.mysql.jdbc.Driver"; // 加载数据库驱动类
            String url="jdbc:mysql://localhost:3306/filelist"; // 声明数据库的URL
            String user="root"; // 数据库用户
            String password="200511@fym.com";
            Connection connection = null;
// close Statement and Connection
            try {
                connection =DriverManager.getConnection(url,user,password);
                connection.close();
            }catch  (SQLException sqlException){
                sqlException.printStackTrace();
            }finally {
                connectToDB = false ;
            }
        }
    }
}


