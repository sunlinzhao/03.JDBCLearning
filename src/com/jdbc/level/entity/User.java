package com.jdbc.level.entity;

import com.jdbc.annotion.IdType;
import com.jdbc.annotion.TableId;
import com.jdbc.annotion.TableName;

import java.lang.annotation.Target;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */
@TableName("user")
public class User { // 属性对应数据表中的字段
    @TableId(IdType.UUID)
    private String id;
    private int name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
