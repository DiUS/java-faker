package com.github.javafaker;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakerTest {
    private static final Logger logger = LoggerFactory.getLogger(FakerTest.class);
    private Faker faker = new Faker();

    @Test
    public void shouldFetchNames() {
        String firstName = faker.firstName();
        logger.info("Test first name: " + firstName);
        Assert.assertNotNull(firstName);

        String lastName = faker.lastName();
        logger.info("Test last name: " + lastName);
        Assert.assertNotNull(lastName);

        String prefix = faker.prefix();
        logger.info("Test prefix: " + prefix);
        Assert.assertNotNull(prefix);

        String suffix = faker.suffix();
        logger.info("Test suffix: " + suffix);
        Assert.assertNotNull(suffix);

        String name = faker.name();
        logger.info("Test name: " + name);
        Assert.assertNotNull(name);
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = faker.phoneNumber();
        logger.info("Phone number: " + phoneNumber);
        Assert.assertNotNull(phoneNumber);
    }

    @Test
    public void shouldCreateFixedLenghtString() {
        Assert.assertEquals(10, faker.fixedString(10).length());
        Assert.assertEquals(50, faker.fixedString(50).length());
        Assert.assertEquals(0, faker.fixedString(0).length());
        Assert.assertEquals(0, faker.fixedString(-1).length());
    }

    @Test
    public void shouldReturnSpecifiedNumOfWords() {
        List<String> words = faker.words(4);
        logger.info("Test words: " + words.toString());
        Assert.assertEquals(4, words.size());
    }

    @Test
    public void printFakeSentence() {
        String sentence = faker.sentence(7);
        logger.info("Test sentence: " + sentence);
    }

    @Test
    public void printFakeSentences() {
        List<String> sentences = faker.sentences(3);
        logger.info("Test sentences: " + sentences);
    }

    @Test
    public void printFakeParagraph() {
        String paragraph = faker.paragraph(5);
        logger.info("Test paragraph: " + paragraph);
    }


    @Test
    public void testStreetName() {
        String streetName = faker.streetName();
        logger.info("Street name: " + streetName);
    }

    @Test
    public void testStreetAddress() {
        String streetAddress = faker.streetAddress(true);
        logger.info("Street address: " + streetAddress);
    }

    @Test
    public void testSecondaryAddress() {
        String secondaryAddress = faker.secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
    }

    @Test
    public void testZipCode() {
        String zip = faker.zipCode();
        logger.info("Address zip code: " + zip);
    }

    @Test
    public void testStreetSuffix() {
        String streetSuffix = faker.streetSuffix();
        logger.info("Address suffix: " + streetSuffix);
    }

    @Test
    public void testCitySuffix() {
        String citySuffix = faker.citySuffix();
        logger.info("City suffix: " + citySuffix);
    }

    @Test
    public void testCityPrefix() {
        String cityPrefix = faker.cityPrefix();
        logger.info("City prefix: " + cityPrefix);
    }

    @Test
    public void testStateAbbr() {
        String stateAbbr = faker.stateAbbr();
        logger.info("State abbr: " + stateAbbr);
    }

    @Test
    public void testCountry() {
        String country = faker.country();
        logger.info("Country: " + country);
    }
}
