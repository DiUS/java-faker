package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class SwiftCodeTest extends AbstractFakerTest {

    /**
     *
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/721
     */
    @Test
    @Repeat(times = 100)
    public void headquarterSwiftCodeTest() {
        String swiftcode = faker.swiftCode().headquarterSwiftcode();
        assertThat(swiftcode, matchesRegularExpression("[0-9A-Z ]+"));
    }

    /**
     *
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/721
     */
    @Test
    @Repeat(times = 100)
    public void headquarterSwiftCodeNotNull() {
        assertThat(faker.swiftCode().headquarterSwiftcode(), not(isEmptyOrNullString()));
    }

    /**
     *
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/721
     */
    @Test
    @Repeat(times = 100)
    public void BranchSwiftCodeTest() {
        String swiftcode = faker.swiftCode().branchSwiftcode();
        assertThat(swiftcode, matchesRegularExpression("[0-9A-Z ]+"));
    }

    /**
     *
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/721
     */
    @Test
    @Repeat(times = 100)
    public void branchSwiftCodeNotNull() {
        assertThat(faker.swiftCode().branchSwiftcode(), not(isEmptyOrNullString()));
    }
}
