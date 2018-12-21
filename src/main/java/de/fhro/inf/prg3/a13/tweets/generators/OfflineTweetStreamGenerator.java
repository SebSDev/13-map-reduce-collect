package de.fhro.inf.prg3.a13.tweets.generators;

import com.google.gson.Gson;
import de.fhro.inf.prg3.a13.model.Tweet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Sebastian Schäffler
 * created at 21.12.2018
 * description:
 */
public class OfflineTweetStreamGenerator implements TweetStreamGenerator
{
    @Override
    public Stream<Tweet> getTweetStream()
    {
        InputStream iStream = getClass().getResourceAsStream("/trump_tweets.json");
        InputStreamReader isr = new InputStreamReader(iStream);
        Gson gson = new Gson();
        Tweet[] tweets = gson.fromJson(isr, Tweet[].class);

        return Arrays.stream(tweets);
    }
}
