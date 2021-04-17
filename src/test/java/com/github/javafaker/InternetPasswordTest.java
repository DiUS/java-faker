package com.github.javafaker;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;

public class InternetPasswordTest {
    @Test
    public void testPassword1000(){
        Faker faker = new Faker();
        for (int i=0;i<1000;i++){
            String password = faker.internet().password(8, 16, true, true, true);

            Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

            Pattern digitPattern = Pattern.compile("[0-9]");
            Matcher digitMatcher = digitPattern.matcher(password);

            boolean isPasswordContainsSpecialCharacter = specialCharacterMatcher.find();
            boolean isPasswordContainsDigit = digitMatcher.find();

            assertTrue(isPasswordContainsDigit);
            assertTrue(isPasswordContainsSpecialCharacter);
        }
    }
}
