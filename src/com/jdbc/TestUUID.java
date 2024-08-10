package com.jdbc;

import com.jdbc.util.DBUtil;

import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/9
 */
public class TestUUID {
    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString()); // 7ddfef2b-b213-4a97-a7ce-0313457ec5f3
        String sql = "insert into uuid values(?,?)";
        for (int i = 0; i < 100; i++) {
            DBUtil.executePreparedUpdate(sql, UUID.randomUUID().toString().replace("-",""), i);
        }
    }
}