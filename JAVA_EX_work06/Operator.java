package JAVA_EX_work06;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Operator extends User {
    private String name;
    private String password;
    private String role;
    //Operator(){}
    Operator(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }
    public void showMenu()
    {
        String tip_system = "档案录入员菜单";
        String infos = "****欢迎进入" + tip_system + "****\n" +
                "1.上传文件\n2.下载文件\n3.文件列表\n4.修改密码\n5.退出\n" +
                "**************************************";
        int logIn;
        Scanner sc = new Scanner(System.in);
        do {//循环实现反复执行菜单任务
            System.out.println(infos);
            System.out.println("请选择菜单:");
            String doc;
            try
            {
                logIn = sc.nextInt();
            }catch(InputMismatchException e)
            {
                logIn = 0;//给logIn赋值避免未赋值情况，同时logIn=0在后续switch中进入default
            }
            try {
                switch (logIn) {
                    case 1://上传文件
                        System.out.println("上传文件\n请输入源文件地址：");
                        String fileName = sc.next();
                        System.out.println("请输入档案号");
                        doc = sc.next();
                        System.out.println("请输入档案描述");
                        String fileDes;
                        sc.nextLine();//清空回车
                        fileDes = sc.nextLine();
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        if(uploadFile(doc,this.name,timestamp,fileDes,fileName))
                        {
                            System.out.println("上传成功");
                        }else{
                            System.out.println("上传失败");
                        }
                        break;
                    case 2://下载文件
                        System.out.println("文件下载");
                        System.out.println("请输入档案号");
                        doc = sc.next();
                        if(downloadFile(doc)){
                            System.out.println("下载成功！");
                        }else {
                            System.out.println("下载失败！");
                        }
                        break;
                    case 3://文件列表
                        showFileList();
                        break;
                    case 4://修改密码
                        System.out.println("修改本人密码\n请输入新密码：");
                        password = sc.next();
                        setName(password);
                        System.out.println("修改成功！");
                        break;
                    case 5://退出
                        break;
                    default://错误输入信息
                        System.out.println("错误输入信息！");
                        break;
                }
            }catch (Exception e) {
                //continue;
            }
        }while(logIn != 5);
        System.out.println("档案录入员菜单退出，谢谢使用!");
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
