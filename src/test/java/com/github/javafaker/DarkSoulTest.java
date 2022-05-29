package com.github.javafaker;

import com.github.javafaker.matchers.MatchesRegularExpression;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class DarkSoulTest extends AbstractFakerTest {

    /**
     * Basic test for generating classes.
     */
    @Test
    public void classes(){
        assertThat(faker.darkSoul().classes(),matchesRegularExpression("[A-Za-z]+"));
    }

    /**
     * Basic test for checking whether generating-classes is empty.
     */
    @Test
    public void classesNotNull(){
        assertThat(faker.darkSoul().classes(),not(isEmptyOrNullString()));
    }

    /**
     * Basic test for generating weapon.
     */
    @Test
    public void weapon(){
        for (int i = 0; i < 1000; i++) {
            assertThat(faker.darkSoul().weapon(), matchesRegularExpression("[A-Za-z' ()-]+"));
        }

    }

    /**
     * Basic test for checking whether generating-weapon is empty.
     */
    @Test
    public void weaponNotNull(){
        assertThat(faker.darkSoul().weapon(),not(isEmptyOrNullString()));
    }

    /**
     * Basic test for generating shield.
     */
    @Test
    public void shield(){
        for (int i = 0; i < 1000; i++) {
            assertThat(faker.darkSoul().shield(), matchesRegularExpression("[A-Za-z' -]+"));
        }

    }

    /**
     * Basic test for checking whether generating-shield is empty.
     */
    @Test
    public void shieldNotNull(){
        assertThat(faker.darkSoul().shield(),not(isEmptyOrNullString()));
    }


    /**
     * Basic test for generating stats.
     */
    @Test
    public void stats(){
        for (int i = 0; i < 1000; i++) {
            assertThat(faker.darkSoul().stats(), matchesRegularExpression("[A-Za-z' ]+"));
        }

    }

    /**
     * Basic test for checking whether generating-stats is empty.
     */
    @Test
    public void statsNotNull(){
        assertThat(faker.darkSoul().stats(),not(isEmptyOrNullString()));
    }


    /**
     * Basic test for generating covenants.
     */
    @Test
    public void covenants(){
        for (int i = 0; i < 1000; i++) {
            assertThat(faker.darkSoul().covenants(), matchesRegularExpression("[A-Za-z' ]+"));
        }
    }

    /**
     * Basic test for checking whether generating-covenants is empty.
     */
    @Test
    public void covenantsNotNull(){
        assertThat(faker.darkSoul().covenants(),not(isEmptyOrNullString()));
    }
}
