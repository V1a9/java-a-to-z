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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertisement)) return false;

        Advertisement that = (Advertisement) o;

        if (!href.equals(that.href)) return false;
        if (!header.equals(that.header)) return false;
        if (!description.equals(that.description)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = href.hashCode();
        result = 31 * result + header.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
