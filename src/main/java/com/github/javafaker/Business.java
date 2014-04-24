package com.github.javafaker;

public class Business {
    private final FakeValuesService fakeValuesService;

    public Business(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String creditCardNumber() {
        return fakeValuesService.fetchString("business.credit_card_numbers");
    }

    public String creditCardType() {
        return fakeValuesService.fetchString("business.credit_card_types");
    }

    public String creditCardExpiry() {
        return fakeValuesService.fetchString("business.credit_card_expiry_dates");
    }
}
