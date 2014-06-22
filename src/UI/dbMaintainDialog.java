package UI;

import DAL.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class dbMaintainDialog extends JDialog{      //数据库维护界面类

    public dbMaintainDialog(MainFrame mainFrame) {
        super(mainFrame,true);
        setTitle("数据库维护");

        JPanel contentPanel=new JPanel();
        this.setContentPane(contentPanel);

        final JLabel tipsJbl=new JLabel("");

        JTextArea backupText=new JTextArea("点击“备份”按钮，将在db文件夹下生成备份文件db.backup　　　　　　　　　　　　　");
        backupText.setEditable(false);
        JButton backBtn=new JButton("备份");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        //添加监听
                String srcFile;
                String destPath;
                srcFile="PIA/data/db.mdb";
                destPath="PIA/data/db.backup";
                DB.close();     //关闭数据库
                FileCopy(srcFile,destPath);     //复制文件
                DB.open();     //打开数据库
                tipsJbl.setText("备份成功");
            }
        });

        JTextArea recoverText=new JTextArea("点击还原按钮，将从db文件夹下的db.backup还原数据库，如不存在备份文件，将不能还原");
        recoverText.setEditable(false);
        JButton recoverBtn=new JButton("还原");
        recoverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String srcFile;
                String destPath;
                srcFile="PIA/data/db.backup";
                destPath="PIA/data/db.mdb";

                File file=new File(srcFile);
                if(file.exists()){          //检测备份文件是否存在
                    DB.close();     //关闭数据库
                    FileCopy(srcFile,destPath);     //复制文件
                    DB.open();     //打开数据库
                    tipsJbl.setText("还原成功");
                }
                else{
                    JOptionPane.showMessageDialog(null,"备份文件不存在，请先备份！");
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

    private static void FileCopy(String readfile,String writeFile) {        //文件复制函数
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
