package com.vgoryashko.collectionspro.orderbook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that tests OrderBookCreator.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 8/27/17
 */
public class OrderBookCreatorTest {

    /**
     * Constant that stores file separator char.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Variable that stores a path to a test xml file.
     */
    private Path testPath;

    /**
     * Variable that stores a path to a working xml file.
     */
    private Path path;

    /**
     * Variable that refers to an instance of Handler.
     */
    private Handler handler;

    /**
     * Variable that refers to an instance of XmlParser.
     */
    private XmlParser parser;

    /**
     * Method tha setups test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        testPath = FileSystems.getDefault().getPath(String.format(".%ssrc%stest%sresources%sordersTest.xml", FS, FS, FS, FS));

        path = FileSystems.getDefault().getPath(String.format(".%ssrc%smain%sresources%sorders.xml", FS, FS, FS, FS));

        handler = new Handler();

        parser = new XmlParser();

    }

    /**
     * Method that tests file parsing.
     * @throws Exception Exception
     */
    @Test
    public void whenParseXmlInvokedThenXmlFileIsParsed() throws Exception {

        Map<String, HashMap<Integer, Order>> map;
        parser.parseFile(testPath, handler);
        map = handler.getMap();

        assertThat(map.size(), is(3));

    }

}