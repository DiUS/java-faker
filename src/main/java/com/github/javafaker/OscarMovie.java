package com.github.javafaker;

/**
 * This is the class generates random stream of Movie
 * @author ak-maker
 * */
public class OscarMovie {
    /**
     * The faker instance for generating random names of things.
     */
    private final Faker faker;
    /**
     * The year instance is used for randomly choose the year (from 2013 to 2022)
     */
    private final String year;
    /**
     * The choice instance eis used for randomly choose the the
     * first/second/third movie of that year.
     */
    private final String choice;
    /**
     * Part of the string to reslove
     */
    private final String str;
    /**
     * This is the constructor initialize faker and two other
     * variable for random generation.
     * @param faker faker The Faker instance for generating random names of things.
     */
    protected OscarMovie(final Faker faker) {
        this.faker = faker;
        this.year = this.faker.resolve("OscarMovie.year.years");
        this.choice = this.faker.resolve("OscarMovie.year.choice");
        this.str = "OscarMovie.".concat(year).concat(".").concat(choice);
    }

    /**
     * @return year
     */
    public String getYear(){
        return year;
    }

    /**
     * @return choice
     */
    public String getChoice(){
        return choice;
    }

    /**
     * @return str
     */
    public String getStr(){
        return str;
    }

    /**
     * This method generates random actor
     * @return random actor from OscarMovie.yml
     */
    public String actor() {
        return faker.resolve(str.concat(".actor"));
    }

    /**
     * This method generates random movieName
     * @return random movieName from OscarMovie.yml
     */
    public String movieName() {
        return faker.resolve(str.concat(".movieName"));
    }

    /**
     * This method generates random quote
     * @return random quote from OscarMovie.yml
     */
    public String quote() {
        return faker.resolve(str.concat(".quote"));
    }
    /**
     * This method generates random character
     * @return random character from OscarMovie.yml
     */
    public String character() {
        return faker.resolve(str.concat(".character"));
    }

    /**
     * This method enerates random releaseDate
     * @return random releaseDate from OscarMovie.yml
     */
    public String releaseDate() {
        return faker.resolve(str.concat(".releaseDate"));
    }
}
