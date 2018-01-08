package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class that implements servlet that returns list of countries from DB.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/24/17
 */
public class GetCountries extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<String> countries = UserStore.getInstance().getCountries();
        int countriesSize = countries.size();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("[");
        for (String country : countries) {
            if (!countries.get(countriesSize - 1).equals(country)) {
                writer.append(String.format("\"%s\", ", country));
            } else {
                writer.append(String.format("\"%s\"", country));
            }
        }
        writer.append("]");
        writer.flush();

    }
}
