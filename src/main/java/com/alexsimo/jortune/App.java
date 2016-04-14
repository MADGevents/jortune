package com.alexsimo.jortune;

import twitter4j.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static String official = "@MADGevents";
    private static List<String> ignored = new ArrayList<String>() {{
        add("MADGevents");
        add("sefford");
        add("Chicisimo");
    }};

    public static void main(String[] args) {

        Set<String> users = new HashSet<>();
        Query query = new Query("#MADGauth");
        Twitter twitter = new TwitterFactory(Config.instance().getConfig()).getInstance();
        query.setCount(100);

        try {
            QueryResult result = twitter.search(query);

            result.getTweets().stream()
                    .filter(App::meetRequierements)
                    .forEach(status -> users.add(status.getUser().getScreenName()));

            ArrayList<String> list = new ArrayList<>(users);
            Collections.shuffle(list);

            AtomicInteger position = new AtomicInteger(1);
            list.forEach(s ->
                    System.out.println(String.format("%d - @%s", position.getAndIncrement(), s))
            );
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

    private static boolean meetRequierements(Status status) {
        return !ignored.contains(status.getUser().getScreenName()) && status.getText().contains(official);
    }

}
