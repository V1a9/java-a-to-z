package com.vgoryashko.collectionslite.setofcoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that prints out all possible sets of coins from given array of coins.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 5/16/17
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

    /**
     * Variable that holds temporary arrays of results.
     */
    private List<Integer> tmp = new ArrayList<>();

    /**
     * Method that prints out all possible sets of coins from given array of coins.
     *
     * @param coins initial set of available coins to construct all possible sets.
     * @param amount a sum of coins in a valid set
     * @return List
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

            for (int i = coins.length - 1, j = coins.length - 1; i >= 0 && j >= 0;) {

                if (i == j) {
                    tmp.add(coins[i]);
                    sum -= coins[i];
                } else {
                    tmp.add(coins[j]);
                    sum -= coins[j];
                }

                if (sum == 0) {

                    sets.add(new ArrayList<>(tmp));

                    if (tmp.size() == 2) {

                        tmp.remove(tmp.size() - 1);
                        tmp.add(coins[--j]);

                    } else if (j > 0) {
                        for (int k = 1; k < tmp.size() - 1; k++) {
                            if (!tmp.get(k).equals(tmp.get(k + 1))) {
                                tmp.remove(k);
                                tmp.add(tmp.get(k));
                                break;
                            } else if (k == tmp.size() - 2) {
                                tmp.subList(tmp.size() - 1, tmp.size()).clear();
                                tmp.add(coins[--j]);
                                break;
                            }
                        }

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

                } else if (sum < 0) {
                    sum += coins[j];
                    tmp.remove(tmp.get(tmp.size() - 1));
                    --j;
                }
            }
        }
        return sets;
    }
}
