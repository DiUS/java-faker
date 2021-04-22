package com.github.javafaker;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
public class passwordTest extends AbstractFakerTest {
    @Test
    public void password(){
        for (int i=0;i<100;i++){
            String password = faker.internet().password(8, 16, true, true, true);

            Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

            Pattern digitPattern = Pattern.compile("[0-9]");
            Matcher digitMatcher = digitPattern.matcher(password);

            boolean isPasswordContainsSpecialCharacter = specialCharacterMatcher.find();
            boolean isPasswordContainsDigit = digitMatcher.find();


            System.out.println(isPasswordContainsSpecialCharacter + " " + isPasswordContainsDigit + " " + password);
        }
    }
}
