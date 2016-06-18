package com.alexsimo.jortune;

import twitter4j.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static final String hashtag = "#MADGrx";
    private static String official = "@MADGevents";
    private static List<String> ignored = new ArrayList<String>() {{
        add("MADGevents");
        add("alexsimonescu");
    }};

    public static void main(String[] args) {

        Set<String> users = new HashSet<>();
        Query query = buildQuery();
        Twitter client = buildTwitter();

        try {

            QueryResult result = queryByTag(query, client);
            processUsers(users, result);

            ArrayList<String> list = new ArrayList<>(users);
            Collections.shuffle(list);

            AtomicInteger position = new AtomicInteger(1);
            list.forEach(s -> printUser(position, s));

        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

    private static void printUser(AtomicInteger position, String value) {
        System.out.println(String.format("%d - @%s", position.getAndIncrement(), value));
    }

    private static Query buildQuery() {
        Query query = new Query(hashtag);
        query.setCount(100);
        return query;
    }

    private static Twitter buildTwitter() {
        return new TwitterFactory(Config.instance().getConfig()).getInstance();
    }

    private static QueryResult queryByTag(Query query, Twitter twitter) throws TwitterException {
        return twitter.search(query);
    }

    private static void processUsers(Set<String> users, QueryResult result) {
        result.getTweets().stream()
                .filter(App::meetRequierements)
                .forEach(status -> users.add(status.getUser().getScreenName()));
    }

    private static boolean meetRequierements(Status status) {
        return !userIgnored(status) && tagIsPresent(status);
    }

    private static boolean tagIsPresent(Status status) {
        return status.getText().contains(hashtag);
    }

    private static boolean userIgnored(Status status) {
        return ignored.contains(status.getUser().getScreenName());
    }

}
