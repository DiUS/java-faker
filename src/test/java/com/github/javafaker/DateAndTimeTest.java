/**
 *
 */
package com.github.javafaker;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author pmiklos
 */
public class DateAndTimeTest extends AbstractFakerTest {

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
    public void testFutureDateWithMinimum(){
        for (int i = 0; i < 1000; i++) {
            Date now = new Date();
            Date future = faker.date().future(5, 4, TimeUnit.SECONDS);
            assertThat("past date", future.getTime(), greaterThan(now.getTime()));
            assertThat("future date over range", future.getTime(), lessThan(now.getTime() + 5001));
            assertThat("future date under minimum range", future.getTime(), greaterThan(now.getTime() + 3999));
        }
    }

    @Test
    public void testPastDateWithMinimum(){
        for (int i = 0; i < 1000; i++) {
            Date now = new Date();
            Date past = faker.date().past(5, 4, TimeUnit.SECONDS);
            assertThat("future date", past.getTime(), lessThan(now.getTime()));
            assertThat("past date over range", past.getTime(), greaterThan(now.getTime() - 5001));
            assertThat("past date under minimum range", past.getTime(), lessThan(now.getTime() - 3999));
        }
    }

    @Test
    public void testPastDateWithReferenceDate() {
        Date now = new Date();

        for (int i = 0; i < 1000; i++) {
            Date past = faker.date().past(1, TimeUnit.SECONDS, now);
            assertThat("past date", past.getTime(), lessThan(now.getTime()));
            assertThat("past date over range", past.getTime(), greaterThan(now.getTime() - 1000));
        }
    }

    @Test
    public void testPastDate() {
        Date now = new Date();
        Date past = faker.date().past(100, TimeUnit.SECONDS);
        assertThat("past date is in the past", past.getTime(), lessThan(now.getTime()));
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

    @Test
    public void testBirthday() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        long from = new GregorianCalendar(currentYear - 65, 0, 1).getTime().getTime();
        long to = new GregorianCalendar(currentYear - 18, 11, 31).getTime().getTime();

        for (int i = 0; i < 1000; i++) {
            Date birthday = faker.date().birthday();
            assertThat("birthday is after upper bound", birthday.getTime(), lessThan(to));
            assertThat("birthday is before lower bound", birthday.getTime(), greaterThanOrEqualTo(from));
        }
    }

    @Test
    public void testBirthdayWithAges() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 0; i < 1000; i++) {
            int minAge = faker.number().numberBetween(1, 99);
            int maxAge = faker.number().numberBetween(minAge, 100);

            long from = new GregorianCalendar(currentYear - maxAge, 0, 1).getTime().getTime();
            long to = new GregorianCalendar(currentYear - minAge, 11, 31).getTime().getTime();

            Date birthday = faker.date().birthday(minAge, maxAge);
            assertThat("birthday is after upper bound", birthday.getTime(), lessThan(to));
            assertThat("birthday is before lower bound", birthday.getTime(), greaterThanOrEqualTo(from));
        }
    }

}
