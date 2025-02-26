/*
 * Created by JFormDesigner on Sat Nov 30 12:32:58 CST 2024
 */

package JAVA_EX_work05.designers;

import JAVA_EX_work05.DataProcessing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 97022
 */
public class upLoadFile extends JDialog {
    public upLoadFile(Window owner,String name) {
        super((Frame) owner,true);
        this.name = name;
        initComponents();
        // 为选择文件按钮添加事件监听器
        button1.addActionListener(this::onSelectFile);

        // 为确认按钮添加事件监听器
        okButton.addActionListener(this::onConfirm);

        // 取消按钮的事件监听器
        cancelButton.addActionListener(e -> dispose());
    }
    private void onSelectFile(ActionEvent e) {
        // 创建文件选择器
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择要上传的文件");

        // 显示文件选择对话框并获取用户选择结果
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的文件
            File selectedFile = fileChooser.getSelectedFile();
            // 将文件的绝对路径显示在文本框中
            textField2.setText(selectedFile.getAbsolutePath());
        }
    }
    private void onConfirm(ActionEvent e) {
        // 获取用户输入的文件路径、档案号和描述  
        String doc = textField1.getText();
        String fileName = textField2.getText();
        String description = textArea1.getText();

        // 验证输入是否有效  
        if (doc.isEmpty() || fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请确保档案号和文件地址都已填写!", "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        try {
            if(Objects.requireNonNull(DataProcessing.searchUser(this.name)).uploadFile(doc,this.name,timestamp,description,fileName))
            {
                JOptionPane.showMessageDialog(this, "文件上传成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // 关闭对话框
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "文件上传失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("文件上传");
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
                label1.setText("文件上传");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 375, 23);

                //---- label2 ----
                label2.setText("档案号");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(25, 55), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("文件描述");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(25, 100), label3.getPreferredSize()));

                //---- label4 ----
                label4.setText("文件地址");
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(25, 165), label4.getPreferredSize()));

                //---- button1 ----
                button1.setText("选择文件");
                contentPanel.add(button1);
                button1.setBounds(new Rectangle(new Point(280, 160), button1.getPreferredSize()));
                contentPanel.add(textField1);
                textField2.setEditable(false); // 不允许手动编辑文件路径
                textField1.setBounds(105, 45, 160, textField1.getPreferredSize().height);
                contentPanel.add(textField2);
                textField2.setBounds(105, 160, 165, textField2.getPreferredSize().height);

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
                    textArea1.setLineWrap(true);
                    scrollPane1.setViewportView(textArea1);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(110, 85, 230, 70);

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
    private JLabel label4;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private String name;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
