/*
 * Created by JFormDesigner on Sat Nov 30 12:34:29 CST 2024
 */

package JAVA_EX_work06;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Vector;

/**
 * @author 97022
 */
public class downloadFile extends JDialog {
    public downloadFile(Window owner,String name, Client appl) {
        super((Frame) owner,true);
        this.name = name;
        application = appl;
        initComponents();
        // 使用用户数据填充表格
        populateUserTable();
        // 添加确认按钮的事件处理
        okButton.addActionListener(this::onOkButtonClick);
        // 添加取消按钮的事件处理
        cancelButton.addActionListener(e -> dispose()); // 直接关闭对话框

    }
    private void populateUserTable() {
        // 定义表格列名
        String[] columnNames = {"档案名", "文件名", "上传人", "上传时间", "文件描述"};
        // 使用 Vector 创建表格行数据
        Vector<Vector<Object>> data = new Vector<>();


        Enumeration<Doc> E;
        try {
            E = DataProcessing.getAllDocs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (E.hasMoreElements())
        {
            // 填充表格
            Doc tempDoc = E.nextElement();
            File source;
            try {
                tempDoc = DataProcessing.searchDoc(tempDoc.getID());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            source = new File(tempDoc.getFilename());
            Vector<Object> row = new Vector<>();
            row.add(tempDoc.getID()); // 档案名
            row.add(source.getName()); // 文件名
            row.add(tempDoc.getCreator()); // 上传人
            row.add(Doc.TimestampToStr(tempDoc.getTimestamp())); // 上传时间
            row.add(tempDoc.getDescription()); // 文件描述
            data.add(row);
        }
        // 创建表格模型并设置到表格中
        DefaultTableModel model = new DefaultTableModel(data, new Vector<>(Arrays.asList(columnNames))) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 所有单元格都不可编辑
            }
        };
        table1.setModel(model); // 设置模型到表格
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 设置选择模式
    }
    private void onOkButtonClick(ActionEvent e) {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) { // 检查是否选择了行
            String fileDoc = (String) table1.getValueAt(selectedRow, 0); // 从选中行获取文件名
            int confirm = JOptionPane.showConfirmDialog(this,
                    "您确定要下载该文件吗?",
                    "确认下载",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // 尝试下载文件
                try {
                    if(Objects.requireNonNull(DataProcessing.searchUser(this.name)).downloadFile(fileDoc)){
                        JOptionPane.showMessageDialog(this, "文件下载成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
                        application.sendData("CLIENT >>> DownloadFile_SUCCESS");
                        application.displayMessage("客户端 >>> 文件下载成功\n");
                    }else {
                        JOptionPane.showMessageDialog(this, "文件下载失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "文件下载失败！", "错误", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "数据库错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "请先选择一个文件!", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("文件下载");
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
                label1.setText("文件下载");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 375, 23);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 25, 360, 175);

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
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private String name;
    private Client application;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
