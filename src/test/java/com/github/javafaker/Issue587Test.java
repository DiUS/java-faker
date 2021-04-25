//package com.github.javafaker;
//
//import org.junit.Test;
//
//import java.util.Locale;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//
//public class Issue494Test extends AbstractFakerTest {
//    @Test
//    public void testLocale(){
//        Faker faker = new Faker(Locale.CHINA);
//        System.out.println(faker.address().city());
//        System.out.println(faker.address().state());
////        System.out.println(String.format("en-US: %s", faker.address().country()));
//        assertEquals("New York", String.format("en-US: %s", faker.address().city()));
//        faker = new Faker(Locale.FRANCE);
////        System.out.println(String.format("FRANCE: %s", faker.address().country()));
//        assertEquals("Paris", String.format("FRANCE: %s", faker.address().city()));
//        faker = new Faker(Locale.UK);
////        System.out.println(String.format("UK: %s", faker.address().country()));
//        assertEquals("London", String.format("UK: %s", faker.address().city()));
//    }
//}
package com.github.javafaker;

import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author CharlotteE67
 */

public class Issue587Test extends AbstractFakerTest {
    static int testTimes = 1000;

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/587
    @Test
    public void testPastDate01() {
        for (int i = 0; i < testTimes; i++) {
            LocalDate date = faker.localDate().past(1, TimeUnit.DAYS);
            assertFalse(date.isAfter(LocalDate.now()));
        }
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/587
    @Test
    public void testPastDate02() {
        for (int i = 0; i < testTimes; i++) {
            LocalDate date = faker.localDate().past(2, TimeUnit.DAYS);
            assertFalse(date.isAfter(LocalDate.now()));
        }
    }

}
