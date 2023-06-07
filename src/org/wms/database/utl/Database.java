package org.wms.database.utl;

import java.sql.*;

public class Database {
    /**
     * 链接数据库
     */
    public static Connection Connect() {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String ip = "localhost";
        String url = "jdbc:mysql://" + ip + ":3306/WareHouseDB?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "030928";

        Connection connection = null;//数据库对象
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭链接
     *
     * @param connection 需要关闭的链接
     */
    public static void CloseConnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
