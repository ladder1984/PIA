package UI;

import BLL.pwdBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class searchDialog extends JDialog{      //搜索对话框
    
    static int selectedItem;
    static String searchText;
    static String temp;
    static String[] str;
    public searchDialog(JDialog fatherDialog,String[] items){   //父对话框，搜索列表

        super(fatherDialog,true);
        setTitle("搜索");
        this.str=items;
        temp=items[0];              //默认选择第一个选项，用于用户未默认选择第一项，而没有触发监听事件
        JPanel contentPanel=new JPanel();       //创建主面板
        setContentPane(contentPanel);
        final JComboBox comboBox=new JComboBox(items);      //创建下拉列表
        comboBox.addItemListener(new ItemListener() {


            public void itemStateChanged(ItemEvent e) {             //创建监听
                temp = String.valueOf(comboBox.getSelectedItem());      //获得用户所选项

            }
        });
        contentPanel.add(new JLabel("选择搜索依据："));
        contentPanel.add(comboBox);         //添加下拉列表

        contentPanel.add(new JLabel("搜索内容："));      //添加搜索内容文本框
        final JTextField textField=new JTextField(10);
        contentPanel.add(textField);

        JButton okBtn=new JButton("确定");            //点击确定按钮提交

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            //监听事件
                searchText=textField.getText();     //获得用户所输入的搜索内容
                dispose();                          //关闭对话框
            }
        });

        contentPanel.add(okBtn);
        setSize(200, 150);              //设置对话框参数
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static int getSelectedItem() {           //外部调用，返回搜索的列
        for(int i=0;i<str.length;i++) {
            if(str[i].equals(temp)){                //列名转换为数组中位置，从1计数
                selectedItem=i+1;
                break;
            }
        }
        return selectedItem;
    }

    public static String getSearchText() {      //外部调用，返回搜索内容
        return searchText;
    }
}
