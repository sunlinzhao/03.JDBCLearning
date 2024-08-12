package com.jdbc.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/12
 */

/**
 * 该注解是放在实体类对象上，用来标识对应的数据库表名
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
    String value();
}
