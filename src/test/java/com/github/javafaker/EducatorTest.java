package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class EducatorTest extends AbstractFakerTest {

    @Test
    public void testUniversity() {
        assertThat(faker.educator().university(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testCourse() {
        assertThat(faker.educator().course(), matchesRegularExpression("(\\(?\\w+\\)? ?){3,6}"));
    }

    @Test
    public void testSecondarySchool() {
        assertThat(faker.educator().secondarySchool(), matchesRegularExpression("(\\w+ ?){2,3}"));
    }

    @Test
    public void testCampus() {
        assertThat(faker.educator().campus(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }
}
