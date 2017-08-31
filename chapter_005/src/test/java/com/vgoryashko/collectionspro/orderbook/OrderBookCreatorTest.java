package com.vgoryashko.collectionspro.orderbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class that tests OrderBookCreator.
 *
 * @author Vlad Goryashko
 * @version 0.9
 * @since 8/31/17
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
     * Variable that refers to an instance of Map with orders after parsing of a xml file.
     */
    private Map<String, HashMap<Integer, Order>> map;

    /**
     * Variable that refers to an instance of the OrderBook.
     */
    private OrderBook orderBook;

    /**
     * Method tha setups test environments.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        this.testPath = FileSystems.getDefault().getPath(String.format(".%ssrc%stest%sresources%sordersTest.xml", FS, FS, FS, FS));

        this.path = FileSystems.getDefault().getPath(String.format(".%ssrc%smain%sresources%sorders.xml", FS, FS, FS, FS));

        this.handler = new Handler();

        this.parser = new XmlParser();

    }

    /**
     * Method that tests file parsing.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenParseXmlInvokedThenXmlFileIsParsed() throws Exception {

        Order order;

        this.parser.parseFile(this.testPath, this.handler);
        this.map = this.handler.getMap();

        assertThat(this.map.size(), is(3));

        HashMap<Integer, Order> hashMap = this.map.get("book-1");
        order = new Order("book-1", "BUY", 99.70F, 16, 3);
        assertThat(hashMap.get(3), is(order));

        hashMap = this.map.get("book-2");
        order = new Order("book-2", "SELL", 100.20F, 42, 8);
        assertThat(hashMap.get(8), is(order));

        assertNull(hashMap.get(5));

    }

    /**
     * Method that tests aggregation of orders with the same price and sorting.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenAggregateOrdersInvokedThenTwoTreesWithAggregatedAndSortedNodesReturned() throws Exception {

        Map<Float, Integer> expectedOrderOfOrders = new LinkedHashMap<>();

        expectedOrderOfOrders.put(101.00F, 10);
        expectedOrderOfOrders.put(100.00F, 12);
        expectedOrderOfOrders.put(99.00F, 27);

        expectedOrderOfOrders.put(98.00F, 9);
        expectedOrderOfOrders.put(99.00F, 30);
        expectedOrderOfOrders.put(102.00F, 10);

        Float[] expectedOrderOfPrices = new Float[]{101.00F, 100.00F, 99.00F, 98.00F, 99.00F, 102.00F};
        Integer[] expectedOrderOfValues = new Integer[]{10, 12, 27, 9, 30, 10};

        this.orderBook = new OrderBook(testPath);
        this.parser = this.orderBook.getXmlParser();
        this.parser.parseFile(testPath, this.orderBook.getHandler());

        this.map = this.orderBook.getHandler().getMap();

        TreeMap[] treeMaps = this.orderBook.aggregateOrders(this.map);

        this.checkTree(treeMaps, expectedOrderOfPrices);
        this.checkTree(treeMaps, expectedOrderOfValues);

    }

    /**
     * Method that checks correctness of TreeMap.
     *
     * @param <T> type to be used
     * @param tree to be checked
     * @param array of expected results
     */
    public <T> void checkTree(TreeMap[] tree, T[] array) {

        int index = 0;

        for (TreeMap t : tree) {

            Set actSet = t.entrySet();

            for (Object o : actSet) {

                Map.Entry entry = (Map.Entry) o;

                if (array.getClass().getName().equals("[Ljava.lang.Float;")) {

                    assertThat(entry.getKey(), is(array[index++]));

                } else {

                    Order order = (Order) entry.getValue();
                    assertThat(order.getVolume(), is(array[index++]));

                }

            }

        }

    }

    /**
     * Method that tests matchOrders method.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenMatchOrdersInvokedThenAllOrdersAreMatched() throws Exception {

        this.orderBook = new OrderBook(testPath);
        this.parser = this.orderBook.getXmlParser();
        this.parser.parseFile(testPath, this.orderBook.getHandler());

        this.map = this.orderBook.getHandler().getMap();
        this.orderBook.aggregateOrders(this.map);

        TreeMap<String, TreeMap<Float, Order>> finalOrderBook = this.orderBook.matchOrders();

        TreeMap<Float, Order> treeMap = finalOrderBook.get("BUY");
        assertThat(treeMap.get(99.00F).getVolume(), is(10));

        treeMap = finalOrderBook.get("SELL");
        assertThat(treeMap.get(102.00F).getVolume(), is(10));

    }

}