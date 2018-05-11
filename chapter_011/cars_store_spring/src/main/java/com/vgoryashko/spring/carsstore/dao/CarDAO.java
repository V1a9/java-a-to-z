package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.cars.Car;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDAO.class);

    private Session session;

    public CarDAO(Session session) {
        this.session = session;
    }

    public long exists(Car car) {
        long result = 0;
        Transaction tx = null;
        Query query;
        List cars;
        String hql = "SELECT id FROM Car where vin=:vin";
        try {
            tx = session.beginTransaction();
            query = session.createQuery(hql);
            query.setParameter("vin", car.getVin());
            cars = query.list();
            if (cars.size() > 0) {
                result = ((Car) cars.get(0)).getId();
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
    
    @Override
    public long create(Car car) {
        long result = 0;
        if (this.exists(car) == 0) {
            result = new DAOHelper<Car>(this.session).create(car);
        }
        return result;
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
                LOGGER.error(e.getMessage(), e);
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
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            cars = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                LOGGER.error(e.getMessage(), e);
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
