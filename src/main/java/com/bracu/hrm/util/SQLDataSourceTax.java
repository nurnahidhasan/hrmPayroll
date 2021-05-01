package com.bracu.hrm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ASUS on 07-Jan-18.
 */
public class SQLDataSourceTax {
    private Connection con;
    public Connection getSqlConnection(){
        Connection connection =null;
        try{
            System.out.printf("Print");
//            DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());
//             connection = DriverManager.getConnection("jdbc:microsoft:sqlserver://172.16.22.5:1433;databaseName=BUAIS;", "bu_it", "bracu_IT#@!");

//             if (connection != null)
//             {
//                 Statement statement = connection.createStatement();String queryString = "select * from sysobjects where type='u'";
//                 ResultSet rs = statement.executeQuery(queryString);
//                 while (
//                         rs.next()) {
//                                System.out.println(rs.getString(1));
//                        }
//             }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  connection;

    }
    public Connection getPgConnection(){
        Connection connection =null;
        try{
            System.out.printf("Print");

            DriverManager.registerDriver(new org.postgresql.Driver());
             connection = DriverManager.getConnection("jdbc:postgresql://172.16.2.110:5432/hrm", "postgres", "@#bupgadmin");

//             if (connection != null)
//             {
//                 Statement statement = connection.createStatement();String queryString = "select * from sysobjects where type='u'";
//                 ResultSet rs = statement.executeQuery(queryString);
//                 while (
//                         rs.next()) {
//                                System.out.println(rs.getString(1));
//                        }
//             }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  connection;

    }

}
