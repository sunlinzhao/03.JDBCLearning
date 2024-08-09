package com.jdbc;

import com.jdbc.util.DBObject;
import com.jdbc.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/9
 */
public class TestDBUtil {
    public static void main(String[] args) {
        test2();
    }
    public static void test2(){
        String sql = "select name,age from employees where age>=?";
        DBObject dbObject = DBUtil.executePreparedQuery(sql, "30");
        ResultSet resultSet = dbObject.getResultSet();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.print(resultSet.getString(1) + "\t");
                System.out.print(resultSet.getString(2) + "\t");
                System.out.println();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        // 关闭资源
        DBUtil.close(dbObject);
    }
    public static void test1(){
        String sql = "select * from employees";
        DBObject dbObject = DBUtil.executeQuery(sql);
        ResultSet resultSet = dbObject.getResultSet();
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(resultSet.getString(3));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        DBUtil.close(dbObject);
    }
}
