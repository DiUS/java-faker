package com.github.javafaker;

import org.apache.commons.lang3.StringUtils;

public class Name {
    
    private final Faker faker;

    /**
     * Internal constructor, not to be used by clients.  Instances of {@link Name} should be accessed via 
     * {@link Faker#name()}.
     */
    protected Name(Faker faker) {
        this.faker = faker;
    }

    /**
     * <p>
     *      A multipart name composed of an optional prefix, a firstname and a lastname
     *      or other possible variances based on locale.  Examples:
     *      <ul>
     *          <li>James Jones Jr.</li>
     *          <li>Julie Johnson</li>
     *      </ul>
     * </p>
     * @return a random name with given and family names and an optional suffix.
     */
    public String name() {
        return faker.fakeValuesService().resolve("name.name", this, faker);
    }

    /**
     * <p>
     *      A multipart name composed of an optional prefix, a given and family name,
     *      another 'firstname' for the middle name and an optional suffix such as Jr. 
     *      Examples:
     *      <ul>
     *          <li>Mrs. Ella Geraldine Fitzgerald</li>
     *          <li>Jason Tom Sawyer Jr.</li>
     *          <li>Helen Jessica Troy</li>
     *      </ul>
     * </p>
     * @return a random name with a middle name component with optional prefix and suffix
     */
    public String nameWithMiddle() {
        return faker.fakeValuesService().resolve("name.name_with_middle", this, faker);
    }

    /**
     * <p>Returns the same value as {@link #name()}</p>
     * @see Name#name() 
     */
    public String fullName() {
        return name();
    }

    // CS427 Issue Link: https://github.com/DiUS/java-faker/issues/361
    private String getNameByMaxLength(String key, int maxNameLength) {
        String name = faker.fakeValuesService().resolve(key, this, faker);
        final int maxAttempts = 4272;  // max number of names in M/F/Surname
        int attemptNum = 1;
        // keep pulling names until we find one of the correct length
        while (name.length() > maxNameLength){
            name = faker.fakeValuesService().resolve(key, this, faker);
            attemptNum++;
            // abort if we've pulled more names than are present in the largest collection
            if (attemptNum > maxAttempts) {
                name = null;
                break;
            }
        }
        return name;
    }

    // CS427 Issue Link: https://github.com/DiUS/java-faker/issues/361
    private String firstName(int maxNameLength) {
        return getNameByMaxLength("name.first_name", maxNameLength);
    }

    // CS427 Issue Link: https://github.com/DiUS/java-faker/issues/361
    private String lastName(int maxNameLength) {
        return getNameByMaxLength("name.last_name", maxNameLength);
    }

    /**
     * <p>Returns a random 'given' name such as Aaliyah, Aaron, Abagail or Abbey</p>
     * @return a 'given' name such as Aaliyah, Aaron, Abagail or Abbey
     */
    public String firstName() {
        return faker.fakeValuesService().resolve("name.first_name", this, faker);
    }

    /**
     * <p>Returns a random last name such as Smith, Jones or Baldwin</p>
     * @return a random last name such as Smith, Jones or Baldwin
     */
    public String lastName() {
        return faker.fakeValuesService().resolve("name.last_name", this, faker);
    }

    /**
     * <p>Returns a name prefix such as Mr., Mrs., Ms., Miss, or Dr.</p>
     * @return a name prefix such as Mr., Mrs., Ms., Miss, or Dr.
     */
    public String prefix() {
        return faker.fakeValuesService().resolve("name.prefix", this, faker);
    }

    /**
     * <p>Returns a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM</p>
     * @return a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM
     */
    public String suffix() {
        return faker.fakeValuesService().resolve("name.suffix", this, faker);
    }

    /**
     * <p>
     *     A three part title composed of a descriptor level and job.  Some examples are :
     *     <ul>
     *         <li>(template) {descriptor} {level} {job}</li>
     *         <li>Lead Solutions Specialist</li>
     *         <li>National Marketing Manager</li>
     *         <li>Central Response Liaison</li>
     *     </ul>
     * </p>
     * @return a random three part job title
     */
    public String title() {
        return StringUtils.join(new String[] {
            faker.fakeValuesService().resolve("name.title.descriptor", this, faker), 
            faker.fakeValuesService().resolve("name.title.level", this, faker), 
            faker.fakeValuesService().resolve("name.title.job", this, faker) }, " ");
    }

    private String makeUserName(String firstName, String lastName) {
        String username = StringUtils.join(
                firstName.replaceAll("'", "").toLowerCase(),
                ".",
                lastName.replaceAll("'", "").toLowerCase()
        );

        return StringUtils.deleteWhitespace(username);
    }

    /**
     * <p>
     *     A lowercase username composed of the first_name and last_name joined with a '.'. Some examples are:
     *     <ul>
     *         <li>(template) {@link #firstName()}.{@link #lastName()}</li>
     *         <li>jim.jones</li>
     *         <li>jason.leigh</li>
     *         <li>tracy.jordan</li>
     *     </ul>
     * </p>
     * @return a random two part user name.
     * @see Name#firstName() 
     * @see Name#lastName()
     */
    public String username() {
        return makeUserName(firstName(), lastName());
    }

    /**
     * <p>
     *     A lowercase username composed of the first_name and last_name joined with a '.'.
     *      Constrained by minimum length and maximum length.
     *      Some examples are:
     *     <ul>
     *         <li>(template) {@link #firstName()}.{@link #lastName()}</li>
     *         <li>jim.jones</li>
     *         <li>jason.leigh</li>
     *         <li>tracy.jordan</li>
     *     </ul>
     * </p>
     * @return a random two part user name with a minimum length minLength and a maximum length maxLength.
     * @see Name#firstName() 
     * @see Name#lastName()
     */
    public String username(int minLength, int maxLength) {
        if (maxLength < minLength) {
            throw new IllegalArgumentException("maxLength cannot be smaller than minLength");
        }

        final int minLengthUsername = 6; // minimum length username = 5 (6 including dot)

        if(minLength < minLengthUsername || maxLength < minLengthUsername) {
            // we can't make a username shorter than 6 characters
            throw new IllegalArgumentException("minimum allowable username length is " + minLengthUsername);
        }

        int maxFirstNameLength;
        int maxLastNameLength;

        if (maxLength % 2 == 0){
            maxFirstNameLength = maxLastNameLength = maxLength / 2;
        } else {
            maxFirstNameLength = (maxLength / 2) + 1;
        }

        String firstName = firstName(maxFirstNameLength);
        // maximum last name length is maximum allowable length
        // minus the first name we retrieved minus the dot that joins the two names

        if (firstName == null) {
            throw new RuntimeException("firstName aborted because it was unable to find a name matching constraints");
        }

        maxLastNameLength = maxLength - firstName.length() - 1; 
        String lastName = lastName(maxLastNameLength);

        if (lastName == null){
            throw new RuntimeException("lastName aborted because it was unable to find a name matching constraints");
        }

        return makeUserName(firstName, lastName);
    }
    
    /**
     * <p>Returns a blood group such as O−, O+, A-, A+, B-, B+, AB-, AB+</p>
     * @return a blood group such as O−, O+, A-, A+, B-, B+, AB-, AB+
     */
    public String bloodGroup() {
        return faker.fakeValuesService().resolve("name.blood_group", this, faker);
    }
}
