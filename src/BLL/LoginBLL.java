package BLL;

import DAL.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2014/6/15.
 */
public class LoginBLL {     //登陆逻辑处理类

    public static int check(String name, String pass) {
        int result=1;       //校验结果
        String rightName=null,rightPass=null;
        ResultSet rs;
        String sql="select * from User";        //建立数据库连接
        DB.open();
        rs=DB.sqlQuery(sql);

        try {                   //获得数据库存储的用户名、密码
            while (rs.next()){
                rightName=rs.getString("Name");
                rightPass=rs.getString("Pass");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(rightName.equals(name)&&rightPass.equals(pass))
            result=1;   //校验成功返回1
        else
            result=0;   //校验失败返回2

        DB.close();     //关闭数据库连接
        return result;
    }
}
