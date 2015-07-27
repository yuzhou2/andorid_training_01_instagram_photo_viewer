package com.yuzhou.viewer.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhou on 2015/07/27.
 */
public class ImageFactory
{
    public static List<ImageItem> create(JSONObject response)
    {
        List<ImageItem> result = new ArrayList<>();

        try {
            JSONArray jsonData = response.getJSONArray("data");
            Log.d("IG_VIEWER", "JSON size : " + jsonData.length());
            Log.d("IG_VIEWER", jsonData.toString());
            for (int i = 0, n = jsonData.length(); i < n; i++) {
                JSONObject json = jsonData.getJSONObject(i);
                ImageItem item = new ImageItem();

                item.setType(json.getString("type"));
                item.setCreatedTime(json.getLong("created_time"));

                item.setUser(createUser(json));
                item.setThumbnail(createImage(json));
                item.setCaption(createCaption(json));
                item.setLikes(createLikes(json));

                result.add(item);
            }
        } catch (JSONException e) {
            Log.i("IG_VIEWER", "Can not parse JSON data");
            e.printStackTrace();
        }

        return result;
    }

    private static User createUser(JSONObject object) throws JSONException
    {
        User user = new User();
        if (!object.isNull("images")) {
            JSONObject json = object.getJSONObject("user");
            user.setUserName(json.getString("username"));
            user.setFullName(json.getString("full_name"));
            user.setPictureUrl(json.getString("profile_picture"));
        }
        return user;
    }

    private static Image createImage(JSONObject object) throws JSONException
    {
        Image image = new Image();
        if (!object.isNull("images")) {
            JSONObject json = object.getJSONObject("images").getJSONObject("thumbnail");
            image.setUrl(json.getString("url"));
            image.setHeight(json.getInt("height"));
            image.setWidth(json.getInt("width"));
        }
        return image;
    }

    private static Caption createCaption(JSONObject object) throws JSONException
    {
        Caption caption = new Caption();
        if (!object.isNull("caption")) {
            JSONObject json = object.getJSONObject("caption");
            caption.setText(json.getString("text"));
        }
        return caption;
    }

    private static Likes createLikes(JSONObject object) throws JSONException
    {
        Likes likes = new Likes();
        if (!object.isNull("likes")) {
            JSONObject json = object.getJSONObject("likes");
            likes.setCount(json.getInt("count"));
        }
        return likes;
    }
}
