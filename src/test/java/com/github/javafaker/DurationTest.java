package com.github.javafaker;

import org.junit.Test;
import java.time.Duration;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class DurationTest extends AbstractFakerTest {
    final int DURATION_IS_EQUAL = 0;
    final int DURATION_IS_GREATER = 1;
    final int DURATION_IS_LESS = -1;

    @Test
    public void testDurationSeconds(){
        final long maxSeconds = 55;
        Duration randomDuration = faker.duration().atMostSeconds(maxSeconds);
        Duration lowerBound = Duration.ofSeconds(0);
        Duration upperBound = Duration.ofSeconds(maxSeconds);

        assertThat(randomDuration.compareTo(lowerBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_GREATER)));
        assertThat(randomDuration.compareTo(upperBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_LESS)));
    }

    @Test
    public void testDurationMinutes(){
        final long maxMins = 45;
        Duration randomDuration = faker.duration().atMostMinutes(maxMins);
        Duration lowerBound = Duration.ofMinutes(0);
        Duration upperBound = Duration.ofMinutes(maxMins);
        
        assertThat(randomDuration.compareTo(lowerBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_GREATER)));
        assertThat(randomDuration.compareTo(upperBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_LESS)));
    }

    @Test
    public void testDurationHours(){
        final long maxHours = 35;
        Duration randomDuration = faker.duration().atMostHours(maxHours);
        Duration lowerBound = Duration.ofHours(0);
        Duration upperBound = Duration.ofHours(maxHours);

        assertThat(randomDuration.compareTo(lowerBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_GREATER)));
        assertThat(randomDuration.compareTo(upperBound), anyOf(
            is(DURATION_IS_EQUAL),is(DURATION_IS_LESS)));
    }

    @Test
    public void testDurationDays(){
        final long maxDays = 40;
        Duration randomDuration = faker.duration().atMostDays(maxDays);
        Duration lowerBound = Duration.ofDays(0);
        Duration upperBound = Duration.ofDays(maxDays);

        assertThat(randomDuration.compareTo(lowerBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_GREATER)));
        assertThat(randomDuration.compareTo(upperBound), anyOf(
            is(DURATION_IS_EQUAL), is(DURATION_IS_LESS)));
    }

}
