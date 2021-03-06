package com.andytenholder.comradetrump;

import android.text.TextUtils;
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
import java.util.List;

/**
 * Helper methods related to requesting and receiving data from Gaurdian API.
 */

public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the API and return a list of objects.
     */
    public static List<Article> fetchBookData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of objects
        List<Article> articles = extractFromJson(jsonResponse);

        // Return the list of objects
        return articles;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the book JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Article> extractFromJson(String articleJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(articleJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding objects to
        List<Article> articles = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(articleJSON);

            JSONObject guardianResponse = baseJsonResponse.getJSONObject("response");

            // Extract the JSONArray associated with the key called "items",
            // which represents a list of items (or books).
            JSONArray articleArray = guardianResponse.getJSONArray("results");

            // For each item in the Array, create an object
            for (int i = 0; i < articleArray.length(); i++) {

                // Get a single object at position i within the list
                JSONObject article_items = articleArray.getJSONObject(i);

                // create holder and then check JSON for object
                String articleTitle = "No Title Found";
                if (article_items.has("webTitle")){
                    // get article title
                    articleTitle = article_items.getString("webTitle");
                }

                // create holder and then check JSON for object
                String articleSectionName = "No Section Name Found";
                if (article_items.has("sectionName")){
                    // get article section name
                    articleSectionName = article_items.getString("sectionName");
                }

                // create holder and then check JSON for object
                String articlePublishDate = "No Date Found";
                if (article_items.has("webPublicationDate")){
                    // get article publish date
                    articlePublishDate = article_items.getString("webPublicationDate");
                }

                // create holder and then check JSON for object
                String articleURL = "No URL Found";
                if (article_items.has("webUrl")){
                    // get article URL
                    articleURL = article_items.getString("webUrl");
                }

                // create holder and then check JSON for object
                String coverThumbnail = "https://3.bp.blogspot.com/-uAG1DTgMrXo/V6lPxO4YEUI/AAAAAAAAHOU/oKzk-BK8KpAM4NS3PttVDadiNRaTC_e6QCLcB/s1600/comradetrumpweb.jpg";
                if (article_items.has("fields")){
                    JSONObject article_fields = article_items.getJSONObject("fields");
                    if(article_fields.has("thumbnail")){
                        coverThumbnail = article_fields.getString("thumbnail");
                    }
                }

                //(String webTitle, String webURL, String sectionName, String webPublicationDate, String coverThumbnail)
                articles.add(new Article (articleTitle, articleURL, articleSectionName, articlePublishDate, coverThumbnail));

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }

        // Return the list
        return articles;
    }
}
