/*
 * Created by JFormDesigner on Sat Nov 30 12:39:31 CST 2024
 */

package JAVA_EX_work05.designers;

import JAVA_EX_work05.DataProcessing;
import JAVA_EX_work05.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author 97022
 */
public class setUsersInfo extends JDialog {
    public setUsersInfo(Window owner,String name) {
        super((Frame) owner,true);
        this.name = name;
        initComponents();
        // 添加确认按钮的事件处理
        okButton.addActionListener(this::onOkButtonClick);
        // 添加取消按钮的事件处理
        cancelButton.addActionListener(e -> dispose()); // 直接关闭对话框
    }
    private void onOkButtonClick(ActionEvent e) {
        String username = (String) comboBox1.getSelectedItem();
        String passwordInput = new String(passwordField1.getPassword());
        String userRole = (String) comboBox2.getSelectedItem();
        // 弹出确认对话框
        int confirm = JOptionPane.showConfirmDialog(this,
                "您确定要修改用户 " + username + " 的身份吗?",
                "确认修改",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // 尝试修改用户信息

            try {
                if (DataProcessing.search(username, passwordInput) != null) {
                    // 登录成功，打开对应用户菜单
                    DataProcessing.update(username, passwordInput, userRole);
                    JOptionPane.showMessageDialog(this, "成功修改用户信息!", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // 关闭对话框
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                    this.passwordField1.setText("");
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
        label0 = new JLabel();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        label1 = new JLabel();
        label3 = new JLabel();
        comboBox2 = new JComboBox<>();
        comboBox1 = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("修改用户信息");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label0 ----
                label0.setText("修改用户信息");
                label0.setFont(label0.getFont().deriveFont(label0.getFont().getStyle() | Font.BOLD, label0.getFont().getSize() + 5f));
                label0.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label0);
                label0.setBounds(0, 0, 375, label0.getPreferredSize().height);

                //---- label2 ----
                label2.setText("用户密码");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(30, 95), label2.getPreferredSize()));
                contentPanel.add(passwordField1);
                passwordField1.setBounds(120, 85, 140, 40);

                //---- label1 ----
                label1.setText("用户名");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(30, 40), label1.getPreferredSize()));

                //---- label3 ----
                label3.setText("更新身份");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(30, 145), label3.getPreferredSize()));

                //---- comboBox2 ----
                comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "operator",
                    "browser",
                    "administrator"
                }));
                comboBox2.setMaximumRowCount(12);
                contentPanel.add(comboBox2);
                comboBox2.setBounds(120, 135, 135, comboBox2.getPreferredSize().height);
                contentPanel.add(comboBox1);
                comboBox1.setBounds(120, 35, 135, comboBox1.getPreferredSize().height);
                Enumeration<User> E  ;
                try {
                    E = DataProcessing.getAllUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                while (E.hasMoreElements())
                {
                    // 填充 comboBox1
                    User tempUser = E.nextElement();
                    if (!tempUser.getName().equals(this.name))
                    {
                        comboBox1.addItem(tempUser.getName());
                    }

                }

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
    private JLabel label0;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JLabel label1;
    private JLabel label3;
    private JComboBox<String> comboBox2;
    private JComboBox comboBox1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private String name;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
