package com.vgoryashko.collectionspro.orderbook;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 7/20/17
 */
public class OrderCreatorTest {

    private final static String FS = System.getProperty("file.separator");

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void parseXmlFile() throws Exception {


        Path path = FileSystems.getDefault().getPath(String.format(".%sauxiliary%sorders.xml", FS, FS));
        new OrderCreator(path).printOrderBook();

    }

}