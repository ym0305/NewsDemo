package com.example.yamin.jsontest;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamin on 2017/5/27.
 */

public class JsonDate {

    private static final String TAG = "JsonDate";

    public static List<News> newsList = new ArrayList<>();

    public List<News> parseJSONDate(String jsonData){
        if (!TextUtils.isEmpty(jsonData)) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("result");

                for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
                    News news[] = new News[jsonObject.getJSONArray("data").length()];
                    JSONObject dataObject = (JSONObject) jsonObject.getJSONArray("data").opt(i);
                    String title = dataObject.getString("title");
                    String date = dataObject.getString("date");
                    String url = dataObject.getString("url");
                    String author = dataObject.getString("author_name");
                    String imageUrl = dataObject.getString("thumbnail_pic_s");
                    //Log.d(TAG, "parseJSONDate: " + title);
                    //Log.d(TAG, "parseJSONDate: " + date);
                    //Log.d(TAG, "parseJSONDate: " + url);
                    news[i] = new News(title,date,url,author,imageUrl);
                    newsList.add(news[i]);

                    Log.d(TAG, "parseJSONDate: "+ news[i].getTitle() + news[i].getData());


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "parseJSONDate: " + newsList.size());

            return newsList;

        }
        else Log.d(TAG, "parseJSONDate: jsondate is empty");

        return newsList;
    }

}
