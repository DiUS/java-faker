package com.github.javafaker;

public class Size {

    private final Faker faker;

    protected Size(Faker faker){
        this.faker = faker;
    }

    public String adjective(){
        return faker.fakeValuesService().resolve("size.adjective", this, faker);
    }

}
