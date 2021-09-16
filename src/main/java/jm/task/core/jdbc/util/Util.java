package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mySQL://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Util() {
    }

    private static volatile Util instance;
    private static Connection connection = null;

    public static Util getInstance() {
        Util localInstance = instance;
        if (localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Util();
                }
            }
        }
        return localInstance;
    }

    public static Connection getConnection() {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("соединение установлено");
            }
        } catch (
                SQLException e) {
            System.out.println("ошибка соединения");
        }
        return connection;
    }
}
