package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id int not null primary key auto_increment," +
                " name VARCHAR(64), lastName VARCHAR(64),age INT)";
        final Connection connection = Util.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            // создание таблицы
            statement.executeUpdate(sql);

            System.out.println("Таблица создана!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        final Connection connection = Util.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name,lastName,age) VALUES( ?, ?, ?)";
        final Connection connection = Util.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        final Connection connection = Util.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM users where id";
            statement.executeUpdate(sql);
            System.out.println("User " + id + " удален");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        final Connection connection = Util.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User users = new User();
                users.setId((long) rs.getInt("id"));
                users.setName(rs.getString("name"));
                users.setLastName(rs.getString("lastName"));
                users.setAge(rs.getByte("age"));
                result.add(users);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        final Connection connection = Util.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM users";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
