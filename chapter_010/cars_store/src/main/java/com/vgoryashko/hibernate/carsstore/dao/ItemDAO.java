package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.items.Item;
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
public class ItemDAO implements DAO<Item> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    public ItemDAO(Session session) {
        this.session = session;
    }

    @Override
    public void create(Item item) {
        new DAOHelper<Item>(this.session).create(item);
    }

    @Override
    public Item read(long id) {
        Item item = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            item = session.get(Item.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public List readAll() {
        List items = null;
        Transaction tx = null;
        try {
            String hql = "FROM Item";
            Query query = session.createQuery(hql);
            items = query.list();
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
        return items;
    }

    @Override
    public boolean update(Item item) {
        return new DAOHelper<Item>(this.session).update(item);
    }

    @Override
    public boolean delete(Item item) {
        return new DAOHelper<Item>(this.session).delete(item);
    }
}
