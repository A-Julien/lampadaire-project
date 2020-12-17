package com.lampaderum.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "fr";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String SERV_IMG_URL = "https://lampaderum-img.s3.eu-west-3.amazonaws.com/cat_img/";


    private Constants() {
    }
}
