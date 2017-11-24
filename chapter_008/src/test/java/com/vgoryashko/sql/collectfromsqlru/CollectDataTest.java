package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class that tests implementation of application that retrieves and analyzes data from sql.ru.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 11/24/17
 */
public class CollectDataTest {


    private Connection connection;
    private Connect connect;
    private PreparedStatement preparedStatement;

    @Mock
    private FetchPage fetchPage;

    @Before
    public void setUp() throws SQLException {

        MockitoAnnotations.initMocks(this);

        this.connect = new Connect("app.properties");
        this.connection = connect.getConnection();
        this.preparedStatement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS adverts("
                + "id serial PRIMARY KEY,"
                + "header text,"
                + "href text,"
                + "description text,"
                + "create_date text);");
        this.preparedStatement.execute();
        this.preparedStatement = this.connection.prepareStatement("TRUNCATE adverts RESTART IDENTITY;");
        this.preparedStatement.execute();
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

        FetchPage notMockFetch = new FetchPage();
        notMockFetch.setUrl("http://www.sql.ru/forum/job-offers");
        Document document = notMockFetch.fetch();
        assertNotNull(document);

    }

    @Test
    public void whenGetPagesQtyInvokedThenQtyOfJobPagesReturned() throws IOException {

        ClassLoader loader = this.getClass().getClassLoader();

        File file = new File(loader.getResource("job-offers.html").getFile());
        Document document = Jsoup.parse(file, "windows-1251");

        GetPagesQty getPagesQty = new GetPagesQty(document);
        assertThat(getPagesQty.retrieveNumberOfPages(), is(639));

    }

    @Test
    public void whenRetrieveDataFromAdvertsInvokedThenNewAvertReturned() throws IOException {

        ClassLoader loader = this.getClass().getClassLoader();

        Element link = new Element("a");
        link.attr("href", "http://www.sql.ru/forum/1277386/trebuetsya-starshiy-java-razrabotchik-moskva-ot-170-t-r>");
        link.appendText("Требуется старший Java разработчик (Москва) от 170 т.р.");

        File file = new File(loader.getResource("job-offers.html").getFile());
        Document document = Jsoup.parse(file, "windows-1251");

        when(fetchPage.fetch()).thenReturn(document);

        Document fetch = fetchPage.fetch();

        RetrieveDataFromAdverts retrieveDataFromAdverts = new RetrieveDataFromAdverts(fetch, LocalDate.now(), false, fetchPage);

        file = new File(loader.getResource("job.html").getFile());
        document = Jsoup.parse(file, "windows-1251");
        when(fetchPage.fetch()).thenReturn(document);

        Advertisement advertisement = retrieveDataFromAdverts.retrieveAdvertData(link);

        assertEquals(advertisement.getHeader(), "Требуется старший Java разработчик (Москва) от 170 т.р");

    }

    @Mock
    private UpdateDataBase updateDataBase;

    @Mock
    private Connect mockConnect;

    @Test
    public void whenUpdateDataBaseInsertInvokedThenNewAdvertAdded() throws IOException, SQLException {
//        when(updateDataBase.exists("desc")).thenReturn(false);
//
//        assertTrue(!updateDataBase.exists("desc"));
//
//        Advertisement advertisement = new Advertisement();
//
//        advertisement.setHeader("header");
//        advertisement.setHref("href");
//        advertisement.setDescription("desc");
//        advertisement.setDate("10 НОЯ 17");
//
//        assertTrue(!updateDataBase.insert(advertisement));

    }

    @After
    public void purgeTable() throws SQLException {
        this.connect = new Connect("app.properties");
        this.connection = connect.getConnection();
        this.preparedStatement = this.connection.prepareStatement("TRUNCATE adverts RESTART IDENTITY;");
        this.preparedStatement.execute();
        this.connect.closeConnection();
    }

}
