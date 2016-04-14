package com.alexsimo.jortune;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Config {

    private static final String consumer = "guxVAIjaPMZO0TOR0IsOwCY64";
    private static final String consumerKey = "83fg7tZupgneMPQSM3bHY4bVQAwt1f5IXcCC5I3ZcL88atKYvc";
    private static final String token = "287673291-0Mi0SIs3wSQ3ErLeEyyM9Bk1Q9KCnGxv5mMSJ5b1";
    private static final String tokenSecret = "R82EbVwo46QQG60X0AcDM8Au6kNtGyRJ5ihErGP3sFTYz";

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
