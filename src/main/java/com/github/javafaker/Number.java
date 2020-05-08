package com.github.javafaker;

import java.math.BigDecimal;

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
     * Generate random number between min and max.
     * Notice min is the smaller one, max is larger one
     * @param min Usually the smaller number, also the inclusive lower bound of generated number
     * @param max Usually the larger number, also the exclusive(inclusive only min=max) upper bound of generated number
     * @return A generated number between min and max
     */
    public int numberBetween(int min, int max) {
        
        if (min == max) return min;
        else{
            if (min>max){int tmp=max;max=min;min=tmp;}
            int value = decimalBetween(min,max).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            return value >= max ? max - 1 : value;
        }
    }

    /**
     * Generate random number between min and max.
     * Notice min is the smaller one, max is larger one
     * @param min Usually the smaller number, also the inclusive lower bound of generated number
     * @param max Usually the larger number, also the exclusive(inclusive only min=max) upper bound of generated number
     * @return A generated number between min and max
     */
    public long numberBetween(long min, long max) {
         if (min == max) return min;
         else{
            if (min>max){long tmp=max;max=min;min=tmp;}
            long value = decimalBetween(min,max).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            return value >= max ? max - 1 : value;
        }
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
     * @param min inclusive
     * @param max exclusive
     * @return
     */
    private BigDecimal decimalBetween(long min, long max) {
        if (min == max) {
            return new BigDecimal(min);
        }
        final long trueMin = Math.min(min, max);
        final long trueMax = Math.max(min, max);

        final double range = (double) trueMax - (double) trueMin;
        
        final double chunkCount = Math.sqrt(Math.abs(range));
        final long decimalOfChunkCount=(long)Math.ceil(chunkCount);
        final long randomChunk = faker.random().nextLong(decimalOfChunkCount);
        final double chunkStart = trueMin + randomChunk * chunkCount;
        final double adj = chunkCount * faker.random().nextDouble();
        return chunkStart + adj >= trueMax ? new BigDecimal(trueMax -1) :new BigDecimal(chunkStart + adj);
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
