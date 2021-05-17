package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NumberTest extends AbstractFakerTest {

    private static final Logger logger = LoggerFactory.getLogger(NumberTest.class);
    public static final int RANDOMIZATION_QUALITY_RANGE_END = 1000;
    public static final int RANDOMIZATION_QUALITY_RANGE_STEP = 25;
    private static final int RANDOMIZATION_QUALITY_RANGE_START = RANDOMIZATION_QUALITY_RANGE_STEP;
    public static final int RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET = 1000;

    final double individualRunGtPercentUnique= 0.8;
    final double percentRunsGtUniquePercentage = 0.90;

    @Test
    public void testRandomDigit() {
        Set<Integer> nums = Sets.newHashSet();
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().randomDigit();
            assertThat(value, is(lessThanOrEqualTo(9)));
            assertThat(value, is(greaterThanOrEqualTo(0)));
            nums.add(value);
        }
        assertThat(nums, contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void testRandomDigitNotZero() {
        Set<Integer> nums = Sets.newHashSet();
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().randomDigitNotZero();
            assertThat(value, is(lessThanOrEqualTo(9)));
            assertThat(value, is(greaterThan(0)));
            nums.add(value);
        }
        assertThat(nums, contains(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void testRandomNumber() {
        long value = faker.number().randomNumber();
        assertThat(value, is(lessThan(Long.MAX_VALUE)));
    }

    @Test
    public void testRandomNumberWithSingleDigitStrict() {
        for (int i = 0; i < 100; ++i) {
            long value = faker.number().randomNumber(1, true);
            assertThat(value, is(lessThan(10L)));
            assertThat(value, is(greaterThanOrEqualTo(0L)));
        }
    }

    @Test
    public void testRandomNumberWithZeroDigitsStrict() {
        for (int i = 0; i < 100; ++i) {
            long value = faker.number().randomNumber(0, true);
            assertThat(value, is(0L));
        }
    }

    @Test
    public void testRandomNumberWithGivenDigitsStrict() {
        for (int i = 1; i < 9; ++i) {
            for (int x = 0; x < 100; ++x) {
                long value = faker.number().randomNumber(i, true);
                String stringValue = String.valueOf(value);
                assertThat(stringValue.length(), is(i));
            }
        }
    }

    @Test
    public void testRandomDouble() {
        for (int i = 1; i < 5; ++i) {
            for (int x = 0; x < 100; ++x) {
                double value = faker.number().randomDouble(i, 1, 1000);
                String strVal = BigDecimal.valueOf(value).stripTrailingZeros().toString();
                if (strVal.contains(".") && !strVal.contains("+")) {
                    int ind = strVal.indexOf(".");
                    assertThat(strVal.length() - ind - 1, is(lessThanOrEqualTo(i)));
                }
            }
        }
    }

    @Test
    public void testNumberBetween() {
        for (int i = 1; i < 100; ++i) {
            int v = faker.number().numberBetween(0, i);
            assertThat(v, is(lessThanOrEqualTo(i)));
            assertThat(v, is(greaterThanOrEqualTo(0)));
        }

        for (long i = 1L; i < 100L; ++i) {
            long v = faker.number().numberBetween(0, i);
            assertThat(v, is(lessThanOrEqualTo(i)));
            assertThat(v, is(greaterThanOrEqualTo(0L)));
        }

        int min1 = 1;
        long v1 = faker.number().numberBetween(min1, 980000000L);
        assertThat(v1, is(greaterThan((long) min1)));
        assertThat(v1, is(lessThan(980000000L)));
    }

    @Test
    @Repeat(times = 100)
    public void testLongNumberBetweenRepeated() {
        long low = 1;
        long high = 10;
        long v = faker.number().numberBetween(low, high);
        assertThat(v, is(lessThan(high)));
        assertThat(v, is(greaterThanOrEqualTo(low)));
    }

    @Test
    @Repeat(times = 100)
    public void testIntNumberBetweenRepeated() {
        int low = 1;
        int high = 10;
        int v = faker.number().numberBetween(low, high);
        assertThat(v, is(lessThan(high)));
        assertThat(v, is(greaterThanOrEqualTo(low)));
    }

    @Test
    public void testNumberBetweenOneAndThree() {
        Set<Integer> nums = Sets.newHashSet();
        final int lowerLimit = 0;
        final int upperLimit = 3;
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().numberBetween(lowerLimit, upperLimit);
            assertThat(value, is(lessThan(upperLimit)));
            assertThat(value, is(greaterThanOrEqualTo(lowerLimit)));
            nums.add(value);
        }
        assertThat(nums, contains(0, 1, 2));
    }

    @Test
    public void testLongBetweenOneAndThree() {
        Set<Long> nums = Sets.newHashSet();
        final long lowerLimit = 0;
        final long upperLimit = 3;
        for (int i = 0; i < 1000; ++i) {
            long value = faker.number().numberBetween(lowerLimit, upperLimit);
            assertThat(value, is(lessThan(upperLimit)));
            assertThat(value, is(greaterThanOrEqualTo(lowerLimit)));
            nums.add(value);
        }
        assertThat(nums, contains(0L, 1L, 2L));
    }

    @Test
    public void numberBetweenIntIntZeroMinMax() {
        assertThat("Calling numberBetween with min==max yields min, with 0",
                faker.number().numberBetween(0, 0),
                is(0));
        assertThat("Calling numberBetween with min==max yields min",
                faker.number().numberBetween(2, 2),
                is(2));
    }

    @Test
    public void numberBetweenLongLongZeroMinMax() {
        assertThat("Calling numberBetween with min==max yields min, with 0",
                faker.number().numberBetween(0L, 0L),
                is(0L));
        assertThat("Calling numberBetween with min==max yields min", 
                faker.number().numberBetween(2L, 2L),
                is(2L));
    }

    /**
     * Given a number of min/max ranges
     *  for each min/max range, call {@link Number#randomDouble(int, int, int)} with min/max 'n' times
     *  calculate the uniqueness for that given min/max range.
     * For all 'uniqueness' values
     *  verify the percentage of 'uniqueness' ratios over 80% is 90%.
     *
     * This isn't perfect but it ensures a pretty good degree of uniqueness in the random number generation.
     */
    @Test
    public void randomDoubleRandomizationQuality() {
        Function<Pair<Long, Long>, Double> minMaxRangeToUniquePercentageFunction = new Function<Pair<Long, Long>, Double>() {
            @Override
            public Double apply(Pair<Long, Long> minMax) {
                final int min = minMax.getLeft().intValue(), max = minMax.getRight().intValue();
                long numbersToGet = calculateNumbersToGet(min, max);

                return uniquePercentageOfResults(numbersToGet, new Callable<Double>() {
                    @Override
                    public Double call() throws Exception {
                        return faker.number().randomDouble(0, min, max);
                    }
                });
            }
        };

        final double percentGreaterThan80Percent = randomizationQualityTest(individualRunGtPercentUnique, minMaxRangeToUniquePercentageFunction);
        assertThat("Percentage of runs > 80% unique is gte 90%",
                percentGreaterThan80Percent, greaterThanOrEqualTo(percentRunsGtUniquePercentage));

        // this covers Issue # 121, the number of times the function is called with the MIN/MAX values here
        // is RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET
        final double extremeRunUniquePercent = minMaxRangeToUniquePercentageFunction.apply(Pair.of((long) Integer.MIN_VALUE, (long) Integer.MAX_VALUE));
        assertThat("Percentage of extreme runs > 80%",
                extremeRunUniquePercent, greaterThanOrEqualTo(individualRunGtPercentUnique));
    }

    /**
     * Given a number of min/max ranges
     *  for each min/max range, call numberBetween with min/max 'n' times
     *  calculate the uniqueness for that given min/max range.
     * For all 'uniqueness' values
     *  verify the percentage of 'uniqueness' ratios over 80% is 90%.
     *  
     * This isn't perfect but it ensures a pretty good degree of uniqueness in the random number generation.
     */
    @Test
    public void numberBetweenIntIntRandomizationQuality() {
        Function<Pair<Long, Long>, Double> minMaxRangeToUniquePercentageFunction = new Function<Pair<Long, Long>, Double>() {
            @Override
            public Double apply(Pair<Long, Long> minMax) {
                final int min = minMax.getLeft().intValue();
                final int max = minMax.getRight().intValue();
                long numbersToGet = calculateNumbersToGet(min, max);

                return uniquePercentageOfResults(numbersToGet, new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return faker.number().numberBetween(min, max);
                    }
                });
            }
        };

        final double percentGreaterThan80Percent = randomizationQualityTest(individualRunGtPercentUnique, minMaxRangeToUniquePercentageFunction);
        assertThat("Percentage of runs > 80% unique is gte 90%",
                percentGreaterThan80Percent, greaterThanOrEqualTo(percentRunsGtUniquePercentage));

        // this covers Issue # 121, the number of times the function is called with the MIN/MAX values here
        // is RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET
        final double extremeRunUniquePercent = minMaxRangeToUniquePercentageFunction.apply(Pair.of((long) Integer.MIN_VALUE, (long) Integer.MAX_VALUE));
        assertThat("Percentage of extreme runs > 80%",
                extremeRunUniquePercent, greaterThanOrEqualTo(individualRunGtPercentUnique));
    }

    /**
     * Given a number of min/max ranges
     *  for each min/max range, call {@link Number#numberBetween(long, long)}  with min/max 'n' times
     *  calculate the uniqueness for that given min/max range.
     * For all 'uniqueness' values
     *  verify the percentage of 'uniqueness' ratios over 80% is 90%.
     *
     * This isn't perfect but it ensures a pretty good degree of uniqueness in the random number generation.
     */
    @Test
    public void numberBetweenLongLongRandomizationQuality() {
        Function<Pair<Long, Long>, Double> minMaxRangeToUniquePercentageFunction = new Function<Pair<Long, Long>, Double>() {
            @Override
            public Double apply(Pair<Long, Long> minMax) {
                final long min = minMax.getLeft(), max = minMax.getRight();
                long numbersToGet = calculateNumbersToGet(min, max);

                return uniquePercentageOfResults(numbersToGet, new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        return faker.number().numberBetween(min, max);
                    }
                });
            }
        };
        
        final double percentGreaterThan80Percent = randomizationQualityTest(individualRunGtPercentUnique, minMaxRangeToUniquePercentageFunction);
        assertThat("Percentage of runs > 80% unique is gte 90%",
                percentGreaterThan80Percent, greaterThanOrEqualTo(percentRunsGtUniquePercentage));

        // this covers Issue # 121, the number of times the function is called with the MIN/MAX values here
        // is RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET.
        final double extremeRunUniquePercent = minMaxRangeToUniquePercentageFunction.apply(Pair.of(Long.MIN_VALUE, Long.MAX_VALUE));
        assertThat("Percentage of extreme runs > 80%",
                extremeRunUniquePercent, greaterThanOrEqualTo(individualRunGtPercentUnique));
    }

    @Test
    public void testRandomDoubleMaxEqualsMin() {
        double actual = faker.number().randomDouble(1, 42, 42);

        double expected = BigDecimal.valueOf(42).doubleValue();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testDigit() {
        String digit = faker.number().digit();

        assertThat(digit, matchesRegularExpression("[0-9]"));
    }

    @Test
    public void testDigits() {
        String digits = faker.number().digits(5);

        assertThat(digits, matchesRegularExpression("[0-9]{5}"));
    }

    /**
     * Over the series of numbers identified from RANDOMIZATION_QUALITY_RANGE_START to
     * RANDOMIZATION_QUALITY_RANGE_END, create a min/max range of -value/value and
     * with of those min/max values, call <em>percentUniqueRunner</em>.
     * 
     * Collect the number of calls to <em>percentUniqueRunner</em> that were
     * above the threshold and finally return that number divided by the total number of calls to
     * <em>percentUniqueRunner</em>.
     * 
     * @return percent of percentUniqueRunner's results greater than the threshold
     */
    private double randomizationQualityTest(final double threshold, 
                                            final Function<Pair<Long,Long>,Double> percentUniqueRunner) {
        final int rangeEnd = RANDOMIZATION_QUALITY_RANGE_END;
        final int rangeStep = RANDOMIZATION_QUALITY_RANGE_STEP;
        final int rangeStart = RANDOMIZATION_QUALITY_RANGE_START;

        final AtomicLong greaterThanThreshold = new AtomicLong();
        final AtomicLong total = new AtomicLong();

        for (long l = rangeStart; l < rangeEnd; l += rangeStep) {
            final double percentUnique = percentUniqueRunner.apply(Pair.of(-l,l));
            logger.info("Range {} to {} is {} percent unique.", -l, l, percentUnique);
            if (percentUnique > threshold) {
                greaterThanThreshold.incrementAndGet();
            }
            total.incrementAndGet();
        }

        return (double) greaterThanThreshold.get() / (double) total.get();
    }

    
    /**
     * Given a number of iterations, calls <em>callable</em> 'iterations' times and collects the results,
     * then calculates the number of results that were unique and returns the percentage that where unique.
     */
    private <T> double uniquePercentageOfResults(long iterations, Callable<T> callable) {
        try {
            List<T> values = Lists.newArrayList();
            for (long i = 0; i < iterations; i++) {
                values.add(callable.call());
            }
            long setSize = Sets.newHashSet(values).size();
            return (double) setSize / (double) values.size();
        } catch (Exception e) {
            logger.error("error in uniquePercentageOfResults", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * given a range, what is the number of values to get within that range for the randomization quality tests.
     */
    private long calculateNumbersToGet(long min, long max) {
        long numbersToGet = Math.min((max - min) / 4, RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET);
        if (numbersToGet == 0) numbersToGet = RANDOMIZATION_TESTS_MAX_NUMBERS_TO_GET;
        return numbersToGet;
    }

    /*
     * This file was used to test the issue #458
     * Wed May 15 GMT 2021
     * by SE_CHWJ
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    @Test(timeout=5000)
    public void testIntNumberBetweenQuality() {
        //test whether the fake number made by numberBetween(int min, int max)
        // is not randomly and evenly distributed
        // (The difference between the average is less than 10%)
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random();
        int testCase = 100000;

        int min = Math.abs(random.nextInt());
        int max = min + Math.abs(random.nextInt(100));
        double mean = testCase/(double)(max-min);
        for (int j = 0; j < testCase; j++) {
            int r = faker.number().numberBetween(min,max);
            Integer count = map.get(r);
            map.put(r, count == null ? 1 : count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            assertTrue((mean-count)/mean < 0.2);
        }

    }

    /*
     * This file was used to test the issue #458
     * Wed May 15 GMT 2021
     * by SE_CHWJ
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    @Test(timeout=8000)
    public void testLongNumberBetweenQuality() {
        //test whether the fake number made by numberBetween(long min, long max)
        // is not randomly and evenly distributed
        // (The difference between the average is less than 10%)
        Map<Long, Integer> map = new HashMap<>();
        Random random = new Random();
        int testCase = 1000000;

        long min = Math.abs(random.nextLong());
        long max = min + Math.abs(random.nextInt(200));
        double mean = testCase/(double)(max-min);
        for (int j = 0; j < testCase; j++) {
            Long r = faker.number().numberBetween(min,max);
            Integer count = map.get(r);
            map.put(r, count == null ? 1 : count + 1);
        }

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            assertTrue((mean-count)/mean < 0.2);
        }

    }

    /*
     * This file was used to test the issue #458
     * Wed May 15 GMT 2021
     * by SE_CHWJ
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    @Test(timeout=7000)
    public void testNumberBetweenContain() {

        Set<Integer> ints = Sets.newHashSet();
        Set<Long> longs = Sets.newHashSet();
        Random random = new Random();
        int size = Math.abs(random.nextInt(100));

        //test whether NumberBetween(int min, int max) can
        // create all number between min and max(not included)
        // and not use crossing the border
        int minInt = Math.abs(random.nextInt());
        int maxInt = minInt + size;
        for (int i = 0; i < 100000; ++i) {
            int value = faker.number().numberBetween(minInt, maxInt);
            assertThat(value, is(lessThan(maxInt)));
            assertThat(value, is(greaterThanOrEqualTo(minInt)));
            ints.add(value);
        }
        assertEquals(ints.size(), size);

        //test whether NumberBetween(long, long) can
        // create all number between min and max(not included)
        // and not use crossing the border
        long minLong = Math.abs(random.nextLong());
        long maxLong = minLong + size;
        for (int i = 0; i < 100000; ++i) {
            long value = faker.number().numberBetween(minLong, maxLong);
            assertThat(value, is(lessThan(maxLong)));
            assertThat(value, is(greaterThanOrEqualTo(minLong)));
            longs.add(value);
        }
        assertEquals(longs.size(), size);
    }

    /*
     * This file was used to test the issue #458
     * Wed May 15 GMT 2021
     * by SE_CHWJ
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/458
    @Test(timeout=8000)
    public void testNumberBetweenBorder() {

        Random random = new Random();

        //test whether NumberBetween(long, long) not use crossing the border
        for (int i = 0; i <= 100; i++) {

            //create long integer max and min
            long size, min = 0, max = -1;
            while (max < min){
                size = Math.abs(random.nextLong());
                min = Math.abs(random.nextLong());
                max = min + size;
            }

            for (int j = 0; j < 3000; j++) {
                long value = faker.number().numberBetween(min, max);
                assertThat(value, is(lessThan(max)));
                assertThat(value, is(greaterThanOrEqualTo(min)));
            }
        }
    }

}
