package JAVA_EX_work03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception
    {
        String tip_system = "档案系统";
        String infos = "****欢迎进入" + tip_system + "****\n" +
                "1.登录\n2.退出\n" +
                "**************************************";
            int logIn;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println(infos);
                System.out.println("请选择菜单:");
                logIn = sc.nextInt();
                if (logIn == 1)
                {
                    System.out.println("请输入用户名");
                    String name = sc.next();
                    try {
                        if (DataProcessing.searchUser(name)!=null)
                        {
                            System.out.println("请输入用户密码");
                            String password = sc.next();
                            if (DataProcessing.search(name, password) != null)
                            {
                                DataProcessing.users.get(name).showMenu();
                            }else
                            {
                                System.out.println("密码错误！");
                            }
                        }else
                        {
                            System.out.println("该用户不存在！");
                        }
                    }catch (Exception e)
                    {
                        continue;
                    }
                }else if (logIn != 2)
                {
                    System.out.println("错误输入信息！");
                }
            }while(logIn != 2);
            System.out.println("系统退出，谢谢使用!");

    }
}

