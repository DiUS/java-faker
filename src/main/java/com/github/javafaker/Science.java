package com.github.javafaker;

public class Science {
    private final Faker faker;

    protected Science(Faker faker) {
        this.faker = faker;
    }

    public String element() {
        return faker.fakeValuesService().resolve("science.element", this, faker);
    }

    public String elementSymbol() {
        return faker.fakeValuesService().resolve("science.element_symbol", this, faker);
    }

    public String scientist() {
        return faker.fakeValuesService().resolve("science.scientist", this, faker);
    }

    public String quark() {
        return faker.fakeValuesService().resolve("science.particles.quarks", this, faker);
    }

    public String leptons() {
        return faker.fakeValuesService().resolve("science.particles.leptons", this, faker);
    }

    public String bosons() {
        return faker.fakeValuesService().resolve("science.particles.bosons", this, faker);
    }
}
