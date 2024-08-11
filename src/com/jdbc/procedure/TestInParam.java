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
public class TestInParam {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "{call inparam(?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, 2);
        boolean execute = callableStatement.execute();
        ResultSet resultSet = callableStatement.getResultSet();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
        DBUtil.close(resultSet);
    }
}
