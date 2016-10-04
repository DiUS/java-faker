package com.github.javafaker.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CountOfCharactersMatcher extends TypeSafeMatcher<String> {

  private final char character;
  private Matcher<Integer> matcher;

  public CountOfCharactersMatcher(char character, Matcher<Integer> matcher) {
    this.character = character;
    this.matcher = matcher;
  }

  @Factory
  public static <T> Matcher<String> countOf(char character, Matcher<Integer> matcher) {
    return new CountOfCharactersMatcher(character, matcher);
  }

  @Override
  protected boolean matchesSafely(String item) {
    int count = count(item);
    return matcher.matches(count);
  }

  private int count(String item) {
    int count = 0;
    for (char c : item.toCharArray()) {
      count += (c == character) ? 1 : 0;
    }
    return count;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("count of " + character + " ").appendDescriptionOf(matcher);
  }

  @Override
  protected void describeMismatchSafely(String item, Description mismatchDescription) {
    mismatchDescription.appendText("count of " + character + " ");
    matcher.describeMismatch(count(item), mismatchDescription);
  }
}
