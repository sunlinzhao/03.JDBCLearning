package com.jdbc.transcation;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class TestTransaction {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        try {
            // 开启事务
            connection.setAutoCommit(false);
            // sql1
            String sql1 = "insert into user values('1234', 5678)";
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
//            int e = 10 /0; // 异常
            // sql2
            String sql2 = "insert into user values('5678', 1234)";
            statement = connection.createStatement();
            statement.executeUpdate(sql2);
            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            try {
                // 回滚事务
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection);
            DBUtil.close(statement);
        }
    }
}
