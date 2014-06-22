package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import BLL.pwdBLL;

public class pwdDialog extends JDialog{

    private static JTable table;        //显示与输入的文本框
    private static JLabel IDLabel;
    private static JTextField nameText;
    private static JTextField pwdText;
    private static JTextField commentsText;
    String[] items={"名称","密码","备注"};    //列名数列，用于传递给搜索对话框

    public pwdDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("密码备忘");       //设置标题

        JPanel contentPanel=new JPanel(new BorderLayout());     //创建主面板
        this.setContentPane(contentPanel);

        JScrollPane tablePanel=new JScrollPane();       //穿件放置表格的面板
        contentPanel.add(tablePanel,BorderLayout.CENTER);   //tablepanel置于中间
        table=pwdBLL.getJTable();                   //从pwdBLL类获取JTable
        final DefaultTableModel tableModel= (DefaultTableModel) table.getModel();   //获得表格的model

        tablePanel.setViewportView(table);          //表格加入tablepanel

        JPanel controlPanel=new JPanel(new BorderLayout());     //创建放置控制按钮的面板
        contentPanel.add(controlPanel,BorderLayout.SOUTH);

        JPanel textPanel=new JPanel();                  //创建显示文本框的面板
        controlPanel.add(textPanel,BorderLayout.NORTH);

        textPanel.add(new JLabel("编号: "));
        IDLabel=new JLabel("编号");           //添加ID标签
        textPanel.add(IDLabel);


        textPanel.add(new JLabel("名称: "));      //添加文本框
        nameText=new JTextField(10);
        textPanel.add(nameText);

        textPanel.add(new JLabel("密码: "));
        pwdText=new JTextField(10);
        textPanel.add(pwdText);

        textPanel.add(new JLabel("备注: "));
        commentsText=new JTextField(10);
        textPanel.add(commentsText);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //选择模式为单选
        table.addMouseListener(new MouseAdapter(){    //鼠标事件
            public void mouseClicked(MouseEvent e){
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object o1 = tableModel.getValueAt(selectedRow, 0);      //获得选择行的第0列元素
                Object o2 = tableModel.getValueAt(selectedRow, 1);
                Object o3 = tableModel.getValueAt(selectedRow, 2);
                Object o4 = tableModel.getValueAt(selectedRow, 3);
                IDLabel.setText(o1.toString());  //给文本框赋值
                nameText.setText(o2.toString());
                pwdText.setText(o3.toString());
                commentsText.setText(o4.toString());
            }
        });

        JPanel buttonPanel=new JPanel();            //添加按钮
        controlPanel.add(buttonPanel,BorderLayout.SOUTH);

        final JButton addButton = new JButton("添加");   //添加按钮
        addButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){   //添加监听

               int resultID=-1;     //resultID为添加数据的ID
               resultID=pwdBLL.add(nameText.getText(),pwdText.getText(),commentsText.getText());    //象数据库添加数据并获得ID
               String []rowValues = {String.valueOf(resultID),nameText.getText(),pwdText.getText(),commentsText.getText()};
               tableModel.addRow(rowValues);  //添加一行
               int rowCount = table.getRowCount() +1;   //行数加上1
                JOptionPane.showMessageDialog(null,"添加成功");
            }
        });
        buttonPanel.add(addButton);



        final JButton updateButton = new JButton("修改");   //修改按钮
        updateButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if(selectedRow!= -1)   //是否存在选中行
                {
                    //修改指定的值：

                    tableModel.setValueAt(nameText.getText(), selectedRow, 1);
                    tableModel.setValueAt(pwdText.getText(), selectedRow, 2);
                    tableModel.setValueAt(commentsText.getText(), selectedRow, 3);
                }

                pwdBLL.update(IDLabel.getText(),nameText.getText(),pwdText.getText(),commentsText.getText());   //更新数据库
                JOptionPane.showMessageDialog(null,"修改成功");
            }
        });
        buttonPanel.add(updateButton);


        final JButton delButton = new JButton("删除");        //删除按钮
        delButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                String ID;
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                ID=(String)tableModel.getValueAt(selectedRow, 0);
                if(selectedRow!=-1)  //存在选中行
                {
                    tableModel.removeRow(selectedRow);  //删除行
                }
                pwdBLL.delete(ID);
                JOptionPane.showMessageDialog(null,"删除成功");
            }
        });
        buttonPanel.add(delButton);

        final JButton searchButton = new JButton("搜索");     //搜索按钮
        searchButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
                int selectedItem;
                String searchText;
                new searchDialog(pwdDialog.this,items);     //创建搜索对话框
                selectedItem=searchDialog.getSelectedItem();        //获得搜索的列
                searchText=searchDialog.getSearchText();            //获得搜索的内容
                highlightRow(selectedItem,searchText);              //高亮查询结果所在行
            }

            private void highlightRow(final int selectedItem, final String searchText) {    //高亮指定行

                final DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
                {

                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus, int row, int column)
                    {
                        if(tableModel.getValueAt(row, selectedItem).toString().equals(searchText)){
                            setBackground(Color.yellow);        //匹配行背景设为黄色
                        }
                        else
                            setBackground(Color.white);         //其他行为白色
                        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    }
                };
                for(int i=0;i<items.length;i++)
                {
                    table.getColumn(items[i]).setCellRenderer(tcr);
                }
                tableModel.fireTableDataChanged();

            }
        });
        buttonPanel.add(searchButton);
        searchButton.setToolTipText("符合结果将高亮，无搜索结果则无高亮");



        setSize(800, 400);          //设置对话框参数
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
