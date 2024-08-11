package com.jdbc.procedure;

import com.jdbc.util.DBUtil;

import java.sql.*;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class TestMultiResult {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "{call multi_result_set(?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, 2);
        callableStatement.execute();
        ResultSet resultSet = callableStatement.getResultSet(); // 获取下一个结果集
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
        // 先获取一次结果集，然后再判断是否还有更多结果集
        while (callableStatement.getMoreResults()){ // 判断是否还有更多结果集
            System.out.println("------------");
            resultSet = callableStatement.getResultSet(); // 获取下一个结果集
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取列数
            while (resultSet.next()){ // 判断当前结果集是否还有更多数据
                System.out.println(resultSet.getString(1));
            }
            // 关闭结果集
            DBUtil.close(resultSet);
        }
        // 关闭资源
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
    }
}
