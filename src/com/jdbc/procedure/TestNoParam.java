package com.jdbc.procedure;

import com.jdbc.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class TestNoParam {
    public static void main(String[] args) throws SQLException {
        // 获取连接
        Connection connection = DBUtil.getConnection();
        String sql = "{call noparam()}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        boolean execute = callableStatement.execute(); // 调用存储过程无论是更新还是查询都可以
        ResultSet resultSet = callableStatement.getResultSet(); // 获取结果集
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
        DBUtil.close(resultSet);
    }
}
