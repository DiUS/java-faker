package com.github.javafaker.foods;

import com.github.javafaker.Faker;
import com.github.javafaker.idnumbers.SvSEIdNumber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HebrewFoodTest {
    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/598

    public final String matchHebrewFood = "[\\u0590-\\u05FF ']+";
    public static Faker food;

    @Before
    public void before(){
        food = new Faker(new Locale("he"));
    }

    @Test
    public void hebrewIngredient() {
        Assert.assertThat(food.food().ingredient(), matchesRegularExpression(matchHebrewFood));
    }

    @Test
    public void hebrewFruit() {
        Assert.assertThat(food.food().fruit(), matchesRegularExpression(matchHebrewFood));
    }

    @Test
    public void hebrewVegetable() {
        Assert.assertThat(food.food().vegetable(), matchesRegularExpression(matchHebrewFood));
    }

    @Test
    public void hebrewSpice() {
        Assert.assertThat(food.food().spice(), matchesRegularExpression(matchHebrewFood));
    }

    @Test
    public void hebrewSushi() {
        Assert.assertThat(food.food().sushi(), matchesRegularExpression(matchHebrewFood));
    }
}
