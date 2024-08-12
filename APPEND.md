# JDBC part 2

### 9. DAO 设计模式

> Database Access Object: 数据库访问(存取)对象，专门用于业务逻辑层与持久化数据之间，实现持久化数据的访问。专门与数据库打交道的层。所谓 DAO 层是从系统分层结构出发，把数据存取操作集中到DAO层，以便于维护和逻辑清。

ORM: Object Relation Mapping, 对象关系映射，即将关系型数据库中的记录和面向对象编程中的对象，进行映射操作；

![image.png](assets/image13.png)

#### DAO 入门操作

1. 创建一个实体对象，对应数据库表的所有字段，用来承载数据库表中的数据的；

   > 分层是为了应对业务逻辑更加复杂的场景，各层之间传递的数据对象：可以叫做 domain / model / entity / pojo
   >
2. 创建 xxxDAO 接口
3. 创建 xxxDAO 接口实现类 xxxDaoImpl

进阶: public class BaseDao < T>

思路：将公共方法提取到 BaseDao类中， 并利用反射，获取泛型，操作数据实体。由此，不同的 xxxDAOImpl 实现类可以继承自 BaseDao类(提供公共方法), 并实现 xxxDao 接口(规定特殊方法)；例如：

```java
public class UserDaoImpl extends BaseDao<User> implements UserDao{}
```


### 10. JDBC 事务管理
