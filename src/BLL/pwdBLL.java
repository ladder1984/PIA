package BLL;

import DAL.DB;
import com.sun.org.apache.bcel.internal.generic.Select;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;



public class pwdBLL{            //逻辑处理类，由界面类获得内容，生成sql语句并执行
    private static JTable table;        //创建表格变量

    private static DefaultTableModel tableModel;        //创建表格模型

    static String  sql=null;

    public pwdBLL(){

    }

    public static JTable getJTable(){   //建立并返回JTable

        sql="SELECT * FROM Pwd";        //sql语句，获得表格内容

        Vector data = new Vector();     //表格数据容器
        Vector dataRow = new Vector();  //每行的容器

        ResultSet rs=DB.sqlQuery(sql);      //获得查询结果

        int colNum=4;               //定义表格列数

        try {
            while (rs.next()) {
                //新的一行记录
                dataRow = new Vector ();
                for (int i = 1; i <= colNum; i ++) {
                    dataRow.add(rs.getString(i).toString());
                }

                //将该行添加的总结果中
                data.add(dataRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector cols = new Vector();     //创建列名容器
        cols.addElement("编号");
        cols.addElement("名称");
        cols.addElement("密码");
        cols.addElement("备注");
        tableModel = new DefaultTableModel(data, cols);     //穿件表格，并添加数据
        table = new JTable(tableModel);

        return table;       //返回表格
    }

    public static int add(String name, String pwd, String comments){        //insert语句，添加一行内容
        name="'"+name+"'";          //生成sql语句
        pwd="'"+pwd+"'";
        comments="'"+comments+"'";
        sql="INSERT INTO Pwd (Name,Pwd,comments) VALUES " + "(" + name + ","+pwd+","+comments+")";

        DB.sql(sql);        //执行sql语句

        String sql2= "Select * from pwd where name="+name;      //获得新建内容的ID
        int resultID = -1;
        ResultSet rs=DB.sqlQuery(sql2);

        try {
            while (rs.next()){
                resultID=rs.getInt("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultID;        //返回ID

    }

    public static void update(String ID, String name, String pwd, String comments){     //update语句，修改一行内容
        String sqlDelete;       //生成sql语句
        name="'"+name+"'";
        pwd="'"+pwd+"'";
        comments="'"+comments+"'";
        sql="INSERT INTO Pwd VALUES "+"("+ID+","+name+","+pwd+","+comments+")";

        sqlDelete="DELETE FROM pwd WHERE  ID= "+ID;     //删除原数据
        DB.sql(sqlDelete);
        DB.sql(sql);            //添加修改后的数据，起到修改效果
    }

    public static void delete(String ID) {      //delete语句，删除一行内容
        sql="DELETE FROM pwd WHERE  ID= "+ID;   //生成sql语句
        DB.sql(sql);            //执行删除语句
    }


}
