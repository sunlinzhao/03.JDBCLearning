package com.jdbc.level.test;

import com.jdbc.level.dao.BaseDao;
import com.jdbc.level.dao.impl.UserDaoImpl;
import com.jdbc.level.entity.User;
import com.jdbc.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class Test {
    public static void main(String[] args) {
        User user = new User();
//        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setName(666666);
//        UserDaoImpl userDao = new UserDaoImpl();
//        int save = userDao.save(user);
//        System.out.println(save>0?"成功":"失败");
//
//        String id = "b9574e1e4d7d4800b00b020f479a44eb";
//        User user1 = userDao.selectById(id);
//        System.out.println(user1);

//        List<User> users = userDao.selectAll();
//        List<User> users = userDao.selectAllByReflect();
//        users.stream().forEach(System.out::println);
//        BaseDao<User> baseDao = new BaseDao<>();
//        int save = baseDao.save(user);
//        System.out.println(save > 0 ? "成功" : "失败");
        UserDaoImpl userDao = new UserDaoImpl();
        User user1 = userDao.selectById("b0947adcca8f4d60a8396ef66c0909f0");
        System.out.println(user1);
    }
}
