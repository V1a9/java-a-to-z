package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

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
 * @version 0.5
 * @since 11/22/17
 */
public class CollectData extends TimerTask {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private int updateTime;

    private boolean firstStart = true;

    public CollectData() {
    }

    @Override
    public void run() {
        logger.debug("Start run()");
        collect();
        logger.debug("Exit from run()");
    }

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

    public void collect() {

        logger.debug("Database update has been started.");

        LocalDate localDate = LocalDate.now();

        RetrieveDataFromAdverts retrieveDataFromAdverts;

        FetchPage fetchPage = new FetchPage();

        fetchPage.setUrl("http://www.sql.ru/forum/job-offers");

        Document document = fetchPage.fetch();

        int numberOfPages = new GetPagesQty(document).retrieveNumberOfPages();

        for (int i = 2; i < numberOfPages; i++) {

            if (i != 2) {
                fetchPage = new FetchPage();
                fetchPage.setUrl(String.format("http://www.sql.ru/forum/job-offers/%d", i));
                document = fetchPage.fetch();
            }

            retrieveDataFromAdverts = new RetrieveDataFromAdverts(document, localDate, this.firstStart, fetchPage);

            if (retrieveDataFromAdverts.retrieveAdvert()) {
                break;
            }
        }

        this.firstStart = false;

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
}
