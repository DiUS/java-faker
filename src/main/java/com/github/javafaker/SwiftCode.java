package com.github.javafaker;

/**
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/721
 * A class for generating random swift code
 */

public class SwiftCode {

    private final Faker faker;

    protected SwiftCode(final Faker faker) {
        this.faker = faker;
    }

    /**
     *
     * @return a random swift code of a bank's headquarter as a string value
     */
    public String headquarterSwiftcode() {
        String chars1 = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
        String chars2 = "0123456789ABCDEFGHIZKLMNOPQRSTUVWXYZ";
        String swiftcode = "";
        for (int i = 0 ; i < 4 ; i++) {
            String bank = String.valueOf(chars1.charAt((int) (Math.random() * 26)));
            swiftcode += bank;
        }
        String country = faker.address().countryCode();
        swiftcode += country;
        for (int i = 0 ; i < 2 ; i++) {
            String region = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
            swiftcode += region;
        }
        swiftcode += "XXX";
        return swiftcode;
    }

    /**
     *
     * @return a random swift code of a bank's branch as a string value
     */
    public String branchSwiftcode() {
        String chars1 = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
        String chars2 = "0123456789ABCDEFGHIZKLMNOPQRSTUVWXYZ";
        String swiftcode = "";
        for (int i = 0 ; i < 4 ; i++) {
            String bank = String.valueOf(chars1.charAt((int) (Math.random() * 26)));
            swiftcode += bank;
        }
        String country = faker.address().countryCode();
        swiftcode += country;
        for (int i = 0 ; i < 2 ; i++) {
            String region = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
            swiftcode += region;
        }
        for (int i = 0 ; i < 3 ; i++) {
            String branch = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
            swiftcode += branch;
        }
        return swiftcode;
    }
}
