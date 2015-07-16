/**
 * 
 */
package com.github.javafaker;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author pmiklos
 *
 */
public class DateAndTimeTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testFutureDate() {
        Date now = new Date();

        for (int i = 0; i < 1000; i++) {
            Date future = faker.date().future(1, TimeUnit.SECONDS, now);
            assertThat("past date", future.getTime(), greaterThan(now.getTime()));
            assertThat("future date over range", future.getTime(), lessThan(now.getTime() + 1000));
        }
    }

    @Test
    public void testPastDate() {
        Date now = new Date();

        for (int i = 0; i < 1000; i++) {
            Date future = faker.date().past(1, TimeUnit.SECONDS, now);
            assertThat("future date", future.getTime(), lessThan(now.getTime()));
            assertThat("past date over range", future.getTime(), greaterThan(now.getTime() - 1000));
        }
    }

    @Test
    public void testBetween() {
        Date now = new Date();
        Date then = new Date(now.getTime() + 1000);

        for (int i = 0; i < 1000; i++) {
            Date date = faker.date().between(now, then);
            assertThat("after upper bound", date.getTime(), lessThan(then.getTime()));
            assertThat("before lower bound", date.getTime(), greaterThanOrEqualTo(now.getTime()));
        }
    }

}
