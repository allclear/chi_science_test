package com.train.day19;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * @author lc
 * @date 2019/8/6 10:02
 */
public class JdbcUtil {
    private final static JdbcUtil JDBC_UTIL = new JdbcUtil();

    private JdbcUtil() {
    }

    /**
     * 获取实例
     * @return
     */
    public static JdbcUtil getInstance() {
        return JDBC_UTIL;
    }

    /**
     * 获取数据库连接
     * @return
     * @throws IOException
     */
    public Connection getConnection() throws IOException {
        //操作Properties文件的工具类
        Properties properties = new Properties();
        String filePath = JdbcUtil.class.getResource("/").getFile() + "jdbc.properties";
        InputStream is = new FileInputStream(filePath);
        properties.load(is);
        String driver = properties.get("driver").toString();
        String uri = properties.get("uri").toString();
        String user = properties.get("user").toString();
        String password = properties.get("password").toString();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(uri, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param autoCloseable
     */
    public static void closeResource(AutoCloseable autoCloseable){
        if (null != autoCloseable){
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
