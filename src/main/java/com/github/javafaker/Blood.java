package com.github.javafaker;

/**
 * This class is used to generate blood type randomly.
 *
 * @author Tang
 */

public class Blood{

    private final Faker faker;

    protected Blood(Faker faker) {
        this.faker = faker;
    }
    /**
     * This method returns a blood type
     * generate blood type according to the probability
     * @return a string of blood type
     */
    public String types() {
        final double d = Math.random();
        final int i = (int)(d*10000000);
        if (i < 10) {
            return faker.fakeValuesService().fetchString("blood.P_types");
        }
        else if (i < 100) {
            return faker.fakeValuesService().fetchString("blood.Rh_types");
        }
        else {
            return faker.fakeValuesService().fetchString("blood.ABO_types");
        }
    }

    /**
     * This method returns a ABO blood type
     *
     * @return a string of ABO blood type
     */
    public String ABO_Types() {
        return faker.fakeValuesService().fetchString("blood.ABO_types");
    }

    /**
     * This method returns a Rh blood type
     *
     * @return a string of Rh blood type
     */
    public String Rh_Types() {
        return faker.fakeValuesService().fetchString("blood.Rh_types");
    }

    /**
     * This method returns a P blood type
     *
     * @return a string of P blood type
     */
    public String P_Types() {
        return faker.fakeValuesService().fetchString("blood.P_types");
    }
}