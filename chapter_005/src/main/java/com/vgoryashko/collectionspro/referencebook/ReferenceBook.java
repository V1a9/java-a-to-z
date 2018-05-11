package com.vgoryashko.collectionspro.referencebook;

import java.util.Iterator;

/**
 * Class that implements reference book.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/17/17
 *
 * @param <K> key
 * @param <V> value
 */
public class ReferenceBook<K, V> implements Iterable<K> {

    /**
     * Class that stores pairs of Key -> Value.
     * @param <K>
     * @param <V>
     */
    private class Entry<K, V> {

        /**
         * Variable that stores a key.
         */
        private K key;

        /**
         * Variable that stores a value that corresponds to the key.
         */
        private V value;

        /**
         * Constructor for the class.
         *
         * @param newKey new key
         * @param newValue new value
         */
        Entry(K newKey, V newValue) {
            this.key = newKey;
            this.value = newValue;
        }
    }

    /**
     * Variable that stores a reference to an array of Objects.
     */
    private Entry[] book;

    /**
     * Variable that is used as size of the array of entries.
     */
    private int size = 10;

    /**
     * Constructor for the class.
     */
    public ReferenceBook() {

        book = new Entry[size];

    }

    /**
     * Method that insert entries into reference book.
     *
     * @param key to be inserted
     * @param value to be associated wit the key
     * @return {@code boolean}
     */
    public boolean insert(K key, V value) {

        boolean result = false;

        int position = key.hashCode() / book.length;

        if (book[position] == null) {

            book[position] = new Entry(key, value);
            result = true;

        }

        return result;

    }

    /**
     * Method that return a value based on a given key.
     *
     * @param key to be used for searching of a value
     * @return V value
     */
    public V get(K key) {

        return book[key.hashCode() / book.length] != null ? (V) book[key.hashCode() / book.length].value : null;

    }

    /**
     * Method that deletes entry based on a key value.
     *
     * @param key of an entry to be removed
     * @return {@code boolean}
     */
    public boolean delete(K key) {

        boolean result = false;
        int position = key.hashCode() / book.length;

        if (book[position] != null) {

            book[position] = null;
            result = true;

        }

        return result;

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private int position = 0;

            @Override
            public boolean hasNext() {

                boolean result = false;
                for (int i = position; i < book.length; i++) {

                    if (book[i] != null) {
                        result = true;
                        break;
                    }

                }
                return result;
            }

            @Override
            public K next() {

                K result = null;

                for (int i = position; i < book.length; i++) {

                    if (book[i] != null) {
                        result = (K) book[i].value;
                        if (position < book.length - 1) {
                            position++;
                        }
                        break;
                    }

                }
                return result;
            }
        };
    }
}
