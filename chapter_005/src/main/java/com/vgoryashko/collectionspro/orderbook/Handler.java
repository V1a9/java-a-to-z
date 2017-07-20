package com.vgoryashko.collectionspro.orderbook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles actions on data from xml file based on attributes read.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 7/20/17
 */
public class Handler extends DefaultHandler {

    /**
     * Class that defines structure of an element.
     */
    private class Node {

        int orderId;
        String bookNumber;
        String operation;
        float price;
        int volume;

        public Node(String bookNumber, String operation, float price, int volume, int orderId) {

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

            Node node = (Node) o;

            if (orderId != node.orderId) return false;
            if (Float.compare(node.price, price) != 0) return false;
            if (volume != node.volume) return false;
            if (!bookNumber.equals(node.bookNumber)) return false;
            return operation.equals(node.operation);
        }

        @Override
        public int hashCode() {
            int result = orderId;
            result = 31 * result + bookNumber.hashCode();
            result = 31 * result + operation.hashCode();
            result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
            result = 31 * result + volume;
            return result;
        }
    }

    /**
     * Map that stores all orders.
     */
    private Map<Integer, Node> map;

    /**
     * Setter for the instance member map.
     * @param map
     */
    public void setMap(Map<Integer, Node> map) {
        this.map = map;
    }


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

        if (!qName.equals( "Orders")) {

            if (qName.equals("AddOrder")) {

                map.put(Integer.parseInt(attributes.getValue("orderId")),
                        new Node(attributes.getValue("book"),
                                attributes.getValue("operation"),
                                Float.parseFloat(attributes.getValue("price")),
                                Integer.parseInt(attributes.getValue("volume")),
                                Integer.parseInt(attributes.getValue("orderId"))));
            } else if (qName.equals("DeleteOrder")) {

                map.remove(Integer.parseInt(attributes.getValue("orderId")));

            }

        }

    }

    /**
     * Receive notification of the end of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }


}
