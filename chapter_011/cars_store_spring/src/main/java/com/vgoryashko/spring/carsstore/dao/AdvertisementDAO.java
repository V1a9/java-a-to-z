package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.items.Advertisement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements DAO object for Items model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class AdvertisementDAO implements DAO<Advertisement> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementDAO.class);

    private Session session;

    public AdvertisementDAO(Session session) {
        this.session = session;
    }

    @Override
    public long create(Advertisement advertisement) {
        return new DAOHelper<Advertisement>(this.session).create(advertisement);
    }

    @Override
    public Advertisement read(long id) {
        Advertisement advertisement = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            advertisement = session.get(Advertisement.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return advertisement;
    }

    @Override
    public List readAll() {
        List items = null;
        Transaction tx = null;
        try {
            String hql = "FROM Advertisement";
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            items = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public boolean update(Advertisement advertisement) {
        return new DAOHelper<Advertisement>(this.session).update(advertisement);
    }

    @Override
    public boolean delete(Advertisement advertisement) {
        return new DAOHelper<Advertisement>(this.session).delete(advertisement);
    }

    public List readNotSold() {
        String criteria = "FROM Advertisement AS a WHERE a.sold='false' ORDER BY a.id ASC";
        return new DAOHelper<Advertisement>(this.session, criteria).readByCriteria();
    }

    public List readByCriteria(String criteria) {
        return new DAOHelper<Advertisement>(this.session, criteria).readByCriteria();
    }
}
