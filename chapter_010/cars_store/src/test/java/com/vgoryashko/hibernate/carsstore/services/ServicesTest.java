package com.vgoryashko.hibernate.carsstore.services;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.parts.*;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/26/18
 */
public class ServicesTest {

    @Test
    public void test() {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

//        User user = new User();
//        user.setName("User");
//        user.setLogin("user");
//        user.setPassword("user");
//        session.save(user);

//        Car car = new Car();
//        car.setVin("2222");
//        car.setBrand("Ford");
//        Engine engine = session.get(Engine.class, 6L);
//        Body body = session.get(Body.class, 14L);
//        Transmission transmission = session.get(Transmission.class, 1L);
//        Suspension suspension = session.get(Suspension.class, 9L);
//        Set<Part> parts = car.getParts();
//        parts.add(engine);
//        parts.add(body);
//        parts.add(transmission);
//        parts.add(suspension);
//        car.setUser(session.get(User.class, 1L));
//        session.save(car);

//        Advertisement advertisement = new Advertisement();
//        Car car = session.get(Car.class, 1L);
//        advertisement.setDescription("Test advert");
//        advertisement.setSold(false);
//        advertisement.setPrice(15000);
//        advertisement.setCreated(new Timestamp(System.currentTimeMillis()));
//        advertisement.setCar(car);
//        session.save(advertisement);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}
