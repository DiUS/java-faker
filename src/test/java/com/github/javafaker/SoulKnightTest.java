package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;


public class SoulKnightTest extends AbstractFakerTest{
    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void charactersTest(){
        assertThat(faker.soulKnight().characters(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void charactersNotNullTest(){
        assertThat(faker.soulKnight().characters(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void buffsTest(){
        assertThat(faker.soulKnight().buffs(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void buffsNotNullTest(){
        assertThat(faker.soulKnight().buffs(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void statuesTest(){
        assertThat(faker.soulKnight().statues(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void statuesNotNullTest(){
        assertThat(faker.soulKnight().statues(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void weaponsTest(){
        assertThat(faker.soulKnight().weapons(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void weaponsNotNullTest(){
        assertThat(faker.soulKnight().weapons(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void bossesTest(){
        assertThat(faker.soulKnight().bosses(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void bossesNotNullTest(){
        assertThat(faker.soulKnight().bosses(),not(isEmptyOrNullString()));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void enemiesTest(){
        assertThat(faker.soulKnight().enemies(), matchesRegularExpression("[a-zA-Z0-9\\-\\.\\ \\/\\(\\)\\(\\)\\']+"));
    }

    /**
     * CS304 issue link:https://github.com/DiUS/java-faker/issues/723
     */
    @Test
    public void enemiesNotNullTest(){
        assertThat(faker.soulKnight().enemies(),not(isEmptyOrNullString()));
    }
}