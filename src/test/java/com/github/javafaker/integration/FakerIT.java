package com.github.javafaker.integration;

import com.github.javafaker.Faker;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.reflections.ReflectionUtils.*;

/**
 * The purpose of these tests is to ensure that the Locales have been properly configured
 * and that methods return values. The unit tests should ensure what the values returned
 * are correct. These tests just ensure that the methods can be invoked.
 */
@RunWith(value = Parameterized.class)
public class FakerIT {

    private static final Logger logger = LoggerFactory.getLogger(FakerIT.class);
    private final Locale locale;
    private final Faker faker;

    /**
     * a collection of Locales -> Exceptions.
     * In the case of 'pt', city_prefix is '' by design. This test fails because it's testing that all string returning
     * methods return a non blank string. But pt city_prefix is blank ,but the test shouldn't fail. So we add put 
     * exceptions like this into this collection.
     */
    private static final Map<Locale, List<String>> exceptions = Maps.newHashMap();
    static {
        // 'it' has an empty suffix list so it never returns a value
        exceptions.put(new Locale("it"), Arrays.asList("Name.suffix"));
        exceptions.put(new Locale("es-mx"), Arrays.asList("Address.cityPrefix", "Address.citySuffix"));
        exceptions.put(new Locale("pt"), Arrays.asList("Address.cityPrefix", "Address.citySuffix"));
        exceptions.put(new Locale("uk"), Arrays.asList("Address.stateAbbr", "Address.streetSuffix",
                "Address.cityPrefix", "Address.citySuffix"));
    }

    public FakerIT(Locale locale, Random random) {
        this.locale = locale;
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
                {Locale.ENGLISH, new Random()},
                {new Locale("pt-BR"), null},
                {new Locale("pt-br"), null},
                {new Locale("Pt_br"), null},
                {new Locale("pT_Br"), null},
                {new Locale("pt","Br","x2"), null},
                {null, new Random()},
                {null, null}};

        String[] ymlFiles = new File("./src/main/resources").list();
        int numberOfYmlFiles = ymlFiles.length;
        Object[][] dataFromYmlFiles = new Object[numberOfYmlFiles][2];
        for (int i = 0; i < numberOfYmlFiles; i++) {
            String ymlFileName = ymlFiles[i];
            dataFromYmlFiles[i][0] = new Locale(StringUtils.substringBefore(ymlFileName, "."));
        }

        List<Object[]> allData = new ArrayList<Object[]>(Arrays.asList(data));
        allData.addAll(Arrays.asList(dataFromYmlFiles));
        return allData;
    }

    @Test
    public void testAllFakerMethodsThatReturnStrings() throws Exception {
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker);
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.ancient());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.address());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.app());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.business());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.book());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.chuckNorris());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.color());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.commerce());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.company());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.crypto());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.educator());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.internet());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.lorem());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.phoneNumber());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.pokemon());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.music());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.name());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.file());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.finance());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.food());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.gameOfThrones());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.hacker());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.idNumber());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.shakespeare());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.slackEmoji());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.space());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.stock());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.superhero());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.team());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.beer());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.university());
        testAllMethodsThatReturnStringsActuallyReturnStrings(faker.cat());
    }

    private void testAllMethodsThatReturnStringsActuallyReturnStrings(Object object) throws IllegalAccessException, InvocationTargetException {
        @SuppressWarnings("unchecked")
        Set<Method> methodsThatReturnStrings = getAllMethods(object.getClass(),
                withModifier(Modifier.PUBLIC),
                withReturnType(String.class),
                withParametersCount(0));

        for (Method method : methodsThatReturnStrings) {
            if (isExcepted(object, method)) {
                continue;
            }
            final Object returnValue = method.invoke(object);
            logger.info(String.format("%s %s.%s = %s", locale, object.getClass().getSimpleName().toLowerCase(), method.getName(), returnValue));
            assertThat(method + " on " + object, returnValue, is(notNullValue()));
            assertThat(method + " on " + object, (String) returnValue, not(isEmptyString()));
        }
    }

    private boolean isExcepted(Object object, Method method) {
        final List<String> classDotMethod = exceptions.get(this.locale);
        if (classDotMethod == null) {return false;}
        return classDotMethod.contains(object.getClass().getSimpleName() + "." + method.getName());
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

        assertThat(faker.address().streetAddress(), is(notNullValue()));

        assertThat(faker.lorem().words(), is(notNullValue()));
        assertThat(faker.lorem().words(1), is(notNullValue()));
    }

}
