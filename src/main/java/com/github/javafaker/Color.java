package com.github.javafaker;

public class Color {
    private final Faker faker;

    protected Color(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("color.name", this, faker);
    }

    public String hex() {
        return hex(false);
    }

    public String hex(boolean excludeHashSign) {
        String hexString = faker.random().hex(6);
        if(excludeHashSign)
            return hexString;
        return "#" + hexString;
    }
}
