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


    public String getMedia(int index) {
        return media.get(index);
    }

    public Entities() {
        media = new ArrayList<>();

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

        return entity;
    }

    public boolean isEmpty(){
        return media.isEmpty();
    }



}
