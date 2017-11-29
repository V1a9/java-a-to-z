package com.vgoryashko.sql.collectfromsqlru;

/**
 * Class that implements an advertisement element.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class Advertisement {

    private int id;

    private String href;

    private String header;

    private String description;

    private String date;

    public Advertisement() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
