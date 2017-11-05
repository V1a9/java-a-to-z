package com.vgoryashko.sql.filters;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class that tests implementation of classes that generate SQL queries based on an user's input.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 10/11/17
 */
public class GenerateQueryTest {

    /**
     * Member that refers to an instance of GenerateQuery.
     */
    private GenerateQuery generateQuery;

    /**
     * Method that setups test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        this.generateQuery = new GenerateQuery();
    }

    /**
     * Method that tests generate().
     *
     * @throws Exception Exception
     */
    @Test
    public void whenGenerateInvokedWithFilterContainsThenRespectiveQueryReturned() throws Exception {

        Filter filter = new FilterContainsString();

        String result = this.generateQuery.generate(filter, new String[]{"*"}, "users", "description", "Tom");
        assertThat(result, is("SELECT * FROM users AS u WHERE u.description LIKE '%Tom%';"));

    }

}