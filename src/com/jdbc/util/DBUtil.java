package com.jdbc.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @description: 封装数据库操作工具类
 * @date : 2024/8/9
 */

public class DBUtil {
    private static final String USER;
    private static final String PASSWORD;
    private static final String DRIVER;
    private static final String URL;

    static { // 静态代码块，用于加载类时，读取配置
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        USER = resourceBundle.getString("mysql.user");
        PASSWORD = resourceBundle.getString("mysql.password");
        DRIVER = resourceBundle.getString("mysql.driver");
        URL = resourceBundle.getString("mysql.url");
    }

    /**
     * 获取连接对象
     * @return Connection
     */
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取语句对象
     * @param connection Connection
     * @return Statement
     */
    public static Statement getStatement(Connection connection){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询
     * @param sql String
     * @return DBObject
     */
    public static DBObject executeQuery(String sql){
        Connection connection = getConnection();
        Statement statement = getStatement(connection);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new DBObject(connection, statement, resultSet);
    }

    /**
     * 预编译查询
     * @param sql String
     * @param args Object...
     * @return DBObject
     */
    public static DBObject executePreparedQuery(String sql, Object...args) { // （不定参数）0-n个参数
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            resultSet = preparedStatement.executeQuery();
            return new DBObject(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 预编译更新
     * @param sql String
     * @param args Object...
     * @return int
     */
    public static int executePreparedUpdate(String sql, Object...args){
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    /**
     * 关闭结果集
     * @param resultSet ResultSet
     */
    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 关闭语句对象
     * @param statement Statement
     */
    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 关闭连接
     * @param connection Connection
     */
    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭资源
     * @param dbObject DBObject
     */
    public static void close(DBObject dbObject){
        close(dbObject.getResultSet());
        close(dbObject.getStatement());
        close(dbObject.getConnection());
    }
}
