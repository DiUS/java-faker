package com.github.javafaker;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RelationshipTest extends AbstractFakerTest {
    
    @Test
    public void anyTest() {
        assertThat(faker.relationships().any(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void directTest() {
        assertThat(faker.relationships().direct(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void extendedTest() {
        assertThat(faker.relationships().extended(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void inLawTest() {
        assertThat(faker.relationships().inLaw(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void spouseTest() {
        assertThat(faker.relationships().spouse(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void parentTest() {
        assertThat(faker.relationships().parent(), not(isEmptyOrNullString()));
    }
    
    @Test
    public void siblingTest() {
        assertThat(faker.relationships().sibling(), not(isEmptyOrNullString()));
    }

}