package de.fhro.inf.prg3.a13.tweets;

import de.fhro.inf.prg3.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Peter Kurfer
 */
public class TrumpTweetStats
{
    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream)
    {
        // counting the Tweets by source app
        Map<String, Long> map = tweetStream
                .collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.counting()));

        return map;
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream)
    {
        // collecting the tweets in `Set`s for each source app
        Map<String, Set<Tweet>> map = tweetStream
                .collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.toSet()));

        return map;
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords)
    {
        Stream<String[]> words = tweetStream
                // splitting all the string at the whitespaces
                .map(t -> t.getText().toLowerCase().split("( )+"))
                // removing whitespaces from the words
                .map(w -> {
                    for (int i = 0; i < w.length; i++)
                        w[i] = w[i].trim();
                    return w;
                });

        Stream<String> stringStream = words
                .flatMap(w -> Arrays.stream(w))
                // filter out the stopwords
                .filter(w -> !stopWords.contains(w)
                );

        HashMap<String, Integer> id = new HashMap<>();

        Map<String, Integer> map = stringStream
                .reduce(id, (mp, i) -> {
                    Integer val;
                    try {
                        val = mp.get(i);
                    }
                    catch (Exception e) {
                        val = 0;
                    }
                    val++;
                    mp.put(i, val);
                    return mp;
                }, null); // maybe "null" has to be "(m1, m2) -> m1"

        return map;
    }
}
