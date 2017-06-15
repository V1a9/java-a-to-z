package com.vgoryashko.collectionspro.simplearray;

/**
 * Class that implements simple array (SimpleArray<T>).
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 15.06.2017
 *
 * @param <T> type to be used
 */
public class SimpleArray<T> {

    /**
     * Variable that is referring to an array of Objects.
     */
    private Object[] simpleArray;

    /**
     * Getter for the member size.
     * @return {@code int}
     */
    public int getIndex() {
        return index;
    }

    /**
     * Variable that is used as an index in the array.
     */
    private int index = 0;

    /**
     * Getter for simpleArray.
     */
    public Object[] getSimpleArray() {
        return this.simpleArray;
    }

    /**
     * Constructor for the class.
     *
     * @param size of an array to be created.
     */
    public SimpleArray(int size) {
        this.simpleArray = new Object[size];
    }

    /**
     * Method that adds an object to an array.
     *
     * @param object to be added.
     */
    public void add(T object) {
        simpleArray[index++] = object;
    }

    /**
     * Method  that gets an object on the requested position.
     * @param position of the element in the array.
     * @return {@code T}
     */
    public T get(int position) {
        return (T) simpleArray[position];
    }

    /**
     * Method that update an object on the requested position.
     *
     * @param position where an object to be updated
     * @param object to be updated
     */
    public void update(int position, T object) {
        this.simpleArray[position] = object;
    }

    /**
     * Method that deletes an object.
     * @param position of an object to be deleted
     */
    public void delete(int position) {
        simpleArray[position] = null;
    }
}
