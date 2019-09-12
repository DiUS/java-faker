package com.github.javafaker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class VehicleTest extends AbstractFakerTest {
    private static final String WORD_MATCH = "\\w+\\.?";
    private static final String WORDS_MATCH = "^[a-zA-Z0-9_/ -]*$";
    private static final String FOREIGN_WARDS_MATCH = "^[\\p{L}]*[a-zA-Z0-9/ -/(/)']*[\\p{L}]*";

    @Test
    public void testVin() {
        assertThat(faker.vehicle().vin(), matchesRegularExpression(Vehicle.VIN_REGEX));
    }

    @Test
    public void testManufacture() {
        assertThat(faker.vehicle().manufacture(), matchesRegularExpression(FOREIGN_WARDS_MATCH));
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
        assertTrue((Integer) faker.vehicle().door() > 0);
    }

    @Test
    public void testEngine() {
        assertThat(faker.vehicle().engine(), matchesRegularExpression("\\d Cylinder Engine"));
    }

    @Test
    public void testMileage() {
        int mileage = faker.vehicle().mileage(61,90000);
        assertTrue(mileage >= 61);
        assertTrue(mileage <= 90000);
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
    public void testCarOption() {
        ArrayList carOption = faker.vehicle().carOption();
        assertTrue(carOption.size() >= 5);
        assertTrue(carOption.size() <= 10);
    }

    @Test
    public void testStandardSpec() {
        ArrayList standardSpecs = faker.vehicle().standardSpecs();
        assertTrue(standardSpecs.size() >= 5);
        assertTrue(standardSpecs.size() <= 10);
    }
    @Test
    public void testYear() {
        int mileage = faker.vehicle().year();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int pastYear = currentYear - 10;
        assertTrue(mileage >= pastYear);
        assertTrue(mileage <= currentYear);
    }
}
