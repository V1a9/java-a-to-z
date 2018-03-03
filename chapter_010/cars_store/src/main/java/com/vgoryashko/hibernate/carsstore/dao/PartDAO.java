package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements DAO for Part model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class PartDAO implements DAO<Part> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    public PartDAO(Session session) {
        this.session = session;
    }

    @Override
    public void create(Part part) {
        new DAOHelper<Part>(this.session).create(part);
    }

    @Override
    public Part read(long id) {
        Part part = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            part = session.get(Part.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return part;
    }

    @Override
    public List readAll() {
        List parts = null;
        Transaction tx = null;
        try {
            String hql = "FROM Part ";
            Query query = session.createQuery(hql);
            parts = query.list();
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
        return parts;
    }

    @Override
    public boolean update(Part part) {
        return new DAOHelper<Part>(this.session).update(part);
    }

    @Override
    public boolean delete(Part part) {
        return new DAOHelper<Part>(this.session).delete(part);
    }
}
