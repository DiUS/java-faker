package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RandomFakerTest extends AbstractFakerTest {

    private static final int CONSTANT_SEED_VALUE = 10;
    private Faker faker;
    private Random random;

    @Before
    public void before() {
        super.before();
        random = new Random();
        faker = new Faker(random);
    }

    @Test
    public void testNumerifyRandomnessCanBeControlled() {
        resetRandomSeed();
        final String firstInvocation = faker.numerify("###");

        resetRandomSeed();
        final String secondInvocation = faker.numerify("###");
        assertThat(firstInvocation, is(secondInvocation));
    }

    @Test
    public void testLetterifyRandomnessCanBeControlled() {
        resetRandomSeed();
        final String firstInvocation = faker.letterify("???");

        resetRandomSeed();
        final String secondInvocation = faker.letterify("???");
        assertThat(firstInvocation, is(secondInvocation));
    }

    @Test
    public void testNameRandomnessCanBeControlled() {
        resetRandomSeed();
        final String firstInvocation = faker.name().name();

        resetRandomSeed();
        final String secondInvocation = faker.name().name();
        assertThat(firstInvocation, is(secondInvocation));
    }

    @Test
    public void testEmailRandomnessCanBeControlled() {
        resetRandomSeed();
        final String firstInvocation = faker.internet().emailAddress();

        resetRandomSeed();
        final String secondInvocation = faker.internet().emailAddress();
        assertThat(firstInvocation, is(secondInvocation));
    }

    private void resetRandomSeed() {
        random.setSeed(CONSTANT_SEED_VALUE);
    }
}
