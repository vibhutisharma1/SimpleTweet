package com.codepath.apps.restclienttemplate.models;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Entities {

    public ArrayList<String> media;
    public ArrayList<String> hashtags;


    public String getMedia(int index) {
        return media.get(index);
    }

    public String getHashtag(int index) {
        return hashtags.get(index);
    }

    public Entities() {
        media = new ArrayList<>();
        hashtags = new ArrayList<>();

    }

    public static Entities fromJsonArray(JSONObject jsonObject) throws JSONException {
        Entities entity = new Entities();
        JSONArray media_json;
        boolean hasMedia = jsonObject.has("media");

        if(hasMedia) {
             media_json = jsonObject.getJSONArray("media");
                //gets the media url of all the media objects in the media
            entity.media.add(media_json.getJSONObject(0).getString("media_url_https"));
        }
//        boolean hashtag = jsonObject.has("hashtags");
//        if(jsonObject.getJSONArray("hashtags").getJSONObject(0) != null){
//            JSONArray hashtags_json = jsonObject.getJSONArray("hashtags");
//            entity.hashtags.add(hashtags_json.getJSONObject(0).getString("text"));
//        }


        return entity;
    }

    public boolean isEmpty(){
        return media.isEmpty();
    }



}
