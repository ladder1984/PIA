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

public class passMaintainDialog extends JDialog{        //��½Ȩ��ά��������
    public passMaintainDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("��½Ȩ��ά��");

        JPanel contentPanel=new JPanel(new FlowLayout());
        this.setContentPane(contentPanel);

        contentPanel.add(new JLabel("���û���:"));      //�û������ı���
        final JTextField nameText=new JTextField(10);
        contentPanel.add(nameText);

        contentPanel.add(new JLabel("������:��"));      //�û������ı���
        final JTextField passText=new JTextField(10);
        contentPanel.add(passText);

        JButton okBtn=new JButton("�޸�");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName;
                String newPass;

                newName=nameText.getText();     //������û���
                newPass=passText.getText();     //���������
                changePass(newName, newPass);
                JOptionPane.showMessageDialog(null,"��½Ȩ���޸ĳɹ�"); //��ʾ��ʾ
                dispose();


            }
        });

        JButton cancelBtn=new JButton("ȡ��");        //���ȡ����ť�رնԻ���
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
