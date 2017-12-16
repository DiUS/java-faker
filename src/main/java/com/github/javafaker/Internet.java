package com.github.javafaker;

import com.github.javafaker.service.FakerIDN;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.join;

public class Internet {
    private final Faker faker;

    protected Internet(Faker faker) {
        this.faker = faker;
    }

    public String emailAddress() {
        return emailAddress(faker.name().username());
    }

    public String emailAddress(String localPart) {
        return join(localPart,
                "@",
                FakerIDN.toASCII(faker.fakeValuesService().resolve("internet.free_email", this, faker)));
    }

    public String safeEmailAddress() {
        return safeEmailAddress(faker.name().username());
    }

    public String safeEmailAddress(String localPart) {
        return join(localPart, 
                "@",
                FakerIDN.toASCII(faker.fakeValuesService().resolve("internet.safe_email", this, faker)));
    }

    public String domainName() {
        return domainWord() + "." + domainSuffix();
    }

    public String domainWord() {
        return FakerIDN.toASCII(faker.name().lastName().toLowerCase().replaceAll("'", ""));
    }

    public String domainSuffix() {
        return faker.fakeValuesService().resolve("internet.domain_suffix", this, faker);
    }

    public String url() {
        return join(new Object[]{
                "www",
                ".",
                FakerIDN.toASCII(
                        faker.name().firstName().toLowerCase().replaceAll("'", "") +
                                "-" +
                                domainWord()
                ),
                ".",
                domainSuffix()
        });
    }

    /**
     * Generates a random avatar url based on a collection of profile pictures of real people. All this avatar have been
     * authorized by its awesome users to be used on live websites (not just mockups). For more information, please
     * visit: http://uifaces.com/authorized
     *
     * @return an url to a random avatar image.
     * @see <a href="http://uifaces.com/authorized">Authorized UI Faces</a>
     */
    public String avatar() {
        return "https://s3.amazonaws.com/uifaces/faces/twitter/" + faker.fakeValuesService().resolve("internet.avatar", this, faker);
    }

    /**
     * Generates a random image url based on the lorempixel service. All the images provided by this service are released
     * under the creative commons license (CC BY-SA). For more information, please visit: http://lorempixel.com/
     *
     * @return an url to a random image.
     * @see <a href="http://lorempixel.com/">lorempixel - Placeholder Images for every case</a>
     */
    public String image() {
        String[] dimension = StringUtils.split(faker.fakeValuesService().resolve("internet.image_dimension", this, faker), 'x');
        if (dimension.length == 0) return "";
        return image(
                Integer.valueOf(StringUtils.trim(dimension[0])), Integer.valueOf(StringUtils.trim(dimension[1])),
                faker.bool().bool(), null);
    }

    /**
     * Same as image() but allows client code to choose a few image characteristics
     *
     * @param width  the image width
     * @param height the image height
     * @param gray   true for gray image and false for color image
     * @param text   optional custom text on the selected picture
     * @return an url to a random image with the given characteristics.
     */
    public String image(Integer width, Integer height, Boolean gray, String text) {
        return String.format("http://lorempixel.com/%s%s/%s/%s/%s",
                gray ? "g/" : StringUtils.EMPTY, width, height, faker.fakeValuesService().resolve("internet.image_category", this, faker),
                StringUtils.isEmpty(text) ? StringUtils.EMPTY : text);
    }

    public String password() {
        return password(8, 16);
    }

    public String password(int minimumLength, int maximumLength) {
        return password(minimumLength, maximumLength, false);
    }

    public String password(int minimumLength, int maximumLength, boolean includeUppercase) {
        return password(minimumLength, maximumLength, includeUppercase, false);
    }

    public String password(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial) {
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
        }
    }
    
    /**
     * <p>Returns a MAC address in the following format: 6-bytes in MM:MM:MM:SS:SS:SS format.</p>
     * @return a correctly formatted MAC address
     * @param prefix a prefix to put on the front of the address
     */
    public String macAddress(String prefix) {
        final String tmp = (prefix == null) ? "" : prefix;
        final int prefixLength = tmp.trim().length() == 0 
          ? 0 
          : tmp.split(":").length;
        
        final StringBuilder out = new StringBuilder(tmp);
        for (int i=0;i < 6 - prefixLength;i++) {
            if (out.length() > 0) {
                out.append(':');
            }
            out.append(Integer.toHexString(faker.random().nextInt(16)));
            out.append(Integer.toHexString(faker.random().nextInt(16)));
        }
        return out.toString();
    }

    /**
     * @see Internet#macAddress(String) 
     */
    public String macAddress() {
        return macAddress("");
    }

    /**
     * returns an IPv4 address in dot separated octets. 
     * @return a correctly formatted IPv4 address.
     */
    public String ipV4Address() {
        return String.format("%d.%d.%d.%d",
          faker.random().nextInt(254) + 2,
          faker.random().nextInt(254) + 2,
          faker.random().nextInt(254) + 2,
          faker.random().nextInt(254) + 2);
    }

    /**
     * @return a valid private IPV4 address in dot notation
     */
    public String privateIpV4Address() {
        final Integer[] PRIVATE_FIRST_OCTET = {10,127,169,192,172};
        final Integer[] PRIVATE_SECOND_OCTET_172 = {16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

        final RandomService r = faker.random();
        int first = random(PRIVATE_FIRST_OCTET),
                second = r.nextInt(256),
                third = r.nextInt(256),
                fourth = r.nextInt(256);

        switch (first) {
            case 172:
                second = random(PRIVATE_SECOND_OCTET_172);
                break;
            case 192:
                second = 168;
                break;
            case 169:
                second = 254;
                break;
        }
        return String.format("%d.%d.%d.%d", first, second, third, fourth);
    }

    /**
     * @return a valid public IPV4 address in dot notation
     */
    public String publicIpV4Address() {
        final RandomService r = faker.random();
        
        final int[] PRIVATE_FIRST_OCTET = {10,127,169,192,172};

        int first = r.nextInt(256),
                second = r.nextInt(256),
                third = r.nextInt(256),
                fourth = r.nextInt(256);
        
        while (Arrays.binarySearch(PRIVATE_FIRST_OCTET, first) > 0) {
            first = r.nextInt(256);
        }
        return String.format("%d.%d.%d.%d", first, second, third, fourth);
    }

    /**
     * @return a valid IPV4 CIDR
     */
    public String ipV4Cidr() {
        return new StringBuilder(ipV4Address())
          .append('/')
          .append(faker.random().nextInt(31) + 1)
          .toString();
    }

    /**
     * <p>Returns an IPv6 address in hh:hh:hh:hh:hh:hh:hh:hh format.</p>
     * @return a correctly formatted IPv6 address.
     */
    public String ipV6Address() {
        final StringBuilder tmp = new StringBuilder();
        for (int i=0;i < 8;i++) {
            if (i > 0) {
                tmp.append(":");
            }
            tmp.append(Integer.toHexString(faker.random().nextInt(16)));
            tmp.append(Integer.toHexString(faker.random().nextInt(16)));
            tmp.append(Integer.toHexString(faker.random().nextInt(16)));
            tmp.append(Integer.toHexString(faker.random().nextInt(16)));
        }
        return tmp.toString();
    }

    /**
     * @return a valid IPV6 CIDR
     */
    public String ipV6Cidr() {
        return new StringBuilder(ipV6Address())
          .append('/')
          .append(faker.random().nextInt(127) + 1)
          .toString();
    }

    /**
     * @return a slug using '_' as the word separator and two {@link Lorem} words as the values
     */
    public String slug() {
        return slug(faker.lorem().words(2), "_");
    }

    /**
     * @param wordsOrNull if null, then 2 {@link Lorem} words
     * @param glueOrNull  if null, "_"
     * @return a slug string combining wordsOrNull with glueOrNull (ex. x_y)
     */
    public String slug(List<String> wordsOrNull, String glueOrNull) {
        final String glue = glueOrNull == null
                ? "_"
                : glueOrNull;
        final List<String> words = wordsOrNull == null
                ? faker.lorem().words(2)
                : wordsOrNull;

        final StringBuilder slug = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) {
                slug.append(glue);
            }
            slug.append(words.get(i));
        }
        return slug.toString();
    }

    /**
     * Returns a UUID (type 4) as String.
     * @return A UUID as String.
     */
    public String uuid() {
        return UUID.randomUUID().toString();
    }
          
    private <T> T random(T[] src) {
        return src[faker.random().nextInt(src.length)];
    }
}
