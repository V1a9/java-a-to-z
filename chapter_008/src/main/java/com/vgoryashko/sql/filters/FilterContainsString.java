package com.vgoryashko.sql.filters;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements filter for search text queries.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 10/13/17
 */
public class FilterContainsString implements Filter {

    /**
     * Member that defines the this.filter's structure.
     */
    private final List<String> filter;

    /**
     * Field that refers to an instance of Builder.
     */
    private final StringBuilder builder;

    /**
     * Constructor for the class.
     */
    public FilterContainsString() {
        filter = new LinkedList<>(Arrays.asList("SELECT ", " FROM ", " AS ", " WHERE ", " LIKE", " '%", "%'", ";"));
        builder = new StringBuilder();
    }

    /**
     * Method that defines a position of the keyWord in the this.filter.
     *
     * @param keyWord which position to be found
     * @return int position number
     */
    public int findPosition(String keyWord) {

        int counter = 0;
        for (String s : this.filter) {
            if (s.equals(keyWord)) {
                ++counter;
                break;
            }
            counter++;
        }
        return counter;
    }

    /**
     * Method that creates SQL query.
     *
     * @param fields to be selected
     * @param param [fields] [table_name] [condition] [key_word] [condition] ...
     * @return String SQL query
     */
    @Override
    public String createQuery(String[] fields, String... param) {

        int fieldsIndex;

        fieldsIndex = 1;
        for (String s : fields) {
            this.filter.add(fieldsIndex++, s);
        }

        this.filter.add(findPosition(" FROM "), param[0]);
        this.filter.add(findPosition(" AS "), param[0].substring(0, 1));
        this.filter.add(findPosition(" WHERE "), String.format("%s%s%s", param[0].substring(0, 1), ".", param[1]));
        this.filter.add(findPosition(" '%"), param[2]);

        for (String s : this.filter) {
            this.builder.append(s);
        }
        return builder.toString();
    }
}
