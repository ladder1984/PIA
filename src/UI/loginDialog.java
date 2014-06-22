package UI;

import BLL.LoginBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class loginDialog extends JDialog {      //软件启动时的登陆对话框

    public loginDialog(MainFrame frame) {
        super(frame,true);
        setTitle("登陆框");

        JPanel contentPanel=new JPanel(new FlowLayout());   //设置主面板
        this.setContentPane(contentPanel);

        contentPanel.add(new JLabel("用户名:"));
        final JTextField nameText=new JTextField(10);
        contentPanel.add(nameText);

        contentPanel.add(new JLabel("密码:　"));
        final JPasswordField passText=new JPasswordField(10);
        contentPanel.add(passText);

        JButton okBtn=new JButton("确定");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //监听按钮
                String name;        //用户所输入用户名
                String pass;        //用户所输入密码
                int result;         //登陆校验结果
                name=nameText.getText();    //获得用户输入的用户名
                pass=String.valueOf(passText.getPassword());    //获得用户输入的密码
                result= LoginBLL.check(name,pass);      //校验用户名、密码
                if(result==1)           //输入正确进入软件
                {
                    JOptionPane.showMessageDialog(null,"登陆成功"); //成功提示
                    loginDialog.this.dispose();     //关闭对话框
                }
                else        //输入错误则提示
                {
                    JOptionPane.showMessageDialog(null,"密码错误");
                }

            }
        });

        JButton cancelBtn=new JButton("取消");        //取消按钮
        cancelBtn.addActionListener(new ActionListener() {  //用户取消登录则退出软件
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPanel.add(okBtn);
        contentPanel.add(cancelBtn);

        addWindowListener(new WindowAdapter() {     //用户关闭对话框则退出软件
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(200,150);       //设置对话框参数
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
