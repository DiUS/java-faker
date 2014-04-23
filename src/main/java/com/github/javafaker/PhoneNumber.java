package com.github.javafaker;

public class PhoneNumber {

    private final FakeValuesService fakeValuesService;

    public PhoneNumber(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String phoneNumber() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("phone_number.formats"));
    }
}
