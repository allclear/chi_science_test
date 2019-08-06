package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//饿汉式单例
public class JdbcUtil {
    private static final JdbcUtil JDBC_UTIL = new JdbcUtil();

    public static JdbcUtil getIntance(){
        return JDBC_UTIL;
    }
//关闭连接
    public static void closeConnection(AutoCloseable closeable){
        if(null != closeable){

            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    //建立数据库连接
    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw?characterEncoding=utf-8","root","huayuhui");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;


    }
}
