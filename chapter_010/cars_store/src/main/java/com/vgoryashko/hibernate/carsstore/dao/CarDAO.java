package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that implements DAO for Car model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class CarDAO implements DAO<Car> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;

    public CarDAO(Session session) {
        this.session = session;
    }

    @Override
    public void create(Car car) {
        new DAOHelper<Car>(this.session).create(car);
    }

    @Override
    public Car read(long id) {
        Car car = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            car = session.get(Car.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                logger.error(e.getMessage(), e);
            }
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public List readAll() {
        List cars = null;
        Transaction tx = null;
        try {
            String hql = "FROM Car ";
            Query query = session.createQuery(hql);
            cars = query.list();
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
        return cars;
    }

    @Override
    public boolean update(Car car) {
        return new DAOHelper<Car>(this.session).update(car);
    }

    @Override
    public boolean delete(Car car) {
        return new DAOHelper<Car>(this.session).delete(car);
    }
}
