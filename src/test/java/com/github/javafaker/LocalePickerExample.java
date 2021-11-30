package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

import com.github.javafaker.service.LocalePicker;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;

public class LocalePickerExample {


    public static void main(String args[]) {
        LocalePicker lp = new LocalePicker();

        // Get list of all locales supported by Java Faker
        List<String> allLocales = lp.getAllSupportedLocales();
        System.out.println("All Supported Locales: " + Arrays.toString(allLocales.toArray()));

        // Get a random locale
        String randomLocaleString = lp.getLocale();
        System.out.println("Random Locale: " + randomLocaleString);

        // Instantiate a Faker object with a randomized locale
        Locale randomLocale = new Locale(randomLocaleString);
        Faker faker = new Faker(randomLocale);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String fullName = faker.name().fullName();

        System.out.println ("Full Name" + fullName);
        System.out.println ("First Name: " + firstName);
        System.out.println ("Last Name: " + lastName);
        System.out.println ("Street Address: " + streetAddress);
        System.out.println ("Phone Number: " + phoneNumber);

        // Tests
        System.out.println (randomLocale.getLanguage());
        System.out.println (randomLocale.getCountry());
        System.out.println (randomLocale.toString());


    }

    
}
