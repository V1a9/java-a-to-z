package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * Class that tests implementation of application that retrieves and analyzes data from sql.ru.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 11/24/17
 */
public class CollectDataTest {

    @Mock
    private FetchPage fetchPage;
    @Mock
    private Elements links;
    @Mock
    private Element link;
    @Mock
    private Document document;
    @Mock
    private Iterator<Element> iterator;
    @Mock
    private Connect connect;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private GetPagesQty getPagesQty;
    @Mock
    private RetrieveLinks retrieveLinks;
    @Mock
    private RetrieveAdvert retrieveAdvert;
    @Mock
    private CheckEligibility checkEligibility;
    @Mock
    private TraverseLinks traverseLinks;
    @Mock
    private UpdateDataBase updateDataBase;

    @Before
    public void setUp() throws SQLException {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void whenConnectionEstablishedEndClosedThenItsOpenAndClosed() throws Exception {

        Connect connect = new Connect("app.properties");
        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS adverts("
                + "id serial PRIMARY KEY,"
                + "header text,"
                + "href text,"
                + "description text,"
                + "create_date text);");
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement("TRUNCATE adverts RESTART IDENTITY;");
        preparedStatement.execute();

        assertTrue(!connection.isClosed());
        connect.closeConnection();
        assertTrue(connection.isClosed());

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
    public void whenRetrieveAdvertsInvokedThenNewAdvertReturned() throws IOException {

        ClassLoader loader = this.getClass().getClassLoader();

        Element link = new Element("a");
        link.attr("href", "http://www.sql.ru/forum/1277386/trebuetsya-starshiy-java-razrabotchik-moskva-ot-170-t-r>");
        link.appendText("Требуется старший Java разработчик (Москва) от 170 т.р.");

        File file = new File(loader.getResource("job-offers.html").getFile());
        Document document = Jsoup.parse(file, "windows-1251");

        when(fetchPage.fetch()).thenReturn(document);

        file = new File(loader.getResource("job.html").getFile());
        document = Jsoup.parse(file, "windows-1251");
        when(fetchPage.fetch()).thenReturn(document);

        Advertisement advertisement = new RetrieveAdvert(link, fetchPage).retrieveAdvert();

        assertEquals(advertisement.getHeader(), "Требуется старший Java разработчик (Москва) от 170 т.р");

    }

    @Test
    public void whenUpdateDataBaseInsertInvokedThenNewAdvertAdded() throws IOException, SQLException {

        Advertisement advertisement = this.createAdvertisement();

        UpdateDataBase updateDataBase = new UpdateDataBase(connect);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(false);
        assertTrue(updateDataBase.insert(advertisement));

    }

    @Test
    public void whenUpdateDataBaseInsertInvokedWithExistingAdvertThenAdvertDiscarded() throws IOException, SQLException {

        Advertisement advertisement = this.createAdvertisement();

        UpdateDataBase updateDataBase = new UpdateDataBase(connect);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        assertFalse(updateDataBase.insert(advertisement));

    }

    @Test
    public void whenCheckEligibilityInvokedForValidAdvertThenTrueReturned() {

        Advertisement advertisement = this.createAdvertisement();
        LocalDate advertDate = LocalDate.of(2017, 10, 12);
        LocalDate lastUpdateTime = LocalDate.of(2017, 10, 11);
        boolean firstStart = true;

        CheckEligibility checkEligibility = new CheckEligibility(advertisement, advertDate, lastUpdateTime, firstStart);
        assertTrue(checkEligibility.check());

        firstStart = false;
        advertDate = LocalDate.of(2017, 10, 11);
        checkEligibility = new CheckEligibility(advertisement, advertDate, lastUpdateTime, firstStart);

        assertTrue(checkEligibility.check());

    }

    @Test
    public void whenCheckEligibilityInvokedForInvalidAdvertThenFalseReturned() {

        Advertisement advertisement = this.createAdvertisement();
        LocalDate advertDate = LocalDate.of(2016, 11, 12);
        LocalDate lastUpdateTime = LocalDate.of(2017, 10, 11);
        boolean firstStart = true;

        CheckEligibility checkEligibility = new CheckEligibility(advertisement, advertDate, lastUpdateTime, firstStart);
        assertFalse(checkEligibility.check());

        firstStart = false;
        advertDate = LocalDate.of(2017, 10, 10);
        checkEligibility = new CheckEligibility(advertisement, advertDate, lastUpdateTime, firstStart);

        assertFalse(checkEligibility.check());

    }

    @Test
    public void whenRetrieveLinksInvokedThenArrayOfElementsReturned() {

        RetrieveLinks retrieveLinks = new RetrieveLinks(this.document);
        when(document.select(anyString())).thenReturn(links);
        assertEquals(this.links, retrieveLinks.retrieveLinks());

    }

    @Test
    public void whenTraverseLinksInvokedThenAllLinksReturned() {

        when(this.links.iterator()).thenReturn(this.iterator);
        when(this.iterator.hasNext()).thenReturn(true);
        when(this.iterator.next()).thenReturn(this.link);

        TraverseLinks traverseLinks = new TraverseLinks(this.links.iterator());

        assertTrue(traverseLinks.hasNextLink());
        assertEquals(traverseLinks.nextLink(), this.link);

    }

    @InjectMocks
    private CollectData collectData;
    @Test
    public void whenCollectInvokedThenFirstStartSetToFalseWhenFinished() {

        Advertisement advertisement = this.createAdvertisement();

        collectData.setDocument(this.document);
        collectData.setGetPagesQty(this.getPagesQty);

        when(this.fetchPage.fetch()).thenReturn(this.document);
        when(this.getPagesQty.retrieveNumberOfPages()).thenReturn(3);
        when(this.retrieveLinks.retrieveLinks()).thenReturn(this.links);
        when(this.links.iterator()).thenReturn(this.iterator);

        when(this.traverseLinks.hasNextLink()).thenReturn(true);
        when(this.traverseLinks.nextLink()).thenReturn(this.link);

        when(this.retrieveAdvert.retrieveAdvert()).thenReturn(advertisement);

        when(this.checkEligibility.check()).thenReturn(true, false, false, false, false, false);

        when(this.updateDataBase.insert(advertisement)).thenReturn(true);

        collectData.collect(this.getPagesQty.retrieveNumberOfPages());

        assertFalse(collectData.getStop());
    }

    public Advertisement createAdvertisement() {

        Advertisement advertisement = new Advertisement();
        advertisement.setHeader("header");
        advertisement.setHref("href");
        advertisement.setDescription("desc");
        advertisement.setDate("10/11/17");

        return advertisement;

    }
}
