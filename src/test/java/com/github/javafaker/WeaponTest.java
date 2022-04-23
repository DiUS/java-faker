package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class WeaponTest extends AbstractFakerTest {

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void getWeapon(){
        assertThat(faker.weapon().getWeapon(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void provenance(){
        assertThat(faker.weapon().provenance(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void user(){
        assertThat(faker.weapon().user(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void function(){
        assertThat(faker.weapon().function(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void target(){
        assertThat(faker.weapon().target(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
     */
    @Test
    public void type(){
        assertThat(faker.weapon().type(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/]+"));
    }

}