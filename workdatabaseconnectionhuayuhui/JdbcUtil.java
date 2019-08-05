package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    private static final JdbcUtil JDBC_UTIL = new JdbcUtil();

    public static JdbcUtil getIntance(){
        return JDBC_UTIL;
    }

    public static void closeConnection(AutoCloseable closeable){
        if(null != closeable){

            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw","root","huayuhui");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;


    }
}
