package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class WeaponTest extends AbstractFakerTest {

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void getWeaponTest(){
        assertThat(faker.weapon().getWeapon(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void provenanceTest(){
        assertThat(faker.weapon().provenance(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void provenanceNotNullTest(){
        assertThat(faker.weapon().provenance(),not(isEmptyOrNullString()));
    }


    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void userTest(){
        assertThat(faker.weapon().user(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void userNotNullTest(){
        assertThat(faker.weapon().user(),not(isEmptyOrNullString()));
    }


    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void functionTest(){
        assertThat(faker.weapon().function(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void functionNotNullTest(){
        assertThat(faker.weapon().function(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void targetTest(){
        assertThat(faker.weapon().target(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void targetNotNullTest(){
        assertThat(faker.weapon().target(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void typeTest(){
        assertThat(faker.weapon().type(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void typeNotNullTest(){
        assertThat(faker.weapon().type(),not(isEmptyOrNullString()));
    }

}