package com.vgoryashko.collectionslite.setofcoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that prints out all possible sets of coins from given array of coins.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 5/4/17
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

    int sum;

    /**
     * Method that prints out all possible sets of coins from given array of coins.
     *
     * @param coins initial set of available coins to construct all possible sets.
     * @param amount a sum of coins in a valid set
     */
    public void printSets(int[] coins, int amount) {

        if (coins.length > 0) {

            int sum = amount;
            int step = 1;
            tmp.clear();

            for (int j = coins.length - 1, k = coins.length - 1; j >= 0 && k >= 0;) {

                if (j == k) {
                    sum -= coins[j];
                    tmp.add(coins[j]);
                } else if (k < j) {
                    sum -= coins[k];
                    tmp.add(coins[k]);
                }

                if (sum == 0) {

                    sets.add(new ArrayList<>(tmp));
                    tmp.subList(step++, tmp.size()).clear();

                    if (k != 0) {
                        tmp.add(coins[k - 1]);
                    }

                    sum = amount;

                    if (k != 0) {
                        for (Integer element : tmp) {
                            sum -= element;
                        }
                        k--;
                    }
                }
            }
        }
    }
}
