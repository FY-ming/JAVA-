package JAVA_EX_work;
//底层User类
public abstract class User {
    private String name;
    private String password;
    private String role;

    //构造函数
    User(){}
    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }
    //修改User对象的密码与身份
    public boolean changeSelfInfo(String password){
        if (DataProcessing.update(name, password, role)){
            this.password=password;
            System.out.println("修改成功！");
            return true;
        }else
            return false;
    }
    //抽象类，继承展示子类的操作菜单
    public abstract void showMenu();
    //下载文件
    public boolean downloadFile(String filename){
        System.out.println("下载文件... ...");
        return true;
    }
    //展示文件列表
    public void showFileList(){
        System.out.println("列表... ...");
    }
    //退出系统，终止程序
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

