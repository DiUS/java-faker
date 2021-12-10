package com.github.javafaker;

import java.time.Duration;

// Issue link: https://github.com/DiUS/java-faker/issues/677
public class FakeDuration {
    private final Faker faker;

    protected FakeDuration(Faker faker){
        this.faker = faker;
    }

    /**
    * Returns a random long between 0 and max inclusive
    * 
    * @param max: upper bound of number range
    * 
    * @return a random long between 0 and max inclusive
    */
    private long getNumberBetween(long max){
        return this.faker.random().nextLong(max + 1);  // nextLong max is exclusive
    }

    /**
    * Returns a Duration object representing a random duration of minutes in the given range
    * 
    * @param minutesMax: max number of minutes for Duration (inclusive)
    * 
    * @return a Duration object representing a number of minutes in the given range
    */
    public Duration atMostMinutes(long minutesMax){
        return Duration.ofMinutes(getNumberBetween(minutesMax));
    }

    /**
    * Returns a Duration object representing a random duration of hours in the given range
    * 
    * @param hoursMax: max number of hours for Duration (inclusive)
    * 
    * @return a Duration object representing a number of hours in the given range
    */
    public Duration atMostHours(long hoursMax){
        return Duration.ofHours(getNumberBetween(hoursMax));
    }

    /**
    * Returns a Duration object representing a random duration of days in the given range
    * 
    * @param daysMax: max number of days for Duration (inclusive)
    * 
    * @return a Duration object representing a number of days in the given range
    */
    public Duration atMostDays(long daysMax){
        return Duration.ofDays(getNumberBetween(daysMax));
    }

    /**
    * Returns a Duration object representing a random duration of seconds in the given range
    * 
    * @param secondsMax: max number of seconds for Duration (inclusive)
    * 
    * @return a Duration object representing a number of seconds in the given range
    */
    public Duration atMostSeconds(long secondsMax){
        return Duration.ofSeconds(getNumberBetween(secondsMax));
    }
}
