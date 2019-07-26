package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class WitcherTest extends AbstractFakerTest {
    
    @Test
    public void testCharacter() {
        assertThat(faker.witcher().character(), matchesRegularExpression("[A-Za-z' -éúï]+"));
    }
    
    @Test
    public void testWitcher() {
        assertThat(faker.witcher().character(), matchesRegularExpression("[A-Za-z -ëúï]+"));
    }
    
    @Test
    public void testSchool() {
        assertThat(faker.witcher().school(), matchesRegularExpression("[A-Za-z]+"));
    }
    
    @Test
    public void testLocation() {
        assertThat(faker.witcher().location(), matchesRegularExpression("[A-Za-z -áâé]+"));
    }
    
    @Test
    public void testQuote() {
        assertThat(faker.witcher().quote(), matchesRegularExpression("[-A-Za-z0-9 —;…\\?\\!\\.’‘'”“,\\[\\]]+"));
    }
    
    @Test
    public void testMonster() {
        assertThat(faker.witcher().monster(), matchesRegularExpression("[A-Za-z -]+"));
    }
}
