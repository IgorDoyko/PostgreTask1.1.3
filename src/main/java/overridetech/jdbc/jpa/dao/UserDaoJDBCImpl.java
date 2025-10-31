package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Statement statement;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            statement = Util.getPostgresqlConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.execute("CREATE TABLE users (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastName VARCHAR(20) NOT NULL, " +
                    "age SMALLINT NOT NULL" +
                    ")");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("INSERT INTO users (name, lastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен");
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM users WHERE id = '" + id + "'");
            System.out.println("Юзер удален");
        } catch (SQLException e) {
            System.out.println("Юзер не удален");
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try {
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
            return list;
        } catch (SQLException e) {
            System.out.println("Информация о юзерах не получена");
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Строки удалены");
        } catch (SQLException e) {
            System.out.println("Строки не удалены");
        }
    }
}