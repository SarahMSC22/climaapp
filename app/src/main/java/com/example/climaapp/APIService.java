package com.example.climaapp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {

    @Headers({"x-rapidapi-host: community-open-weather-map.p.rapidapi.com",
            "x-rapidapi-key: bb3d11df00msh4a5a6724bcf50b5p16d912jsna22440cd87fe"})
    @GET("/weather")
    Observable<Object> getWeather(@Query("q") String city);

}
