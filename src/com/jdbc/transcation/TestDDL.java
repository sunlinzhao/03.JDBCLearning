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
public class TestDDL {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        try {
            // 开启事务
            connection.setAutoCommit(false);
            // sql
            String sql = "create table fuck(id int)";
            statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            int x = 10 / 0; // 制造异常
            // sql1
            String sql1 = "create table shit(id int)";
            statement = connection.createStatement();
            int i1 = statement.executeUpdate(sql1);
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                // 回滚事务
                connection.rollback(); // 对于DDL语句。回滚无效，且永远自动提交
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
            DBUtil.close(statement);
        }
    }
}
