package com.vgoryashko.multithreading.wait.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that implements cache for storing models.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/28/17
 *
 * @param <K> parameter of a key
 * @param <V> parameter for a value
 */
@ThreadSafe
public class Cache<K, V> {

    /**
     * Variable that refers to an instance of collection for storing models.
     */
    private final ConcurrentHashMap<K, Model<K, V>> cacheMap;

    /**
     * Constructor for the class.
     */
    public Cache() {
        cacheMap = new ConcurrentHashMap<>();
    }

    /**
     * Method that adds an model to the cache.
     *
     * @param key parameter
     * @param value parameter
     */
    public void add(K key, V value) {

        this.cacheMap.computeIfAbsent(key, (v) -> new Model<>(key, value));
    }

    /**
     * Method tha that deletes an model from the cache.
     *
     * * @param key parameter
     */
    public void delete(K key) {

        this.cacheMap.computeIfPresent(key, (k, v) -> {

            return this.cacheMap.remove(k);

        });
    }

    /**
     * Method that updates an entry in the cache.
     *
     * @param key parameter
     * @param value parameter
     * @param version number of a current version of value
     */
    public void update(K key, V value, int version) {

        this.cacheMap.computeIfPresent(key, (k, v) -> {

            if (v.getVersion() != version) {

                throw new OptimisticException("Model can't be overridden as its version doesn't match to the model's version in the collection.");

            } else {
                v.setValue(value);
                return this.cacheMap.replace(k, v);
            }

        });
    }

    /**
     * Getter for the cacheMap.
     *
     * @return cacheMap
     */
    public ConcurrentHashMap<K, Model<K, V>> getCacheMap() {
        return this.cacheMap;
    }
}
