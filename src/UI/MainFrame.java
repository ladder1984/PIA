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

    JToolBar myTooBar;      //������
    JMenuBar myMenuBar;     //�˵���
    
    public MainFrame(){
        setTitle("������Ϣ����");     //���ñ���
        JPanel contentPanel=new JPanel(new BorderLayout()); //���������
        this.setContentPane(contentPanel);

        JLabel infoJbl=new JLabel("������Ϣ����");
        infoJbl.setFont(new   java.awt.Font("Dialog",   1,   15));  //��������

        JPanel centerJp=new JPanel(new BorderLayout());     //����center��ʾ���
        centerJp.add(infoJbl,BorderLayout.CENTER);
        contentPanel.add(centerJp,BorderLayout.CENTER);
        addMenu();      //��Ӳ˵�
        addToolbar();   //��ӹ�����
    }

    private void addToolbar() {         //��ӹ�����
        myTooBar=new JToolBar();
        myTooBar.setSize(600,300);
        this.getContentPane().add(myTooBar, BorderLayout.NORTH);
        JButton passBtn=new JButton("����ά��");        //������ť
        JButton notepadBtn=new JButton("�ճ�����");
        JButton addrBookBtn=new JButton("ͨѶ��");
        JButton pwdBtn=new JButton("���뱸��");
        JButton dbMaintainBtn=new JButton("���ݿ�ά��");

        passBtn.setToolTipText("�޸ĵ�½����");       //������ʾ
        notepadBtn.setToolTipText("�����ճ�����");
        addrBookBtn.setToolTipText("����ͨѶ��");
        pwdBtn.setToolTipText("�������뱸��");
        dbMaintainBtn.setToolTipText("���ݡ���ԭ���ݿ�");


        //Ϊ��ť��Ӽ���
        notepadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new notepadDialog(MainFrame.this);      //����notepad�Ի���
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

    private void addMenu() {        //��Ӳ˵�
        myMenuBar=new JMenuBar();
        this.setJMenuBar(myMenuBar);
        JMenu fileMenu=new JMenu("�ļ�");
        JMenu helpMenu=new JMenu("����");
        myMenuBar.add(fileMenu);
        myMenuBar.add(helpMenu);

        JMenuItem exitItem=new JMenuItem("�˳�");         //�˳�����
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem helpBookItem=new JMenuItem("�����ĵ� ");      //�򿪰����ĵ�
        helpBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desk=Desktop.getDesktop();

                try
                {

                    File file=new File("PIA/helpBook/help.html");
                    desk.open(file); //����open��File f���������ļ�
                }catch(Exception fileNotExist)
                {
                    JOptionPane.showMessageDialog(null,"�ļ�������","����",JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        JMenuItem aboutItem=new JMenuItem("����");        //���˵��
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "���ߣ�zeroten\n" +
                        "��ַ��http://www.itoldme.net\n" +
                        "�汾��1.0","����",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        helpMenu.add(helpBookItem);
        helpMenu.add(aboutItem);

        setSize(600, 400);          //���ô��ڲ���
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DB.close();     //���ڹر�ʱ�ر����ݿ�����
                System.exit(0);
            }
        });

    }
}
