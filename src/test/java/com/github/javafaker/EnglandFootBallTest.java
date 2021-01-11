package com.github.javafaker;


import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EnglandFootBallTest extends AbstractFakerTest{



    @Test
    public void testLeague() {
        String league = faker.englandfootball().league();
        assertThat(league, not(isEmptyOrNullString()));

    }

    @Test
    public void testTeam() {
        String team = faker.englandfootball().team();
        assertThat(team, not(isEmptyOrNullString()));

    }
}
