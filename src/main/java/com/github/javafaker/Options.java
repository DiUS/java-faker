package com.github.javafaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Options {
    private final Faker faker;

    protected Options(Faker faker) {
        this.faker = faker;
    }

    /**
     * Returns a random element from an varargs.
     *
     * @param options The varargs to take a random element from.
     * @param <E>   The type of the elements in the varargs.
     * @return A randomly selected element from the varargs.
     */
    public <E> E option(E... options) {
        return options[faker.random().nextInt(options.length)];
    }

    /**
     * Returns a random element from Enum.
     *
     * @param enumeration The Enum to take a random element from.
     * @return A randomly selected element from the enum.
     */
    public <E extends Enum<E>> E option(Class<E> enumeration) {
        E[] enumConstants = enumeration.getEnumConstants();
        return enumConstants[faker.random().nextInt(enumConstants.length)];
    }

    /**
     * Returns a random element from an array.
     *
     * @param array The array to take a random element from.
     * @param <E>   The type of the elements in the array.
     * @return A randomly selected element from the array.
     */
    public <E> E nextElement(E[] array) {
        return array[faker.random().nextInt(array.length)];
    }

    /**
     * Returns a random element from a list.
     *
     * @param list The list to take a random element from.
     * @param <E>  The type of the elements in the list.
     * @return A randomly selected element from the list.
     */
    public <E> E nextElement(List<E> list) {
        return list.get(faker.random().nextInt(list.size()));
    }

    /**
     * Returns a random subset of the input list
     * @param list input list
     *
     * @return A random subset.
     */
    public <E> List<E> getRandomSubset(List<E> list) {
        List<E> toReturn = new ArrayList<E>();

        int inputSize = list.size();
        if (inputSize == 0) {
            return toReturn; // If we get an empty set, we have nothing to do but return the empty set back
        }

        int subsetSize = (int)(Math.random() * (inputSize)); // Pick a subset size. Can also be the full list
        Set<Integer> subsetIndex = new HashSet<Integer>();

        for (int i = 0; i < subsetSize; i++) {
            int randomIndex = (int)(Math.random() * (inputSize)); // Pick a random index within the input list
            while (subsetIndex.contains(randomIndex)) {
                randomIndex = (int)(Math.random() * (inputSize)); // If the index is already picked, choose another one
            }
            subsetIndex.add(randomIndex);
        }

        for (Integer index : subsetIndex) {
            toReturn.add(list.get(index)); // Build the subset list
        }

        return toReturn;
    }
}
