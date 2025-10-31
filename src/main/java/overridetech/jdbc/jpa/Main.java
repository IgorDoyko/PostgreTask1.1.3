package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();

        User user1 = new User("Igor", "Doyko", (byte) 36);
        User user2 = new User("Sergey", "Lubenniy", (byte) 36);
        User user3 = new User("Maxim", "Antropov", (byte) 32);
        User user4 = new User("Elena", "Syrkina", (byte) 48);

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        for (User user : list) {
            userServiceImpl.saveUser(user.getName(), user.getLastName(), user.getAge());
        }

        List<User> list1 = userServiceImpl.getAllUsers();
        for (User user : list1) {
            System.out.println(user);
        }

        userServiceImpl.removeUserById(2);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}