package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that implements methods that allows perform CRUD operations with Users.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 12/01/17
 */
public class UserServlet extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    public UserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = this.userStore.read(req.getParameter("email"));

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

        boolean post = this.userStore.create(new User(
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

        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {

            if (this.userStore.exists(req.getParameter("email"))) {

                this.userStore.update(new User(
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

        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            if (this.userStore.delete(req.getParameter("email"))) {
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
