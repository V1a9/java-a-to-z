package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that implements the servlet that deletes an User from BD.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/4/17
 */
public class DeleteUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    public DeleteUser() {
    }

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
                       + "    <title>Delete an User</title>"
                       + "    <h3>Delete an User</h3>"
                       + "<style>"
                       + "        table#t01, th, td {"
                       + "            border: 1px solid black;"
                       + "            border-collapse: collapse;"
                       + "        }"
                       + "</style>"
                       + "</head>"
                       + "<body>" + stringBuilder.toString()
                       + "<form method=\"POST\" action=\"delete\">"
                       + "    Enter User's Email:<br>"
                       + "    <input type=\"text\" name=\"email\"><br>"
                       + "    <input type=\"submit\" value=\"Submit\">"
                       + "</body>"
                       + "</html>"
        );
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (this.userStore.delete(req.getParameter("email"))) {
            writer.append("user was deleted!<br> "
                    + "<a href=/items/usersstorage>Back</a>");
        } else {
            writer.append("user doesn't exist!<br> "
                    + "<a href=/items/usersstorage>Back</a>");
        }
        writer.flush();
    }
}
