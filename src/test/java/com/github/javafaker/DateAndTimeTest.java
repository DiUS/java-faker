/**
 * 
 */
package com.github.javafaker;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Peter Miklos (pmiklos@sirokko.net)
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

}
