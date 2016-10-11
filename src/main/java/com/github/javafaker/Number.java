package com.github.javafaker;

import java.math.BigDecimal;

public class Number {
    private final Faker faker;

    Number(Faker faker) {
        this.faker = faker;
    }

    /**
     * Returns a random number between 0 and 9
     */
    public int randomDigit() {
        return faker.random().nextInt(9);
    }

    /**
     * Returns a random number between 1 and 9
     */
    public int randomDigitNotZero() {
        return faker.random().nextInt(8) + 1;
    }

    public long numberBetween(int min, long max) {
        return numberBetween((long) min, max);
    }

    public int numberBetween(int min, int max) {
        return faker.random().nextInt(max - min) + min;
    }

    public long numberBetween(long min, long max) {
        return faker.random().nextLong(max - min) + min;
    }

    /**
     * @param numberOfDigits the number of digits the generated value should have
     * @param strict         whether or not the generated value should have exactly <code>numberOfDigits</code>
     */
    public long randomNumber(int numberOfDigits, boolean strict) {
        long max = (long) Math.pow(10, numberOfDigits);
        if (strict) {
            long min = (long) Math.pow(10, numberOfDigits - 1);
            return faker.random().nextLong(max - min) + min;
        }

        return faker.random().nextLong(max);
    }

    /**
     * Returns a ranbom number
     */

    public long randomNumber() {
        int numberOfDigits = faker.random().nextInt(8) + 1;

        return randomNumber(numberOfDigits, false);
    }

    /**
     * Returns a random double
     *
     * @param maxNumberOfDecimals maximum number of places
     * @param min                 minimum value
     * @param max                 maximum value
     */

    public double randomDouble(int maxNumberOfDecimals, int min, int max) {
        double value = min + (max - min) * faker.random().nextDouble();

        return new BigDecimal(value).setScale(maxNumberOfDecimals, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    public String digits(int count) {
        final StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < count; i++) {
            tmp.append(faker.random().nextInt(10));
        }
        return tmp.toString();
    }

    public String digit() {
        return digits(1);
    }
}
