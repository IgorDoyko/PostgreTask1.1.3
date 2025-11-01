package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastName VARCHAR(20) NOT NULL, " +
                    "age SMALLINT NOT NULL" +
                    ")");
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Таблица не создана" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Таблица не удалена" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (name, lastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Пользователь не добавлен" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id = '" + id + "'");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Юзер не удален" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                list.add(user);
            }
            statement.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Информация о юзерах не получена" + e.getMessage());
        }
    }

    public void cleanUsersTable() {
        try {
            Util util = new Util();
            connection = util.getPostgresqlConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Строки не удалены" + e.getMessage());
        }
    }
}