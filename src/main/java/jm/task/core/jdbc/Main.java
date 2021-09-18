package jm.task.core.jdbc;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

//import com.mysql.cj.jdbc.Driver;

public class Main {

    private static UserServiceImpl userService = new UserServiceImpl();

    private static User user1 = new User("Тимур", "Акмурзин", (byte) 24);
    private static User user2 = new User("Руслан", "Литвинов", (byte) 20);
    private static User user3 = new User("Роман", "Зобнин", (byte) 27);
    private static User user4 = new User("Артем", "Ребров", (byte) 35);

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
////        userService.getAllUsers();
////        userService.cleanUsersTable();
        //  userService.dropUsersTable();
    }
}

