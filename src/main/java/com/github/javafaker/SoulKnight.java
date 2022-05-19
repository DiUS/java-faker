package com.github.javafaker;

/**
 * @author zhou mintao
 * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
 * A class for generating random value of soul Knight
 */

public class SoulKnight {
    private final Faker faker;

    protected SoulKnight(final Faker faker){
        this.faker = faker;
    }

    /**
     * @return a random value of characters
     */

    public String characters(){
        String characters = faker.resolve("soulKnight.characters");
        return characters;
    }

    /**
     * @return a random value of buffs
     */

    public String buffs(){
        String buffs = faker.resolve("soulKnight.buffs");
        return buffs;
    }

    /**
     * @return a random value of statues
     */

    public String statues(){
        String statues = faker.resolve("soulKnight.statues");
        return statues;
    }

    /**
     * @return a random value of weapons
     */

    public String weapons(){
        String weapons = faker.resolve("soulKnight.weapons");
        return weapons;
    }

    /**
     * @return a random value of bosses
     */

    public String bosses(){
        String bosses = faker.resolve("soulKnight.bosses");
        return bosses;
    }

    /**
     * @return a random value of enemies
     */

    public String enemies(){
        String enemies = faker.resolve("soulKnight.enemies");
        return enemies;
    }

}
