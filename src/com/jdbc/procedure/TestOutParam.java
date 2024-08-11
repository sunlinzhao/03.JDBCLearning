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
public class TestOutParam {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "{call outparam(?, ?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1, 2); // 入参
        // 对应out参数的处理，标注参数类型
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        boolean execute = callableStatement.execute();
        String string = callableStatement.getString(2); // 出参
        System.out.println(string);
        DBUtil.close(connection);
        DBUtil.close(callableStatement);
    }
}
