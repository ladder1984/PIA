import DAL.DB;
import UI.MainFrame;

import javax.swing.*;
import UI.*;

//ϵͳ������
public class PIA {
    boolean packFrame=false;
    public PIA() {
        MainFrame frame = new MainFrame();//����������
        //�Ż��������
        if (packFrame) {
            frame.pack();
        }
        else {
            frame.validate();
        }

        new loginDialog(frame);   //�򿪵�½�򣬳�ʼ�û�����admin����ʼ���룺pass
        DB.open();      //�����ݿ�����

    }

    public static void main(String []args){
        new PIA();
    }
}
