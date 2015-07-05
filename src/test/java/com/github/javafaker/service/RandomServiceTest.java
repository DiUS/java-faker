/**
 * 
 */
package com.github.javafaker.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pmiklos
 *
 */
public class RandomServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(RandomServiceTest.class);

    private RandomService randomService;

    @Before
    public void before() {
        randomService = new RandomService(new Random());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPositiveBoundariesOnly() {
        randomService.nextLong(0L);
    }

    @Test
    public void testLongWithinBoundary() {
        assertThat(randomService.nextLong(1), is(0L));

        for (int i = 1; i < 10; i++) {
            assertThat(randomService.nextLong(2), lessThan(2L));
        }
    }

    @Test
    public void testLongMaxBoundary() {
        assertThat(randomService.nextLong(Long.MAX_VALUE), greaterThan(0L));
        assertThat(randomService.nextLong(Long.MAX_VALUE), lessThan(Long.MAX_VALUE));
    }

    @Test
    public void testLongUniformDistribution() {
        assumeTrue(Boolean.getBoolean("probabilisticTestsEnabled"));

        long max = 100;
        int sampleSize = 10;
        double expectedMean = max / 2.0;
        double expectedDeviation = Math.sqrt(max * max / 12);

        double sampleMean = 0.0;
        long[] numbers = new long[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            numbers[i] = randomService.nextLong(max);
            sampleMean += numbers[i] / (double) sampleSize;
        }

        double sampleVariance = 0.0;
        for (int i = 0; i < sampleSize; i++) {
            double diff = numbers[i] - sampleMean;
            sampleVariance += diff * diff / (sampleSize - 1);
        }

        double sampleDeviation = Math.sqrt(sampleVariance);

        assertThat(sampleMean, greaterThan(expectedMean - expectedDeviation));
        assertThat(sampleMean, lessThan(expectedMean + expectedDeviation));

        assertThat(sampleVariance, greaterThan(0.0));

        logger.info("nextLong(n): sampleMean=" + sampleMean + " (expected: " + expectedMean + ")");
        logger.info("nextLong(n): sampleVariance=" + sampleDeviation + " (expected: " + expectedDeviation + ")");
    }

}
