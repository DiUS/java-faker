package com.github.javafaker;


/**
 * @author LinZhiyuan bianzheng
 * */
public class Disease{
    private final Faker faker;

    protected Disease(final Faker faker) {
        this.faker = faker;
    }
    /**
     * Generate a random type of disease
     * @return random type of disease
     * */
    public String type(){
        return faker.fakeValuesService().resolve("disease.type",this,faker);
    }


}