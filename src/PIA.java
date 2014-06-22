import DAL.DB;
import UI.MainFrame;

import javax.swing.*;
import UI.*;

//系统主程序
public class PIA {
    boolean packFrame=false;
    public PIA() {
        MainFrame frame = new MainFrame();//创建主窗体
        //优化窗体界面
        if (packFrame) {
            frame.pack();
        }
        else {
            frame.validate();
        }

        new loginDialog(frame);   //打开登陆框，初始用户名：admin，初始密码：pass
        DB.open();      //打开数据库连接

    }

    public static void main(String []args){
        new PIA();
    }
}
