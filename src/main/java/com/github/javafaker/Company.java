package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

public class Company {
    private final FakeValuesService fakeValuesService;

    public Company(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        String companyName = fakeValuesService.fetchString("company.name");
        try {
            companyName = companyName + " " + fakeValuesService.fetchString("company.suffix");
        } catch (Exception ignored) {
            //ignored
        }
        return companyName;
    }

    public String suffix() {
        return fakeValuesService.fetchString("company.suffix");
    }

}
