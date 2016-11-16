package com.github.javafaker;

public class Music {

    private static final String[] KEYS = new String[] { "C", "D", "E", "F", "G", "A", "B" };
    private static final String[] KEY_VARIANTS = new String[] { "b", "#", "" };

    private final Faker faker;

    Music(Faker faker) {
        this.faker = faker;
    }

    public String instrument() {
        return faker.resolve("music.instruments");
    }

    public String key() {
        return faker.options().option(KEYS) + faker.options().option(KEY_VARIANTS);
    }
}
