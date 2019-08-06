package com.train.day19;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lc
 * @date 2019/8/6 18:42
 */
public class TransactionDemo {
    public static void main(String[] args) {
        try {
            transfer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void transfer() throws IOException {
        Connection mycon = JdbcUtil.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            mycon.setAutoCommit(false);
            //事务中转账发起方余额扣除
            String sql1 = "update account set balance=balance-100 where id='2019001'";
            stmt = mycon.prepareStatement(sql1);
            stmt.executeUpdate();

            //事务中，转账对象余额增加
            String sql2 = "update account set balance=balance+100 where id='2019002'";
            stmt = mycon.prepareStatement(sql2);
            stmt.executeUpdate();

            //提交转账，将数据持久化更新到数据库中
            mycon.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeResource(stmt);
            JdbcUtil.closeResource(mycon);
        }



    }
}
