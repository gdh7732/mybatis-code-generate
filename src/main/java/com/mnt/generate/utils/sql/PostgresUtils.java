package com.mnt.generate.utils.sql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresUtils {
    private static Logger log = Logger.getLogger(PostgresUtils.class);


    public static Connection getConnection(String url, String name, String password, String driver) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException | ClassNotFoundException e) {
            log.error("数据库连接失败 ", e);
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("连接关闭失败 ", e);
        }
    }
}
