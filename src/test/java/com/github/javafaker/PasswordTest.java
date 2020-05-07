package com.github.javafaker;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class PasswordTest {
    @Test
    public void generateDigitPasswordTest(){
        boolean isSatisfied =true;
        for(int i=0;i<10000;i++){
            Faker faker = new Faker();
            String s=faker.internet().password(10, 25, true,true,true);
            boolean hasDigit=false;
            for(int j=0;j<s.length();j++){
                if (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    hasDigit = true;
                    break;
                }
            }
            if (!hasDigit){isSatisfied=false;break;}
        }
        Assert.assertTrue(isSatisfied);
    }

    @Test
    public void generatePasswordWithoutUppercaseTest(){
        boolean isSatisfied =true;
        for(int i=0;i<10000;i++){
            Faker faker = new Faker();
            String s=faker.internet().password(10, 25, false,true,true);
            boolean notHasUppercase=true;
            for(int j=0;j<s.length();j++){
                char ch=s.charAt(j);
                if (ch >='A'&& ch<='Z'){notHasUppercase=false;break;}
            }
            if (!notHasUppercase){isSatisfied=false;break;}
        }
        Assert.assertTrue(isSatisfied);
    }
}
