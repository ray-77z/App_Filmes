package com.example.myapplicationfilmespiderman.Api;

import com.example.myapplicationfilmespiderman.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  ApiService {

    // Busca filmes por query (TheMovieDB)
    @GET("search")
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );
}
