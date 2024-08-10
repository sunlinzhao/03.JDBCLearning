package com.jdbc.connectionPool;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestHikariCP {
    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()) {
            // 连接配置
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/JDBC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            // 连接池配置
            dataSource.setMinimumIdle(10); // 最小空闲连接数

            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
