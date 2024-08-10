package com.jdbc.connectionPool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestDruid {
    public static void main(String[] args) throws SQLException {
        Connection connection;
        try (DruidDataSource druidDataSource = new DruidDataSource()) {
            // 连接配置
            druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            druidDataSource.setUrl("jdbc:mysql://localhost:3306/JDBC");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("root");
            // 连接池配置
            druidDataSource.setInitialSize(5); // 连接池创建的时候，自动创建的数据库连接数量
            druidDataSource.setMinIdle(10); // 最小空闲连接数
            druidDataSource.setMaxActive(20); // 最大同时激活连接数量
            druidDataSource.setMaxWait(6000); // 最大等待时间，以毫秒为单位，-1表示无限等待

            // DruidPooledConnection 类实现了 Connection
            // DruidPooledConnection connection = druidDataSource.getConnection();
            connection = druidDataSource.getConnection();
        }
        System.out.println(connection);
    }
}
