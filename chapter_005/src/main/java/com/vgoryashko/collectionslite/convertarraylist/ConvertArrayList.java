package com.vgoryashko.collectionslite.convertarraylist;

import com.vgoryashko.collectionslite.convertlist.ConvertList;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that converts List of arrays to the List<Integer>.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/23/17
 */
public class ConvertArrayList extends ConvertList {

    /**
     * Method that converts List of arrays to the List<Integer>.
     *
     * @param list list to be converted.
     * @return <code>List<Integer></code>
     */
    public List<Integer> convert(List<int[]> list) {

        List<Integer> integerList = new ArrayList<>();

        for (int[] array : list) {
            for (int i : array) {
                integerList.add(i);
            }
        }
        return integerList;
    }

}
