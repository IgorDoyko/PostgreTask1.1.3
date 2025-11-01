package overridetech.jdbc.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String URL = "jdbc:postgresql://localhost:5432/UsersDataBase";
    private final String LOGIN = "postgres";
    private final String PASSWORD = "Nfqkth317";
    private Connection connection;

    public Connection getPostgresqlConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Соединение с БД не установлено" + e.getMessage());
        }
    }

    public void closePostgresqlConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Соединение с БД не закрыто" + e.getMessage());
        }
    }
}