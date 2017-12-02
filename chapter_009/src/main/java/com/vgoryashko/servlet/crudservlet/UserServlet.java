package com.vgoryashko.servlet.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class that implements methods that allows perform CRUD operations with Users.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/02/17
 */
public class UserServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private UserStore userStore;

    private Connection connection;

    public UserServlet() {
    }

    @Override
    public void init() throws ServletException {
        this.userStore = UserStore.getInstance();
        try {
            this.connection = userStore.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = this.userStore.read(this.connection, req.getParameter("email"));

        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {

            if (user != null) {
                writer.format("name: %s, login: %s, email: %s, date: %s.", user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
            } else {
                writer.append("user doesn't exists.");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        boolean post = this.userStore.create(this.connection, new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("date"))
        );

        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {

            if (post) {
                writer.format("user: %s was added.", req.getParameter("name"));
            } else {
                writer.append("user with such email exists.");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {

            if (this.userStore.exists(this.connection, req.getParameter("email"))) {

                this.userStore.update(this.connection, new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("date"))
                );

                writer.format("user with email: %s was updated.", req.getParameter("email"));
            } else {
                writer.append("user doesn't exists.");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            if (this.userStore.delete(this.connection, req.getParameter("email"))) {
                writer.append("user was deleted");
            } else {
                writer.append("user doesn't exists");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}