package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Class that fetches a web-page.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class FetchPage {

    Logger logger = LogManager.getLogger(this.getClass());

    private String url;

    public Document fetch() {

        Document document = null;

        try {
             document = Jsoup.connect(this.url).get();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return document;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
