package BLL;

import DAL.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Administrator on 2014/6/16.
 */
public class notepadBLL {
    private static JTable table;

    private static DefaultTableModel tableModel;

    static String  sql=null;

    public notepadBLL(){



    }
    public static JTable getJTable() {      //建立并返回JTable
        sql="SELECT * FROM Notepad";

        Vector data = new Vector();
        Vector dataRow = new Vector();

        ResultSet rs= DB.sqlQuery(sql);

        int colNum=5;

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
        cols.addElement("日期");
        cols.addElement("类别");
        cols.addElement("标题");
        cols.addElement("备注");
        tableModel = new DefaultTableModel(data, cols);
        table = new JTable(tableModel);


        return table;
    }

    public static int add(String mydate, String sort, String caption, String comments) {
        int resultID = -1;
        mydate="'"+mydate+"'";
        sort="'"+sort+"'";
        caption="'"+caption+"'";
        comments="'"+comments+"'";
        sql="INSERT INTO Notepad (mydate,sort,caption,comments) VALUES "+ "(" + mydate + ","+sort+"," +caption + "," + comments + ")";

         DB.sql(sql);

      String sql2= "Select * from Notepad where caption="+caption;

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

    public static void update(String ID, String mydate, String sort, String caption, String comments) {
        String sqlDelete;
        mydate="'"+mydate+"'";
        sort="'"+sort+"'";
        caption="'"+caption+"'";
        comments="'"+comments+"'";
        String sql="INSERT INTO Notepad (ID,mydate,sort,caption,comments) VALUES "+ "("+ID+","+ mydate + ","+sort+"," +caption + "," + comments + ")";

        sqlDelete="DELETE FROM Notepad WHERE  ID= "+ID;
        DB.sql(sqlDelete);

        DB.sql(sql);
    }



    public static void delete(String ID) {
        sql="DELETE FROM Notepad WHERE  ID= "+ID;
        DB.sql(sql);
    }
}
