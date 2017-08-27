package com.vgoryashko.collectionspro.orderbook;

import java.nio.file.Path;
import java.util.Map;

/**
 * Class that creates and prints an Order Book.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 8/27/17
 */
public class OrderBook {

    /**
     * Map that stores all orders according to a book parameter.
     */
    private Map<String, Map<Integer, Order>> map;

    /**
     * Variable that refers to an instance of XmlParser.
     */
    private XmlParser xmlParser;

    /**
     * Variable that refers to an instance of a Handler.
     */
    private Handler handler;

    /**
     * Variable that stores a path to a file to be parsed.
     */
    private Path pathFile;

    /**
     * Constructor for the class.
     */
    public OrderBook(Path pathFile) {

        this.handler = new Handler();
        this.xmlParser = new XmlParser();
        this.pathFile = pathFile;

    }

    /**
     * Method that implements main logic of a program.
     */
    public void mainLogic() throws Exception {

        this.xmlParser.parseFile(this.pathFile, this.handler);
        this.map = this.handler.getCollection();


    }

}
