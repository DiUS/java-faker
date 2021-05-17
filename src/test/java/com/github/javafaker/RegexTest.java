package com.github.javafaker;
import org.junit.Test;
import static org.junit.Assert.*;
//CS304 issue link: https://github.com/DiUS/java-faker/issues/486
public class RegexTest extends AbstractFakerTest{
    @Test
    public void regexTest_1() {
        System.out.println(faker.regexify("[\\w\\d]{5}"));
        System.out.println(faker.regexify("\\w\\d]{5}"));
        System.out.println(faker.regexify("[\\w\\d{5}"));
        System.out.println(faker.regexify("\\w\\d{5}"));
    }
 @Test
    public void regexTest_2() {
        System.out.println(faker.regexify("(\\w\\d){5}"));
        System.out.println(faker.regexify("(\\w|\\d){5}"));
    }
}
