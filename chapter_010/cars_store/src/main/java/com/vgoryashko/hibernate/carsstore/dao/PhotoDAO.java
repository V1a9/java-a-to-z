package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.items.Photo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements DAO layer for Photo model.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class PhotoDAO implements DAO<Photo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoDAO.class);

    private Session session;

    public PhotoDAO(Session session) {
        this.session = session;
    }

    @Override
    public long create(Photo photo) {
        return new DAOHelper<Photo>(this.session).create(photo);
    }

    @Override
    public Photo read(long id) {
        Photo photo = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            photo = session.get(Photo.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return photo;
    }

    @Override
    public List readAll() {
        List photos = null;
        Transaction tx = null;
        try {
            String hql = "FROM Photo ";
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            photos = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return photos;
    }

    @Override
    public boolean update(Photo photo) {
        return new DAOHelper<Photo>(this.session).update(photo);
    }

    @Override
    public boolean delete(Photo photo) {
        return new DAOHelper<Photo>(this.session).delete(photo);
    }

}
