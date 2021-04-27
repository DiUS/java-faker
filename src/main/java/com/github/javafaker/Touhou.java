package com.github.javafaker;

public class Touhou {

	private final Faker faker;

	protected Touhou(Faker faker) {
		this.faker = faker;
	}

	public String characterName() {
		return faker.fakeValuesService().resolve("touhou.full_name", this, faker);
	}

	public String characterFirstName() {
		return faker.fakeValuesService().resolve("touhou.first_name", this, faker);
	}

	public String characterLastName() {
		return faker.fakeValuesService().resolve("touhou.last_name", this, faker);
	}

	public String trackName() {
		return faker.fakeValuesService().resolve("touhou.track_name", this, faker);
	}

	public String gameName() {
		return faker.fakeValuesService().resolve("touhou.game_name", this, faker);
	}
}
