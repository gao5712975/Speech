package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * 连接mysql返还Connection对象
 * Created by Yuan on 2015/8/29.
 */
public class JDBCUtil {
    private static Connection connection = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static Map<String,Object> properties = PropertiesUtils.getPropertiesFileToMap("JDBC.properties");

    public static Connection getConnection(){
        url = properties.get("url").toString();
        user = properties.get("user").toString();
        password = properties.get("password").toString();
        try {
            Class.forName(properties.get("driver").toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println(connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
