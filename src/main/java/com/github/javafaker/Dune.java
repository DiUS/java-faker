package com.github.javafaker;

public class Dune {

    private final Faker faker;

    protected Dune(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("dune.characters", this, faker);
    }

    public String title() {
        return faker.fakeValuesService().resolve("dune.titles", this, faker);
    }

    public String planet() {
        return faker.fakeValuesService().resolve("dune.planets", this, faker);
    }

    public String quote() {
        return quote(faker.options().option(Dune.Quote.class));
    }

    public String quote(Quote quote) {
        return faker.fakeValuesService().resolve("dune.quotes." + quote.yamlKey, this, faker);
    }

    public String saying() {
        return saying(faker.options().option(Dune.Saying.class));
    }

    public String saying(Saying saying) {
        return faker.fakeValuesService().resolve("dune.sayings." + saying.yamlKey, this, faker);
    }

    public enum Quote {
        GUILD_NAIVGATOR("guild_navigator"),
        EMPEROR("emperor"),
        PAUL("paul"),
        THUFIR("thufir"),
        JESSICA("jessica"),
        IRULAN("irulan"),
        MOHIAM("mohiam"),
        GURNEY("gurney"),
        LETO("leto"),
        STILGAR("stilgar"),
        LIET_KYNES("liet_kynes"),
        PARDOT_KYNES("pardot_kynes"),
        BARON_HARKONNEN("baron_harkonnen"),
        PITER("piter"),
        ALIA("alia"),
        MAPES("mapes"),
        DUNCAN("duncan"),
        YUEH("yueh");

        private final String yamlKey;

        Quote(String yamlKey) {
            this.yamlKey = yamlKey;
        }
    }

    public enum Saying {
        BENE_GESSERIT("bene_gesserit"),
        FREMEN("fremen"),
        MENTAT("mentat"),
        MUADDIB("muaddib"),
        ORANGE_CATHOLIC_BIBLE("orange_catholic_bible");

        private final String yamlKey;

        Saying(String yamlKey) {
            this.yamlKey = yamlKey;
        }
    }

}
