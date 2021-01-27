package com.github.javafaker;

public class Military {

    private final Faker faker;

    protected Military(Faker faker) {
        this.faker = faker;
    }

    public String armyRank() {
        return faker.fakeValuesService().fetchString("military.army_rank");
    }

    public String marinesRank() {
        return faker.fakeValuesService().fetchString("military.marines_rank");
    }

    public String navyRank() {
        return faker.fakeValuesService().fetchString("military.navy_rank");
    }

    public String airForceRank() {
        return faker.fakeValuesService().fetchString("military.air_force_rank");
    }

    public String dodPaygrade() {
        return faker.fakeValuesService().fetchString("military.dod_paygrade");
    }

}
