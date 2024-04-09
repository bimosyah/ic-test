package com.app.identiticoders.services.impl;

import com.app.identiticoders.responses.AsteroidDetailResponse;
import com.app.identiticoders.responses.AsteroidResponse;
import com.app.identiticoders.services.AsteroidServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AsteroidServicesImplTest {

    @Autowired
    private AsteroidServices asteroidServices;

    @Test
    public void givenGetAsteroidDetail_whenDataExist_thenReturnAsteroidDetail() {
        String id = "2162162";
        AsteroidDetailResponse asteroidDetail = asteroidServices.getAsteroidDetail(id);
        Assert.assertEquals(id, asteroidDetail.getId());
    }

    @Test
    public void givenGetTopTenAsteroids_whenDataExist_thenReturnAsteroidDetail() {
        String startDate = "2020-10-10";
        String endDate = "2020-10-14";
        List<AsteroidResponse> topTenAsteroids = asteroidServices.getTopTenAsteroids(startDate, endDate);
        Assert.assertEquals(10, topTenAsteroids.size());
    }

}