package com.github.javafaker;

public class DragonBall {
    private final Faker faker;

    protected DragonBall(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("dragon_ball.characters", this, faker);
    }
}
