package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestPreparedStatementBatch {
    public static void main(String[] args) throws SQLException {
        // 获取连接 jdbc:mysql://localhost:3306/JDBC?rewriteBatchStatements=true
        Connection connection = DBUtil.getConnection();
        connection.setAutoCommit(false); // 关闭自动提交
        // 提供 sql 模板
        String sql = "insert into user values(?,?)";
        // 获取 Statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 循环填入参数
        for (int i = 11; i < 201; i++) {
            preparedStatement.setString(1, UUID.randomUUID().toString().replace("-", ""));
            preparedStatement.setInt(2, i);
            preparedStatement.addBatch();
        }
        // 执行批次
        int[] ints = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(ints));
        // 手动提交
        connection.commit();
        // 关闭资源
        DBUtil.close(connection);
        DBUtil.close(preparedStatement);
    }
}
