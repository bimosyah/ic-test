package com.app.identiticoders.externals.nasa.services;

import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@Slf4j
public class NasaConfiguration {
    private NasaClient nasaClient;

    @Value("${nasa.api.key}")
    String apiKey;

    public NasaConfiguration(@Value("${nasa.host}") String host) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            Request request = builder.build();
            return chain.proceed(request);
        }).addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        this.nasaClient = retrofit.create(NasaClient.class);
    }

    public NeoFeedResponse getNeoFeedData(String startDate, String endDate) {
        Call<NeoFeedResponse> call = nasaClient.getAsteroids(startDate, endDate, apiKey);
        Response<NeoFeedResponse> execute = null;
        try {
            execute = call.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert execute != null;
        return execute.body();
    }
}