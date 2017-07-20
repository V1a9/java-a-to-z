package com.vgoryashko.collectionspro.orderbook;

import java.util.*;
import java.io.*;

import java.nio.file.Path;

/**
 * Class that implements main logic of the program.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 7/20/17
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
    public void printOrderBook() throws Exception {

        new XmlParser().parseFile(this.pathToFile);

    }

}
