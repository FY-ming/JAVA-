package JAVA_EX_work02;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrator extends User {
    private String name;
    private String password;
    private String role;
    //Administrator(){}
    Administrator(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }
    public void showMenu()throws Exception
    {
        String tip_system = "档案管理员菜单";
        String infos = "****欢迎进入" + tip_system + "****\n" +
                "1.修改用户\n2.删除用户\n3.新增用户\n4.列出用户\n5.下载文件\n6.文件列表\n7.修改密码\n8.退出\n" +
                "**************************************";
        int logIn;
        Scanner sc = new Scanner(System.in);
        do {//循环实现反复执行菜单任务
            System.out.println(infos);
            System.out.println("请选择菜单:");
            try
            {
                logIn = sc.nextInt();
            }catch(InputMismatchException e)
            {
                logIn = 0;//给logIn赋值避免未赋值情况，同时logIn=0在后续switch中进入default
            }
            String tempName;
            String tempPassword;
            String tempRole;
            try {
                switch (logIn) {
                    case 1://修改用户
                        System.out.println("修改用户身份\n请输入用户名:");
                        tempName = sc.next();
                        if (DataProcessing.searchUser(tempName)!=null)
                        {
                            System.out.println("请输入用户密码");
                            tempPassword = sc.next();
                            if (DataProcessing.search(tempName, tempPassword) != null)
                            {
                                System.out.println("请输入修改后用户身份");
                                tempRole = sc.next();
                                if(DataProcessing.update(tempName, tempPassword, tempRole))
                                {
                                    System.out.println("修改成功！");
                                }
                                else
                                {
                                    System.out.println("修改失败！");
                                }
                            }else
                            {
                                System.out.println("密码错误！");
                            }
                        }else
                        {
                            System.out.println("该用户不存在！");
                        }
                        break;
                    case 2://删除用户
                        System.out.println("删除用户\n请输入用户名：");
                        tempName  = sc.next();
                        if (DataProcessing.searchUser(tempName)!=null)
                        {
                            DataProcessing.delete(tempName);
                            System.out.println("删除成功！");
                        }
                        break;
                    case 3://新增用户
                        System.out.println("新增用户\n请输入用户名：");
                        tempName  = sc.next();
                        System.out.println("请输入用户密码");
                        tempPassword = sc.next();
                        System.out.println("请输入用户身份");
                        tempRole = sc.next();
                        DataProcessing.insert(tempName, tempPassword, tempRole);
                        System.out.println("新增成功！");
                        break;
                    case 4://列出用户
                        Enumeration<User> e = DataProcessing.getAllUser();
                        System.out.println("用户列表");
                        while (e.hasMoreElements())
                        {
                            User tempUser = e.nextElement();
                            System.out.println("姓名：" + tempUser.getName() + "\t密码："+tempUser.getPassword() +"\t身份："+tempUser.getRole());
                        }
                        break;
                    case 5://下载文件
                        System.out.println("文件下载");
                        System.out.println("请输入档案号");
                        String doc;
                        doc = sc.next();
                        downloadFile(doc);
                        break;
                    case 6://文件列表
                        showFileList();
                        break;
                    case 7://修改密码
                        System.out.println("修改本人密码\n请输入新密码：");
                        password = sc.next();
                        setName(password);
                        System.out.println("修改成功！");
                        break;
                    case 8://退出
                        break;
                    default://错误输入信息
                        System.out.println("错误输入信息！");
                }
            }catch (Exception e) {
                continue;
            }
        }while(logIn != 8);
        System.out.println("档案管理员菜单退出，谢谢使用!");
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
