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
    private final Random random;

    /**
     * Constructor for LocalePicker class
     */
    public LocalePicker() {
        this((Random) null);
    }

    /**
     * Constructor for LocalePicker class
     * @param random random number generator (can have a seed for deterministic random selection)
     */
    public LocalePicker(Random random) {
        if (random != null) {
            this.random = random;
        } else {
            this.random = new Random();
        }
        this.locales = getAllSupportedLocales();
    }

    /**
     * Retrieves list of all locales supported by Java Faker
     * @return a List of Strings with the name of the locale (eg. "es", "es-MX")
     */
    public List<String> getAllSupportedLocales() {

        // Retrieve list of all locales supported by Java Faker based on files in "resources" folder
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

    /**
     * Select a locale at random with replacement
     * @param random random number generator (can have a seed for deterministic random selection)
     * @return String of a randomly selected locale (eg. "es", "es-MX")
     */
    public String getRandomLocale(Random random) {

        // Randomly select a locale from list of all locales supported by Java Faker
        Integer randomCountry = random.nextInt(locales.size());
        return locales.get(randomCountry);
    }

    /**
     * Select a locale at random with replacement
     * @return String of a randomly selected locale (eg. "es", "es-MX")
     */
    public String getLocale() {
        Random random = new Random();
        return getRandomLocale(random);
    }

}