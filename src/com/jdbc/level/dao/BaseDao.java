package com.jdbc.level.dao;

import com.jdbc.annotion.IdType;
import com.jdbc.annotion.TableId;
import com.jdbc.annotion.TableName;
import com.jdbc.util.DBObject;
import com.jdbc.util.DBUtil;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
public class BaseDao <T>{
    private Class<T> persitentClass = null;

    public BaseDao() {
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            persitentClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0]; // 获取泛型
        }
    }

    public int save(T entity){
        Class<?> aClass = entity.getClass();
        try {
            Object o = aClass.getConstructor().newInstance();
            // 反射获取字段列表
            Field[] declaredFields = aClass.getDeclaredFields();
//            IdType idType = null; // 获取主键生成方式
            Object[] objects = new Object[declaredFields.length]; // 暂存属性
            for (int i = 0; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                TableId annotation = declaredFields[i].getAnnotation(TableId.class);
                if(annotation!=null){
                    if(annotation.value()==IdType.UUID){
                        objects[i] = UUID.randomUUID().toString().replace("-", "");
                    }
                }
                else objects[i] = declaredFields[i].get(entity);
            }
            for (int i = 0; i < objects.length; i++) {
                System.out.println(objects[i]);
            }
            // 拼接sql
            // insert into 表名(字段列表) values(?,?)
            StringBuilder sb = new StringBuilder("insert into");
            String table = aClass.getAnnotation(TableName.class).value();
            sb.append(" " + table + " (");
            for (Field declaredField : declaredFields) {
                sb.append(declaredField.getName());
                sb.append(",");
            }
            sb.delete(sb.length()-1, sb.length());
            sb.append(") values(?,?)");
            String sql = sb.toString();
            return DBUtil.executePreparedUpdate(sql, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T selectById(Object id){ // id 类型也是不确定的，也可以用Object接收
        T entity = null;
        try {
            entity = persitentClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TableName tableName = persitentClass.getAnnotation(TableName.class); // 获取对应数据库表
        Field[] declaredFields = persitentClass.getDeclaredFields();
        Field declaredField = declaredFields[0];
        declaredField.setAccessible(true);
        // 拼接sql
        StringBuilder sb = new StringBuilder();
        sb.append("select * from");
        sb.append(" " + tableName.value() + " where ");
        sb.append(declaredField.getName() + "=?");
        String sql = sb.toString();
        DBObject dbObject = DBUtil.executePreparedQuery(sql, id);
        ResultSet resultSet = dbObject.getResultSet();
        try {
            if(resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String methodName = "set" +  columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Field field = persitentClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    Method method = persitentClass.getMethod(methodName, field.getType());
                    method.invoke(entity, resultSet.getObject(i));
                }
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(dbObject);
        }
        return entity;
    }
}
