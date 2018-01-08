package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class that implements servlet that retrieves all cities from DB that belong to a give n country.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/25/17
 */
public class GetCities extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<String> cities = UserStore.getInstance().getCities(req.getParameter("country"));
        int citiesSize = cities.size();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("[");
        for (String city : cities) {
            if (!cities.get(citiesSize - 1).equals(city)) {
                writer.append(String.format("\"%s\", ", city));
            } else {
                writer.append(String.format("\"%s\"", city));
            }
        }
        writer.append("]");
        writer.flush();
        
    }
}
