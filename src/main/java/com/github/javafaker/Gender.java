package com.github.javafaker;

public class Gender {

    private final Faker faker;

    protected Gender(Faker faker) { this.faker = faker; }

    /**
     * <p>
     *      A gender which maybe special. Examples:
     *      <ul>
     *          <li>Non-binary</li>
     *          <li>Genderfluid</li>
     *      </ul>
     * </p>
     * @return a random gender.
     */
    public String types() { return faker.fakeValuesService().resolve("gender.types", this, faker); }

    /**
     * <p>
     *      A gender which if male or female. Examples:
     *      <ul>
     *          <li>male</li>
     *          <li>female</li>
     *      </ul>
     * </p>
     * @return a random binary gender.
     */
    public String binarytypes() {
        return faker.fakeValuesService().resolve("gender.binary_types", this, faker);
    }}