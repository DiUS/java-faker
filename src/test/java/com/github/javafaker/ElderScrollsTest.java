

package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class ElderScrollsTest extends AbstractFakerTest  {

    @Test
    public void testCity() {
        assertThat(faker.elderScrolls().city(), not(isEmptyOrNullString()));
    }

    @Test
    public void testCreature() {
        assertThat(faker.elderScrolls().creature(), not(isEmptyOrNullString()));
    }

    @Test
    public void testDragon() {
        assertThat(faker.elderScrolls().dragon(), not(isEmptyOrNullString()));
    }

    @Test
    public void testFirstName() {
        assertThat(faker.elderScrolls().firstName(), not(isEmptyOrNullString()));
    }

    @Test
    public void testLastName() {
        assertThat(faker.elderScrolls().lastName(), not(isEmptyOrNullString()));
    }

    @Test
    public void testRace() {
        assertThat(faker.elderScrolls().race(), not(isEmptyOrNullString()));
    }

    @Test
    public void testRegion() {
        assertThat(faker.elderScrolls().region(), not(isEmptyOrNullString()));
    }
}