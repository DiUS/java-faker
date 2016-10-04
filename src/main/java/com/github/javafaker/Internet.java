package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.StringUtils;

import java.net.IDN;

import static org.apache.commons.lang3.StringUtils.join;

public class Internet {
    private static final String[] PRIVATE_IPV4_NETS_REGEX = {
            "^10\\..+",
            "^127\\..+",
            "^169\\.254\\..+",
            "^172\\.(16|17|18|19|2\\d|30|31)\\..+",
            "^192\\.168\\..+"
    };
    
    private final Name name;
    private final Lorem lorem;
    private final FakeValuesServiceInterface fakeValuesService;
    private final RandomService randomService;

    public Internet(Name name, Lorem lorem, FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.name = name;
        this.lorem = lorem;
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public String emailAddress() {
        return emailAddress(join(new Object[]{
                name.firstName().toLowerCase().replaceAll("'", ""),
                ".",
                name.lastName().toLowerCase().replaceAll("'", "")
        }));
    }

    public String emailAddress(String localPart) {
        return join(new Object[]{
                localPart,
                "@",
                IDN.toASCII(fakeValuesService.fetchString("internet.free_email"))
        });
    }

    public String safeEmailAddress(){
        return emailAddress(join(new Object[]{
                name.firstName().toLowerCase().replaceAll("'", ""),
                ".",
                name.lastName().toLowerCase().replaceAll("'", "")
        }));
    }

    public String safeEmailAddress(String localPart){
        return join(new Object[]{
                localPart,
                "@",
                IDN.toASCII(fakeValuesService.fetchString("internet.free_email"))
        });
    }

    public String domainName() {
        return domainWord() + "." + domainSuffix();
    }

    public String domainWord() {
        return IDN.toASCII(name.lastName().toLowerCase().replaceAll("'", ""));
    }

    public String domainSuffix() {
        return fakeValuesService.fetchString("internet.domain_suffix");
    }

    public String url() {
        return join(new Object[]{
                "www",
                ".",
                IDN.toASCII(
                    name.firstName().toLowerCase().replaceAll("'", "") +
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
        return "https://s3.amazonaws.com/uifaces/faces/twitter/" + fakeValuesService.fetchString("internet.avatar");
    }

    /**
     * Generates a random image url based on the lorempixel service. All the images provided by this service are released
     * under the creative commons license (CC BY-SA). For more information, please visit: http://lorempixel.com/
     *
     * @return an url to a random image.
     * @see <a href="http://lorempixel.com/">lorempixel - Placeholder Images for every case</a>
     */
    public String image() {
        String[] dimension = StringUtils.split(fakeValuesService.fetchString("internet.image_dimension"), 'x');
        if (dimension .length == 0) return "";
        return image(
                Integer.valueOf(StringUtils.trim(dimension[0])), Integer.valueOf(StringUtils.trim(dimension[1])),
                randomService.nextBoolean(), null);
    }

    /**
     * Same as image() but allows client code to choose a few image characteristics
     * @param width the image width
     * @param height the image height
     * @param gray true for gray image and false for color image
     * @param text optional custom text on the selected picture
     * @return an url to a random image with the given characteristics.
     */
    public String image(Integer width, Integer height, Boolean gray, String text) {
        return String.format("http://lorempixel.com/%s%s/%s/%s/%s",
                gray ? "g/" : StringUtils.EMPTY, width, height, fakeValuesService.fetchString("internet.image_category"),
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
            char[] password = lorem.characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[] { '!', '@', '#', '$', '%', '^', '&', '*' };
            for (int i = 0; i < randomService.nextInt(minimumLength); i++) {
                password[randomService.nextInt(password.length)] = special[randomService.nextInt(special.length)];
            }
            return new String(password);
        } else {
            return lorem.characters(minimumLength, maximumLength, includeUppercase);
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
            out.append(Integer.toHexString(randomService.nextInt(16)));
            out.append(Integer.toHexString(randomService.nextInt(16)));
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
          randomService.nextInt(254) + 2,
          randomService.nextInt(254) + 2,
          randomService.nextInt(254) + 2,
          randomService.nextInt(254) + 2);
    }

    /**
     * @return a valid private IPV4 address in dot notation
     */
    public String privateIpV4Address() {
        String addr = null;
        do {
            addr = ipV4Address(); 
        } while (!isPrivate(addr));
        return addr;
    }

    /**
     * @return a valid public IPV4 address in dot notation
     */
    public String publicIpV4Address() {
        String addr = null;
        do {
            addr = ipV4Address();
        } while (isPrivate(addr));
        return addr;
    }

    /**
     * @return a valid IPV4 CIDR
     */
    public String ipV4Cidr() {
        return new StringBuilder(ipV4Address())
          .append('/')
          .append(randomService.nextInt(31) + 1)
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
            tmp.append(Integer.toHexString(randomService.nextInt(16)));
            tmp.append(Integer.toHexString(randomService.nextInt(16)));
            tmp.append(Integer.toHexString(randomService.nextInt(16)));
            tmp.append(Integer.toHexString(randomService.nextInt(16)));
        }
        return tmp.toString();
    }

    /**
     * @return a valid IPV6 CIDR
     */
    public String ipV6Cidr() {
        return new StringBuilder(ipV6Address())
          .append('/')
          .append(randomService.nextInt(127) + 1)
          .toString();
    }

    /**
     * @return true if the specified ipv4 address is a private address, false otherwise.
     */
    private final boolean isPrivate(String addr) {
        for (int i = 0; i< PRIVATE_IPV4_NETS_REGEX.length; i++) {
            if (addr.matches(PRIVATE_IPV4_NETS_REGEX[i])) {
                return true;
            }
        }
        return false;
    }



}
