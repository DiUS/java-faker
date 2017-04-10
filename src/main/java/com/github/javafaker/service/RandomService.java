package com.github.javafaker.service;

import java.util.List;
import java.util.Random;

public class RandomService {
    private static final Random SHARED_RANDOM = new Random();
    private final Random random;

    /**
     * Uses a default shared random.
     */
    public RandomService() {
        this(SHARED_RANDOM);
    }

    /**
     * @param random If null is passed in, a default Random is assigned
     */
    public RandomService(Random random) {
        this.random = random != null ? random : SHARED_RANDOM;
    }

    public int nextInt(int n) {
        return random.nextInt(n);
    }

    public long nextLong() {
        return random.nextLong();
    }

    // lifted from http://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range
    public long nextLong(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }

        long bits, val;
        do {
            long randomLong = random.nextLong();
            bits = (randomLong << 1) >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0L);
        return val;
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public Boolean nextBoolean() {
        return random.nextBoolean();
    }

    /**
     * Returns a random element from an array.
     *
     * @param array The array to take a random element fom.
     * @param <E>   The type of the elements in the array.
     * @return A randomly selected element from the array.
     */
    public <E> E nextElement(E[] array)
    {
        return array[this.nextInt(array.length)];
    }

    /**
     * Returns a random element from a list.
     *
     * @param list The list to take a random element fom.
     * @param <E>  The type of the elements in the list.
     * @return A randomly selected element from the list.
     */
    public <E> E nextElement(List<E> list)
    {
        return list.get(this.nextInt(list.size()));
    }

    /**
     * Returns a random enumeration value
     *
     * @param enumeration The enumberation to take a random value from
     * @param <E>         The type of the enumeration
     * @return A randomly selected emumeration value
     */
    public <E extends Enum<E>> E nextEnumValue(Class<E> enumeration)
    {
        return nextElement(enumeration.getEnumConstants());
    }
}
