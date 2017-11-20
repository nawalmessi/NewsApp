package com.example.android.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by High Spec on 10/28/2017.
 */

public class NewsUti {
    public static ArrayList<News> ExtraData(String jsonData) {
        String authorName = "";
        ArrayList<News> news = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONArray jsonArray = response.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                JSONArray author = item.getJSONArray("tags");
                for (int j = 0; j < author.length(); j++) {
                    JSONObject authorItem = author.getJSONObject(j);
                    if (authorItem != null) {
                        authorName = authorItem.getString("webTitle");
                    }
                }
                String sectionName = item.getString("sectionName");
                String webPublicationDate = item.getString("webPublicationDate");
                String webTitle = item.getString("webTitle");
                String webUrl = item.getString("webUrl");
                news.add(new News(sectionName, webPublicationDate, webTitle, authorName, webUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }


}
