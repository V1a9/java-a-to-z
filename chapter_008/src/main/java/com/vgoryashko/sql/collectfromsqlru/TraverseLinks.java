package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.nodes.Element;

import java.util.Iterator;

/**
 * Class that traverses all links passed to it.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class TraverseLinks {

    private Iterator<Element> iterator;

    public TraverseLinks() {
    }

    public TraverseLinks(Iterator<Element> iterator) {
        this.iterator = iterator;
    }

    public boolean hasNextLink() {
        return this.iterator.hasNext();
    }

    public Element nextLink() {
        return iterator.next();
    }

    public void setIterator(Iterator<Element> iterator) {
        this.iterator = iterator;
    }
}
