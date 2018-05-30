package com.example.retrofitmoviedb.API;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Albert on 5/1/2018.
 */

public interface MovieClient {


    @GET("{id}")
    Call<String> getMovieDetailAsString(
            @Path("id") int id,
            @Query("api_key") String api_key);

    @GET("{id}")
    Call<JsonObject> getMovieDetail(
            @Path("id") int id,
            @Query("api_key") String api_key);

    @GET("movie")
    Call<JsonObject> getSearchQuery(
            @Query("query") String text,
            @Query("api_key") String api_key);

    @GET("popular")
    Call<JsonObject> getPopularMovies(
            @Query("api_key") String api_key);

}
