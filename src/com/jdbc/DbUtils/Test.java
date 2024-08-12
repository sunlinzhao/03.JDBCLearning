package com.jdbc.DbUtils;

import com.jdbc.level.entity.User;
import com.jdbc.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        // 插入
//        String sql1 = "insert into user values(?,?)";
//        int update = queryRunner.update(connection, sql1, "5555", 5555);
//        System.out.println(update > 0 ? "成功" : "失败");

        // 查询一条
//        String sql2 = "select * from user where id=?";
//        BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
//        User query = queryRunner.query(connection, sql2, beanHandler, "1111");
//        System.out.println(query);

        // 查询多条
//        String sql3 = "select * from user";
//        BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
//        List<User> query = queryRunner.query(connection, sql3, beanListHandler);
//        query.stream().forEach(System.out::println);

        // 特殊查询 (对于无法封装数据对象的结果查询)
        String sql4 = "select count(*), max(name) from user";
        MapHandler handler = new MapHandler();
        Map<String, Object> query = queryRunner.query(connection, sql4, handler);
        query.forEach((k,v)->{
            System.out.println(k + "----" + v);
        });
    }
}
