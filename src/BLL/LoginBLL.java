package BLL;

import DAL.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2014/6/15.
 */
public class LoginBLL {     //��½�߼�������

    public static int check(String name, String pass) {
        int result=1;       //У����
        String rightName=null,rightPass=null;
        ResultSet rs;
        String sql="select * from User";        //�������ݿ�����
        DB.open();
        rs=DB.sqlQuery(sql);

        try {                   //������ݿ�洢���û���������
            while (rs.next()){
                rightName=rs.getString("Name");
                rightPass=rs.getString("Pass");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rightName.equals(name)&&rightPass.equals(pass))
            result=1;   //У��ɹ�����1
        else
            result=0;   //У��ʧ�ܷ���2

        DB.close();     //�ر����ݿ�����
        return result;
    }
}
