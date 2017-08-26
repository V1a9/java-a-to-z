package com.vgoryashko.collectionspro.orderbook;

/**
 * Class that defines a structure of an order.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 8/26/17
 */
public class Order {

    /**
     * Store an order Id.
     */
    private int orderId;

    /**
     * Stores a value of a book that an order belongs.
     */
    private String bookNumber;

    /**
     * Stores a value that defines an operation (SELL/BUY).
     */
    private String operation;

    /**
     * Stores price for a particular order.
     */
    private float price;

    /**
     * Getter for the order Id.
     * @return int
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * Getter for the bookNumber.
     * @return book number identifier
     */
    public String getBookNumber() {
        return this.bookNumber;
    }

    /**
     * Getter for the operation type.
     * @return operation identifier
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Getter for the price.
     * @return float
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Getter for the volume.
     * @return int
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Stores a volume for an order.
     */
    private int volume;

    /**
     * Constructor for the class.
     * @param bookNumber book number
     * @param operation type of an operation (SELL/BUY)
     * @param price price
     * @param volume volume
     * @param orderId order Id
     */
    public Order(String bookNumber, String operation, float price, int volume, int orderId) {

        this.bookNumber = bookNumber;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;

    }

    /**
     * Method that overrides equals() from the Object class.
     * @param o order
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return orderId == order.orderId;
    }

    /**
     * Method that overrides hashCode() from the Object class.
     * @return
     */
    @Override
    public int hashCode() {
        return orderId;
    }

}
