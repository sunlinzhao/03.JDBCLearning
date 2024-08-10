package com.jdbc;

import com.jdbc.util.DBObject;
import com.jdbc.util.DBUtil;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author : SunLZ
 * @project : JDBCLearning
 * @date : 2024/8/10
 */
public class TestBLOB {
    public static void main(String[] args) throws IOException, SQLException {
//        insert();
        query();
    }

    public static void query() throws SQLException, IOException {
        // BLOB 数据读取
        String sql = "select * from profile where id=?";
        String id = "6b61ce4817da459b814dc57b27f902ba";
        DBObject dbObject = DBUtil.executePreparedQuery(sql, id);
        ResultSet resultSet = dbObject.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            Blob blob = resultSet.getBlob(2);
            InputStream binaryStream = blob.getBinaryStream(); // 返回输入流
            FileOutputStream fileOutputStream = new FileOutputStream("resource/image/6.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = binaryStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            // 关闭流
            binaryStream.close();
            fileOutputStream.close();
        }
        // 关闭资源
        DBUtil.close(dbObject);
    }

    public static void insert() throws FileNotFoundException {
        // BLOB 数据插入
        String sql = "insert into profile values(?, ?)";
        String id = UUID.randomUUID().toString().replace("-", "");
        FileInputStream fileInputStream = new FileInputStream("resource/image/5.png");
        int i = DBUtil.executePreparedUpdate(sql, id, fileInputStream);
        System.out.println(i > 0 ? "成功！" : "失败");
    }
}
