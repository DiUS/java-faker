package com.github.javafaker;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakerTest {
    private static final Logger logger = LoggerFactory.getLogger(FakerTest.class);
    private Faker faker = new Faker(new Random(10));

    @Test
    public void shouldFetchNames() {
        String firstName = faker.firstName();
        logger.info("Test first name: " + firstName);
        assertEquals("Clyde", firstName);

        String lastName = faker.lastName();
        logger.info("Test last name: " + lastName);
        assertEquals("Gleason", lastName);

        String prefix = faker.prefix();
        logger.info("Test prefix: " + prefix);
        assertEquals("Miss", prefix);

        String suffix = faker.suffix();
        logger.info("Test suffix: " + suffix);
        assertEquals("I", suffix);

        String name = faker.name();
        logger.info("Test name: " + name);
        assertEquals("Harry Volkman", name);
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = faker.phoneNumber();
        logger.info("Phone number: " + phoneNumber);
        assertEquals("(030)667-8143 x9185", phoneNumber);
    }



    @Test
    public void shouldReturnSpecifiedNumOfWords() {
        List<String> words = faker.words(4);
        logger.info("Test words: " + words.toString());
        assertEquals(4, words.size());
        assertEquals("dolores", words.get(0));
        assertEquals("repellat", words.get(1));
        assertEquals("adipisci", words.get(2));
        assertEquals("quos", words.get(3));
    }

    @Test
    public void printFakeSentence() {
        String sentence = faker.sentence(7);
        logger.info("Test sentence: " + sentence);
        assertEquals("Molestiae ea labore ipsum voluptas dolores placeat voluptatibus consectetur in.", sentence);
    }

    @Test
    public void printFakeSentences() {
        List<String> sentences = faker.sentences(3);
        logger.info("Test sentences: " + sentences);
        assertEquals(3, sentences.size());
    }

    @Test   
    public void printFakeParagraph() {
        String paragraph = faker.paragraph(5);
        logger.info("Test paragraph: " + paragraph);
        assertEquals("Perspiciatis et odit. Consequatur atque fuga quos consequatur. Autem natus animi ex dolor. Fuga eos consectetur eos. At esse sunt a.", paragraph);
    }


    @Test
    public void testStreetName() {
        String streetName = faker.streetName();
        logger.info("Street name: " + streetName);
        assertEquals("Langosh Loop", streetName);
    }

    @Test
    public void testStreetAddress() {
        String streetAddress = faker.streetAddress(true);
        logger.info("Street address: " + streetAddress);
        assertEquals("39185 Francesco Mountains Apt. 814", streetAddress);
    }

    @Test
    public void testSecondaryAddress() {
        String secondaryAddress = faker.secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
        assertEquals("Suite 030", secondaryAddress);
    }

    @Test
    public void testZipCode() {
        String zip = faker.zipCode();
        logger.info("Address zip code: " + zip);
        assertEquals("03066-7814", zip);
    }

    @Test
    public void testStreetSuffix() {
        String streetSuffix = faker.streetSuffix();
        logger.info("Address suffix: " + streetSuffix);
        assertEquals("Gateway", streetSuffix);
    }

    @Test
    public void testCitySuffix() {
        String citySuffix = faker.citySuffix();
        logger.info("City suffix: " + citySuffix);
        assertEquals("port", citySuffix);
    }

    @Test
    public void testCityPrefix() {
        String cityPrefix = faker.cityPrefix();
        logger.info("City prefix: " + cityPrefix);
        assertEquals("West", cityPrefix);
    }

    @Test
    public void testStateAbbr() {
        String stateAbbr = faker.stateAbbr();
        logger.info("State abbr: " + stateAbbr);
        assertEquals("DC", stateAbbr);
    }

    @Test
    public void testCountry() {
        String country = faker.country();
        logger.info("Country: " + country);
        assertEquals("Haiti", country);
        
    }
}
