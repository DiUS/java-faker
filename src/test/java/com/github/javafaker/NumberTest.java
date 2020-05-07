package com.github.javafaker;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;

public class NumberTest {
    @Test
    public void satisfyIssueTest(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        outer: for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j < 100000; j++) {
                int r = fake.number().numberBetween(0, i);
                if (r == i-1) {
                    continue outer;
                }
            }
            isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
    @Test
    public void generateNumberAboveZeroTest(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(0, 10);
            if(r<0||r>=10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
    @Test
    public void generateNumberAboveOrBelowZero(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(-10, 10);
            if(r<-10||r>=10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
    @Test
    public void generateNumberBelowZero(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(-20, -10);
            if(r<-20||r>=-10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }

    @Test
    public void generateDecimalAboveZeroTest(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(0, 10);
            if(r<0||r>=10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
    @Test
    public void generateDecimalAboveOrBelowZero(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(-10, 10);
            if(r<-10||r>=10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
    @Test
    public void generateDecimalBelowZero(){
        Faker fake = new Faker();
        boolean isSatisfied =true;
        for(int i=0;i<1000;i++){
            int r = fake.number().numberBetween(-20, -10);
            if(r<-20||r>=-10)isSatisfied=false;
        }
        Assert.assertTrue(isSatisfied);
    }
}
