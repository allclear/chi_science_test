package com.mysql;

import java.sql.*;

/**
 * @author lc
 * @date 2019/8/5 19:15
 */
public class DBManager {
    private String uri="jdbc:mysql://localhost:3306/cong_l";
    private String usr="root";
    private String psw="123456";
    private Connection mycon=null;
    private ResultSet rs=null;
    private Statement stmt=null;

    /**
     * 构造器，new实例是自动获取数据库连接
     */
    public DBManager(){
        this.mycon=this.getConnnection();
    }

    /**
     * 获取连接
     * @return
     */
    private Connection getConnnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mycon= DriverManager.getConnection(uri,usr,psw);
            if(mycon!=null){
                System.out.println("数据库连接成功！");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动器连接失败！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return mycon;
    }

    /**
     * @param sql
     * @return rs
     */
    public ResultSet executeQuery(String sql) {
        try {
            stmt = mycon.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("执行获取结果集错误");
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行更新的方法
     * @param sql
     * @return count
     * */
    public int executeUpdate(String sql){
        int count=0;

        try {
            stmt=mycon.createStatement();
            count=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("执行更新错误");
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 释放数据库连接
     */
    public void releaseDB() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (mycon != null) {
                mycon.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
