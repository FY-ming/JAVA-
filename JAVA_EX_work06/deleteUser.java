/*
 * Created by JFormDesigner on Sat Nov 30 12:39:58 CST 2024
 */

package JAVA_EX_work06;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author 97022
 */
public class deleteUser extends JDialog {
    public deleteUser(Window owner, String name, Client appl) {
        super((Frame) owner,true);
        this.name = name;
        application = appl;
        initComponents();

        // 添加确认按钮的事件处理
        okButton.addActionListener(this::onOkButtonClick);
        // 添加取消按钮的事件处理
        cancelButton.addActionListener(e -> dispose()); // 直接关闭对话框
    }
    private void onOkButtonClick(ActionEvent e) {
        String username = (String) comboBox1.getSelectedItem();

        // 简单的用户删除验证
        // 弹出确认对话框
        int confirm = JOptionPane.showConfirmDialog(this,
                "您确定要删除用户 " + username + " 吗?",
                "确认删除",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // 尝试删除用户
            try {
                DataProcessing.delete(username);
                JOptionPane.showMessageDialog(this, "成功删除用户!", "成功", JOptionPane.INFORMATION_MESSAGE);
                application.sendData("CLIENT >>> DeleteUser_SUCCESS");
                application.displayMessage("客户端 >>> 删除用户成功\n");
                dispose(); // 关闭对话框
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "删除用户失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("删除用户");
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
                label1.setText("删除用户");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 375, label1.getPreferredSize().height);

                //---- label4 ----
                label4.setText("待删除用户名");
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label4);
                label4.setBounds(15, 75, 100, 35);

                //---- comboBox1 ----
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
                contentPanel.add(comboBox1);
                comboBox1.setBounds(130, 60, 140, 60);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label4;
    private JComboBox comboBox1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private String name;
    private Client application;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
