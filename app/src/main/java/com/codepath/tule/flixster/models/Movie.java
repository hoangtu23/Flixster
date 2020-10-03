package com.codepath.tule.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double popularity;
    int voteCount;
    long id;
    boolean isAdult;
    String originLanguage;
    String originTitle;
    double voteAverage;
    String releaseDate;

    public Movie() {

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.popularity = jsonObject.getDouble("popularity");
        this.voteCount = jsonObject.getInt("vote_count");
        this.id = jsonObject.getLong("id");
        this.isAdult = jsonObject.getBoolean("adult");
        this.originLanguage = jsonObject.getString("original_language");
        this.originTitle = jsonObject.getString("original_title");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w300/%s", backdropPath);
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public long getId() {
        return id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOriginLanguage() {
        return originLanguage;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
