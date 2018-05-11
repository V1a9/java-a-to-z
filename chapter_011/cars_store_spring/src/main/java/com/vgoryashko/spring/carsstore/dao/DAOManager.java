package com.vgoryashko.spring.carsstore.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Class that implements DAO factory.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/3/18
 */
public class DAOManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static DAOManager INSTANCE = new DAOManager();

    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();;

    public enum TABLES { USERS, ADVERTISEMENTS, CARS, PARTS, PHOTOS }

    private DAOManager() { }

    public static synchronized DAOManager getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public boolean closeSessionFactory() {
        boolean result = false;
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
            result = true;
            logger.info("Session factory has been closed sucessfully..");
        } else {
            logger.info("Session factory is closed..");
        }
        return result;
    }

    public DAO daoFactory(TABLES table) throws SQLException {
        switch (table) {
            case USERS: return new UserDAO(this.sessionFactory.openSession());
            case ADVERTISEMENTS: return new AdvertisementDAO(this.sessionFactory.openSession());
            case PHOTOS : return new PhotoDAO(this.sessionFactory.openSession());
            case CARS: return new CarDAO(this.sessionFactory.openSession());
            case PARTS: return new PartDAO(this.sessionFactory.openSession());
            default: throw new SQLException("Trying to link unexisting table...");
        }
    }
}
