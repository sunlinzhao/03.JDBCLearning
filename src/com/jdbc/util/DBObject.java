package com.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/9
 */
public class DBObject {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBObject(Connection connection, Statement statement, ResultSet resultSet) {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
