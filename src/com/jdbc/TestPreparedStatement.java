package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/9
 */
public class TestPreparedStatement {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        String sql_model = "insert into employees (code, name, age) values (?, ?, ?)";
        try {
            // 预编译的方式获得 SQL 语句对象，需要先获得 SQL语句 模板
            PreparedStatement preparedStatement = connection.prepareStatement(sql_model);
            // 从键盘获取数据
            Scanner sc = new Scanner(System.in);
            System.out.println("输入编号：");
            String code = sc.next();
            System.out.println("输入姓名：");
            String name = sc.next();
            System.out.println("输入年龄：");
            String age = sc.next();
            // 填入数据; 参数索引位置从1开始
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, age);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
            // 一个模板，多次插入
            preparedStatement.setString(1, "1030");
            preparedStatement.setString(2, "李火旺");
            preparedStatement.setString(3, "23");
            i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
            // 关闭资源
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
