package com.github.javafaker;


import org.junit.Test;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class GarmentSizeTest extends AbstractFakerTest {

  @Test
  public void sizes() {
    assertThat(faker.garmentSize().size(), matchesRegularExpression("([A-Z]+)"));
  }
}