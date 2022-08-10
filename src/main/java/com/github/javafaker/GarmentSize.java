package com.github.javafaker;


/**
 * This class is used to generate garments sizes randomly.
 *
 */

public class GarmentSize {
  private final Faker faker;

  protected GarmentSize(Faker faker) {
    this.faker = faker;
  }

  /**
   * This method returns a garment size
   *
   * @return a string of garment size
   */
  public String size() {
    return faker.fakeValuesService().fetchString("garments.sizes");
  }


}
