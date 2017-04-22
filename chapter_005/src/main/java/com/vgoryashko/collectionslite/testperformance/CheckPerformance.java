package com.vgoryashko.collectionslite.testperformance;

import java.util.Collection;
import java.util.Iterator;

/**
 * Class that checks performance of collections ArrayList, LinkedList & TreeSet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/22/17
 */
public class CheckPerformance {

    /**
     * Method that adds an String element n times to a collection.
     *
     * @param collection collection where elements are going to be inserted
     * @param line string to be added
     * @param amount number of lines to be inserted
     * @return <code>long</code>
     */
    public long add(Collection<String> collection, String line, int amount) {

        long result;
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(line + i);
        }
        long timeEnd = System.currentTimeMillis();
        result = timeEnd - timeStart;

        return result;
    }

    /**
     * Method that checks removing of elements from a collection.
     *
     * @param collection collection where elements are going to be inserted
     * @param amount number of lines to be inserted
     * @return <code>long</code>
     */
    public long delete(Collection<String> collection, int amount) {
        long result = 0;
        Iterator<String> iterator = collection.iterator();

        if (amount <= collection.size()) {

            long timeStart = System.currentTimeMillis();

            while (iterator.hasNext() && amount-- > 0) {
                iterator.next();
                iterator.remove();
            }

            long timeEnd = System.currentTimeMillis();
            result = timeEnd - timeStart;
        }
        return result;
    }

}
