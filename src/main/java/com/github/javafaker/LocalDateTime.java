package com.github.javafaker;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LocalDateTime {

    private final Faker faker;

    protected LocalDateTime(Faker faker) {
        this.faker = faker;
    }


    /* These methods below are methods of issues which adjust
      original methods into those whose return type is LocalDate
      All methods are based on DAY-Scale */

    /**
     * Generates a past date from now. Note that there is a 1 second slack added.
     *
     * @param atMost
     *            at most this amount of time earlier from now exclusive.
     * @param unit
     *            the time unit.
     * @return a past date from now.
     */
    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/587
    public LocalDate past(int atMost, TimeUnit unit) {
        Date now = new Date();
        Date referenceDate = new Date(now.getTime() - 1000);
        long upperBound = unit.toMillis(atMost);
        long futureMillis = referenceDate.getTime();
        futureMillis -= 1 + faker.random().nextLong(upperBound - 1);
        return new Date(futureMillis).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
