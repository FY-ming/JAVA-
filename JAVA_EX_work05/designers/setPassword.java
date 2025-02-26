/*
 * Created by JFormDesigner on Sat Nov 30 12:40:24 CST 2024
 */

package JAVA_EX_work05.designers;

import JAVA_EX_work05.DataProcessing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author 97022
 */
public class setPassword extends JDialog {
    public setPassword(Window owner, String name) {
        super((Frame) owner,true);
        this.name = name;
        initComponents();
        // 添加确认按钮的事件处理
        okButton.addActionListener(this::onOkButtonClick);
        // 添加取消按钮的事件处理
        cancelButton.addActionListener(e -> dispose()); // 直接关闭对话框
    }
    private void onOkButtonClick(ActionEvent e) {
        String username = this.name;
        String passwordInput = new String(passwordField1.getPassword());
        String newPasswordInput = new String(passwordField2.getPassword());
        // 弹出确认对话框
        int confirm = JOptionPane.showConfirmDialog(this,
                "您确定要修改用户 " + username + " 的密码吗?",
                "确认修改",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // 尝试修改用户密码

            try {
                if (DataProcessing.search(username, passwordInput) != null) {
                    // 登录成功，打开对应用户菜单
                    if (newPasswordInput.isEmpty())
                    {
                        JOptionPane.showMessageDialog(this, "新密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                        this.passwordField1.setText("");
                        this.passwordField2.setText("");
                    }else
                    {
                        Objects.requireNonNull(DataProcessing.searchUser(username)).setPassword(newPasswordInput);
                        DataProcessing.update(username, newPasswordInput, Objects.requireNonNull(DataProcessing.searchUser(this.name)).getRole());
                        JOptionPane.showMessageDialog(this, "成功修改用户密码!", "成功", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // 关闭对话框
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                    this.passwordField1.setText("");
                    this.passwordField2.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "修改用户失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        label4 = new JLabel();
        label5 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("修改密码");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("修改密码");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 375, label1.getPreferredSize().height);

                //---- label2 ----
                label2.setText("原密码");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(30, 85), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("新密码");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(30, 130), label3.getPreferredSize()));
                contentPanel.add(passwordField1);
                passwordField1.setBounds(120, 75, 140, 40);
                contentPanel.add(passwordField2);
                passwordField2.setBounds(120, 125, 140, 40);

                //---- label4 ----
                label4.setText("用户名");
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(30, 40), label4.getPreferredSize()));

                //---- label5 ----
                label5.setText(this.name);
                label5.setEnabled(false);
                contentPanel.add(label5);
                label5.setBounds(125, 30, 130, 40);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("确认");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("取消");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel label4;
    private JLabel label5;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private String name;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
