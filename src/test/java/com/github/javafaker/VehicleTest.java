package com.github.javafaker;

import org.junit.Test;

import java.util.ArrayList;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class VehicleTest extends AbstractFakerTest {
    private static final String WORD_MATCH = "\\w+\\.?";
    private static final String WORDS_MATCH = "^[a-zA-Z0-9_/ -]*$";
    private static final String FOREIGN_WARDS_MATCH = "\\P{Cc}+";

    @Test
    public void testVin() {
        assertThat(faker.vehicle().vin(), matchesRegularExpression(Vehicle.VIN_REGEX));
    }

    @Test
    public void testManufacturer() {
        assertThat(faker.vehicle().manufacturer(), matchesRegularExpression(FOREIGN_WARDS_MATCH));
    }

    @Test
    public void testColor() {
        assertThat(faker.vehicle().color(), matchesRegularExpression(WORD_MATCH));
    }

    @Test
    public void testTransmission() {
        assertThat(faker.vehicle().transmission(), matchesRegularExpression(WORD_MATCH));
    }

    @Test
    public void testDriveType() {
        assertThat(faker.vehicle().driveType(), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testFuelType() {
        assertThat(faker.vehicle().fuelType(), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testStyle() {
        assertThat(faker.vehicle().style(), matchesRegularExpression(WORD_MATCH));
    }

    @Test
    public void testCarType() {
        assertThat(faker.vehicle().carType(), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testDoor() {
        assertTrue((Integer) faker.vehicle().doors() > 0);
    }

    @Test
    public void testEngine() {
        assertThat(faker.vehicle().engine(), matchesRegularExpression("\\d Cylinder Engine"));
    }

    @Test
    public void testLicensePlate() {
        assertThat(faker.vehicle().licensePlate(""), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testLicensePlateWithParam() {
        assertThat(faker.vehicle().licensePlate("GA"), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testMake() {
        assertThat(faker.vehicle().make(), matchesRegularExpression(WORD_MATCH));
    }

    @Test
    public void testModel() {
        assertThat(faker.vehicle().model(faker.vehicle().make()), matchesRegularExpression(WORDS_MATCH));
        assertThat(faker.vehicle().model(faker.dragonBall().character()), containsString("Only Supports models of the following make: "));
    }

    @Test
    public void testMakeAndModel() {
        assertThat(faker.vehicle().makeAndModel(), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testCarOptionsMinMax() {
        ArrayList carOptions = faker.vehicle().carOptions(11, 12);
        assertThat(carOptions.size(), greaterThanOrEqualTo(11));
        assertThat(carOptions.size(), lessThanOrEqualTo(12));
    }

    @Test
    public void testCarOptions() {
        ArrayList carOptions = faker.vehicle().carOptions();
        assertThat(carOptions.size(), greaterThanOrEqualTo(5));
        assertThat(carOptions.size(), lessThanOrEqualTo(10));
    }

    @Test
    public void testStandardSpecsMinMax() {
        ArrayList standardSpecs = faker.vehicle().standardSpecs(13, 14);
        assertThat(standardSpecs.size(), greaterThanOrEqualTo(13));
        assertThat(standardSpecs.size(), lessThanOrEqualTo(14));
    }

    @Test
    public void testStandardSpecs() {
        ArrayList standardSpecs = faker.vehicle().standardSpecs();
        assertThat(standardSpecs.size(), greaterThanOrEqualTo(5));
        assertThat(standardSpecs.size(), lessThanOrEqualTo(10));
    }

}
