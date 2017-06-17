package com.vgoryashko.collectionspro.referencebook;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests Reference Book.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/17/17
 */
public class ReferenceBookTest {

    /**
     * Variable that referring to an instance of ReferenceBook.
     */
    private ReferenceBook<String, String> book;

    /**
     * Variable that referring to an instance of Iterator.
     */
    private Iterator<String> iterator;

    /**
     * Method that setups test environments.
     */
    @Before
    public void init() {

        book = new ReferenceBook<>();
        iterator = book.iterator();

        book.insert("1", "First entry.");
        book.insert("2", "Second entry.");
    }

    /**
     * Method that tests insertion of an entry into book.
     * @throws Exception Exception
     */
    @Test
    public void whenInsertInvokedThenEntryIsAdded() throws Exception {

        while (iterator.hasNext()) {

            if (iterator.next().equals("1")) {
                assertThat(iterator.next(), is("First entry."));
            }

        }

    }

    /**
     * Method that tests get().
     *
     * @throws Exception Exception
     */
    @Test
    public void whenGetInvokedThenItReturnsValueThatCorrespondsToTheKey() throws Exception {

        assertThat(book.get("2"), is("Second entry."));

    }

    /**
     * Method that tests removing of an entry.
     * @throws Exception Exception
     */
    @Test
    public void whenDeleteInvokedThenRespectiveEntryRemoved() throws Exception {

        assertTrue(book.delete("2"));
        assertNull(book.get("2"));

    }
}