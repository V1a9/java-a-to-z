package com.vgoryashko.sql.filters;

/**
 * Interface that defines filters structure.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 10/13/17
 */
public interface Filter {

    /**
     * Method that creates SQL query.
     *
     * @param fields to be selected
     * @param param [fields] [table_name] [condition] [key_word] [condition] ...
     * @return String SQL query
     */
    String createQuery(String[] fields, String... param);

}
