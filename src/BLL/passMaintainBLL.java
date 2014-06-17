package BLL;

import DAL.DB;

/**
 * Created by Administrator on 2014/6/15.
 */
public class passMaintainBLL {
    private static String sql;

    public static void changePass(String newName, String newPass) {
        newName="'"+newName+"'";        //…˙≥…sql”Ôæ‰
        newPass="'"+newPass+"'";
        sql="update User set Name="
                +newName+" " +
                "," +
                "Pass =" + newPass + "where ID=1";
        DB.sql(sql);        //÷¥––sql”Ôæ‰

    }
}
