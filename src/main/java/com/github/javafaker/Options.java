package com.github.javafaker;

public class Options {
    private final Faker faker;

    protected Options(Faker faker) {
        this.faker = faker;
    }


    public String option(String... options) {
        return options[faker.random().nextInt(options.length)];
    }

    public <E extends Enum<E>> E option(Class<E> enumeration) {
        E[] enumConstants = enumeration.getEnumConstants();
        return enumConstants[faker.random().nextInt(enumConstants.length)];
    }
}
