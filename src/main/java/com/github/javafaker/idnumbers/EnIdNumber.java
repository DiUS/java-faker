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
        for (String invalidSSNPattern : invalidSSNPatterns) {
            if (ssn.matches(invalidSSNPattern)) {
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
