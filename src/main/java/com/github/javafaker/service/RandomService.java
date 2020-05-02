package com.github.javafaker.service;

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

    public Integer nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public String hex() {
        return hex(8);
    }

    public String hex(int length) {
        if (length <= 0) {
            return ""; // Keep the existing behavior instead of throwing an error.
        }
        final char[] hexChars = new char[length];
        for (int i = 0; i < length; i++) {
            final int nextHex = nextInt(16);
            if (nextHex < 10) {
                hexChars[i] = (char) ('0' + nextHex);
            } else {
                hexChars[i] = (char) ('A' + nextHex - 10);
            }
        }
        return new String(hexChars);
    }
}
