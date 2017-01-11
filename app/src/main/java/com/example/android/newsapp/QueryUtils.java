package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.android.newsapp.NewsActivity.LOG_TAG;

/**
 * Created by kempm on 1/8/2017.
 */

public class QueryUtils {

    //public static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":189,\"startIndex\":1,\"pageSize\":20,\"currentPage\":1,\"pages\":10,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"technology/2017/jan/07/switch-2017-nintendo-new-portable-platform\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Nintendo flips Switch: will new console make 2017 a winning year?\",\"webUrl\":\"https://www.theguardian.com/technology/2017/jan/07/switch-2017-nintendo-new-portable-platform\",\"apiUrl\":\"https://content.guardianapis.com/technology/2017/jan/07/switch-2017-nintendo-new-portable-platform\",\"isHosted\":false},{\"id\":\"technology/2016/oct/20/nintendo-switch-hybrid-console-to-use-at-home-and-on-the-go\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-10-20T14:42:59Z\",\"webTitle\":\"Nintendo Switch revealed: hybrid console to use at home and on the go\",\"webUrl\":\"https://www.theguardian.com/technology/2016/oct/20/nintendo-switch-hybrid-console-to-use-at-home-and-on-the-go\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/oct/20/nintendo-switch-hybrid-console-to-use-at-home-and-on-the-go\",\"isHosted\":false},{\"id\":\"technology/2016/oct/21/nintendo-switch-new-portable-console-wii-u\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-10-21T11:01:43Z\",\"webTitle\":\"Nintendo Switch: can the new console succeed where Wii U failed?\",\"webUrl\":\"https://www.theguardian.com/technology/2016/oct/21/nintendo-switch-new-portable-console-wii-u\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/oct/21/nintendo-switch-new-portable-console-wii-u\",\"isHosted\":false},{\"id\":\"technology/2016/nov/21/games-reviews-roundup-nintendo-classic-mini-nes-robinson-the-journey-call-of-duty-infinite-warfare\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-11-21T07:00:01Z\",\"webTitle\":\"Games reviews roundup: Nintendo Classic Mini NES; Robinson: The Journey; Call of Duty: Infinite Warfare\",\"webUrl\":\"https://www.theguardian.com/technology/2016/nov/21/games-reviews-roundup-nintendo-classic-mini-nes-robinson-the-journey-call-of-duty-infinite-warfare\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/nov/21/games-reviews-roundup-nintendo-classic-mini-nes-robinson-the-journey-call-of-duty-infinite-warfare\",\"isHosted\":false},{\"id\":\"technology/2015/jun/01/splatoon-review-wii-u-nintendo-wiiu-inkling-multiplayer-action-shooter\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2015-06-01T06:00:12Z\",\"webTitle\":\"Splatoon review – Inky multiplayer fun from Nintendo\",\"webUrl\":\"https://www.theguardian.com/technology/2015/jun/01/splatoon-review-wii-u-nintendo-wiiu-inkling-multiplayer-action-shooter\",\"apiUrl\":\"https://content.guardianapis.com/technology/2015/jun/01/splatoon-review-wii-u-nintendo-wiiu-inkling-multiplayer-action-shooter\",\"isHosted\":false},{\"id\":\"technology/2017/jan/07/best-games-2017-resident-evil-7-gravity-rush-2-nintendo-switch-halo-wars-2\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Shock, dread and yakuza: games not to miss in 2017\",\"webUrl\":\"https://www.theguardian.com/technology/2017/jan/07/best-games-2017-resident-evil-7-gravity-rush-2-nintendo-switch-halo-wars-2\",\"apiUrl\":\"https://content.guardianapis.com/technology/2017/jan/07/best-games-2017-resident-evil-7-gravity-rush-2-nintendo-switch-halo-wars-2\",\"isHosted\":false},{\"id\":\"technology/2015/jun/16/donkey-kong-bowser-skylanders-amiibos\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2015-06-16T16:30:10Z\",\"webTitle\":\"Nintendo legends Donkey Kong and Bowser to feature in Skylanders\",\"webUrl\":\"https://www.theguardian.com/technology/2015/jun/16/donkey-kong-bowser-skylanders-amiibos\",\"apiUrl\":\"https://content.guardianapis.com/technology/2015/jun/16/donkey-kong-bowser-skylanders-amiibos\",\"isHosted\":false},{\"id\":\"society/2015/sep/01/nhs-scheme-provides-holidays-satnav-and-pedalo\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2015-09-01T08:08:35Z\",\"webTitle\":\"NHS health budgets funding holidays, Nintendo consoles and a pedalo ride\",\"webUrl\":\"https://www.theguardian.com/society/2015/sep/01/nhs-scheme-provides-holidays-satnav-and-pedalo\",\"apiUrl\":\"https://content.guardianapis.com/society/2015/sep/01/nhs-scheme-provides-holidays-satnav-and-pedalo\",\"isHosted\":false},{\"id\":\"film/2017/jan/07/40-films-to-kick-off-2017-great-wall-fifty-shades-darker-lego-batman\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Forty films to kick off 2017 in UK cinemas\",\"webUrl\":\"https://www.theguardian.com/film/2017/jan/07/40-films-to-kick-off-2017-great-wall-fifty-shades-darker-lego-batman\",\"apiUrl\":\"https://content.guardianapis.com/film/2017/jan/07/40-films-to-kick-off-2017-great-wall-fifty-shades-darker-lego-batman\",\"isHosted\":false},{\"id\":\"stage/2017/jan/07/best-live-comedy-2017-bridget-christie-leicester-comedy-festival\",\"type\":\"article\",\"sectionId\":\"stage\",\"sectionName\":\"Stage\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Laughing gear: the best live comedy to start 2017\",\"webUrl\":\"https://www.theguardian.com/stage/2017/jan/07/best-live-comedy-2017-bridget-christie-leicester-comedy-festival\",\"apiUrl\":\"https://content.guardianapis.com/stage/2017/jan/07/best-live-comedy-2017-bridget-christie-leicester-comedy-festival\",\"isHosted\":false},{\"id\":\"technology/2014/nov/29/super-smash-bros-wii-u-review\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2014-11-29T06:00:06Z\",\"webTitle\":\"Super Smash Bros review\",\"webUrl\":\"https://www.theguardian.com/technology/2014/nov/29/super-smash-bros-wii-u-review\",\"apiUrl\":\"https://content.guardianapis.com/technology/2014/nov/29/super-smash-bros-wii-u-review\",\"isHosted\":false},{\"id\":\"tv-and-radio/2017/jan/07/must-see-tv-shows-of-2017-girls-twin-peaks-game-of-thrones\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Watch this! The must-see TV shows of 2017\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2017/jan/07/must-see-tv-shows-of-2017-girls-twin-peaks-game-of-thrones\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2017/jan/07/must-see-tv-shows-of-2017-girls-twin-peaks-game-of-thrones\",\"isHosted\":false},{\"id\":\"music/2017/jan/07/best-pop-rock-2017-black-sabbath-drake-craig-david-xx\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Future sounds: the best pop and rock of 2017\",\"webUrl\":\"https://www.theguardian.com/music/2017/jan/07/best-pop-rock-2017-black-sabbath-drake-craig-david-xx\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/jan/07/best-pop-rock-2017-black-sabbath-drake-craig-david-xx\",\"isHosted\":false},{\"id\":\"technology/2015/jul/13/samurai-warriors-chronicles-3-review\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2015-07-13T07:00:04Z\",\"webTitle\":\"Samurai Warriors: Chronicles 3 review – stylised history, little to wow\",\"webUrl\":\"https://www.theguardian.com/technology/2015/jul/13/samurai-warriors-chronicles-3-review\",\"apiUrl\":\"https://content.guardianapis.com/technology/2015/jul/13/samurai-warriors-chronicles-3-review\",\"isHosted\":false},{\"id\":\"stage/2017/jan/07/best-theatre-of-2017-andrew-scott-hamlet-damian-lewis-goat\",\"type\":\"article\",\"sectionId\":\"stage\",\"sectionName\":\"Stage\",\"webPublicationDate\":\"2017-01-07T10:00:16Z\",\"webTitle\":\"Centre stage: the best theatre of 2017\",\"webUrl\":\"https://www.theguardian.com/stage/2017/jan/07/best-theatre-of-2017-andrew-scott-hamlet-damian-lewis-goat\",\"apiUrl\":\"https://content.guardianapis.com/stage/2017/jan/07/best-theatre-of-2017-andrew-scott-hamlet-damian-lewis-goat\",\"isHosted\":false},{\"id\":\"technology/2016/sep/07/iphone-7-apple-launches-new-waterproof-smartphones-camera-airpods-headphones\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-09-08T07:04:40Z\",\"webTitle\":\"Apple reveals waterproof iPhone 7 with new camera ... and no headphone jack\",\"webUrl\":\"https://www.theguardian.com/technology/2016/sep/07/iphone-7-apple-launches-new-waterproof-smartphones-camera-airpods-headphones\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/sep/07/iphone-7-apple-launches-new-waterproof-smartphones-camera-airpods-headphones\",\"isHosted\":false},{\"id\":\"money/2015/apr/04/junior-isa-child-trust-fund-switch\",\"type\":\"article\",\"sectionId\":\"money\",\"sectionName\":\"Money\",\"webPublicationDate\":\"2015-04-04T06:00:07Z\",\"webTitle\":\"Junior Isas offer a better deal than child trust funds – switching is child’s play\",\"webUrl\":\"https://www.theguardian.com/money/2015/apr/04/junior-isa-child-trust-fund-switch\",\"apiUrl\":\"https://content.guardianapis.com/money/2015/apr/04/junior-isa-child-trust-fund-switch\",\"isHosted\":false},{\"id\":\"technology/2014/jun/10/e3-2014-nintendo-open-world-zelda-star-fox-yoshis-woolly-world\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2014-06-11T07:47:06Z\",\"webTitle\":\"Nintendo at E3: Zelda and Star Fox for Wii U a much-need shot in the arm\",\"webUrl\":\"https://www.theguardian.com/technology/2014/jun/10/e3-2014-nintendo-open-world-zelda-star-fox-yoshis-woolly-world\",\"apiUrl\":\"https://content.guardianapis.com/technology/2014/jun/10/e3-2014-nintendo-open-world-zelda-star-fox-yoshis-woolly-world\",\"isHosted\":false},{\"id\":\"technology/2014/may/06/replica-nintendo-classic-console-imperfections-nes-aluminium\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2014-05-06T15:12:39Z\",\"webTitle\":\"Replica of Nintendo's classic console goes on sale, without the imperfections\",\"webUrl\":\"https://www.theguardian.com/technology/2014/may/06/replica-nintendo-classic-console-imperfections-nes-aluminium\",\"apiUrl\":\"https://content.guardianapis.com/technology/2014/may/06/replica-nintendo-classic-console-imperfections-nes-aluminium\",\"isHosted\":false},{\"id\":\"technology/2016/apr/29/yo-kai-watch-review-pokemon\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2016-04-29T10:39:39Z\",\"webTitle\":\"Yo-Kai Watch review – a cute contender to the Pokemon throne\",\"webUrl\":\"https://www.theguardian.com/technology/2016/apr/29/yo-kai-watch-review-pokemon\",\"apiUrl\":\"https://content.guardianapis.com/technology/2016/apr/29/yo-kai-watch-review-pokemon\",\"isHosted\":false}]}}";

    /**
     * Pull news stories from url
     * @param requestUrl
     * @return
     */
    public static ArrayList<News> fetchNewsData(String requestUrl) {

        // Create url object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the url and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from this JSON response
        ArrayList<News> newsList = extractNewsFromJSON(jsonResponse);

        return newsList;
    }

    /**
     * Make url object out of request url string
     * @param requestUrl
     * @return
     */
    private static URL createUrl(String requestUrl) {

        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an http request to the given url and return a json String response
     * @param url
     * @return
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        // If the URL is null, return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000); // milliseconds
            urlConnection.setConnectTimeout(15000); // milliseconds
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If successful
            if (urlConnection.getResponseCode() == 200) {

                // Get input stream from url connection and make a json response out of it
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {

            // Close connections to save memory
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Throws IO Exception
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // Read json response from input stream
    private static String readFromStream(InputStream inputStream) throws IOException {

        // String builder that will hold json response
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            // Reads bytes and decodes them into characters
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            // Makes the reading of the decoded characters much more memory-efficient
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // Throws IO Exception
            String line = reader.readLine();

            while (line != null) {

                // Append this line to the json string builder and try to read the next line
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    // Get news from the guardian
    public static ArrayList<News> extractNewsFromJSON(String jsonResponse) {

        // News list to be built
        ArrayList<News> newsList = new ArrayList<>();

        // Get data from internet
        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                // Get this piece of news and extract what we need
                JSONObject newsObject = (JSONObject) results.get(i);

                String webTitle = newsObject.getString("webTitle");
                String webPublicationDate = newsObject.getString("webPublicationDate");
                String webUrl = newsObject.getString("webUrl");
                String sectionName = newsObject.getString("sectionName");

                // Make a news object and add it to the list
                News story = new News(webTitle, webPublicationDate, webUrl, sectionName);
                newsList.add(story);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return newsList;
    }
}
