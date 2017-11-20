package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;

/**
 * Class that tests implementation of application that retrieves and analyzes data from sql.ru.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class CollectDataTest {


    private Connection connection;
    private Connect connect;
    private PreparedStatement truncateTable;

    @Before
    public void setUp() throws SQLException {
        this.connect = new Connect("app.properties");
        this.connection = connect.getConnection();
        this.truncateTable = this.connection.prepareStatement("TRUNCATE adverts RESTART IDENTITY;");
        this.truncateTable.execute();
    }

    @Test
    public void whenConnectionEstablishedEndClosedThenItsOpenAndClosed() throws Exception {

        assertTrue(!this.connection.isClosed());
        this.connect.closeConnection();
        assertTrue(this.connection.isClosed());

    }

    @Test
    public void whenConvertInvokedThenTextDateConvertedIntoIntArray() {

        String[] date = new String[]{"12", "МАР", "13"};

        ConvertDate convertDate = new ConvertDate(date);

        int[] newDate = convertDate.convert();

        assertThat(newDate[0], is(12));
        assertThat(newDate[1], is(3));
        assertThat(newDate[2], is(13));

    }

    @Test
    public void whenFetchPageInvokedThenDocumentReturned() {

        FetchPage fetchPage = new FetchPage();
        fetchPage.setUrl("http://www.sql.ru/forum/job-offers");
        Document document = fetchPage.fetch();
        assertNotNull(document);

    }

    @Test
    public void whenGetPagesQtyInvokedThenQtyOfJobPagesReturned() throws IOException {

        ClassLoader loader = this.getClass().getClassLoader();

        File file = new File(loader.getResource("job-offers.html").getFile());
        Document document = Jsoup.parse(file, "windows-1251");

        GetPagesQty getPagesQty = new GetPagesQty(document);
        assertThat(getPagesQty.retrieveNumberOfPages(), is(638));

    }

    @Test
    public void whenUpdateDataBaseInvokedThenRespectiveBooleanReturned() {

//        Advertisement ad = new Advertisement();
//        ad.setHeader("header");
//        ad.setDescription("desc");
//        ad.setHref("href");
//        ad.setDate("date");

    }



    @After
    public void purgeTable() throws SQLException {
        this.connect = new Connect("app.properties");
        this.connection = connect.getConnection();
        this.truncateTable = this.connection.prepareStatement("TRUNCATE adverts RESTART IDENTITY;");
        this.truncateTable.execute();
        this.connect.closeConnection();
    }

}
