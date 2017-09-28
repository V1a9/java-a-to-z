package com.vgoryashko.multithreading.wait.cache;

/**
 * Class that defines model structure.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/28/17
 *
 * @param <K> parameter of a key
 * @param <V> parameter for a value
 */
public class Model<K, V> {

    /**
     * Variable that stores current version of a model.
     */
    private int version;

    /**
     * Variable that stores a model name.
     */
    private K key;

    /**
     * Value that stores a model instance.
     */
    private V value;

    /**
     * Constructor for the class.
     *
     * @param newKey name of a Model
     * @param newValue that will be stored
     */
    public Model(K newKey, V newValue) {

        this.version = 0;
        this.key = newKey;
        this.value = newValue;
    }

    /**
     * Constructor for the class.
     *
     * @param newKey name of a Model
     * @param newValue that will be stored
     * @param newVersion number of a current version of value
     */
    public Model(K newKey, V newValue, int newVersion) {

        this.version = newVersion;
        this.key = newKey;
        this.value = newValue;

    }

    /**
     * Getter for the version field.
     * @return int
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Getter for the name field.
     * @return String
     */
    public K getName() {
        return this.key;
    }

    /**
     * Getter for the value field.
     * @return T
     */
    public V getValue() {
        return value;
    }

    /**
     * Setter for the field value.
     * @param value T
     */
    public void setValue(V value) {
        this.value = value;
        ++this.version;
    }
}
