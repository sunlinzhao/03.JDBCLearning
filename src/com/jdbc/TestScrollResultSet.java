package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class TestScrollResultSet {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from user";
        // 设置结果集可滚动可更新
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next(); // 向后移动
        System.out.println(resultSet.getString(1));
        resultSet.next(); // 向后移动
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getRow()); // 获取当前行
        resultSet.afterLast(); // 设置最后一行之后
        resultSet.previous(); // 向前移动
        System.out.println(resultSet.getString(1));
        resultSet.updateInt(2, 6666); // 更新当前行
        resultSet.updateRow(); // 提交更新，数据库也会更新

        DBUtil.close(resultSet);
        DBUtil.close(statement);
        DBUtil.close(connection);
    }
}
