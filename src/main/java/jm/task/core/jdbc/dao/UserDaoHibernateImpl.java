package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    SessionFactory factory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {

            String sql = "CREATE TABLE IF NOT EXISTS users (Id int not null primary key auto_increment," +
                    " name VARCHAR(64), lastName VARCHAR(64),age INT)";
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
            System.out.println("таблица создана");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            String sql = "DROP TABLE IF EXISTS users";
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
            System.out.println("таблица удалена");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            User user = new User(name, lastName, age);
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
            System.out.println("User " + id + " удален");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            tx.commit();
            return users;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            tx.commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
