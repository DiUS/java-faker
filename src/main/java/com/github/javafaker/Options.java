package com.github.javafaker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
     * Returns a random subset from Enum.
     *
     * @param enumeration The Enum to take a random subset from.
     * @return A set that is a subset contains randomly selected element from the enum.
     */
    public <E extends Enum<E>> Set<E> randomSubset(Class<E> enumeration) {
        E[] enumConstants = enumeration.getEnumConstants();
        return randomSubset(enumConstants);
    }

    /**
     * Returns a random subset from an array.
     *
     * @param array The array to take a random subset from.
     * @param <E>   The type of the elements in the array.
     * @return An set that is a subset contains randomly selected element from the array.
     */
    public <E> Set<E> randomSubset(E[] array) {
        Set<E> randomSubset = new HashSet();
        if (array == null || array.length == 0) {
            return randomSubset;
        }
        int random = faker.random().nextInt(array.length + 1);
        for (int i = 0; i < random; i++) {
            randomSubset.add(nextElement(array));
        }
        return randomSubset;
    }

    /**
     * Returns a random subset from a list.
     *
     * @param list The list to take a random subset from.
     * @param <E>  The type of the elements in the list.
     * @return A set that is a subset contains randomly selected element from the list.
     */
    public <E> Set<E> randomSubset(List<E> list) {
        Set<E> randomSubset = new HashSet();;
        if (list == null || list.size() == 0) {
            return randomSubset;
        }
        int random = faker.random().nextInt(list.size() + 1);
        for (int i = 0; i < random; i++) {
            randomSubset.add(nextElement(list));
        }
        return randomSubset;
    }
}
