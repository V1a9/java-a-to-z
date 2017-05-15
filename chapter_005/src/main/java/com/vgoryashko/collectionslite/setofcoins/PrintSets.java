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

        int sum = amount;

        if (coins.length == 1) {
            while ((sum -= coins[0]) >= 0) {
                tmp.add(coins[0]);
                if (sum == 0) {
                    sets.add(new ArrayList<>(tmp));
                }
            }
        } else {

        }

//        if (coins.length > 0) {
//
//            int sum = amount;
//            tmp.clear();
//            int currentK;
//
//            for (int j = coins.length - 1, k = coins.length - 1; j >= 0 && k >= 0;) {
//
//                if (j == k) {
//                    sum -= coins[j];
//                    tmp.add(coins[j]);
//                } else if (k < j) {
//                    sum -= coins[k];
//                    tmp.add(coins[k]);
//                }
//
//                boolean equal = false;
//
//                if (sum == 0) {
//                    sets.add(new ArrayList<>(tmp));
//                    if (tmp.size() == 2) {
//                        if (tmp.get(0) == tmp.get(1)) {
//                            tmp.subList(tmp.size() - 1, tmp.size()).clear();
//                        }
//                    } else {
//                        for (int l = 1; l < tmp.size() - 1; l++) {
//                            if (tmp.get(l) != tmp.get(l + 1)) {
//                                tmp.subList(l, tmp.size()).clear();
//                                if (k > 0) {
//                                    tmp.add(coins[--k]);
//                                    currentK = k;
//                                } else {
//                                    tmp.add(coins[0]);
//                                }
//                                break;
//                            } else {
//                                equal = true;
//                            }
//                        }
//                    }
//
//                    if (equal) {
//                        tmp.subList(tmp.size() - 1, tmp.size()).clear();
//                        if (k > 0) {
//                            tmp.add(coins[--k]);
//                            currentK = k;
//                        } else {
//                            tmp.add(coins[0]);
//                        }
//                    }
//
//                    sum = amount;
//
//                    if (k != 0) {
//                        for (Integer element : tmp) {
//                            sum -= element;
//                        }
//                    }
//                }
//            }
//        }
    }
}
