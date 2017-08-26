package com.vgoryashko.collectionspro.orderbook;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Class that tests OrderBookCreator.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 8/26/17
 */
public class OrderBookCreatorTest {

    /**
     * Constant that stores file separator char.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Method tha setups test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Method that tests file parsing.
     * @throws Exception Exception
     */
    @Test
    public void parseXmlFile() throws Exception {

        Path path = FileSystems.getDefault().getPath(String.format(".%sauxiliary%sorders.xml", FS, FS));
        new XmlParser().parseFile(path);

    }

}