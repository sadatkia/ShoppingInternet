package com.example.shoppinginternet.network;

import android.net.Uri;
import android.util.Log;

import com.example.shoppinginternet.Model.GalleryItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import com.example.ShoppingInternet.model.GalleryItem;

//import static com.example.ShoppingInternet.controller.PhotoGalleryFragment.index;

public class FlickrFetcher {

    public static final String TAG = "FlickrFetcher";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + "url: " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 2];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            return out.toByteArray();
        } finally {
            connection.disconnect();
            inputStream.close();
            out.close();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {
        Uri uri = Uri.parse("https://www.flickr.com/services/rest")
                .buildUpon()
                .appendQueryParameter("method", "flickr.photos.getRecent")
                .appendQueryParameter("api_key", "79b5c28546b0c0fd5a0bdc65ac9eab18")
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("extras", "url_s")
               // .appendQueryParameter("page", String.valueOf(index))
                .build();

        String url = uri.toString();
        Log.d(TAG, "url: " + url);

        try {
            String bodyString = getUrlString(url);
            Log.d(TAG, "result: " + bodyString);

            return parseItems(bodyString);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    public List<GalleryItem> parseItems(String bodyString) throws JSONException {
        List<GalleryItem> items = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();




        JSONObject jsonBody = new JSONObject(bodyString);
        JSONObject photosObject = jsonBody.getJSONObject("photos");
        JSONArray photoArray = photosObject.getJSONArray("photo");

        for (int i = 0; i < photoArray.length(); i++) {
            JSONObject photoObject = photoArray.getJSONObject(i);

            if (!photoObject.has("url_s"))
                continue;

  /*          String id = photoObject.getString("id");
            String title = photoObject.getString("title");
            String url = photoObject.getString("url_s");*/
            GalleryItem galleryItem = gson.fromJson(photoObject.toString(), GalleryItem.class);
            items.add(galleryItem);
        }

        return items;
    }
}
