package com.github.javafaker;

import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

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
        // String locale = TestConfig.getConfig().getString("locale");
        // String locale = "ca";
        String locale = "random";
        
        String chosenLocale;
        if (locale.equals("random")) {
            chosenLocale = getRandomLocale();
        } else if (getLocales().contains(locale)) {
            chosenLocale = locale;
        } else {
            chosenLocale = "fa";
        }

        // log.info("Chosen locale: {}", chosenLocale);
        System.out.println(chosenLocale);
        return chosenLocale;
    }

    public static void main(String args[]) {
        System.out.println("BEGIN TEST");
        List<String> locales = getLocales();
        System.out.println(Arrays.toString(locales.toArray()));

        String randomLocale = getRandomLocale();
        System.out.println(randomLocale);

        String chosenLocale = getLocale();

        // // Test Data
        // private Locale locale = new Locale(LocalePicker.getLocale());
        // private Faker faker = new Faker(locale);

        // private String firstName = faker.name().firstName(); // Emory
        // private String lastName = faker.name().lastName(); // Barton
    }
}