package overridetech.jdbc.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:postgresql://localhost:5432/UsersDataBase";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "Nfqkth317";
    private static final String exceptionGetConnection = "Соединение с БД не установлено";
    private static final String exceptionCloseConnection = "Соединение с БД не закрыто";
    private static Connection connection;

    public static Connection getPostgresqlConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println(exceptionGetConnection);
        }
        return null;
    }

    public static void closePostgresqlConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(exceptionCloseConnection);
        }
    }
}