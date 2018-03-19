package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that adds photos to adverts.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/12/18
 */
public class AddPhotoController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPhotoController.class);

    // Create a factory for disk-based file items
    private DiskFileItemFactory factory;

    private String photosDirName;

    @Override
    public void init(ServletConfig config) throws ServletException {
        factory = new DiskFileItemFactory();
        ServletContext servletContext = config.getServletContext();

        photosDirName = String.format(
                "%s%s%s%s",
                servletContext.getRealPath(""),
                File.separator, servletContext.getInitParameter("javax.servlet.context.photosdir"),
                File.separator);
        this.createDir(photosDirName);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        long advertId = Long.valueOf(req.getParameter("advertId"));

        try {
            String newFile = this.uploadFile(req);
            if (newFile != null) {

                Advertisement advertisement = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).read(advertId);
                advertisement.getPhotos().add(String.format("%s/photos/%s", req.getContextPath(), newFile));
                ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).update(advertisement);
                req.setAttribute("advertId", advertisement.getId());
            } else {
                req.setAttribute("error", "Photo hasn't been added.");
            }
            req.getRequestDispatcher("/WEB-INF/views/AddPhotoView.jsp").forward(req, resp);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
