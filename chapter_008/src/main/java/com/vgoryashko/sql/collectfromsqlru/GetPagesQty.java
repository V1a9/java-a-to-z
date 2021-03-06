package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Class that retrieves number of pages in vacation forum.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class GetPagesQty {

    private Document document;

    public GetPagesQty() {
    }

    public GetPagesQty(Document document) {

        this.document = document;

    }

    public int retrieveNumberOfPages() {

        Elements sortOptions = this.document.getElementsByClass("sort_options");

        Elements sortOptionsChildren = sortOptions.get(1).children();

        Elements sortOptionsChildrenTdTags = sortOptionsChildren.get(0).getElementsByTag("td");

        String[] text = sortOptionsChildrenTdTags.text().split("^.*\\.\\. ?| ?П.*");

        return Integer.parseInt(text[1]);

    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
