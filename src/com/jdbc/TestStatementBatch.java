package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestStatementBatch {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        // 批量插入10条数据
        for (int i = 0; i < 10; i++) {
            String id = UUID.randomUUID().toString().replace("-", "");
            String sql = "INSERT INTO user VALUES ('" + id + "', " + i + ")";
            statement.addBatch(sql);
        }
        int[] ints = statement.executeBatch();
        System.out.println(Arrays.toString(ints));
        DBUtil.close(connection);
        DBUtil.close(statement);
    }
}
