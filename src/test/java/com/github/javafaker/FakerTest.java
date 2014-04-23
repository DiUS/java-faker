package com.github.javafaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

@RunWith(value = Parameterized.class)
public class FakerTest {
    private static final Logger logger = LoggerFactory.getLogger(FakerTest.class);
    private Faker faker;

    public FakerTest(Locale locale) {
        faker = new Faker(locale);
    }

    @Parameters(name = "testing locale {0}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{Locale.ENGLISH}, {Locale.FRENCH}, {new Locale("fi", "FI")}};
        return Arrays.asList(data);
    }

    @Test
    public void testNames() {
        String firstName = faker.firstName();
        logger.info("Test first name: " + firstName);
        assertNotNull(firstName);

        String lastName = faker.lastName();
        logger.info("Test last name: " + lastName);
        assertNotNull(lastName);

        String prefix = faker.prefix();
        logger.info("Test prefix: " + prefix);
        assertNotNull(prefix);

        String suffix = faker.suffix();
        logger.info("Test suffix: " + suffix);
        assertNotNull(suffix);

        String name = faker.name();
        logger.info("Test name: " + name);
        assertNotNull(name);
    }

    @Test
    public void testEmailAddress() {
        String emailAddress = faker.emailAddress();
        logger.info("Test email address: " + emailAddress);
        assertNotNull(emailAddress);
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = faker.phoneNumber();
        logger.info("Phone number: " + phoneNumber);
        assertNotNull(phoneNumber);
    }


    @Test
    public void testSpecifiedNumOfWords() {
        List<String> words = faker.words(4);
        logger.info("Test words: " + words.toString());
        assertEquals(4, words.size());
    }

    @Test
    public void testSentence() {
        String sentence = faker.sentence(7);
        logger.info("Test sentence: " + sentence);
        assertNotNull(sentence);
    }

    @Test
    public void testSentences() {
        List<String> sentences = faker.sentences(3);
        logger.info("Test sentences: " + sentences);
        assertEquals(3, sentences.size());
    }

    @Test
    public void testSpecifiedNumOfParagraphs() {
        String paragraph = faker.paragraph(5);
        logger.info("Test paragraph: " + paragraph);
        assertNotNull(paragraph);
    }

    @Test
    public void testStreetName() {
        String streetName = faker.streetName();
        logger.info("Street name: " + streetName);
        assertNotNull(streetName);
    }

    @Test
    public void testStreetAddress() {
        String streetAddress = faker.streetAddress(true);
        logger.info("Street address: " + streetAddress);
        assertNotNull(streetAddress);
    }

    @Test
    public void testSecondaryAddress() {
        String secondaryAddress = faker.secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
        assertNotNull(secondaryAddress);
    }

    @Test
    public void testZipCode() {
        String zip = faker.zipCode();
        logger.info("Address zip code: " + zip);
        assertNotNull(zip);
    }

    @Test
    public void testStreetSuffix() {
        String streetSuffix = faker.streetSuffix();
        logger.info("Street suffix: " + streetSuffix);
        assertNotNull(streetSuffix);
    }

    @Test
    public void testCitySuffix() {
        String citySuffix = faker.citySuffix();
        logger.info("City suffix: " + citySuffix);
        assertNotNull(citySuffix);
    }

    @Test
    public void testCityPrefix() {
        String cityPrefix = faker.cityPrefix();
        logger.info("City prefix: " + cityPrefix);
        assertNotNull(cityPrefix);
    }

    @Test
    public void testStateAbbr() {
        String stateAbbr = faker.stateAbbr();
        logger.info("State abbr: " + stateAbbr);
        assertNotNull(stateAbbr);
    }

    @Test
    public void testCountry() {
        String country = faker.country();
        logger.info("Country: " + country);
        assertNotNull(country);
    }

    @Test
    public void testParagraph() {
        String paragraph = faker.paragraph();
        logger.info("Paragraph: " + paragraph);
        assertNotNull(paragraph);
    }

    @Test
    public void testParagraphs() {
        final int paragraphCount = 10;
        List<String> paragraphs = faker.paragraphs(paragraphCount);
        logger.info("Paragraphs: " + paragraphs);
        assertEquals(paragraphCount, paragraphs.size());
    }
}
