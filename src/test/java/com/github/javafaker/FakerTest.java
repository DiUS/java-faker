package com.github.javafaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Object[][] data = new Object[][]{{Locale.ENGLISH}, {Locale.FRENCH}};
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

    @Test
    public void testEmail() {
        String eMail = faker.eMail();
        logger.info("email: " + eMail);
        assertNotNull(eMail);
    }

    @Test
    public void testNumber() {
        int number = faker.number();
        logger.info("number: "+number);
        assertTrue(number > 0 && number < 1000);
    }

    @Test
    public void testNumberWithRange() {
        int number = faker.number(1000,2000);
        logger.info("number: "+number);
        assertTrue(number > 1000 && number < 2000);
    }

    @Test
    public void testDate() throws ParseException {
        String date = faker.date();
        logger.info("date: "+date);

        Calendar actualCalDate = Calendar.getInstance();
        actualCalDate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date));
        Calendar expectedCalDate = new GregorianCalendar();
        expectedCalDate.setTime(new Date());

        assertEquals(expectedCalDate.get(Calendar.DAY_OF_MONTH), actualCalDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(expectedCalDate.get(Calendar.MONTH), actualCalDate.get(Calendar.MONTH));
        assertEquals(expectedCalDate.get(Calendar.YEAR), actualCalDate.get(Calendar.YEAR));
    }


    @Test
    public void testDateWithOneDayForward() throws ParseException {
        String date = faker.date(1);
        logger.info("date: "+date);

        Calendar actualCalDate = Calendar.getInstance();
        actualCalDate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date));
        Calendar expectedCalDate = new GregorianCalendar();
        expectedCalDate.setTime(new Date());

        assertEquals(expectedCalDate.get(Calendar.DAY_OF_MONTH)+1, actualCalDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(expectedCalDate.get(Calendar.MONTH), actualCalDate.get(Calendar.MONTH));
        assertEquals(expectedCalDate.get(Calendar.YEAR), actualCalDate.get(Calendar.YEAR));
    }

    @Test
    public void testDateWithOneDayBack() throws ParseException {
        String date = faker.date(-1);
        logger.info("date: "+date);

        Calendar actualCalDate = Calendar.getInstance();
        actualCalDate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date));
        Calendar expectedCalDate = new GregorianCalendar();
        expectedCalDate.setTime(new Date());

        assertEquals(expectedCalDate.get(Calendar.DAY_OF_MONTH)-1, actualCalDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(expectedCalDate.get(Calendar.MONTH), actualCalDate.get(Calendar.MONTH));
        assertEquals(expectedCalDate.get(Calendar.YEAR), actualCalDate.get(Calendar.YEAR));
    }

    @Test
    public void testDateWithFormat() throws ParseException {
        String format = "yyyy-dd-MM";
        String date = faker.date(0, format);
        logger.info("date: "+date);

        Calendar actualCalDate = Calendar.getInstance();
        actualCalDate.setTime(new SimpleDateFormat(format, Locale.ENGLISH).parse(date));
        Calendar expectedCalDate = new GregorianCalendar();
        expectedCalDate.setTime(new Date());

        assertEquals(expectedCalDate.get(Calendar.DAY_OF_MONTH)+1, actualCalDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(expectedCalDate.get(Calendar.MONTH), actualCalDate.get(Calendar.MONTH));
        assertEquals(expectedCalDate.get(Calendar.YEAR), actualCalDate.get(Calendar.YEAR));
    }

    @Test
    public void testFileCreation() throws Exception {
        // setting path to test file that we're going to create
        URL baseUrl = Faker.class.getClassLoader().getResource(".");
        String url =  new URL(baseUrl,"../../src/main/resources/test.txt").toString().replace("file:","");
        logger.info("creating a new file in "+url);
        url = URLDecoder.decode(url,"UTF-8");
        // creatign file with 10k lines
        faker.file(url);
        logger.info("File was created : "+url);
        // making sure that file was created
        File file = new File(url);
        assertNotNull(file);
        //cleaning up the mess
        if(file.delete()){
            logger.info(file.getName() + " is deleted!");
        }else{
            logger.error("Delete operation is failed.");
            throw new Exception("file was not deleted properly");
        }

    }

    @Test
    public void testFileCreationWithNumberOfLines() throws Exception {
        // setting path to test file that we're going to create
        URL baseUrl = Faker.class.getClassLoader().getResource(".");
        String url =  new URL(baseUrl,"../../src/main/resources/test.txt").toString().replace("file:","");
        logger.info("creating a new file in "+url);
        url = URLDecoder.decode(url,"UTF-8");
        // creatign file with custom number lines
        int linesNumber = 25000;
        faker.file(url, linesNumber);
        logger.info("File was created : " + url);
        // making sure that file was created
        File file = new File(url);
        assertNotNull(file);
        // counting number of lines
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        int actualLinesNumber = lnr.getLineNumber();
        lnr.close();
        assertEquals(linesNumber, actualLinesNumber);
        //cleaning up the mess
        if(file.delete()){
            logger.info(file.getName() + " is deleted!");
        }else{
            logger.error("Delete operation is failed.");
            throw new Exception("file was not deleted properly");
        }

    }
}
