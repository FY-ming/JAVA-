package JAVA_EX_work02;

import java.sql.SQLException;
import java.io.IOException;

public abstract class User {
    private String name;
    private String password;
    private String role;

    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }
    User() {}

    public boolean changeSelfInfo(String password) throws SQLException{
        //写用户信息到存储
        if (DataProcessing.update(name, password, role)){
            this.password=password;
            System.out.println("修改成功");
            return true;
        }else
            return false;
    }

    public boolean downloadFile(String filename) throws IOException{
        double ranValue=Math.random();
        if (ranValue>0.5) {
            System.out.println("访问文件错误！");
            throw new IOException("访问文件错误！");//有参构造，出错时弹出的文字
        }
        System.out.println("下载文件... ...");
        return true;
    }

    public void showFileList() throws SQLException{
        double ranValue=Math.random();
        if (ranValue>0.5)
        {
            System.out.println("访问文件数据库错误！");
            throw new SQLException( "访问文件数据库错误！" );
        }
        System.out.println("文件列表");
        System.out.println("列表... ...");
    }

    public abstract void showMenu()throws Exception;

    public void exitSystem(){
        System.out.println("系统退出, 谢谢使用 ! ");
        System.exit(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

