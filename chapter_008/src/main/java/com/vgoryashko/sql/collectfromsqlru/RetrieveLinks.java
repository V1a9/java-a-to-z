package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Class that retrieves links from a Document.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class RetrieveLinks {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private Document document;

    public RetrieveLinks() {
    }

    public RetrieveLinks(Document document) {
        this.document = document;
    }

    public Elements retrieveLinks() {
        return this.document.select("td.postslisttopic a[href]");
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
