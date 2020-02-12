package com.github.javafaker;

public class Music {

    private static final String[] KEYS = new String[] { "C", "D", "E", "F", "G", "A", "B" };
    private static final String[] KEY_VARIANTS = new String[] { "b", "#", "" };
    private static final String[] CHORD_TYPES = new String[] { "", "maj", "6", "maj7", "m", "m7", "-7", "7", "dom7", "dim", "dim7", "m7b5"};

    private final Faker faker;

    protected Music(Faker faker) {
        this.faker = faker;
    }

    public String instrument() {
        return faker.resolve("music.instruments");
    }

    public String key() {
        return faker.options().option(KEYS) + faker.options().option(KEY_VARIANTS);
    }

    public String chord() {
        return key() + faker.options().option(CHORD_TYPES);
    }

    public String genre() {
        return faker.resolve("music.genres");
    }
}
