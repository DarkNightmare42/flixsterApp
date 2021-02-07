package com.example.flixsterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixsterapp.adapters.movieAdapter;
import com.example.flixsterapp.models.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String sourceUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String tag = "MainActivity";

    List<movies> movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movie = new ArrayList<>();
        RecyclerView rView = findViewById(R.id.rView);

        movieAdapter MovieAdapter = new movieAdapter(this, movie);

        rView.setAdapter(MovieAdapter);

        rView.setLayoutManager(new LinearLayoutManager(this));


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(sourceUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(tag, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(tag, "Results: " + results.toString());
                    movie.addAll(movies.fromJsonArray(results));
                    MovieAdapter.notifyDataSetChanged();
                    Log.i(tag, "Movies: " + movie.size());
                } catch (JSONException e) {
                    Log.e(tag, "Hit json exception", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(tag, "onFailure");
            }
        });
    }
}