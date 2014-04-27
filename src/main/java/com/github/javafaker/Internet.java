package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

import static org.apache.commons.lang.StringUtils.join;

public class Internet {

    private final Name name;
    private final FakeValuesService fakeValuesService;

    public Internet(Name name, FakeValuesService fakeValuesService) {
        this.name = name;
        this.fakeValuesService = fakeValuesService;
    }

    public String emailAddress() {
        return join(new Object[]{
                name.firstName().toLowerCase(),
                ".",
                name.lastName().toLowerCase(),
                "@",
                fakeValuesService.fetchString("internet.free_email")
        });
    }
}
