package com.vgoryashko.collectionslite.setofcoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that prints out all possible sets of coins from given array of coins.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 5/15/17
 */
public class PrintSets {

    /**
     * Variable that referring to a List of sets of coins.
     */
    private List<List<Integer>> sets;

    /**
     * Constructor for the class.
     */
    public PrintSets() {
        sets = new ArrayList<>();
    }

    private List<Integer> tmp = new ArrayList<>();

    /**
     * Method that prints out all possible sets of coins from given array of coins.
     *
     * @param coins initial set of available coins to construct all possible sets.
     * @param amount a sum of coins in a valid set
     */
    public List<List<Integer>> printSets(int[] coins, int amount) {

        int sum = amount;
        int arrayLength = coins.length;
        tmp.clear();

        if (arrayLength == 1) {

            while ((sum -= coins[0]) >= 0) {
                tmp.add(coins[0]);
                if (sum == 0) {
                    sets.add(new ArrayList<>(tmp));
                }
            }
            --arrayLength;

        } else if (arrayLength > 0) {

            tmp.add(coins[coins.length - 1]);
            sum -= coins[coins.length - 1];

            for (int i = coins.length - 1, j = coins.length - 1; i >= 0 && j >= 0; ) {

                if (i == j) {
                    tmp.add(coins[i]);
                    sum -= coins[i];
                } else {
                    tmp.add(coins[j]);
                    sum -= coins[j];
                }

                if (sum == 0) {

                    sets.add(new ArrayList<>(tmp));

                    if (tmp.get(tmp.size() - 1).equals(tmp.get(tmp.size() - 2)) && j > 0) {
                        tmp.subList(tmp.size() - 1, tmp.size()).clear();
                        tmp.add(coins[--j]);

                    } else if (tmp.get(1) != coins[j]) {

                        for (int k = 1; k < tmp.size(); k++) {
                            if (!tmp.get(k).equals(tmp.get(k + 1)) && tmp.get(k + 1) == coins[j]) {
                                tmp.remove(k);
                                tmp.add(k, coins[j]);
                                break;
                            }
                        }
                    }

                    sum = amount;
                    for (Integer element : tmp) {
                        sum -= element;
                    }

                    if (sum == 0) {
                       return printSets(Arrays.copyOf(coins, --arrayLength), amount);
                    }
                }
            }
        }
        return sets;
    }
}
