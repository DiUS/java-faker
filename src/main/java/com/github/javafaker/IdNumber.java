package com.github.javafaker;

public class IdNumber {
    private final Faker faker;

    IdNumber(Faker faker) {
        this.faker = faker;
    }

    private static final String[] invalidSSNPatterns = {
            "0{3}-\\\\d{2}-\\\\d{4}",
            "\\d{3}-0{2}-\\d{4}",
            "\\d{3}-\\d{2}-0{4}",
            "666-\\d{2}-\\d{4}",
            "9\\d{2}-\\d{2}-\\d{4}" };

    public String valid() {
        return faker.fakeValuesService().resolve("id_number.valid", this, faker);
    }

    public String invalid() {
        return faker.numerify(faker.fakeValuesService().resolve("id_number.invalid", this, faker));
    }

    public String ssnValid() {
        String ssn = faker.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}");

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
