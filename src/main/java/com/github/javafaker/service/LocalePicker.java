package com.github.javafaker.service;

import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;
import java.io.File;

public class LocalePicker {

    private final String resourcePath = "./src/main/resources";
    private final List<String> locales;

    public LocalePicker() {
        this.locales = getAllSupportedLocales();
    }

    public List<String> getAllSupportedLocales() {

        // Retrieve list of all locales supported by Java Faker
        List<String> locales = new ArrayList<String>();

        String[] resourceFiles = new File(resourcePath).list();
        int numResourceFiles = resourceFiles.length;
        for (int i=0; i < numResourceFiles; i++) {
            String resourceFileName = resourceFiles[i];
            if (resourceFileName.endsWith(".yml")) {
                String localeName = resourceFileName.substring(0, resourceFileName.lastIndexOf('.'));
                locales.add(localeName);
            }
        }

        return locales;
    }

    public String getRandomLocale(Random random) {

        // Randomly select a locale from list of all locales supported by Java Faker
        Integer randomCountry = random.nextInt(locales.size());
        return locales.get(randomCountry);
    }

    public String getLocale() {
        Random random = new Random();
        return getRandomLocale(random);
    }

    public static void main(String args[]) {

        LocalePicker lp = new LocalePicker();
        List<String> allLocales = lp.getAllSupportedLocales();
        System.out.println("All Supported Locales: " + Arrays.toString(allLocales.toArray()));
        String randomLocale = lp.getLocale();
        System.out.println("Random Locale: " + randomLocale);
    }
}