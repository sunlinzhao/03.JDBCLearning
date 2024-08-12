package com.jdbc.level.dao.impl;

import com.jdbc.level.dao.BaseDao;
import com.jdbc.level.dao.UserDao;
import com.jdbc.level.entity.User;
import com.jdbc.util.DBObject;
import com.jdbc.util.DBUtil;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public int save(User user) {
        String sql = "insert into user values(?,?)";
        return DBUtil.executePreparedUpdate(sql, user.getId(), user.getName());
    }

//    @Override
//    public User selectById(String id) {
//        String sql = "select * from user where id=?";
//        DBObject dbObject = DBUtil.executePreparedQuery(sql, id);
//        ResultSet resultSet = dbObject.getResultSet();
//        try {
//            if(resultSet.next()){
//                User user = new User();
//                user.setId(resultSet.getString(1));
//                user.setName(resultSet.getInt(2));
//                return user;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBUtil.close(dbObject);
//        }
//        return null;
//    }

    @Override
    public List<User> selectAll() {
        List<User> res = new ArrayList<>();
        String sql = "select * from user";
        DBObject dbObject = DBUtil.executeQuery(sql);
        ResultSet resultSet = dbObject.getResultSet();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString(1));
                user.setName(resultSet.getInt(2));
                res.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(dbObject);
        }
        return res;
    }

    @Override
    public List<User> selectAllByReflect() {
        List<User> res = new ArrayList<>();
        String sql = "select * from user";
        DBObject dbObject = DBUtil.executeQuery(sql);
        ResultSet resultSet = dbObject.getResultSet();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                User user = new User();
                Class<? extends User> aClass = user.getClass();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Field field = aClass.getDeclaredField(columnName); // Declared 可获取私有字段
                    field.setAccessible(true); // 设置不检查访问控制
                    field.set(user, resultSet.getObject(i));
                }
                res.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(dbObject);
        }
        return res;
    }
}
