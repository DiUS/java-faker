package com.github.javafaker;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    private final Faker faker;

    protected Crypto(Faker faker) {
        this.faker = faker;
    }

    public String md5() {
        return generateString("MD5", "%032x");
    }

    public String sha1() {
        return generateString("SHA-1", "%040x");
    }

    public String sha256() {
        return generateString("SHA-256", "%064x");
    }

    public String sha512() {
        return generateString("SHA-512", "%0128x");
    }

    private String generateString(String algorithm, String format) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            String characters = faker.lorem().characters();
            messageDigest.update(characters.getBytes(), 0, characters.length());
            return String.format(format, new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
    }

}
