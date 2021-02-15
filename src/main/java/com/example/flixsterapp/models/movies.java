package com.example.flixsterapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class movies {

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    int movieID;

    public movies(){
        //constructor for parceler
    }

    public movies(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }

    public static List<movies> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<movies> movie =  new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movie.add(new movies(movieJsonArray.getJSONObject(i)));
        }
        return movie;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {

        return title;
    }

    public String getOverview() {

        return overview;
    }

    public int getMovieID() {
        return movieID;
    }
}
