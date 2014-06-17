package BLL;

import DAL.DB;
import com.sun.org.apache.bcel.internal.generic.Select;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;



public class pwdBLL{            //�߼������࣬�ɽ����������ݣ�����sql��䲢ִ��
    private static JTable table;        //����������

    private static DefaultTableModel tableModel;        //�������ģ��

    static String  sql=null;

    public pwdBLL(){

    }

    public static JTable getJTable(){   //����������JTable

        sql="SELECT * FROM Pwd";        //sql��䣬��ñ������

        Vector data = new Vector();     //�����������
        Vector dataRow = new Vector();  //ÿ�е�����

        ResultSet rs=DB.sqlQuery(sql);      //��ò�ѯ���

        int colNum=4;               //����������

        try {
            while (rs.next()) {
                //�µ�һ�м�¼
                dataRow = new Vector ();
                for (int i = 1; i <= colNum; i ++) {
                    dataRow.add(rs.getString(i).toString());
                }

                //��������ӵ��ܽ����
                data.add(dataRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector cols = new Vector();     //������������
        cols.addElement("���");
        cols.addElement("����");
        cols.addElement("����");
        cols.addElement("��ע");
        tableModel = new DefaultTableModel(data, cols);     //������񣬲��������
        table = new JTable(tableModel);

        return table;       //���ر��
    }

    public static int add(String name, String pwd, String comments){        //insert��䣬���һ������
        name="'"+name+"'";          //����sql���
        pwd="'"+pwd+"'";
        comments="'"+comments+"'";
        sql="INSERT INTO Pwd (Name,Pwd,comments) VALUES " + "(" + name + ","+pwd+","+comments+")";

        DB.sql(sql);        //ִ��sql���

        String sql2= "Select * from pwd where name="+name;      //����½����ݵ�ID
        int resultID = -1;
        ResultSet rs=DB.sqlQuery(sql2);

        try {
            while (rs.next()){
                resultID=rs.getInt("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultID;        //����ID

    }

    public static void update(String ID, String name, String pwd, String comments){     //update��䣬�޸�һ������
        String sqlDelete;       //����sql���
        name="'"+name+"'";
        pwd="'"+pwd+"'";
        comments="'"+comments+"'";
        sql="INSERT INTO Pwd VALUES "+"("+ID+","+name+","+pwd+","+comments+")";

        sqlDelete="DELETE FROM pwd WHERE  ID= "+ID;     //ɾ��ԭ����
        DB.sql(sqlDelete);
        DB.sql(sql);            //����޸ĺ�����ݣ����޸�Ч��
    }

    public static void delete(String ID) {      //delete��䣬ɾ��һ������
        sql="DELETE FROM pwd WHERE  ID= "+ID;   //����sql���
        DB.sql(sql);            //ִ��ɾ�����
    }


}
