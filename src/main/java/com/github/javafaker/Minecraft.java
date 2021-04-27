package com.github.javafaker;

import java.util.Random;

public class Minecraft {
	private final Faker faker;
	private Random rand = new Random();

	public Minecraft(Faker faker) {
		this.faker = faker;
	}

	public String itemName() {
		return faker.fakeValuesService().resolve("minecraft.item_name", this, faker);
	}

	public String tileName() {
		return faker.fakeValuesService().resolve("minecraft.tile_name", this, faker);
	}

	public String entityName() {
		return faker.fakeValuesService().resolve("minecraft.entity_name", this, faker);
	}

	public String monsterName() {
		return faker.fakeValuesService().resolve("minecraft.monster_name", this, faker);
	}

	public String animalName() {
		return faker.fakeValuesService().resolve("minecraft.animal_name", this, faker);
	}

	public String tileItemName() {
		return rand.nextBoolean() ? itemName() : tileName();
	}
}
