package com.example.myapplicationfilmespiderman.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getYear() {
        if (releaseDate == null) return "";
        if (releaseDate.length() >= 4) return releaseDate.substring(0, 4);
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public boolean getDescription() {
        return false;
    }
}
