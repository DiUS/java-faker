package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpaceTest extends AbstractFakerTest {

    @Test
    public void testPlanet() {
        assertThat(faker.space().planet(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testMoon() {
        assertThat(faker.space().moon(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testGalaxy() {
        assertThat(faker.space().galaxy(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testNebula() {
        assertThat(faker.space().nebula(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testStarCluster() {
        assertThat(faker.space().starCluster(), matchesRegularExpression("(\\w+[ -]?){1,3}"));
    }

    @Test
    public void testConstellation() {
        assertThat(faker.space().constellation(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testStar() {
        assertThat(faker.space().star(), matchesRegularExpression("(\\w+[ -]?){2,3}"));
    }

    @Test
    public void testAgency() {
        assertThat(faker.space().agency(), matchesRegularExpression("(\\w+ ?){2,5}"));
    }

    @Test
    public void testAgencyAbbreviation() {
        assertThat(faker.space().agencyAbbreviation(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testNasaSpaceCraft() {
        assertThat(faker.space().nasaSpaceCraft(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testCompany() {
        assertThat(faker.space().company(), matchesRegularExpression("(\\w+ ?){2,4}"));
    }

    @Test
    public void testDistanceMeasurement() {
        assertThat(faker.space().distanceMeasurement(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }
}
