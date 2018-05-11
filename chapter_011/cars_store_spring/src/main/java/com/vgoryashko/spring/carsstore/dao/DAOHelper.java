package com.vgoryashko.spring.carsstore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements generic methods for DAOs objects.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class DAOHelper<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    private String criteria;

    public DAOHelper(Session session) {
        this.session = session;
    }

    public DAOHelper(Session session, String criteria) {
        this.session = session;
        this.criteria = criteria;
    }

    public long create(T t) {
        long result = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            result = (long) session.save(t);
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

   List readByCriteria() {
        List elements = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(this.criteria);
            elements = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return elements;
    }
}
