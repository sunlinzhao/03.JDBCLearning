package com.jdbc.procedure;

import com.jdbc.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class TestInOutParam {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "{call inoutparam(?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, 5);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        int anInt = callableStatement.getInt(1);
        System.out.println(anInt);
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
    }
}
