/**
 * 
 */
package com.github.javafaker;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.service.RandomService;

/**
 * A generator of random dates.
 * 
 * @author pmiklos
 *
 */
public class DateAndTime {

    private RandomService randomService;

    public DateAndTime(RandomService randomService) {
        this.randomService = randomService;
    }

    /**
     * Generates a future date from now. Note that there is a 1 second slack to avoid generating a past date.
     * 
     * @param atMost
     *            at most this amount of time ahead from now exclusive.
     * @param unit
     *            the time unit.
     * @return a future date from now.
     */
    public Date future(int atMost, TimeUnit unit) {
        Date now = new Date();
        Date aBitLaterThanNow = new Date(now.getTime() + 1000);
        return future(atMost, unit, aBitLaterThanNow);
    }

    /**
     * Generates a future date relative to the {@code referenceDate}.
     * 
     * @param atMost
     *            at most this amount of time ahead to the {@code referenceDate} exclusive.
     * @param unit
     *            the time unit.
     * @param referenceDate
     *            the future date relative to this date.
     * @return a future date relative to {@code referenceDate}.
     */
    public Date future(int atMost, TimeUnit unit, Date referenceDate) {
        long upperBound = unit.toMillis(atMost);

        long futureMillis = referenceDate.getTime();
        futureMillis += 1 + randomService.nextLong(upperBound - 1);

        return new Date(futureMillis);
    }

    /**
     * Generates a past date from now. Note that there is a 1 second slack added.
     * 
     * @param atMost
     *            at most this amount of time ahead from now exclusive.
     * @param unit
     *            the time unit.
     * @return a future date from now.
     */
    public Date past(int atMost, TimeUnit unit) {
        Date now = new Date();
        Date aBitEarlierThanNow = new Date(now.getTime() - 1000);
        return future(atMost, unit, aBitEarlierThanNow);
    }

    /**
     * Generates a past date relative to the {@code referenceDate}.
     * 
     * @param atMost
     *            at most this amount of time past to the {@code referenceDate} exclusive.
     * @param unit
     *            the time unit.
     * @param referenceDate
     *            the past date relative to this date.
     * @return a future date relative to {@code referenceDate}.
     */
    public Date past(int atMost, TimeUnit unit, Date referenceDate) {
        long upperBound = unit.toMillis(atMost);

        long futureMillis = referenceDate.getTime();
        futureMillis -= 1 + randomService.nextLong(upperBound - 1);

        return new Date(futureMillis);
    }

}
