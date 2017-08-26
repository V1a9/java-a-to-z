package com.vgoryashko.collectionspro.orderbook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles actions on data from xml file based on attributes read.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 8/26/17
 */
public class Handler extends DefaultHandler {

    /**
     * Class that defines structure of an element.
     */
    private class Order {

        int orderId;
        String bookNumber;
        String operation;
        float price;
        int volume;

        public Order(String bookNumber, String operation, float price, int volume, int orderId) {

            this.bookNumber = bookNumber;
            this.operation = operation;
            this.price = price;
            this.volume = volume;
            this.orderId = orderId;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Order order = (Order) o;

            return orderId == order.orderId;
        }

        @Override
        public int hashCode() {
            return orderId;
        }
    }

    /**
     * Map that stores all orders according to a book parameter.
     */
    private Map<String, Map<Integer, Order>> map;

    /**
     * Receive notification of the beginning of the document.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the beginning
     * of a document (such as allocating the root node of a tree or
     * creating an output file).</p>
     *
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#startDocument
     */
    @Override
    public void startDocument() throws SAXException {

        System.out.println("File is being processed.");
        map = new HashMap<>();

    }

    /**
     * Receive notification of the end of the document.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end
     * of a document (such as finalising a tree or closing an output
     * file).</p>
     *
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#endDocument
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Processing of the file has been finished.");
    }

    /**
     * Receive notification of the start of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        Order order;

        if (!qName.equals( "Orders")) {

            if (qName.equals("AddOrder")) {

                order = new Order(
                        attributes.getValue("book"),
                        attributes.getValue("operation"),
                        Float.parseFloat(attributes.getValue("price")),
                        Integer.parseInt(attributes.getValue("volume")),
                        Integer.parseInt(attributes.getValue("orderId"))
                );

                this.processOrder(order, true);


            } else if (qName.equals("DeleteOrder")) {

                order = new Order(
                        attributes.getValue("book"),
                        null,
                        -1.0f,
                        -1,
                        Integer.parseInt(attributes.getValue("orderId"))
                );

                this.processOrder(order, false);

            }

        }

    }

    /**
     * Method that returns a map of parsed orders.
     */
    public Collection getCollection() {

        return this.map.values();

    }

    /**
     * Method that is creating an order based on it's type.
     */
    public void processOrder(Order newOrder, boolean add) {

        Map<Integer, Order> book;

        if (add) {

            if (!this.map.containsKey(newOrder.bookNumber)) {

                Map<Integer, Order> nestedMap = new HashMap<>();
                this.map.put(newOrder.bookNumber, nestedMap);
                nestedMap.put(newOrder.orderId, newOrder);

            } else {

                book = this.map.get(newOrder.bookNumber);
                book.put(newOrder.orderId, newOrder);

            }

        } else {

            book = this.map.get(newOrder.bookNumber);
            book.remove(newOrder.orderId);

        }

    }

}