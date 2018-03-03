package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.users.User;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserDAO() {
    }

    @Override
    public void create(User user) {
        new DAOHelper<User>(this.session).create(user);
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
                logger.error(e.getMessage(), e);
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
            Query query = session.createQuery(hql);
            users = query.list();
            tx = session.beginTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
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
