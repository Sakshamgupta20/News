package com.example.android.news;

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
 * Created by Saksham on 03-01-2018.
 */

public class utils {
    public static final String LOG_TAG = utils.class.getSimpleName();


    public static URL createUrl(String stringurl) {
        URL url = null;
        try {
            url = new URL(stringurl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "ERROR Creating URL", e);
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream input = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                input = connection.getInputStream();
                jsonResponse = readFromStream(input);
            } else {
                Log.e(LOG_TAG, "Error response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the News JSON results.", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (input != null) {
                input.close();
            }
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder string1 = new StringBuilder();
        {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    string1.append(line);
                    line = reader.readLine();
                }
            }
            return string1.toString();
        }

    }

    public static List<word> extract(String newsjanson)  {
        List<word> news = new ArrayList<>();
        if (TextUtils.isEmpty(newsjanson)) {
            return null;
        }
        try {
            JSONObject newsroot = new JSONObject(newsjanson);
            JSONArray articlesarray = newsroot.getJSONArray("articles");
            for (int i = 0; i < articlesarray.length(); i++) {
                JSONObject currentnews = articlesarray.getJSONObject(i);
                String author = currentnews.getString("author");
                String title = currentnews.getString("title");
                String description = currentnews.getString("description");
                String time = currentnews.getString("publishedAt");
                String url1=currentnews.getString("url");
                String img=currentnews.getString("urlToImage");
                word news1 = new word(title, author, description, time,url1,img);
                news.add(news1);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the News JSON results", e);
        }

        return news;

    }

    public static List<word> fetchdata(String requestUrl)
    {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        List<word> news = extract(jsonResponse);


        return news;
    }
}
