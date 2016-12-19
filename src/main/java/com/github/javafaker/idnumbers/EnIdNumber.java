package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

public class EnIdNumber {
    private static final String[] invalidSSNPatterns = {
            "0{3}-\\\\d{2}-\\\\d{4}",
            "\\d{3}-0{2}-\\d{4}",
            "\\d{3}-\\d{2}-0{4}",
            "666-\\d{2}-\\d{4}",
            "9\\d{2}-\\d{2}-\\d{4}"};

    public String getValidSsn(Faker f) {
        String ssn = f.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}");

        boolean isValid = true;
        for (int i = 0; i < invalidSSNPatterns.length; i++) {
            if (ssn.matches(invalidSSNPatterns[i])) {
                isValid = false;
                break;
            }
        }
        if (!isValid) {
            ssn = getValidSsn(f);
        }
        return ssn;
    }
}
