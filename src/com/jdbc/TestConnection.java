package com.jdbc;

//import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/8
 */
public class TestConnection {

    // com.mysql.jdbc.Driver 是 mysql-connector-java 5中的;
    // com.mysql.cj.jdbc.Driver 是mysql-connector-java 6 以及以上中的;
    public static void main(String[] args) throws Exception {
//        GetConnection1();
//        GetConnection2();
//        getConnection3();
//        getConnection4();
        getConnection5();
    }

    // 获取连接方式一
    public static void GetConnection1() throws SQLException {
        // com.mysql.cj.jdbc.Driver 实现了 java.sql.Driver 接口，后者是 JDBC 的规范，所以创建驱动对象的时候用 java.sql.Driver，便于更换与维护
        Driver driver = new com.mysql.cj.jdbc.Driver();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        // 通过 java 连接数据库 协议
        // 协议:数据库://地址:端口号/数据库名
        String url = "jdbc:mysql://localhost:3306/JDBC";
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
    // 获取连接方式二：通过反射方式静态加载驱动类
    public static void GetConnection2() throws Exception {
        // 通过反射获取驱动的构造方法创建实例
        // 既如此，我们便可以在需要更换数据源时，直接更换传入的字符串（代表驱动类），也可以将该字符串（代表驱动类）写入到配置文件中，便于管理和维护
        Object o = Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        // 强转为 java.sql.Driver
        Driver driver = (Driver) o;
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        String url = "jdbc:mysql://localhost:3306/JDBC";
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
    // 获取连接方式三：通过 DriverManager 获取连接
    public static void getConnection3() throws Exception {
        Object o = Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        // 强转为 java.sql.Driver
        Driver driver = (Driver) o;
        DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/JDBC";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        System.out.println(connection);
    }
    // 获取连接方式四：静态代码块注册
    public static void getConnection4() throws Exception {
        // 提供URL地址
        String url = "jdbc:mysql://localhost:3306/JDBC";
        // 加载驱动
        // com.mysql.cj.jdbc.Driver 有一个静态代码块，在类加载时会被执行，注册到驱动管理，因此不需要再次注册
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接对象
        Connection connection = DriverManager.getConnection(url, "root", "root");
        System.out.println(connection);
    }

    /**
     * 标准获取连接的方式
     */
    // 获取连接方式五：读取配置文件
    public static void getConnection5() throws Exception{
        // 读取配置文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String user = resourceBundle.getString("mysql.user");
        String password = resourceBundle.getString("mysql.password");
        String url = resourceBundle.getString("mysql.url");
        String driver = resourceBundle.getString("mysql.driver");
        // 加载驱动
        Class.forName(driver);
        // 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
