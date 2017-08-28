package com.vgoryashko.collectionspro.orderbook;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * Class that creates and prints an Order Book.
 *
 * @author Vlad Goryashko
 * @version 0.7
 * @since 8/28/17
 */
public class OrderBook {

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
     *
     * @param pathFile a path to a file
     */
    public OrderBook(Path pathFile) {

        this.handler = new Handler();
        this.xmlParser = new XmlParser();
        this.pathFile = pathFile;

    }

    /**
     * Collection that contains all BUY orders.
     */
    private TreeMap<Float, Order> bidBook;

    /**
     * Collection that contains all SELL orders.
     */
    private TreeMap<Float, Order> sellBook;

    /**
     * Method that walks through all orders.
     */
    public void aggregateOrders(Map<String, HashMap<Integer, Order>> map) {

        Collection<HashMap<Integer, Order>> collection = new LinkedList<>(map.values());

        Collection<Order> orders;

        for (HashMap<Integer, Order> hashMap : collection) {

            orders = new LinkedList<>(hashMap.values());

            for (Order order : orders) {

                if (order.getOperation().equals("SELL")) {

                    this.placeOrder(order, true);

                } else {

                    this.placeOrder(order, false);

                }

            }

        }

    }

    /**
     * Method that places orders to a final collections based on the BUY/SELL flag.
     *
     * @param order order to be added
     * @param sell flag that defines either a type of order SELL or BUY
     */
    public void placeOrder(Order order, boolean sell) {

        final Comparator<Float> ASCEN = new Comparator<Float>() {

            @Override
            public int compare(Float o1, Float o2) {
                return (o1 > o2 ? -1 : (o1 < o2 ? 1 : 0));
            }
        };

        final Comparator<Float> DESC = new Comparator<Float>() {

            @Override
            public int compare(Float o1, Float o2) {
                return (o1 > o2 ? 1 : (o1 < o2 ? -1 : 0));
            }
        };

        if (bidBook == null && sellBook == null) {

            bidBook = new TreeMap<>(ASCEN);
            sellBook = new TreeMap<>(DESC);

        }

        if (!sell) {

            if (!bidBook.containsKey(order.getPrice())) {

                bidBook.put(order.getPrice(), order);

            } else {

                Order tmp = bidBook.get(order.getPrice());
                bidBook.put(order.getPrice(), new Order(tmp.getBookNumber(), tmp.getOperation(), tmp.getPrice(), tmp.getVolume() + order.getVolume(), tmp.getOrderId()));

            }

        } else {

            if (!sellBook.containsKey(order.getPrice())) {

                sellBook.put(order.getPrice(), order);

            } else {

                Order tmp = sellBook.get(order.getPrice());
                sellBook.put(order.getPrice(), new Order(tmp.getBookNumber(), tmp.getOperation(), tmp.getPrice(), tmp.getVolume() + order.getVolume(), tmp.getOrderId()));

            }

        }

    }

    /**
     * Method that matches orders on both sides of the Order Book.
     */
    public void matchOrders() {

        /**
         * todo
         */


    }

    /**
     * Method that prints Order Book into standard output.
     */
    public void printBook() {

        Collection<Order> bidCol = bidBook.values();
        Collection<Order> sellCol = sellBook.values();

        System.out.println("\nBid\nprice\t@\tvolume");

        for (Order order : bidCol) {

            System.out.printf("%.1f%s@%s%d\n", order.getPrice(), "\t", "\t", order.getVolume());

        }

        System.out.println("\nSell\nprice\t@\tvolume");

        for (Order order : sellCol) {

            System.out.printf("%.1f%s@%s%d\n", order.getPrice(), "\t", "\t", order.getVolume());

        }

    }

    /**
     * Method that implements main logic of a program.
     *
     * @throws Exception Exception
     */
    public void start() throws Exception {

        this.xmlParser.parseFile(this.pathFile, this.handler);

        Map<String, HashMap<Integer, Order>> map = this.handler.getMap();

        aggregateOrders(map);

        this.printBook();

    }

}

