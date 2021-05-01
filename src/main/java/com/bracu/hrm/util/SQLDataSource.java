package com.bracu.hrm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ASUS on 07-Jan-18.
 */
public class SQLDataSource {
    private Connection con;
    public Connection getSqlConnection(){
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String userName = "bu_it";
            String password = "bracu_IT#@!";
            //String url = "jdbc:sqlserver://123.49.46.169";
//            String url = "jdbc:sqlserver://172.16.22.2";
            String url = "jdbc:sqlserver://115.127.80.14";
            con = DriverManager.getConnection(url, userName, password);
            //String result = new result[20];

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return  con;
    }

}
