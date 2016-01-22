package com.github.javafaker.integration;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withParametersCount;
import static org.reflections.ReflectionUtils.withReturnType;

/**
 * The purpose of these tests is to ensure that the Locales have been properly configured
 * and that methods return values. The unit tests should ensure what the values returned
 * are correct. These tests just ensure that the methods can be invoked.
 */
@RunWith(value = Parameterized.class)
public class FakerIT {

    private static final Locale FINNISH_LOCALE = new Locale("fi", "FI");
    private static final Logger logger = LoggerFactory.getLogger(FakerIT.class);
    private Faker faker;

    public FakerIT(Locale locale, Random random) {
        if (locale != null && random != null) {
            faker = new Faker(locale, random);
        } else if (locale != null) {
            faker = new Faker(locale);
        } else if (random != null) {
            faker = new Faker(random);
        } else {
            faker = new Faker();
        }
    }

    @Parameterized.Parameters(name = "testing locale {0} and random {1}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {Locale.ENGLISH, null},
                {Locale.GERMAN, null},
                {Locale.FRENCH, null},
                {new Locale("pt"), null},
                {FINNISH_LOCALE, null},
                {Locale.ENGLISH, new Random()},
                {Locale.GERMAN, new Random()},
                {null, new Random()},
                {null, null}};
        return Arrays.asList(data);
    }

    @Test
    public void testAllFakerMethodsThatReturnStrings() throws Exception {
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker);
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.address());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.business());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.internet());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.lorem());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.phoneNumber());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.name());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.finance());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.country());
    }


    private void testAllMethodsThatReturnStringsActuallyReturnStrings(Object object) throws IllegalAccessException, InvocationTargetException {
        Set<Method> methodsThatReturnStrings = getAllMethods(object.getClass(),
                withModifier(Modifier.PUBLIC),
                withReturnType(String.class),
                withParametersCount(0));

        for (Method method : methodsThatReturnStrings) {
            final Object returnValue = method.invoke(object);

            logger.info(String.format("Invoked %s.%s = %s", object.getClass().getSimpleName().toLowerCase(), method.getName(), returnValue));
            assertThat(returnValue, is(notNullValue()));
        }
    }

    @Test
    public void testExceptionsNotCoveredInAboveTest() throws Exception {
        assertThat(faker.bothify("####???"), is(notNullValue()));
        assertThat(faker.letterify("????"), is(notNullValue()));
        assertThat(faker.numerify("####"), is(notNullValue()));

        assertThat(faker.lorem().paragraph(1), is(notNullValue()));
        assertThat(faker.lorem().paragraphs(1), is(notNullValue()));

        assertThat(faker.lorem().sentence(1), is(notNullValue()));
        assertThat(faker.lorem().sentences(1), is(notNullValue()));

        assertThat(faker.address().streetAddress(true), is(notNullValue()));

        assertThat(faker.lorem().words(), is(notNullValue()));
        assertThat(faker.lorem().words(1), is(notNullValue()));
    }
}
