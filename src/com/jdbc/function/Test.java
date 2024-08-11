package com.jdbc.function;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/11
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "select func1(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,2);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
        DBUtil.close(connection);
        DBUtil.close(preparedStatement);
        DBUtil.close(resultSet);


    }
}
