package net.datafaker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Strings.isNullOrEmpty;
public class OscarMovieTest extends AbstractFakerTest{
    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/712 https://github.com/DiUS/java-faker/issues/713
    @Test
    public void actor() {
        assertThat(faker.oscarMovie().actor()).matches("[A-Za-z,\\-.() ]+");
    }
    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/712 https://github.com/DiUS/java-faker/issues/713
    @Test
    public void movieName() {
        assertThat(isNullOrEmpty(faker.oscarMovie().movieName())).isFalse();
    }
    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/712 https://github.com/DiUS/java-faker/issues/713
    @Test
    public void quote() {
        assertThat(isNullOrEmpty(faker.oscarMovie().quote())).isFalse();
    }
    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/712 https://github.com/DiUS/java-faker/issues/713
    @Test
    public void character() {
        assertThat(faker.oscarMovie().actor()).matches("[A-Za-z,\\- ]+");
    }
    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/712 https://github.com/DiUS/java-faker/issues/713
    @Test
    public void releaseDate() {
        assertThat(faker.oscarMovie().actor()).matches("[A-Za-z,0-9\\-.() ]+");
    }
}
