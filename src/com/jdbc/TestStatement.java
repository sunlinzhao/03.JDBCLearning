package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/9
 */
public class TestStatement {
    public static void main(String[] args) throws Exception {
//        insert();
        query();
    }

    public static Connection connect() throws Exception {
        // 读取配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        String user = bundle.getString("mysql.user");
        String password = bundle.getString("mysql.password");
        String url = bundle.getString("mysql.url");
        String driver = bundle.getString("mysql.driver");
        // 加载驱动
        Class.forName(driver);
        // 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    public static void insert() throws Exception {
        // 获取连接
        Connection connect = connect();
        // 获取语句对象
        Statement statement = connect.createStatement();
        // 从键盘输入数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入员工编号:");
        String code = sc.next();
        System.out.println("请输入员工姓名:");
        String name = sc.next();
        System.out.println("请输入员工年龄:");
        String age = sc.next();
        // 拼接 SQL 语句
        String sql = "INSERT INTO employees (code, name, age) VALUES ('" + code + "', '" + name + "', " + age + ")";
        // executeUpdate() 主要执行DML语句（DDL语句也可以执行） execute 都可以执行
        // 插入操作
        int i = statement.executeUpdate(sql); // 返回整数表示，影响了几行数据
        if(i>0){
            System.out.println("Successfully! " + i + "row(s) affected");
        } else {
            System.out.println("failed!");
        }
        // 关闭资源
        sc.close();
        statement.close();
        connect.close();
    }

    public static void query() throws Exception {
        // 获取连接
        Connection connect = connect();
        // 获取语句对象
        Statement statement = connect.createStatement();
        // 查询操作
        String selectSql = "select * from employees";
        // executeQuery() 主要执行DQL数据查询语言
        ResultSet resultSet = statement.executeQuery(selectSql); // 返回结果集
        // 打印表头
        System.out.println("id" + "\t" + "code" + "\t" + "name" + "\t" + "age" + "\t" + "gender" + "\t" + "salary" + "\t" + "dept_id");
        // 打印数据
        while (resultSet.next()){
            System.out.print(resultSet.getInt(1) + "\t"); // 列索位置引从 1 开始
            System.out.print(resultSet.getString(2) + "\t");
            System.out.print(resultSet.getString(3) + "\t");
            System.out.print(resultSet.getInt(4) + "\t");
            System.out.print(resultSet.getInt(5) + "\t");
            System.out.print(resultSet.getDouble(6) + "\t");
            System.out.print(resultSet.getInt(7) + "\t");
            System.out.println("\n");
        }
        // 关闭资源
        resultSet.close();
        statement.close();
        connect.close();
    }
}