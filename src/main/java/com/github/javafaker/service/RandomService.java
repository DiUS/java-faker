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

    public double nextDouble() {
        return RandomUtils.nextDouble(random);
    }
}
