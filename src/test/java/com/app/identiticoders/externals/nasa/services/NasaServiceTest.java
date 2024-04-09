package com.app.identiticoders.externals.nasa.services;

import com.app.identiticoders.exceptions.NotFoundException;
import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import com.app.identiticoders.externals.nasa.responses.NeoLookupResponse;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class NasaServiceTest {

    @Autowired
    private NasaService nasaService;

    private NasaClient nasaClient;

    @Value("${nasa.host}")
    String host;

    @Value("${nasa.api.key}")
    String apiKey;

    @BeforeEach
    public void setUp() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        this.nasaClient = retrofit.create(NasaClient.class);
    }

    @Test
    public void givenGetNeoFeedData_whenParamInserted_thenReturnCode200() throws IOException {

        String startDate = "2020-10-10";
        String endDate = "2020-10-10";
        Response<NeoFeedResponse> expectedResponse = nasaClient.getAsteroids(startDate, endDate, apiKey).execute();
        Response<NeoFeedResponse> actualResponse = nasaService.getNeoFeedData(startDate, endDate);
        Assert.assertEquals(200, expectedResponse.code());
        Assert.assertEquals(200, actualResponse.code());
        Assert.assertNotNull(expectedResponse.body());
        Assert.assertNotNull(actualResponse.body());
        Assert.assertNotNull(expectedResponse.body().getNearEarthObjects());
        Assert.assertNotNull(actualResponse.body().getNearEarthObjects());
    }

    @Test
    public void givenGetNeoFeedData_whenParamIsNull_thenReturnCode200() throws IOException {
        String startDate = null;
        String endDate = null;
        Response<NeoFeedResponse> expectedResponse = nasaClient.getAsteroids(startDate, endDate, apiKey).execute();
        Response<NeoFeedResponse> actualResponse = nasaService.getNeoFeedData(startDate, endDate);
        Assert.assertEquals(200, expectedResponse.code());
        Assert.assertEquals(200, actualResponse.code());
        Assert.assertNotNull(expectedResponse.body());
        Assert.assertNotNull(actualResponse.body());
        Assert.assertNotNull(actualResponse.body().getNearEarthObjects());
        Assert.assertNotNull(actualResponse.body().getNearEarthObjects());
    }

    @Test
    public void givengetNeoLookupData_whenIdExist_thenReturnCode200() throws IOException {
        String id = "2162162";
        Response<NeoLookupResponse> expectedResponse = nasaClient.getAsteroidsDetail(id, apiKey).execute();
        Response<NeoLookupResponse> actualResponse = nasaService.getNeoLookupData(id);
        Assert.assertEquals(200, expectedResponse.code());
        Assert.assertEquals(200, actualResponse.code());
        Assert.assertNotNull(expectedResponse.body());
        Assert.assertNotNull(actualResponse.body());
        Assert.assertNotNull(expectedResponse.body().getCloseApproachData());
        Assert.assertNotNull(actualResponse.body().getCloseApproachData());
    }

    @Test
    public void givenGetNeoLookupData_whenIdEmpty_thenReturnCode404() throws IOException {
        String id = "";
        Response<NeoLookupResponse> expectedResponse = nasaClient.getAsteroidsDetail(id, apiKey).execute();
        Assert.assertEquals(404, expectedResponse.code());

        Throwable exception = Assert.assertThrows(NotFoundException.class,
                () -> nasaService.getNeoLookupData(id));
        Assert.assertEquals(exception.getMessage(), "Id not found");
    }
}