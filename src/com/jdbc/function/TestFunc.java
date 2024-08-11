package com.jdbc.function;

import com.jdbc.util.DBUtil;

import java.sql.*;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class TestFunc {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "{?=call func1(?)}"; // 函数调用
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setInt(2, 2);
        boolean execute = callableStatement.execute();
        String string = callableStatement.getString(1);
        System.out.println(string);
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
    }
}
