package com.github.javafaker;

import com.github.javafaker.AbstractFakerTest;
import com.github.javafaker.Faker;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

public class BojackHorsemanTest extends AbstractFakerTest {

    @Test
    public void testCharacters1(){
        Faker faker=new Faker();
        assertThat(faker.bojackHorseman().characters(),matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+")); }

    @Test
    public void testQuotes1(){
        Faker faker=new Faker();
        assertFalse(faker.bojackHorseman().quotes().isEmpty()); }

    @Test
    public void testTongueTwisters1(){
        Faker faker=new Faker();
        assertFalse(faker.bojackHorseman().tongueTwisters().isEmpty());}

    @Test
    public void testCharactersWith10000Times(){
        Faker faker=new Faker();
        boolean isExist=false;
        for(int i=0;i<10000;i++){
            String generateString=faker.bojackHorseman().characters();
            if(generateString.equals("Joseph Sugarman")){isExist=true;}
        }
       assertTrue(isExist);
    }

    @Test
    public void testQuotesWith10000Times(){
        Faker faker=new Faker();
        boolean isExist=false;
        for(int i=0;i<10000;i++){
            String generateString=faker.bojackHorseman().quotes();
            if(generateString.equals("It gets easier. But you have to do it every day, that's the hard part. But it does get easier"))
                {isExist=true;}
        }
        assertTrue(isExist);
    }

    @Test
    public void testTongueTwistersWith10000Times(){
        Faker faker=new Faker();
        boolean isExist=false;
        for(int i=0;i<10000;i++){
            String generateString=faker.bojackHorseman().tongueTwisters();
            if(generateString.equals("Courtly roles like the formerly portly consort are Courtney Portnoy's forté"))
                {isExist=true;}
        }
        assertTrue(isExist);
    }

}
