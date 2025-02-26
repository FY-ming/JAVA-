/*
 * Created by JFormDesigner on Sat Nov 30 12:39:46 CST 2024
 */

package JAVA_EX_work07;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * @author 97022
 */
public class addUser extends JDialog {
    public addUser(Window owner, Client appl) {
        super((Frame) owner,true);
        application = appl;
        initComponents();
        // 添加确认按钮的事件处理
        okButton.addActionListener(this::onOkButtonClick);
        // 添加取消按钮的事件处理
        cancelButton.addActionListener(e -> dispose()); // 直接关闭对话框
    }
    private void onOkButtonClick(ActionEvent e) {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());
        String confirmPassword = new String(passwordField2.getPassword());
        String role = (String) comboBox1.getSelectedItem();

        // 简单的用户输入验证
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空!", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "密码不能为空!", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "密码不匹配!", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if(DataProcessing.searchUser(username)!=null)
            {
                JOptionPane.showMessageDialog(this, "用户已存在!", "添加失败", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                DataProcessing.insert(username, password, role);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // 清空用户名和密码字段
        this.textField1.setText("");
        this.passwordField1.setText("");
        this.passwordField2.setText("");
        JOptionPane.showMessageDialog(this, "用户添加成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
        application.sendData("CLIENT >>> AddUser_SUCCESS");
        application.displayMessage("客户端 >>> 用户添加成功\n");
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
        label6 = new JLabel();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("新增用户");
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
                label1.setText("新增用户");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 375, label1.getPreferredSize().height);

                //---- label2 ----
                label2.setText("密码");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(30, 85), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("确认密码");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(30, 130), label3.getPreferredSize()));
                contentPanel.add(passwordField1);
                passwordField1.setBounds(120, 75, 170, 40);
                contentPanel.add(passwordField2);
                passwordField2.setBounds(120, 120, 170, 40);

                //---- label4 ----
                label4.setText("用户名");
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(30, 40), label4.getPreferredSize()));

                //---- label6 ----
                label6.setText("新用户身份");
                contentPanel.add(label6);
                label6.setBounds(new Rectangle(new Point(30, 170), label6.getPreferredSize()));

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "operator",
                    "browser",
                    "administrator"
                }));
                contentPanel.add(comboBox1);
                comboBox1.setBounds(120, 165, 170, comboBox1.getPreferredSize().height);
                contentPanel.add(textField1);
                textField1.setBounds(120, 25, 170, 45);

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
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel label4;
    private JLabel label6;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private Client application;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
