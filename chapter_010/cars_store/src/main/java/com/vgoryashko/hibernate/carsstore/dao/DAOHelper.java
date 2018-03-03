package com.vgoryashko.hibernate.carsstore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/2/18
 */
public class DAOHelper<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    public DAOHelper(Session session) {
        this.session = session;
    }

    public void create(T t) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
    }

    public boolean update(T t) {
        boolean result = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(t);
            result = true;
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean delete(T t) {
        boolean result = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(t);
            result = true;
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return result;
    }
}
