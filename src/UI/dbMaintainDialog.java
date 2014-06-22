package UI;

import DAL.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class dbMaintainDialog extends JDialog{      //���ݿ�ά��������

    public dbMaintainDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("���ݿ�ά��");

        JPanel contentPanel=new JPanel();
        this.setContentPane(contentPanel);

        final JLabel tipsJbl=new JLabel("");

        JTextArea backupText=new JTextArea("��������ݡ���ť������db�ļ��������ɱ����ļ�db.backup��������������������������");
        backupText.setEditable(false);
        JButton backBtn=new JButton("����");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //��Ӽ���
                String srcFile;
                String destPath;
                srcFile="PIA/data/db.mdb";
                destPath="PIA/data/db.backup";
                DB.close();     //�ر����ݿ�
                FileCopy(srcFile,destPath);     //�����ļ�
                DB.open();     //�����ݿ�
                tipsJbl.setText("���ݳɹ�");
            }
        });

        JTextArea recoverText=new JTextArea("�����ԭ��ť������db�ļ����µ�db.backup��ԭ���ݿ⣬�粻���ڱ����ļ��������ܻ�ԭ");
        recoverText.setEditable(false);
        JButton recoverBtn=new JButton("��ԭ");
        recoverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String srcFile;
                String destPath;
                srcFile="PIA/data/db.backup";
                destPath="PIA/data/db.mdb";

                File file=new File(srcFile);
                if(file.exists()){          //��ⱸ���ļ��Ƿ����
                    DB.close();     //�ر����ݿ�
                    FileCopy(srcFile,destPath);     //�����ļ�
                    DB.open();     //�����ݿ�
                    tipsJbl.setText("��ԭ�ɹ�");
                }
                else{
                    JOptionPane.showMessageDialog(null,"�����ļ������ڣ����ȱ��ݣ�");
                }

            }
        });

        contentPanel.add(backupText);
        contentPanel.add(backBtn);
        contentPanel.add(recoverText);
        contentPanel.add(recoverBtn);
        contentPanel.add(tipsJbl);

        setSize(600, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static void FileCopy(String readfile,String writeFile) {        //�ļ����ƺ���
        try {
            FileInputStream input = new FileInputStream(readfile);
            FileOutputStream output = new FileOutputStream(writeFile);
            int read = input.read();
            while ( read != -1 ) {
                output.write(read);
                read = input.read();
            }
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
