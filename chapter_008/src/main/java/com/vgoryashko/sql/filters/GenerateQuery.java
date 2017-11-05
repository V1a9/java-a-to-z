package com.vgoryashko.sql.filters;

/**
 * Class that generates SQL queries based on an user's input.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 10/13/17
 */
public class GenerateQuery {

    /**
     * Method that implements main logic.
     *
     * @param filter to be used
     * @param fields to be displayed
     * @param param to be used for a query
     * @return String SQL query
     */
    public String generate(Filter filter, String[] fields, String... param) {

        return filter.createQuery(fields, param);

    }


}
