package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpaceTest extends AbstractFakerTest {

    @Test
    public void planet() {
        assertThat(faker.space().planet(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void moon() {
        assertThat(faker.space().moon(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void galaxy() {
        assertThat(faker.space().galaxy(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void nebula() {
        assertThat(faker.space().nebula(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void starCluster() {
        assertThat(faker.space().starCluster(), matchesRegularExpression("(\\w+[ -]?){1,3}"));
    }

    @Test
    public void constellation() {
        assertThat(faker.space().constellation(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void star() {
        assertThat(faker.space().star(), matchesRegularExpression("(\\w+[ -]?){2,3}"));
    }

    @Test
    public void agency() {
        assertThat(faker.space().agency(), matchesRegularExpression("(\\w+ ?){2,5}"));
    }

    @Test
    public void agencyAbbreviation() {
        assertThat(faker.space().agencyAbbreviation(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void nasaSpaceCraft() {
        assertThat(faker.space().nasaSpaceCraft(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void company() {
        assertThat(faker.space().company(), matchesRegularExpression("((\\w|')+ ?){2,4}"));
    }

    @Test
    public void distanceMeasurement() {
        assertThat(faker.space().distanceMeasurement(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void meteorite() {
        assertThat(faker.space().meteorite(), matchesRegularExpression("(?U)([\\w()]+[ -–]?){1,4}"));
    }
}
