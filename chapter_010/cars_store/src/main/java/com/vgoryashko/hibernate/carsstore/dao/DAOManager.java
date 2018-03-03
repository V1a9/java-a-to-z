package com.vgoryashko.hibernate.carsstore.dao;

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

    private final static DAOManager INSTANSE = new DAOManager();

    private final SessionFactory sessionFactory;

    public enum TABLES {USERS, ITEMS, CARS, PARTS};

    private DAOManager() {}

    public static synchronized DAOManager getInstanse() {
        return INSTANSE;
    }

    {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        logger.info("Session factory has been initialized..");
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void closeSessionFactory() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
            logger.info("Session factory has been closed sucessfully..");
        } else {
            logger.info("Session factory is closed..");
        }
    }

    public DAO daoFactory(TABLES table) throws SQLException {
        switch (table) {
            case USERS: return new UserDAO(this.sessionFactory.openSession());
            case ITEMS: return new ItemDAO(this.sessionFactory.openSession());
            case CARS: return new CarDAO(this.sessionFactory.openSession());
            case PARTS: return new PartDAO(this.sessionFactory.openSession());
            default: throw new SQLException("Trying to link unexisting table...");
        }
    }
}
