package UI;

import BLL.LoginBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class loginDialog extends JDialog {      //�������ʱ�ĵ�½�Ի���

    public loginDialog(MainFrame frame) {
        super(frame,true);
        setTitle("��½��");

        JPanel contentPanel=new JPanel(new FlowLayout());   //���������
        this.setContentPane(contentPanel);

        contentPanel.add(new JLabel("�û���:"));
        final JTextField nameText=new JTextField(10);
        contentPanel.add(nameText);

        contentPanel.add(new JLabel("����:��"));
        final JPasswordField passText=new JPasswordField(10);
        contentPanel.add(passText);

        JButton okBtn=new JButton("ȷ��");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //������ť
                String name;        //�û��������û���
                String pass;        //�û�����������
                int result;         //��½У����
                name=nameText.getText();    //����û�������û���
                pass=String.valueOf(passText.getPassword());    //����û����������
                result= LoginBLL.check(name,pass);      //У���û���������
                if(result==1)           //������ȷ�������
                {
                    JOptionPane.showMessageDialog(null,"��½�ɹ�"); //�ɹ���ʾ
                    loginDialog.this.dispose();     //�رնԻ���
                }
                else        //�����������ʾ
                {
                    JOptionPane.showMessageDialog(null,"�������");
                }

            }
        });

        JButton cancelBtn=new JButton("ȡ��");        //ȡ����ť
        cancelBtn.addActionListener(new ActionListener() {  //�û�ȡ����¼���˳����
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPanel.add(okBtn);
        contentPanel.add(cancelBtn);

        addWindowListener(new WindowAdapter() {     //�û��رնԻ������˳����
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(200,150);       //���öԻ������
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
