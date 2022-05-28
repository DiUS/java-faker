//CS304 Issue link: https://github.com/DiUS/java-faker/issues/726
package com.github.javafaker;

public class Mbti {
    private final Faker faker;
    private final String choice;

    /**
     * initialize faker and chioce which means he which movie to choose during the same year
     * @param faker
     */
    public Mbti(final Faker faker) {
        this.faker = faker;
        choice = faker.resolve("mbti.choice");
    }

    /***
     * The method is to randomly generate type of MBTI
     * @return type of MBTI
     */
    public String type() {
        return faker.resolve("mbti.".concat(choice).concat(".type"));
    }

    /**
     * The method is to randomly generate name of the same MBTI
     * @return name of the same MBTI
     */
    public String name() {
        return faker.resolve("mbti.".concat(choice).concat(".name"));
    }

    /**
     * The method is to generate characteristic of the same MBTI
     * @return characteristic of the same MBTI
     */
    public String characteristic() {
        return faker.resolve("mbti.".concat(choice).concat(".characteristic"));
    }

    /**
     * The method is to generate personage of the same MBTI
     * @return personage of the same MBTI
     */
    public String personage() {
        return faker.resolve("mbti.".concat(choice).concat(".personage"));
    }

    /**
     * The method is to generate merit of the same MBTI
     * @return merit of the same MBTI
     */
    public String merit(){return faker.resolve("mbti.".concat(choice).concat(".merit"));}

    /**
     * The method is to generate weakness of the same MBTI
     * @return weakness of the same MBTI
     */
    public String weakness(){return faker.resolve("mbti.".concat(choice).concat(".weakness"));}


}
