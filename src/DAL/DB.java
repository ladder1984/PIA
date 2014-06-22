package DAL;

import java.sql.*;

public class DB {       //���ݿ⴦����
    private static final String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=PIA/data/db.mdb";
    //���ݿ�λ�ã���Access�汾Ϊ2010һ�£����Ϊ{Microsoft Access Driver (*.mdb)}
    private static Connection conn=null;
    private static Statement stmt=null;

    public static void open(){         //�����ݿ�

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

    public static void sql(String sql){     //ִ��SQL���

        try {
            stmt=conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet sqlQuery(String sql){     //ִ�з���ResultSet��SQL���

        ResultSet rs=null;
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void close(){        //�ر����ݿ�����
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
