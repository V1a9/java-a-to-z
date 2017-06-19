package com.vgoryashko.collectionspro.tree;

import java.util.Iterator;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 6/17/17
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    class Node<E> {
        List<Node<E>> children = new Tre;
        E value;
    }

    @Override
    public boolean add(E parent, E child) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}

}
