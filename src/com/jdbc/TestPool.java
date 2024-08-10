package com.jdbc;

import com.jdbc.util.DBUtil;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestPool {
    public static void main(String[] args) {
        String sql = "insert into user values(?,?)";
        int i = DBUtil.executePreparedUpdatePool(sql, "7777", "8888");
        System.out.println(i > 0 ? "成功" : "失败");
    }
}
