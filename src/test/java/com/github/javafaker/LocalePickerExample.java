package com.github.javafaker;

import com.github.javafaker.service.LocalePicker;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;

// CS427 Issue link: https://github.com/DiUS/java-faker/issues/603
public class LocalePickerExample {

    // CS427 Issue link: https://github.com/DiUS/java-faker/issues/603
    /**
     * Example to illustrate use of LocalePicker to randomly select
     *   locales (language and geographical/political/cultural region) when using Faker
     * @param args Command-line arguments
     */
    public static void main(String args[]) {
        LocalePicker lp = new LocalePicker();

        // EXAMPLE: GET LIST OF ALL LOCALES SUPPORTED BY JAVA FAKER
        List<String> allLocales = lp.getAllSupportedLocales();
        System.out.println("All locales supported in Java Faker: " + Arrays.toString(allLocales.toArray()));

        // EXAMPLE: GET A FAKER OBJECT WITH A RANDOM LOCALE (SELECTED WITH REPLACEMENT)
        // Instantiate a Faker object with a randomized locale
        Locale pickedLocale = lp.getLocale();
        Faker faker = new Faker(pickedLocale);

        // Use Faker object to generate data in the randomly selected locale
        String fullName = faker.name().fullName();
        String streetAddress = faker.address().streetAddress();
        String phoneNumber = faker.phoneNumber().phoneNumber();

        System.out.println ("EXAMPLE: SELECT A RANDOM LOCALE (WITH REPLACEMENT)");
        System.out.println ("Random Locale: " + pickedLocale.toString());
        System.out.println ("  Full Name: " + fullName);
        System.out.println ("  Street Address: " + streetAddress);
        System.out.println ("  Phone Number: " + phoneNumber);

        // EXAMPLE: ROTATE THROUGH ALL LOCALES SUPPORTED BY JAVA FAKER TO GENERATE USER DATA
        //   LOCALES PICKED AT RANDOM (SELECTED WITHOUT REPLACEMENT)
        System.out.println ("EXAMPLE: ROTATE THROUGH ALL LOCALES AT RANDOM (WITHOUT REPLACEMENT)");
        Faker currentFaker;

        int numSupportedLocales = allLocales.size();
        for (int i=0; i < numSupportedLocales; i++) {
            Locale currentLocale = lp.getLocaleWithoutReplacement();
            System.out.println ("Random Locale: " + currentLocale.toString());
            currentFaker = new Faker(currentLocale);
            System.out.println ("  First Name: " + currentFaker.name().firstName());
            System.out.println ("  Last Name: " + currentFaker.name().lastName());
            System.out.println ("  Street Address: " + currentFaker.address().streetAddress());
            System.out.println ("  Phone Number: " + currentFaker.phoneNumber().phoneNumber());
        }
        
    }
    
}
