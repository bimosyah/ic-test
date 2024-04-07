package com.app.identiticoders.externals.nasa.services;

import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaClient {

    @GET("v1/feed")
    Call<NeoFeedResponse> getAsteroids(@Query("start_date") String startDate,
                                       @Query("end_date") String endDate,
                                       @Query("api_key") String apiKey);
}
