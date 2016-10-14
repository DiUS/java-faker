package com.github.javafaker.service;

import com.github.javafaker.AbstractFakerTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * @author pmiklos
 *
 */
public class RandomServiceTest extends AbstractFakerTest {

    private RandomService randomService;

    @Before
    public void before() {
        super.before();
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
}
