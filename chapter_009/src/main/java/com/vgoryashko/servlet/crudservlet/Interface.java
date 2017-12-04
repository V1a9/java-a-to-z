package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Class that implements an User Interface of the web app.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/4/17
 */
public class Interface extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    public Interface() {
    }

    @Override
    public void init() throws ServletException {
        try {
            this.userStore.setupPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userStore.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

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

        writer.append(
                "<!DOCTYPE html>"
                       + "<html lang=\"en\">"
                       + "<head>"
                       + "    <meta charset=\"UTF-8\">"
                       + "    <title>User Store</title>"
                       + "    <h3>User storage application</h3>"
                       + "<style>"
                       + "        table#t01, th, td {"
                       + "            border: 1px solid black;"
                       + "            border-collapse: collapse;"
                       + "        }"
                       + "    </style>"
                       + "</head>"
                       + "<body>"
                       + stringBuilder.toString()
                       + "<table>"
                       + "    <tr>"
                       + "        <td>"
                       + "            <a href=new>Create user</a><br>"
                       + "        </td>"
                       + "        <td>"
                       + "            <form action=delete>"
                       + "                <input type=\"submit\" value=\"Delete user\">"
                       + "            </form>"
                       + "        </td>"
                       + "        <td>"
                       + "            <form action=update>"
                       + "                <input type=\"submit\" value=\"Update user\">"
                       + "            </form>"
                       + "        </td>"
                       + "        <td>"
                       + "            <form action=get>"
                       + "                <input type=\"submit\" value=\"Get user\">"
                       + "            </form>"
                       + "        </td>"
                       + "    </tr>"
                       + "</table>"
                       + "</body>"
                       + "</html>"
        );
        writer.flush();
    }
}
