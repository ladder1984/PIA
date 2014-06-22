package UI;

import BLL.LoginBLL;
import BLL.passMaintainBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static BLL.passMaintainBLL.*;

public class passMaintainDialog extends JDialog{        //登陆权限维护界面类
    public passMaintainDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("登陆权限维护");

        JPanel contentPanel=new JPanel(new FlowLayout());
        this.setContentPane(contentPanel);

        contentPanel.add(new JLabel("新用户名:"));      //用户输入文本框
        final JTextField nameText=new JTextField(10);
        contentPanel.add(nameText);

        contentPanel.add(new JLabel("新密码:　"));      //用户输入文本框
        final JTextField passText=new JTextField(10);
        contentPanel.add(passText);

        JButton okBtn=new JButton("修改");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName;
                String newPass;

                newName=nameText.getText();     //获得新用户名
                newPass=passText.getText();     //获得新密码
                changePass(newName, newPass);
                JOptionPane.showMessageDialog(null,"登陆权限修改成功"); //显示提示
                dispose();


            }
        });

        JButton cancelBtn=new JButton("取消");        //点击取消按钮关闭对话框
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPanel.add(okBtn);
        contentPanel.add(cancelBtn);

        setSize(200,150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
