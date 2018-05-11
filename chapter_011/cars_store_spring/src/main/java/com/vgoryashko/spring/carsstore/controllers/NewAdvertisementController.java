package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.PartDAO;
import com.vgoryashko.spring.carsstore.models.cars.Car;
import com.vgoryashko.spring.carsstore.models.items.Advertisement;
import com.vgoryashko.spring.carsstore.models.parts.Part;
import com.vgoryashko.spring.carsstore.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements controller that is responsible for creation of a new Advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class NewAdvertisementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewAdvertisementController.class);

    @GetMapping(path = "/newadvert.do")
    protected String newAdvert() {
        return "NewAdvert";
    }

    @PostMapping(path = "/newadvert.do")
    protected String addPhoto(HttpServletRequest req, Model model) {

        List<Part> carParts = new ArrayList<>();
        List parts;
        String criteria;
        Part part;

        try {

            Car car = new Car();
            car.setVin(req.getParameter("vin"));
            car.setBrand(req.getParameter("brand"));

            criteria = String.format("from Part p where p.type = 'BODY' and p.description = '%s'", req.getParameter("body"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type = 'ENGINE' and description = '%s'", req.getParameter("engine"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type='TRANSMISSION' and description = '%s'", req.getParameter("transmission"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type='SUSPENSION' and description = '%s'", req.getParameter("suspension"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            car.setParts(carParts);

            Advertisement advertisement = new Advertisement();
            advertisement.setDescription(req.getParameter("description"));
            advertisement.setPrice(Integer.parseInt(req.getParameter("price")));
            advertisement.setCreated(new Timestamp(System.currentTimeMillis()));

            advertisement.setCar(car);

            String userId = (String) req.getSession(false).getAttribute("userId");
            User user = (User) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(Long.valueOf(userId));
            advertisement.setUser(user);

            long advertId = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).create(advertisement);
            model.addAttribute("advertId", advertId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return "AddPhotoView";
    }
}
