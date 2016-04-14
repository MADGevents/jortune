package com.alexsimo.jortune;

import twitter4j.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) {

        Set<String> users = new HashSet<>();
        Query query = new Query("#TopDanceCasting2");
        Twitter twitter = new TwitterFactory(Config.instance().getConfig()).getInstance();
        query.setCount(100);

        try {
            QueryResult result = twitter.search(query);
            result.getTweets().forEach(
                    s -> users.add(s.getUser().getScreenName())
            );

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

}
