package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that updates an User with a given email in the DB.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/4/17
 */
public class UpdateUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        StringBuilder stringBuilder = new StringBuilder(
                "<table id=\"t01\" style=\"width:50%\"><br>"
                       + "<th>User name</th>"
                       + "<th>User login</th>"
                       + "<th>User email</th>"
                       + "<th>User create date</th><br>");

        for (User user : this.userStore.getAll()) {
            stringBuilder.append(
                    "<tr>"
                           + "<td>" + user.getName() + "</td>"
                           + "<td>" + user.getLogin() + "</td>"
                           + "<td>" + user.getEmail() + "</td>"
                           + "<td>" + user.getCreateDate() + "</td>"
                    + "</tr>"
            );
        }

        stringBuilder.append("</table><br>");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(
                "<!DOCTYPE html>"
                       + "<html lang=\"en\">"
                       + "<head>"
                       + "    <meta charset=\"UTF-8\">"
                       + "    <title>Update an User</title>"
                       + "    <h3>Update an User</h3>"
                       + "<style>"
                       + "        table#t01, th, td {"
                       + "            border: 1px solid black;"
                       + "            border-collapse: collapse;"
                       + "        }"
                       + "</style>"
                       + "</head>"
                       + "<body>"
                       + stringBuilder.toString()
                       + "<form method=\"POST\" action=\"update\">"
                       + "    User's Email that will be updated:<br>"
                       + "    <input type=\"text\" name=\"email\"><br>"
                       + "    User's new name:<br>"
                       + "    <input type=\"text\" name=\"newName\"><br>"
                       + "    User's new login:<br>"
                       + "    <input type=\"text\" name=\"newLogin\"><br>"
                       + "    User's new create date:<br>"
                       + "    <input type=\"text\" name=\"newDate\"><br><br>"
                       + "    <input type=\"submit\" value=\"Submit\">"
                       + "</body>"
                       + "</html>"
        );
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {

            if (this.userStore.exists(req.getParameter("email"))) {

                this.userStore.update(new User(
                        req.getParameter("newName"),
                        req.getParameter("newLogin"),
                        req.getParameter("email"),
                        req.getParameter("newDate"))
                );

                writer.format("user with email: %s was updated.<br>"
                        + "<a href=/items/usersstorage>Back</a>", req.getParameter("email"));
            } else {
                writer.append("user doesn't exists.<br>"
                        + "<a href=/items/usersstorage>Back</a>");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
