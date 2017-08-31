package com.vgoryashko.collectionspro.orderbook;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that creates and prints an Order Book.
 *
 * @author Vlad Goryashko
 * @version 0.9
 * @since 8/31/17
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
     * Constant that sets the SELL.
     */
    private static final String SELL = "SELL";

    /**
     * Constant that sets the BUY.
     */
    private static final String BUY = "BUY";

    /**
     * Getter for the handler member.
     * @return instance of Handler
     */
    public Handler getHandler() {
        return this.handler;
    }

    /**
     * Getter for the xmlParser member.
     * @return xmlParser
     */
    public XmlParser getXmlParser() {
        return this.xmlParser;
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
     * Variable that refers to a final Map of buy/sell orders.
     */
    private TreeMap<String, TreeMap<Float, Order>> finalOrderBook;

    /**
     * Constructor for the class.
     *
     * @param pathFile a path to a file
     */
    public OrderBook(Path pathFile) {

        this.handler = new Handler();
        this.xmlParser = new XmlParser();
        this.pathFile = pathFile;
        this.finalOrderBook = new TreeMap<>();
        this.finalOrderBook.put(SELL, new TreeMap<>(descending));
        this.finalOrderBook.put(BUY, new TreeMap<>(ascending));

    }

    /**
     * Method that walks through all orders.
     *
     * @param map to be processed
     * @return TreeMap[] of sorted sell and bid orders
     */
    public TreeMap[] aggregateOrders(Map<String, HashMap<Integer, Order>> map) {

        Collection<HashMap<Integer, Order>> collection = new LinkedList<>(map.values());

        Collection<Order> orders;

        for (HashMap<Integer, Order> hashMap : collection) {

            orders = new LinkedList<>(hashMap.values());

            for (Order order : orders) {

                if (order.getOperation().equals(SELL)) {

                    this.placeOrder(order, true);

                } else {

                    this.placeOrder(order, false);

                }

            }

        }

        return new TreeMap[]{this.bidBook, this.sellBook};

    }

    /**
     * Comparator that is used for elements sorting in ascending order.
     */
    private final Comparator<Float> ascending = new Comparator<Float>() {

        @Override
        public int compare(Float o1, Float o2) {
            return (o1 > o2 ? -1 : (o1 < o2 ? 1 : 0));
        }
    };


    /**
     * Comparator that is used for elements sorting in descending order.
     */
    private final Comparator<Float> descending = new Comparator<Float>() {

        @Override
        public int compare(Float o1, Float o2) {
            return (o1 > o2 ? 1 : (o1 < o2 ? -1 : 0));
        }
    };

    /**
     * Method that places orders to a final collections based on the BUY/SELL flag.
     *
     * @param order order to be added
     * @param sell flag that defines either a type of order SELL or BUY
     */
    public void placeOrder(Order order, boolean sell) {



        if (bidBook == null && sellBook == null) {

            bidBook = new TreeMap<>(ascending);
            sellBook = new TreeMap<>(descending);

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
     *
     * @return TreeMap
     */
    public TreeMap matchOrders() {

        LinkedList<Order> bidCol = new LinkedList<>(this.bidBook.values());
        LinkedList<Order> sellCol = new LinkedList<>(this.sellBook.values());

        ListIterator<Order> bidIterator = bidCol.listIterator();
        ListIterator<Order> sellIterator = sellCol.listIterator();

        boolean bidNext = true;
        boolean sellNext = true;

        Order bidOrder = null;
        Order sellOrder = null;

        while (bidIterator.hasNext() && sellIterator.hasNext()) {


            if (bidNext) {

                bidOrder = bidIterator.next();
            }

            while (sellIterator.hasNext()) {

                if (sellNext) {

                    sellOrder = sellIterator.next();
                }

                if (bidOrder.getPrice() >= sellOrder.getPrice()) {

                    if (bidOrder.getVolume() - sellOrder.getVolume() > 0) {

                        bidOrder.setVolume(bidOrder.getVolume() - sellOrder.getVolume());
                        sellIterator.remove();
                        sellNext = true;
                        bidNext = false;

                    } else if (bidOrder.getVolume() - sellOrder.getVolume() < 0) {

                        sellOrder.setVolume(sellOrder.getVolume() - bidOrder.getVolume());
                        bidIterator.remove();
                        sellNext = false;
                        bidNext = true;
                        break;

                    } else {

                        bidIterator.remove();
                        sellIterator.remove();
                        sellNext = true;
                        bidNext = true;
                        break;

                    }

                }

            }

        }

        for (Order order : bidCol) {

            finalOrderBook.get(BUY).put(order.getPrice(), order);

        }

        for (Order order : sellCol) {

            finalOrderBook.get(SELL).put(order.getPrice(), order);

        }

        return finalOrderBook;
    }

    /**
     * Method that prints Order Book into standard output.
     */
    public void printBook() {

        Collection<Order> bidCol = this.finalOrderBook.get(BUY).values();
        Collection<Order> sellCol = this.finalOrderBook.get(SELL).values();

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

        this.aggregateOrders(map);

        this.matchOrders();

        this.printBook();

    }

}

