package com.jdbc.transcation;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class TestSavePoint {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        // 保存点
        Savepoint savepoint = null;
        try {
            // 开启事务
            connection.setAutoCommit(false);
            // sql1
            String sql1 = "insert into user values('1111', 1111)";
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            savepoint = connection.setSavepoint(); // 设置一个保存点
            int e = 10 /0; // 异常
            // sql2
            String sql2 = "insert into user values('2222', 2222)";
            statement = connection.createStatement();
            statement.executeUpdate(sql2);
            // sql3
            String sql3 = "insert into user values('3333', 3333)";
            statement = connection.createStatement();
            statement.executeUpdate(sql3);
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                // 回滚事务
//                connection.rollback();
                connection.releaseSavepoint(savepoint); // 回滚到保存点，但并不提交
                connection.commit();
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
