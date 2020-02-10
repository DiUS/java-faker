/**
 *
 */
package com.github.javafaker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * A generator of random dates.
 *
 * @author pmiklos
 *
 */
public class DateAndTime {
    private static final int DEFAULT_MIN_AGE = 18;
    private static final int DEFAULT_MAX_AGE = 65;

    private final Faker faker;

    protected DateAndTime(Faker faker) {
        this.faker = faker;
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
     * Generates a future date from now, with a minimum time.
     *
     * @param atMost
     *            at most this amount of time ahead from now exclusive.
     * @param minimum
     *            the minimum amount of time in the future from now.
     * @param unit
     *            the time unit.
     * @return a future date from now.
     */
    public Date future(int atMost, int minimum, TimeUnit unit) {
        Date now = new Date();
        Date minimumDate = new Date(now.getTime() + unit.toMillis(minimum));
        return future(atMost - minimum, unit, minimumDate);
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
        futureMillis += 1 + faker.random().nextLong(upperBound - 1);

        return new Date(futureMillis);
    }

    /**
     * Generates a past date from now. Note that there is a 1 second slack added.
     *
     * @param atMost
     *            at most this amount of time earlier from now exclusive.
     * @param unit
     *            the time unit.
     * @return a past date from now.
     */
    public Date past(int atMost, TimeUnit unit) {
        Date now = new Date();
        Date aBitEarlierThanNow = new Date(now.getTime() - 1000);
        return past(atMost, unit, aBitEarlierThanNow);
    }

    /**
     * Generates a past date from now, with a minimum time.
     *
     * @param atMost
     *            at most this amount of time earlier from now exclusive.
     * @param minimum
     *            the minimum amount of time in the past from now.
     * @param unit
     *            the time unit.
     * @return a past date from now.
     */
    public Date past(int atMost, int minimum, TimeUnit unit) {
        Date now = new Date();
        Date minimumDate = new Date(now.getTime() - unit.toMillis(minimum));
        return past(atMost - minimum, unit, minimumDate);
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
     * @return a past date relative to {@code referenceDate}.
     */
    public Date past(int atMost, TimeUnit unit, Date referenceDate) {
        long upperBound = unit.toMillis(atMost);

        long futureMillis = referenceDate.getTime();
        futureMillis -= 1 + faker.random().nextLong(upperBound - 1);

        return new Date(futureMillis);
    }

    /**
     * Generates a random date between two dates.
     *
     * @param from
     *            the lower bound inclusive
     * @param to
     *            the upper bound exclusive
     * @return a random date between {@code from} and {@code to}.
     * @throws IllegalArgumentException
     *             if the {@code to} date represents an earlier date than {@code from} date.
     */
    public Date between(Date from, Date to) throws IllegalArgumentException {
        if (to.before(from)) {
            throw new IllegalArgumentException("Invalid date range, the upper bound date is before the lower bound.");
        }

        if (from.equals(to)) {
            return from;
        }

        long offsetMillis = faker.random().nextLong(to.getTime() - from.getTime());
        return new Date(from.getTime() + offsetMillis);
    }

    /**
     * Generates a random birthday between 65 and 18 years ago.
     *
     * @return a random birthday between 65 and 18 years ago.
     */
    public Date birthday() {
        return birthday(DEFAULT_MIN_AGE, DEFAULT_MAX_AGE);
    }

    /**
     * Generates a random birthday between two ages.
     *
     * @param minAge
     *            the minimal age
     * @param maxAge
     *            the maximal age
     * @return a random birthday between {@code minAge} and {@code maxAge} years ago.
     * @throws IllegalArgumentException
     *             if the {@code maxAge} is lower than {@code minAge}.
     */
    public Date birthday(int minAge, int maxAge) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Calendar from = new GregorianCalendar(currentYear - maxAge, currentMonth, currentDay);
        Calendar to = new GregorianCalendar(currentYear - minAge, currentMonth, currentDay);

        return between(from.getTime(), to.getTime());
    }

}
