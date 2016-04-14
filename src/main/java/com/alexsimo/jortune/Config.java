package com.alexsimo.jortune;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Config {

    private static final String consumer = "CXvW81z2wjLxBBB5ryZUL3bcC";
    private static final String consumerKey = "AMDwiMzcvjhQ8OIVRIIrdmaDrlw7zqyhsZ5OexrCPndEPuS3qW";
    private static final String token = "287673291-OHUSEj6tdrY5PlWqIVW9K3A2fPxgSUisDzff0MD6";
    private static final String tokenSecret = "BxpjgYhxCoMh0C2UBX0Du00H9lcZGNXgUI0yCxrYJIvrb";

    private static Config instance;
    private final Configuration config;


    private Config() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumer)
                .setOAuthConsumerSecret(consumerKey)
                .setOAuthAccessToken(token)
                .setOAuthAccessTokenSecret(tokenSecret);
        this.config = cb.build();
    }

    public static Config instance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }
}
