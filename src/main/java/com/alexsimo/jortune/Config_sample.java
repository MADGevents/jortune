package com.alexsimo.jortune;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/* public class Config */
public class Config_sample {

    // RENAME THIS TO Config

    private static final String consumer = "";
    private static final String consumerKey = "";
    private static final String token = "";
    private static final String tokenSecret = "";

    private static Config_sample instance;
    private final Configuration config;


    private Config_sample() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumer)
                .setOAuthConsumerSecret(consumerKey)
                .setOAuthAccessToken(token)
                .setOAuthAccessTokenSecret(tokenSecret);
        this.config = cb.build();
    }

    public static Config_sample instance() {
        if (instance == null) {
            instance = new Config_sample();
        }
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }
}
