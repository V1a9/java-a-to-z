package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that implements main logic of the app that collects and process data from www.sql.ru.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class CollectData extends TimerTask {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private int updateTime;

    private boolean firstStart = true;

    private LocalDate lastUpdateTime = LocalDate.now();
    
    private FetchPage fetchPage;

    private Elements links;

    private RetrieveLinks retrieveLinks;

    private TraverseLinks traverseLinks;

    private RetrieveAdvert retrieveAdvert;

    private Advertisement advertisement;

    private CheckEligibility checkEligibility;

    private GetPagesQty getPagesQty;

    private Document document;

    private UpdateDataBase updateDataBase;

    private int occasionCounter = 0;

    private boolean stop = false;

    public CollectData() {
        this.fetchPage = new FetchPage();
        this.getPagesQty = new GetPagesQty();
        this.retrieveLinks = new RetrieveLinks();
        this.traverseLinks = new TraverseLinks();
        this.retrieveAdvert = new RetrieveAdvert();
        this.checkEligibility = new CheckEligibility();
        this.updateDataBase = new UpdateDataBase();
    }

    @Override
    public void run() {

        logger.debug("Start run()");

        this.fetchPage.setUrl("http://www.sql.ru/forum/job-offers");

        this.document = this.fetchPage.fetch();

        this.getPagesQty.setDocument(this.document);

        int numberOfPages = this.getPagesQty.retrieveNumberOfPages();

        collect(numberOfPages);

        logger.debug("Exit from run()");

    }

    /**
     * Method that creates DB.
     */
    public void init() {

        logger.traceEntry("init()");

        ClassLoader loader = this.getClass().getClassLoader();

        Properties properties = new Properties();

        try (InputStream inputStream = loader.getResourceAsStream("app.properties")) {
            properties.load(inputStream);
            this.updateTime = Integer.parseInt(properties.getProperty("update_frequency"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        Connect connect = new Connect("app.properties");

        Connection connection = null;
        Statement statement = null;

        try {

            connection = connect.getConnection();

            logger.trace("Connection to DB established.");

            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS adverts("
                    + "id serial PRIMARY KEY,"
                    + "header text,"
                    + "href text,"
                    + "description text,"
                    + "create_date text);");

            statement.execute("TRUNCATE adverts RESTART IDENTITY;");

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                connect.closeConnection();
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Method that implements main logic of the app.
     */
    public void collect(int numberOfPages) {

        logger.debug("Database update has been started.");

        for (int i = 2; i < numberOfPages; i++) {

            if (i != 2) {
                fetchPage = new FetchPage();
                fetchPage.setUrl(String.format("http://www.sql.ru/forum/job-offers/%d", i));
                this.document = fetchPage.fetch();
            }

            this.retrieveLinks.setDocument(this.document);

            this.links = this.retrieveLinks.retrieveLinks();

            this.traverseLinks.setIterator(this.links.iterator());

            while (!stop && this.traverseLinks.hasNextLink()) {

                this.retrieveAdvert.setLink(this.traverseLinks.nextLink());
                this.retrieveAdvert.setFetchPage(this.fetchPage);

                this.advertisement = this.retrieveAdvert.retrieveAdvert();

                if (this.advertisement != null) {

                    String[] date = advertisement.getDate().split("/");
                    LocalDate advertDate = LocalDate.of(Integer.parseInt(date[2]) + 2000, Integer.parseInt(date[1]), Integer.parseInt(date[0]));

                    this.checkEligibility.setAdvertisement(advertisement);
                    this.checkEligibility.setAdvertDate(advertDate);
                    this.checkEligibility.setLastUpdateTime(this.lastUpdateTime);
                    this.checkEligibility.setFirstStart(this.firstStart);

                    if (this.checkEligibility.check()) {
                        this.updateDataBase.setConnect(new Connect("app.properties"));
                        this.updateDataBase.insert(advertisement);
                        this.occasionCounter = 0;
                        logger.debug(advertisement.getHref());
                    } else if (++this.occasionCounter > 4) {
                        this.stop = true;
                        logger.debug("There are no new adverts.");
                        break;
                    }
                }
            }
        }

        this.firstStart = false;
        this.lastUpdateTime = LocalDate.now();
        this.stop = false;
        logger.debug("Database update finished.");
    }

    public void start(CollectData collectData) {

        logger.traceEntry("start()");

        Thread thread = Thread.currentThread();

        this.init();

        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(collectData, 0, 86400000 / this.updateTime);

        try {
            thread.join();
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.traceExit("start()");
    }


    public void setFetchPage(FetchPage fetchPage) {
        this.fetchPage = fetchPage;
    }

    public void setLinks(Elements links) {
        this.links = links;
    }

    public void setTraverseLinks(TraverseLinks traverseLinks) {
        this.traverseLinks = traverseLinks;
    }

    public void setRetrieveAdvert(RetrieveAdvert retrieveAdvert) {
        this.retrieveAdvert = retrieveAdvert;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setCheckEligibility(CheckEligibility checkEligibility) {
        this.checkEligibility = checkEligibility;
    }

    public void setGetPagesQty(GetPagesQty getPagesQty) {
        this.getPagesQty = getPagesQty;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean getStop() {
        return this.stop;
    }

    public boolean getFirstStart() {
        return this.firstStart;
    }
}
