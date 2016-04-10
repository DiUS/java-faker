package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.join;

public class Internet {

    private final Name name;
    private final FakeValuesService fakeValuesService;
    private final RandomService randomService;

    public Internet(Name name, FakeValuesService fakeValuesService, RandomService randomService) {
        this.name = name;
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public String emailAddress() {
        return emailAddress(join(new Object[]{
                name.firstName().toLowerCase(),
                ".",
                name.lastName().toLowerCase()
        }));
    }

    public String emailAddress(String localPart) {
        return join(new Object[]{
                localPart,
                "@",
                java.net.IDN.toASCII(fakeValuesService.fetchString("internet.free_email"))
        });
    }

    public String url() {
        return join(new Object[]{
                "www",
                ".",
                java.net.IDN.toASCII(
                    name.firstName().toLowerCase().replaceAll("'", "") +
                    "-" +
                    name.lastName().toLowerCase().replaceAll("'", "")
                ),
                ".",
                fakeValuesService.fetchString("internet.domain_suffix")
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
        return String.format("https://ssl.webpack.de/lorempixel.com/%s%s/%s/%s/%s",
                gray ? "g/" : StringUtils.EMPTY, width, height, fakeValuesService.fetchString("internet.image_category"),
                StringUtils.isEmpty(text) ? StringUtils.EMPTY : text);
    }

}
