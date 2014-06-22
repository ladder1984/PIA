package DAL;

import java.sql.*;

public class DB {       //数据库处理类
    private static final String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=PIA/data/db.mdb";
    //数据库位置，若Access版本为2010一下，需改为{Microsoft Access Driver (*.mdb)}
    private static Connection conn=null;
    private static Statement stmt=null;

    public static void open(){         //打开数据库

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn=DriverManager.getConnection(url,"","");
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void sql(String sql){     //执行SQL语句

        try {
            stmt=conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet sqlQuery(String sql){     //执行返回ResultSet的SQL语句

        ResultSet rs=null;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void close(){        //关闭数据库连接
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
