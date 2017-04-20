package com.github.javafaker;

public class Business {
    
    private final Faker faker;

    protected Business(Faker faker) {
        this.faker = faker;
    }

    public String creditCardNumber() {
        return faker.fakeValuesService().resolve("business.credit_card_numbers", this, faker);
    }

    public String creditCardType() {
        return faker.fakeValuesService().resolve("business.credit_card_types", this, faker);
    }

    public String creditCardExpiry() {
        return faker.fakeValuesService().resolve("business.credit_card_expiry_dates", this, faker);
    }
}
