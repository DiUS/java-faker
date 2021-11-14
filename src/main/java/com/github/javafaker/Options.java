package com.github.javafaker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
     * Returns a random sub-array from a varargs.
     *
     * @param options The varargs to take random elements from.
     * @param <E>  The type of the elements in the list.
     * @return an array with randomly selected elements from the varargs.
     */
    public <E> E[] randomSubset(E... options) {
        return randomSubArray(options);
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
     * Returns a random sub-array from an array.
     *
     * @param array The list to take a random element from.
     * @param <E>  The type of the elements in the list.
     * @return an array with randomly selected elements from the original array.
     */
    public <E> E[] randomSubArray(E[] array) {
        shuffleArray(array);
        return Arrays.copyOfRange(array, 0, faker.number().numberBetween(0, array.length+1));
    }

    private static <E> void shuffleArray(E[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            E a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
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
     * Returns a random sub-list from a list.
     *
     * @param <E>  The type of the elements in the list.
     * @param list The list to take a random element from.
     * @return A list with randomly selected elements from the original list.
     */
    public <E> List<E> randomSubList(List<E> list) {
        Collections.shuffle(list);
        return list.subList(0, faker.number().numberBetween(0, list.size()+1));
    }
}
