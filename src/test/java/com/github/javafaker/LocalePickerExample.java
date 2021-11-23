package com.github.javafaker;

import com.github.javafaker.service.LocalePicker;
import java.util.List;
import java.util.Arrays;

public class LocalePickerExample {


    public static void main(String args[]) {
        LocalePicker lp = new LocalePicker();

        // Get list of all locales supported by Java Faker
        List<String> allLocales = lp.getAllSupportedLocales();
        System.out.println("All Supported Locales: " + Arrays.toString(allLocales.toArray()));

        // Get a random locale
        String randomLocale = lp.getLocale();
        System.out.println("Random Locale: " + randomLocale);
    }

    
}
