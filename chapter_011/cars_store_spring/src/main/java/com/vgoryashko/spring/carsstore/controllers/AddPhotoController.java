package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.PhotoDAO;
import com.vgoryashko.spring.carsstore.models.items.Advertisement;
import com.vgoryashko.spring.carsstore.models.items.Photo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that adds photos to adverts.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class AddPhotoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPhotoController.class);

    // Create a factory for disk-based file items
    private DiskFileItemFactory factory;

    private String photosDirName;

    private ServletContext servletContext;

    @Autowired
    public AddPhotoController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @PostMapping(path = "/addphoto.do")
    protected String addPhoto(HttpServletRequest req, @RequestParam("advertId") String id, Model model) {

        factory = new DiskFileItemFactory();

        photosDirName = String.format(
                "%s%s%s%s",
                servletContext.getRealPath(""),
                File.separator, servletContext.getInitParameter("photosdir"),
                File.separator);
        this.createDir(photosDirName);

        long advertId = Long.valueOf(id);

        try {
            String newFile = this.uploadFile(req);
            if (newFile != null) {

                Advertisement advertisement = ((AdvertisementDAO) DAOManager.getInstance()
                        .daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).read(advertId);

                Photo photo = new Photo();
                photo.setFileName(String.format("%s/photos/%s", req.getContextPath(), newFile));
                photo.setAdvertisement(advertisement);

                ((PhotoDAO) (DAOManager.getInstance().daoFactory(DAOManager.TABLES.PHOTOS))).create(photo);

                model.addAttribute("advertId", advertisement.getId());
            } else {
                model.addAttribute("error", "Photo hasn't been added.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return "AddPhotoView";
    }

    public String uploadFile(HttpServletRequest req) {

        String newFile = null;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request
        try {
            List<FileItem> fileItems = upload.parseRequest(req);

            if (fileItems != null && fileItems.size() > 0) {
                for (FileItem file : fileItems) {
                    if (!file.isFormField()) {
                        String fileName = file.getName();
                        String uploadPath = String.format("%s%s%s", photosDirName, File.separator, fileName);
                        File storeFile = new File(uploadPath);
                        file.write(storeFile);
                        newFile = fileName;
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return newFile;
    }

    private synchronized boolean createDir(String fileName) {
        boolean result = false;
        File file = new File(fileName);
        if (!file.exists()) {
            result = file.mkdir();
            factory.setRepository(file);
        }
        return result;
    }
}
