package com.vgoryashko.collectionspro.orderbook;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 7/20/17
 */
public class OrderBookCreatorTest {

    private final static String FS = System.getProperty("file.separator");

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void parseXmlFile() throws Exception {

        Path path = FileSystems.getDefault().getPath(String.format(".%sauxiliary%sorders.xml", FS, FS));
        new XmlParser().parseFile(path);

    }

}