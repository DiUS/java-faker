package com.github.javafaker;

import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;

public class LocaleModeRepro {
    private static List<String> getLocales() {
        // List of available locales. It would have been nice if faker provided this.
        List<String> locales = new ArrayList<String>();

        // Add all available locals in Java faker.
        locales.add("bg");
        locales.add("ca");
        locales.add("ca-CAT");
        locales.add("uk");
        locales.add("vi");
        locales.add("zh-CN");
        locales.add("zh-TW");

        return locales;
    }

    private static String getRandomLocale() {
        // Pick a random country and return its locale value.
        Random random = new Random();
        Integer randomCountry = random.nextInt(getLocales().size());
        return getLocales().get(randomCountry);
    }

    public static String getLocale() {
        // Set locale
        String locale = "random";
        
        String chosenLocale;
        if (locale.equals("random")) {
            chosenLocale = getRandomLocale();
        } else if (getLocales().contains(locale)) {
            chosenLocale = locale;
        } else {
            chosenLocale = "fa";
        }

        return chosenLocale;
    }

    public static void main(String args[]) {
        // Test each method
        System.out.println("Test: getLocales");
        List<String> locales = getLocales();
        System.out.println(Arrays.toString(locales.toArray()));

        System.out.println("Test: getRandomLocale");
        String randomLocale = getRandomLocale();
        System.out.println(randomLocale);

        System.out.println("Test: getLocale");
        String chosenLocale = getLocale();
        System.out.println(chosenLocale);

        // Test full implementation
        System.out.println("End-to-end Test");
        Locale locale = new Locale(LocaleModeRepro.getLocale());
        System.out.println (locale.toString());
        Faker faker = new Faker(locale);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        System.out.println (firstName);
        System.out.println (lastName);
    }
}