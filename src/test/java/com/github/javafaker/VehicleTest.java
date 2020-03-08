package com.github.javafaker;

import com.github.javafaker.matchers.MatchesRegularExpression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class VehicleTest extends AbstractFakerTest {

    private static final String WORD_MATCH = "\\w+\\.?";
    private static final String WORDS_MATCH = "^[a-zA-Z0-9_/ -]*$";
    private static final String INTERNATIONAL_WORDS_MATCH = "\\P{Cc}+";

    @Test
    public void testVin() {
        assertThat(faker.vehicle().vin(), matchesRegularExpression(Vehicle.VIN_REGEX));
    }

    @Test
    public void testManufacturer() { assertThat(faker.vehicle().manufacturer(), matchesRegularExpression(INTERNATIONAL_WORDS_MATCH)); }

    @Test
    public void testMake() {
        assertThat(faker.vehicle().make(), matchesRegularExpression(WORD_MATCH));
    }

    @Test
    public void testModel() { assertThat(faker.vehicle().model(), MatchesRegularExpression.matchesRegularExpression(WORDS_MATCH));}

    @Test
    public void testModelWithParams() { assertThat(faker.vehicle().model("Toyota"), matchesRegularExpression(WORDS_MATCH)); }

    @Test
    public void testMakeAndModel() { assertThat(faker.vehicle().makeAndModel(), matchesRegularExpression(WORDS_MATCH)); }

    @Test
    public void testStyle() {
        assertThat(faker.vehicle().style(), matchesRegularExpression(WORD_MATCH));
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
    public void testCarType() {
        assertThat(faker.vehicle().carType(), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testEngine() {
        assertThat(faker.vehicle().engine(), matchesRegularExpression("\\d Cylinder Engine"));
    }

    @Test
    public void testCarOptions() {
        ArrayList carOptions = faker.vehicle().carOptions();
        assertThat(carOptions.size(), greaterThanOrEqualTo(5));
        assertThat(carOptions.size(), lessThanOrEqualTo(10));
    }

    @Test
    public void testCarOptionsMinMax() {
        ArrayList carOptions = faker.vehicle().carOptions(11, 12);
        assertThat(carOptions.size(), greaterThanOrEqualTo(11));
        assertThat(carOptions.size(), lessThanOrEqualTo(12));
        assertThat(carOptions.get(0).toString(), notNullValue());
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

    @Test
    public void testDoor() { assertThat(faker.vehicle().doors(), matchesRegularExpression("\\d")); }



    @Test
    public void testLicensePlate() { assertThat(faker.vehicle().licensePlate(), matchesRegularExpression(WORDS_MATCH)); }

    @Test
    public void testLicensePlateWithParam() {
        assertThat(faker.vehicle().licensePlate("GA"), matchesRegularExpression(WORDS_MATCH));
        assertThat(faker.vehicle().licensePlate("AL"), matchesRegularExpression(WORDS_MATCH));
    }

    @Test
    public void testLicensePlateWithParam_Canada(){
        Faker test = new Faker(Locale.CANADA);
        assertThat(test.vehicle().licensePlate("MB"), matchesRegularExpression(WORDS_MATCH));
        assertThat(test.vehicle().licensePlate("ON"), matchesRegularExpression(WORDS_MATCH));
    }
}