package JAVA_EX_work06;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

public abstract class User {
    private String name;
    private String password;
    private String role;


    String uploadpath = "e:\\JAVA_EX\\uploadfile\\";
    String downloadpath = "e:\\JAVA_EX\\downloadfile\\";


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

    public boolean uploadFile(String ID, String creator, Timestamp timestamp, String description, String filename) throws IOException, SQLException{
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("访问文件错误！");
//            throw new IOException("访问文件错误！");//有参构造，出错时弹出的文字
//        }
        try{
            File source = new File(filename);
            File dest = new File(uploadpath + source.getName());
            if (DataProcessing.searchDoc(ID) == null) {
                // 防止文件重复
                DataProcessing.insertDoc(ID, creator, timestamp, description, filename);//向docs中插入数据
                copyFile(source, dest);
            }
        }catch (Exception e){
            return false;//捕捉文件读写异常
        }
        return true;
    }

    public boolean downloadFile(String ID) throws IOException{
//        double ranValue=Math.random();
//        if (ranValue>0.5) {
//            System.out.println("访问文件错误！");
//            throw new IOException("访问文件错误！");//有参构造，出错时弹出的文字
//        }
        try{
            Doc temp_doc;
            File source, dest;
            if (DataProcessing.searchDoc(ID) != null)
            {
                temp_doc = DataProcessing.searchDoc(ID);
                source = new File(temp_doc.getFilename());
                dest = new File(downloadpath + source.getName());
            }else {
                System.out.println("不存在该文件！");
                return false;
            }
            copyFile(source,dest);
        }catch (Exception e){
            return false;//捕捉文件读写异常
        }
        return true;
    }

    public void showFileList() throws SQLException{
        //展示文件列表
//        double ranValue=Math.random();
//        if (ranValue>0.5)
//        {
//            System.out.println("访问文件数据库错误！");
//            throw new SQLException( "访问文件数据库错误！" );
//        }
        System.out.println("文件列表");
        Enumeration<Doc> e = DataProcessing.getAllDocs();
        while (e.hasMoreElements())
        {
            Doc tempDoc = e.nextElement();
            System.out.println("档案名：" + tempDoc.getID() + "\t上传人："+tempDoc.getCreator() +"\t上传时间："+ Doc.TimestampToStr(tempDoc.getTimestamp()) + "\t文件描述："+tempDoc.getDescription() + "\t文件名："+tempDoc.getFilename() );
        }

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

    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}

