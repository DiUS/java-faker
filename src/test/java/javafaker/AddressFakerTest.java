package javafaker;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressFakerTest {
    private static final Logger logger = LoggerFactory.getLogger(AddressFakerTest.class);

    @Test
    public void testStreetName() {
        String streetName = AddressFaker.streetName();
        logger.info("Street name: " + streetName);
    }

    @Test
    public void testStreetAddress() {
        String streetAddress = AddressFaker.streetAddress(true);
        logger.info("Street address: " + streetAddress);
    }

    @Test
    public void testSecondaryAddress() {
        String secondaryAddress = AddressFaker.secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
    }

    @Test
    public void testZipCode() {
        String zip = AddressFaker.zipCode();
        logger.info("Address zip code: " + zip);
    }

    @Test
    public void testStreetSuffix() {
        String streetSuffix = AddressFaker.streetSuffix();
        logger.info("Address suffix: " + streetSuffix);
    }

    @Test
    public void testCitySuffix() {
        String citySuffix = AddressFaker.citySuffix();
        logger.info("City suffix: " + citySuffix);
    }

    @Test
    public void testCityPrefix() {
        String cityPrefix = AddressFaker.cityPrefix();
        logger.info("City prefix: " + cityPrefix);
    }

    @Test
    public void testStateAbbr() {
        String stateAbbr = AddressFaker.stateAbbr();
        logger.info("State abbr: " + stateAbbr);
    }

    @Test
    public void testCountry() {
        String country = AddressFaker.country();
        logger.info("Country: " + country);
    }

}
