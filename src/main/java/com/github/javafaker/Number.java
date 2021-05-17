package com.github.javafaker;

import java.math.BigDecimal;
import java.util.Random;

public class Number {
    private final Faker faker;

    protected Number(Faker faker) {
        this.faker = faker;
    }

    /**
     * Returns a random number from 0-9 (both inclusive)
     */
    public int randomDigit() {
        return decimalBetween(0,10).intValue();
    }

    /**
     * Returns a random number from 1-9 (both inclusive)
     */
    public int randomDigitNotZero() {
        return decimalBetween(1,10).intValue();
    }

    /**
     * @see Number#numberBetween(int, int)
     * Generate a invalid social security number on faker
     * @param min the lower bound (include min)
     * @param max the lower bound (not include max)
     * @return a random number on faker.number() between min and max
     * if min = max, return min
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    public int numberBetween(int min, int max) {
        if (min == max) return min;
        return decimalBetween(min, max).intValue();
    }

    /**
     * @see Number#numberBetween(long, long)
     * Generate a invalid social security number on faker
     * @param min the lower bound (include min)
     * @param max the lower bound (not include max)
     * @return a random number on faker.number() between min and max
     * if min = max, return min
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    public long numberBetween(long min, long max) {
        if (min == max) return min;
        return decimalBetween(min, max).longValue();
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
     * Returns a random number
     */
    public long randomNumber() {
        int numberOfDigits = decimalBetween(1,10).intValue();
        return randomNumber(numberOfDigits, false);
    }

    public double randomDouble(int maxNumberOfDecimals, int min, int max) {
        return randomDouble(maxNumberOfDecimals,(long) min, (long) max);
    }
    /**
     * Returns a random double
     *
     * @param maxNumberOfDecimals maximum number of places
     * @param min                 minimum value
     * @param max                 maximum value
     */
    public double randomDouble(int maxNumberOfDecimals, long min, long max) {
        return decimalBetween(min,max)
                .setScale(maxNumberOfDecimals, BigDecimal.ROUND_HALF_DOWN)
                .doubleValue();
    }

    /**
     * @see Number#decimalBetween(long, long)
     * Generate a invalid social security number on faker
     * @param min the lower bound (include min)
     * @param max the lower bound (not include max)
     * @return decimalBetween on faker.number() between min and max
     * if min = max, return min
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    private BigDecimal decimalBetween(long min, long max) {
        if (min == max) {
            return new BigDecimal(min);
        }

        final BigDecimal trueMin = new BigDecimal(""+min);
        final BigDecimal trueMax = new BigDecimal(""+max);
        final BigDecimal random = new BigDecimal(Math.random());

        BigDecimal r = trueMax.subtract(trueMin);
        r = trueMin.add(r.multiply(random));

        if (r.compareTo(trueMax) > -1) return decimalBetween(min, max);
        return r;

    }

    public String digits(int count) {
        final StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < count; i++) {
            tmp.append(randomDigit());
        }
        return tmp.toString();
    }

    public String digit() {
        return digits(1);
    }
}
