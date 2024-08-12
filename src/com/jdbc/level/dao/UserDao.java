package com.jdbc.level.dao;

import com.jdbc.level.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public interface UserDao{
    int save(User user);
//    User selectById(String id);
    List<User> selectAll();
    List<User> selectAllByReflect(); // 通过反射实现
}
