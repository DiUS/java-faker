package com.github.javafaker;

public class LeagueOfLegends {
    private final Faker faker;

    protected LeagueOfLegends(Faker faker) {
        this.faker = faker;
    }

    public String champion() {
        return faker.fakeValuesService().resolve("league_of_legends.champion", this, faker);
    }

    public String location() {
        return faker.fakeValuesService().resolve("league_of_legends.location", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("league_of_legends.quote", this, faker);
    }

    public String summonerSpell() {
        return faker.fakeValuesService().resolve("league_of_legends.summoner_spell", this, faker);
    }

    public String masteries() {
        return faker.fakeValuesService().resolve("league_of_legends.masteries", this, faker);
    }

    public String rank() {
        return faker.fakeValuesService().resolve("league_of_legends.rank", this, faker);
    }
}
