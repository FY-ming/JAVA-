/*
 * Created by JFormDesigner on Mon Nov 25 16:32:39 CST 2024
 */

package JAVA_EX_work06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;

/**
 * @author 97022
 */
public class OperatorDesigner extends JFrame {
    private GUImain loginFrame; // 持有登录窗口的引用
    private static Client application;
    public OperatorDesigner(GUImain loginFrame, User user, Client appl) {
        this.loginFrame = loginFrame; // 保存登录窗口引用
        this.user = user;
        application = appl;
        initComponents();
        // setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 不直接关闭框架
        // 设置窗口关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                application.closeConnection();
                try // connect to server, get streams, process connection
                {
                    application.connectToServer(); // create a Socket to make connection
                    application.getStreams(); // get the input and output streams
                } // end try
                catch ( EOFException eofException )
                {
                    application.displayMessage( "\n客户端终止连接" );
                } // end catch
                catch ( IOException ioException )
                {
                    ioException.printStackTrace();
                } // end catch
                showLoginFrame();// 调用方法显示登录窗口

            }
        });
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        label1 = new JLabel();
        exit = new JButton();

        //======== this ========
        setTitle("档案录入员菜单");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {
            menuBar1.setInheritsPopupMenu(true);

            //======== menu1 ========
            {
                menu1.setText("文件处理");

                //---- menuItem1 ----
                menuItem1.setText("文件上传");
                menuItem1.addActionListener(e -> new upLoadFile(this,user.getName(),application).setVisible(true));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("文件下载");
                menuItem2.addActionListener(e -> new downloadFile(this,user.getName(),application).setVisible(true));
                menu1.add(menuItem2);

                //---- menuItem4 ----
                menuItem4.setText("文件列表");
                menuItem4.addActionListener(e -> new filesList(this,application).setVisible(true));
                menu1.add(menuItem4);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("信息修改");

                //---- menuItem3 ----
                menuItem3.setText("修改密码");
                menuItem3.addActionListener(e -> new setPassword(this,user.getName(),application).setVisible(true));
                menu2.add(menuItem3);
            }
            menuBar1.add(menu2);
        }
        contentPane.add(menuBar1);
        menuBar1.setBounds(0, 0, 398, menuBar1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("欢迎来到档案录入员菜单");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 0, 400, 200);

        //---- exit ----
        exit.setText("退出");
        exit.addActionListener(e -> {
            showLoginFrame();  // 显示登录框
            application.closeConnection(); // 关闭与服务器的连接
            try // connect to server, get streams, process connection
            {
                application.connectToServer(); // create a Socket to make connection
                application.getStreams(); // get the input and output streams
            } // end try
            catch ( EOFException eofException )
            {
                application.displayMessage( "\n客户端终止连接" );
            } // end catch
            catch ( IOException ioException )
            {
                ioException.printStackTrace();
            } // end catch
        }); // 退出按钮点击事件
        contentPane.add(exit);
        exit.setBounds(165, 205, 70, 65);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(400, 300);
        setLocationRelativeTo(null);
        // 捕获窗口关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 关闭连接前执行关闭操作
                application.closeConnection();
            }
        });
    }
    private void showLoginFrame() {
        // 清空用户名和密码字段
        loginFrame.textUserName.setText("");
        loginFrame.password.setText("");
        // 显示登录窗口
        loginFrame.setVisible(true);
        // 关闭管理员界面
        dispose();
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JLabel label1;
    private JButton exit;
    private User user;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
