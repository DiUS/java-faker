package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class IdNumber {

    private static final String[] invalidSSNPatterns = {
            "0{3}-\\\\d{2}-\\\\d{4}",
            "\\d{3}-0{2}-\\d{4}",
            "\\d{3}-\\d{2}-0{4}",
            "666-\\d{2}-\\d{4}",
            "9\\d{2}-\\d{2}-\\d{4}" };

    private final Resolver resolver;
    private final FakeValuesServiceInterface fakeValuesService;

    public IdNumber(Resolver resolver, FakeValuesServiceInterface fakeValuesService) {
        this.resolver = resolver;
        this.fakeValuesService = fakeValuesService;
    }

    public String valid() {
        return fakeValuesService.resolve("id_number.valid", this, resolver);
    }

    public String invalid() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("id_number.invalid"));
    }

    public String ssnValid() {
        String ssn = fakeValuesService.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}");

        boolean isValid = true;
        for (int i = 0; i < invalidSSNPatterns.length; i++) {
            if (ssn.matches(invalidSSNPatterns[i])) {
                isValid = false;
                break;
            }
        }
        if (!isValid) {
            ssn = ssnValid();
        }
        return ssn;
    }
}
