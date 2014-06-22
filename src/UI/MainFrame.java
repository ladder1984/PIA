package UI;

import DAL.DB;
import UI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


public class MainFrame  extends JFrame{

    JToolBar myTooBar;      //工具栏
    JMenuBar myMenuBar;     //菜单栏
    
    public MainFrame(){
        setTitle("个人信息助理");     //设置标题
        JPanel contentPanel=new JPanel(new BorderLayout()); //创建主面板
        this.setContentPane(contentPanel);

        JLabel infoJbl=new JLabel("个人信息助理");
        infoJbl.setFont(new   java.awt.Font("Dialog",   1,   15));  //设置字体

        JPanel centerJp=new JPanel(new BorderLayout());     //穿件center显示面板
        centerJp.add(infoJbl,BorderLayout.CENTER);
        contentPanel.add(centerJp,BorderLayout.CENTER);
        addMenu();      //添加菜单
        addToolbar();   //添加工具栏
    }

    private void addToolbar() {         //添加工具栏
        myTooBar=new JToolBar();
        myTooBar.setSize(600,300);
        this.getContentPane().add(myTooBar, BorderLayout.NORTH);
        JButton passBtn=new JButton("口令维护");        //创建按钮
        JButton notepadBtn=new JButton("日常记事");
        JButton addrBookBtn=new JButton("通讯簿");
        JButton pwdBtn=new JButton("密码备忘");
        JButton dbMaintainBtn=new JButton("数据库维护");

        passBtn.setToolTipText("修改登陆口令");       //设置提示
        notepadBtn.setToolTipText("管理日常记事");
        addrBookBtn.setToolTipText("管理通讯簿");
        pwdBtn.setToolTipText("管理密码备忘");
        dbMaintainBtn.setToolTipText("备份、还原数据库");


        //为按钮添加监听
        notepadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new notepadDialog(MainFrame.this);      //调用notepad对话框
            }
        });

        addrBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addrBookDialog(MainFrame.this);
            }
        });

        pwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new pwdDialog(MainFrame.this);
            }
        });

        dbMaintainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dbMaintainDialog(MainFrame.this);
            }
        });

        passBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new passMaintainDialog(MainFrame.this);
            }
        });

        myTooBar.add(passBtn,null);
        myTooBar.add(notepadBtn,null);
        myTooBar.add(addrBookBtn,null);
        myTooBar.add(pwdBtn,null);
        myTooBar.add(dbMaintainBtn,null);
    }

    private void addMenu() {        //添加菜单
        myMenuBar=new JMenuBar();
        this.setJMenuBar(myMenuBar);
        JMenu fileMenu=new JMenu("文件");
        JMenu helpMenu=new JMenu("帮助");
        myMenuBar.add(fileMenu);
        myMenuBar.add(helpMenu);

        JMenuItem exitItem=new JMenuItem("退出");         //退出程序
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem helpBookItem=new JMenuItem("帮助文档 ");      //打开帮助文档
        helpBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desk=Desktop.getDesktop();

                try
                {

                    File file=new File("PIA/helpBook/help.html");
                    desk.open(file); //调用open（File f）方法打开文件
                }catch(Exception fileNotExist)
                {
                    JOptionPane.showMessageDialog(null,"文件不存在","错误",JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        JMenuItem aboutItem=new JMenuItem("关于");        //软件说明
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "作者：zeroten\n" +
                        "网址：http://www.itoldme.net\n" +
                        "版本：1.0","关于",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        helpMenu.add(helpBookItem);
        helpMenu.add(aboutItem);

        setSize(600, 400);          //设置窗口参数
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DB.close();     //窗口关闭时关闭数据库连接
                System.exit(0);
            }
        });

    }
}
