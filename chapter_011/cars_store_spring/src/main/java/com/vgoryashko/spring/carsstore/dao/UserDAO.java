package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.users.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements DAO for User model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class UserDAO implements DAO<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    private long exists(User user) {
        long result = 0;
        Transaction tx = null;
        Query query;
        List userId;
        String hql = "SELECT id FROM User where login=:login";
        try {
            tx = session.beginTransaction();
            query = session.createQuery(hql);
            query.setParameter("login", user.getLogin());
            userId = query.list();
            if (userId.size() > 0) {
                result = (long) userId.get(0);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    public long validateUser(User user) {
        long result = 0;
        Transaction tx = null;
        Query query;
        List userData;
        String hql = "SELECT id, password FROM User where login=:login";
        try {
            tx = session.beginTransaction();
            query = session.createQuery(hql);
            query.setParameter("login", user.getLogin());
            userData = query.list();
            tx.commit();
            if (userData.size() > 0) {
                Object[] objects = (Object[]) userData.get(0);
                String password = (String) objects[1];
                long userId = (long) objects[0];
                if (user.getPassword().equals(password)) {
                    result = userId;
                }
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long create(User user) {
        long result = 0;
        if (this.exists(user) == 0) {
           result = new DAOHelper<User>(this.session).create(user);
        }
        return result;
    }

    @Override
    public User read(long id) {
        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = session.get(User.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List readAll() {
        List users = null;
        Transaction tx = null;
        try {
            String hql = "FROM User";
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            users = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        return new DAOHelper<User>(this.session).update(user);
    }

    @Override
    public boolean delete(User user) {
       return new DAOHelper<User>(this.session).delete(user);
    }
}
