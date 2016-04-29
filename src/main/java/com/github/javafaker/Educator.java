package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Educator {

    private final FakeValuesServiceInterface fakeValuesService;

    public Educator(FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String university() {
        return fakeValuesService.fetchString("educator.name") + " " + fakeValuesService.fetchString("educator.tertiary.type");
    }

    public String course() {
        return fakeValuesService.fetchString("educator.tertiary.course.type") + " "
                + fakeValuesService.fetchString("educator.tertiary.course.subject");
    }

    public String secondarySchool() {
        return fakeValuesService.fetchString("educator.name") + " " + fakeValuesService.fetchString("educator.secondary");
    }

    public String campus() {
        return fakeValuesService.fetchString("educator.name") + " Campus";
    }

}
