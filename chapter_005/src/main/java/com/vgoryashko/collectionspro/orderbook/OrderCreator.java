package com.vgoryashko.collectionspro.orderbook;

import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Class that implements main logic of the program.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 7/25/17
 */
public class OrderCreator {

    /**
     * Variable that refers to a path of a xml file to be parsed.
     */
    private Path pathToFile;

    /**
     * Constructor for the class.
     * @param pathToFile to be parsed
     */
    public OrderCreator(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Method that parses an xml input file.
     */
    public <V> void printOrderBook() throws Exception {

        XmlParser parser = new XmlParser();

        Collection<V> list = new LinkedList<V>(parser.parseFile(this.pathToFile));

        System.out.println("Pause");


    }

}
