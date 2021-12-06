package com.github.javafaker.service;

import java.util.List; 
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;
import java.io.File;
import java.util.Collections;

public class LocalePicker {

    private final static String resourcePath = "./src/main/resources";
    private final List<String> locales;
    private List<String> shuffledLocales = new ArrayList<String>();
    private final Random random;

    /**
     * Constructor for LocalePicker class
     */
    public LocalePicker() {
        this((Random) null);
    }

    /**
     * Constructor for LocalePicker class
     * @param random random number generator (can utilize seed for deterministic random selection)
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

        int numResourceFiles = 0;
        if (resourceFiles != null) {
            numResourceFiles = resourceFiles.length;
        }

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
     * @param random random number generator (can utilize seed for deterministic random selection)
     * @return String of a randomly selected locale (eg. "es", "es-MX")
     */
    public String getLocaleString(Random random) {

        // Randomly select a locale from list of all locales supported by Java Faker
        Integer randomIndex = random.nextInt(locales.size());
        return locales.get(randomIndex);
    }

    /**
     * Select a locale at random without replacement. This can be used to rotate through all locales supported by Java Faker.
     * @param random random number generator (can utilize seed for deterministic random selection)
     * @return String of a randomly selected locale (eg. "es", "es-MX")
     */
    public String getLocaleStringWithoutReplacement(Random random) {
        String pickedLocale = null;

        if (this.shuffledLocales.isEmpty()) {
            // copy list of locales supported by Java Faker into shuffledLocales
            shuffledLocales = new ArrayList<String>(this.locales);
            // randomly shuffle shuffledLocales
            Collections.shuffle(shuffledLocales);
        }

        // retrieve next locale in shuffledLocales and remove from list
        pickedLocale = shuffledLocales.get(0);
        shuffledLocales.remove(0);

        return pickedLocale;
    }

    /**
     * 
     * @return Locale object of a randomly selected locale (eg. "es", "es-MX"). 
     *   Locale is selected at random WITH replacement from all locales supported by Java Faker.
     */
    public Locale getLocale() {
        String pickedLocale = getLocaleString(this.random);
        return new Locale(pickedLocale);
    }

    /**
     * 
     * @return Locale object of a randomly selected locale (eg. "es", "es-MX"). 
     *   Locale is selected at random WITHOUT replacement from all locales supported by Java Faker.
     */
    public Locale getLocaleWithoutReplacement() {
        String pickedLocale = getLocaleStringWithoutReplacement(this.random);
        return new Locale(pickedLocale);
    }

}