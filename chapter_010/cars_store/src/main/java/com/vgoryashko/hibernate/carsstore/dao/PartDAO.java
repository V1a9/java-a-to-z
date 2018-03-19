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

    public long exists(Part part) {
        long result = 0;
        Transaction tx = null;
        String hql = "SELECT id FROM Part where type=:type AND description=:desc";
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("type", part.getType());
            query.setParameter("desc", part.getDescription());
            List parts = query.list();
            if (parts.size() > 0) {
                result = ((Part) parts.get(0)).getId();
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }
    
    @Override
    public long create(Part part) {
        return new DAOHelper<Part>(this.session).create(part);
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
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            parts = query.list();
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

    public List readByCriteri(String criteria) {
        return new DAOHelper<Part>(this.session, criteria).readByCriteria();
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
