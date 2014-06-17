package BLL;

import DAL.DB;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;



public class addrBookBLL{
    private static JTable table;

    private static DefaultTableModel tableModel;

    static String  sql=null;

    public addrBookBLL(){



    }

    public static JTable getJTable(){   //建立并返回JTable

        sql="SELECT * FROM AddrBook";

        Vector data = new Vector();
        Vector dataRow = new Vector();

        ResultSet rs=DB.sqlQuery(sql);

        int colNum=6;

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

        Vector cols = new Vector();
        cols.addElement("编号");
        cols.addElement("姓名");
        cols.addElement("性别");
        cols.addElement("电话");
        cols.addElement("电子邮件");
        cols.addElement("地址");
        tableModel = new DefaultTableModel(data, cols);
        table = new JTable(tableModel);


        return table;
    }

    public static int add(String name, String sex, String phone, String email, String address){
        name="'"+name+"'";
        sex="'"+sex+"'";
        phone="'"+phone+"'";
        email="'"+email+"'";
        address="'"+address+"'";
        sql="INSERT INTO AddrBook (name,sex,phone,email,address) VALUES " + "(" +
                name + ","+ sex+ ","+phone+ ","+email+ ","+address
                +")";

        DB.sql(sql);


        String sql2= "Select * from AddrBook where name="+name;
        int resultID = -1;
        ResultSet rs=DB.sqlQuery(sql2);

        try {
            while (rs.next()){
                resultID=rs.getInt("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultID;


    }

    public static void update(String ID,String name, String sex, String phone, String email, String address){
        String sqlDelete;
        name="'"+name+"'";
        sex="'"+sex+"'";
        phone="'"+phone+"'";
        email="'"+email+"'";
        address="'"+address+"'";
        sql="INSERT INTO AddrBook (ID,name,sex,phone,email,address) VALUES " + "(" +

                ID+","+name + ","+ sex+ ","+phone+ ","+email+ ","+address
                +")";


        sqlDelete="DELETE FROM AddrBook WHERE  ID= "+ID;

        DB.sql(sqlDelete);
        DB.sql(sql);

}

    public static void delete(String ID) {
        sql="DELETE FROM AddrBook WHERE  ID= "+ID;
        DB.sql(sql);

    }



}
