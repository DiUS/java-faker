package com.github.javafaker;

/**
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/721
 * A class for generating random swift code
 */

public class SwiftCode {

  private final Faker faker;

  protected SwiftCode(final Faker faker) {
    this.faker = faker;
  }

  /**
  * The
  * @return a random swift code of a bank's headquarter as a string value
  */
  public String headquarterSwiftcode() {
    String chars1 = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
    String chars2 = "0123456789ABCDEFGHIZKLMNOPQRSTUVWXYZ";
    StringBuffer swiftcode = new StringBuffer();
    for (int i = 0; i < 4; i++) {
      String bank = String.valueOf(chars1.charAt((int) (Math.random() * 26)));
      swiftcode.append(bank);
    }
    String country = faker.address().countryCode();
    swiftcode.append(country);
    for (int i = 0; i < 2; i++) {
      String region = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
      swiftcode.append(region);
    }
    swiftcode.append("XXX");
    return swiftcode.toString();
  }

  /**
   *
   * @return a random swift code of a bank's branch as a string value
   */
  public String branchSwiftcode() {
    String chars1 = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
    String chars2 = "0123456789ABCDEFGHIZKLMNOPQRSTUVWXYZ";
    StringBuffer swiftcode = new StringBuffer();
    for (int i = 0; i < 4; i++) {
      String bank = String.valueOf(chars1.charAt((int) (Math.random() * 26)));
      swiftcode.append(bank);
    }
    String country = faker.address().countryCode();
    swiftcode.append(country);
    for (int i = 0; i < 2; i++) {
      String region = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
      swiftcode.append(region);
    }
    for (int i = 0; i < 3; i++) {
      String branch = String.valueOf(chars2.charAt((int) (Math.random() * 36)));
      swiftcode.append(branch);
    }
    return swiftcode.toString();
  }
}
