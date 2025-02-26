/*
 * Created by JFormDesigner on Mon Nov 25 16:59:57 CST 2024
 */

package JAVA_EX_work05.designers;

import JAVA_EX_work05.DataProcessing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * @author 97022
 */
public class GUImain extends JFrame {
    public GUImain() {
        initComponents();
    }

    private void register(ActionEvent e) {
        String username = textUserName.getText();
        String passwordInput = new String(password.getPassword());

        try {
            if (DataProcessing.searchUser(username) != null) {
                if (DataProcessing.search(username, passwordInput) != null) {
                    // 登录成功，打开对应用户菜单
                    switch (DataProcessing.users.get(username).getRole())
                    {
                        case "operator":
                            OperatorDesigner operatorMenu = new OperatorDesigner(this, DataProcessing.users.get(username));
                            operatorMenu.setVisible(true);
                            this.dispose(); // 关闭登录窗口
                            break;
                        case "browser":
                            BrowserDesigner browserMenu = new BrowserDesigner(this, DataProcessing.users.get(username));
                            browserMenu.setVisible(true);
                            this.dispose(); // 关闭登录窗口
                            break;
                        case "administrator":
                            AdministratorDesigner administratorMenu = new AdministratorDesigner(this, DataProcessing.users.get(username));
                            administratorMenu.setVisible(true);
                            this.dispose(); // 关闭登录窗口
                            default:
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "该用户不存在！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "数据库错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Menu = new JLabel();
        User = new JLabel();
        key = new JLabel();
        register = new JButton();
        password = new JPasswordField();
        textUserName = new JTextField();

        //======== this ========
        setTitle("档案系统菜单页面");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- Menu ----
        Menu.setText("档案系统");
        Menu.setVerticalAlignment(SwingConstants.TOP);
        Menu.setHorizontalAlignment(SwingConstants.CENTER);
        Menu.setFont(Menu.getFont().deriveFont(Menu.getFont().getSize() + 15f));
        contentPane.add(Menu);
        Menu.setBounds(0, 0, 400, 55);

        //---- User ----
        User.setText("用户名：");
        contentPane.add(User);
        User.setBounds(new Rectangle(new Point(30, 70), User.getPreferredSize()));

        //---- key ----
        key.setText("密码：");
        contentPane.add(key);
        key.setBounds(new Rectangle(new Point(30, 130), key.getPreferredSize()));

        //---- register ----
        register.setText("登录");
        register.addActionListener(e -> register(e));
        contentPane.add(register);
        register.setBounds(new Rectangle(new Point(165, 180), register.getPreferredSize()));
        contentPane.add(password);
        password.setBounds(120, 125, 160, 40);
        contentPane.add(textUserName);
        textUserName.setBounds(120, 65, 160, 40);

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel Menu;
    private JLabel User;
    private JLabel key;
    private JButton register;
    JPasswordField password;
    JTextField textUserName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 主方法
    public static void main(String[] args) {
        // 使用 SwingUtilities.invokeLater 启动应用程序
        SwingUtilities.invokeLater(() -> {
            GUImain frame = new GUImain();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭操作
            frame.setVisible(true); // 显示窗口
        });
    }
}
