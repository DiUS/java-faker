package com.github.javafaker;

public class Code {

    private final Faker faker;

    Code(Faker faker) {
        this.faker = faker;
    }

    public String isbn10() {
        StringBuilder isbn10 = new StringBuilder();
        int sum = 0;
        for (int i = 10; i > 1; i--) {
            int n = faker.random().nextInt(10);
            sum += i * n;
            isbn10.append(n);
        }
        int check = (11 - sum % 11) % 11;
        isbn10.append('-');
        isbn10.append(check != 10 ? check : "X");
        return isbn10.toString();
    }

    public String isbn13() {
        StringBuilder isbn13 = new StringBuilder();
        int sum = 0;
        int multiplier = 1;
        int a = 1;
        int b = 2;
        for (int i = 0; i < 12; i++) {
            int n = faker.random().nextInt(10);
            sum += multiplier * n;
            multiplier = multiplier == 1 ? 3 : 1;
            isbn13.append(n);
            if (i == b) {
                isbn13.append('-');
                int t = b;
                b += a;
                a = t;
            }
        }
        int check = (10 - sum % 10) % 10;
        isbn13.append(check);
        return isbn13.toString();
    }

    public String asin() {
        return faker.resolve("code.asin");
    }

    public String imei() {
        char[] str = new char[15];
        int len = str.length;

        // Fill in the first two values of the string based with the specified prefix.
        String arr = faker.options().option(REPORTING_BODY_IDENTIFIERS);
        str[0] = arr.charAt(0);
        str[1] = arr.charAt(1);

        // Fill all the remaining numbers except for the last one with random values.
        for (int i=2; i < len - 1; i++) {
            str[i] = Character.forDigit(faker.number().numberBetween(0, 9), 10);
        }

        // Calculate the Luhn checksum of the values thus far
        int lenOffset = (len + 1) % 2;
        int t = 0;
        int sum = 0;
        for (int i = 0; i < len - 1; i++) {
            if ((i + lenOffset) % 2 != 0) {
                t = Character.getNumericValue(str[i]) * 2;

                if (t > 9) {
                    t -= 9;
                }

                sum += t;
            } else {
                sum += Character.getNumericValue(str[i]);
            }
        }

        // Choose the last digit so that it causes the entire string to pass the checksum.
        str[len - 1] = Character.forDigit(((10 - (sum % 10)) % 10), 10);

        return new String(str);
    }

    private static final String [] REPORTING_BODY_IDENTIFIERS 
        = {"01", "10", "30", "33", "35", "44", "45", "49", "50", "51", "52", "53", "54", "86", "91", "98", "99"};
}
