package training_chi.day17;

import java.sql.*;

/**
 * @author WXJ
 * @version 1.0
 * @date 2019/8/5
 */
public class JDBCUtil {
    private static final JDBCUtil JDBC_UTIL = new JDBCUtil();
    public JDBCUtil(){}
    public static JDBCUtil getInstance(){
        return JDBC_UTIL;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chi_train?useSSL=false",
                    "root","123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
