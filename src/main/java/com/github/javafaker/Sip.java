package com.github.javafaker;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;

/**
 * Faker class for generating Session Initiation Protocol (SIP) related data.
 *
 * @author TomerFi
 */
public final class Sip {
    private final Faker faker;
    private final ArrayList<Integer> portPool;

    protected Sip(final Faker faker) {
        this.faker = faker;
        int port = 40000;
        portPool = new ArrayList<Integer>();
        while (port <= 50000) {
            portPool.add(port);
            port = port + 2;
        }
    }

    /**
     * The various SIP methods are listed in https://en.wikipedia.org/wiki/Session_Initiation_Protocol.
     *
     * @return a SIP method String, e.g. {@code INVITE}.
     */
    public String method() {
        return faker.resolve("sip.methods");
    }

    /**
     * Content types are based on https://tools.ietf.org/html/rfc5621 and
     * https://tools.ietf.org/html/rfc3261.
     *
     * @return a SIP content-type declaration String, e.g. {@code application/sdp}
     */
    public String contentType() {
        return faker.resolve("sip.content.types");
    }

    /**
     * Get a 4 digit random port for SIP messaging.
     *
     * @return a SIP messaging port int, e.g. 5060.
     */
    public int messagingPort() {
        return faker.random().nextInt(1000, 9999);
    }

    /**
     * Get a 5 digit positive even port for rtp udp communication.
     *
     * @return an RTP UDP 5 digit port int, e.g. 40002.
     */
    public int rtpPort() {
        return portPool.get(faker.random().nextInt(0, portPool.size()));
    }

    /**
     * Proviosional code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP provisioan response code between 100 and 199 int, e.g. {@code 180}.
     */
    public int provisionalResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.provisional"));
    }

    /**
     * Success code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP success response code between 200 and 299 int, e.g. {@code 200}.
     */
    public int successResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.success"));
    }

    /**
     * Redirection code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP redirection response code between 300 and 399 int, e.g. {@code 301}.
     */
    public int redirectResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.redirection"));
    }

    /**
     * Client error code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP client error response code between 400 and 499 int, e.g. {@code 486}.
     */
    public int clientErrorResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.clientError"));
    }

    /**
     * Server error code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP server error response code between 500 and 599 int, e.g. {@code 503}.
     */
    public int serverErrorResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.serverError"));
    }

    /**
     * Global error code, the various response codes are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a 3 digit SIP global error response code between 600 and 699 int, e.g. {@code 608}.
     */
    public int globalErrorResponseCode() {
        return Integer.parseInt(faker.resolve("sip.response.codes.globalError"));
    }

    /**
     * Proviosional phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP provisional response phrase String, e.g. {@code Ringing}.
     */
    public String provisionalResponsePhrase() {
        return faker.resolve("sip.response.phrases.provisional");
    }

    /**
     * Success phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP success response phrase String, e.g. {@code OK}.
     */
    public String successResponsePhrase() {
        return faker.resolve("sip.response.phrases.success");
    }

    /**
     * Redirection phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP redirection response phrase String, e.g. {@code Moved Permanently}.
     */
    public String redirectResponsePhrase() {
        return faker.resolve("sip.response.phrases.redirection");
    }

    /**
     * Client error phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP client error response phrase String, e.g. {@code Busy Here}.
     */
    public String clientErrorResponsePhrase() {
        return faker.resolve("sip.response.phrases.clientError");
    }

    /**
     * Server error phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP server erro response phrase String, e.g. {@code Service Unavailable}.
     */
    public String serverErrorResponsePhrase() {
        return faker.resolve("sip.response.phrases.serverError");
    }

    /**
     * Server error phrase, the various response phrases are listed in
     * https://en.wikipedia.org/wiki/List_of_SIP_response_codes.
     *
     * @return a SIP global error response phrase String, e.g. {@code Rejected}.
     */
    public String globalErrorResponsePhrase() {
        return faker.resolve("sip.response.phrases.globalError");
    }

    /**
     * Body example of SDP type can be found in https://tools.ietf.org/html/rfc5621.
     *
     * @return a fake SDP type SIP body String.
     */
    public String bodyString() {
        return "v=0\n" +
        "o=" + faker.name().firstName() + " " + faker.internet().uuid() + " IN IP4 " + faker.internet().domainName() + "\n" +
        "s=-\n" +
        "c=IN IP4 " + faker.internet().ipV4Address() + "\n" +
        "t=0 0\n" +
        "m=audio " + rtpPort() + " RTP/AVP 0\n" +
        "a=rtpmap:0 PCMU/8000";
    }

    /**
     * Body example of SDP type can be found in https://tools.ietf.org/html/rfc5621.
     *
     * @return a fake SDP type SIP body byte array.
     */
    public byte[] bodyBytes() {
        return bodyString().getBytes(UTF_8);
    }

    /**
     * Return a valid name address to use with {@code to/from} headers.
     *
     * @return a valid name address String, e.g. {@code <sip:fakeName@10.1.2.3:5060>}.
     */
    public String nameAddress() {
        return "<sip:" + faker.name().firstName() + "@" + faker.internet().ipV4Address() + ":" + messagingPort() + ">";
    }
}
