package com.github.javafaker.service;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class RandomService {
    private final Random random;

    /**
     * @param random If null is passed in, a default Random is assigned
     */
    public RandomService(Random random) {
        this.random = random != null ? random : RandomUtils.JVM_RANDOM;
    }

    public int nextInt(int n) {
        return RandomUtils.nextInt(random, n);
    }

    // lifted from http://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range
    public long nextLong(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }

        long bits, val;
        do {
            long randomLong = RandomUtils.nextLong(random);
            bits = (randomLong << 1) >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0L);
        return val;
    }

    public double nextDouble() {
        return RandomUtils.nextDouble(random);
    }
}
