package UI;

import BLL.pwdBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class searchDialog extends JDialog{      //�����Ի���
    
    static int selectedItem;
    static String searchText;
    static String temp;
    static String[] str;
    public searchDialog(JDialog fatherDialog,String[] items){   //���Ի��������б�

        super(fatherDialog,true);
        setTitle("����");
        this.str=items;
        temp=items[0];              //Ĭ��ѡ���һ��ѡ������û�δĬ��ѡ���һ���û�д��������¼�
        JPanel contentPanel=new JPanel();       //���������
        setContentPane(contentPanel);
        final JComboBox comboBox=new JComboBox(items);      //���������б�
        comboBox.addItemListener(new ItemListener() {


            public void itemStateChanged(ItemEvent e) {             //��������
                temp = String.valueOf(comboBox.getSelectedItem());      //����û���ѡ��

            }
        });
        contentPanel.add(new JLabel("ѡ���������ݣ�"));
        contentPanel.add(comboBox);         //��������б�

        contentPanel.add(new JLabel("�������ݣ�"));      //������������ı���
        final JTextField textField=new JTextField(10);
        contentPanel.add(textField);

        JButton okBtn=new JButton("ȷ��");            //���ȷ����ť�ύ

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            //�����¼�
                searchText=textField.getText();     //����û����������������
                dispose();                          //�رնԻ���
            }
        });

        contentPanel.add(okBtn);
        setSize(200, 150);              //���öԻ������
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static int getSelectedItem() {           //�ⲿ���ã�������������
        for(int i=0;i<str.length;i++) {
            if(str[i].equals(temp)){                //����ת��Ϊ������λ�ã���1����
                selectedItem=i+1;
                break;
            }
        }
        return selectedItem;
    }

    public static String getSearchText() {      //�ⲿ���ã�������������
        return searchText;
    }
}
