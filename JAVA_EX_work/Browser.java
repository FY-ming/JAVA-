package JAVA_EX_work;

import java.util.Scanner;

public class Browser extends User {
    private String name;
    private String password;
    private String role;
    Browser(){}
    Browser(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }
    public void showMenu()//档案管理员菜单函数
    {
        String tip_system = "档案浏览员菜单";
        String infos = "****欢迎进入" + tip_system + "****\n" +
                "1.下载文件\n2.文件列表\n3.修改密码\n4.退出\n" +
                "**************************************";
        int logIn;
        Scanner sc = new Scanner(System.in);
        do {//循环实现反复执行菜单任务
            System.out.println(infos);
            System.out.println("请选择菜单:");
            logIn = sc.nextInt();
            switch (logIn) {
                case 1://下载文件
                    System.out.println("文件下载");
                    System.out.println("请输入档案号");
                    String doc;
                    doc = sc.next();
                    System.out.println("下载成功！");
                    break;
                case 2://文件列表
                    System.out.println("文件列表");
                    System.out.println("列表... ...");
                    break;
                case 3://修改密码
                    System.out.println("修改本人密码\n请输入新密码：");
                    password = sc.next();
                    setName(password);
                    System.out.println("修改成功！");
                    break;
                case 4://退出
                    break;
                default://错误输入信息
                    System.out.println("错误输入信息！");
            }
        }while(logIn != 4);
        System.out.println("档案浏览员菜单退出，谢谢使用!");
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
