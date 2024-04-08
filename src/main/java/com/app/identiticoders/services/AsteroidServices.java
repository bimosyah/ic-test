package com.app.identiticoders.services;

import com.app.identiticoders.responses.AsteroidDetailResponse;
import com.app.identiticoders.responses.AsteroidResponse;

import java.util.List;

public interface AsteroidServices {

    List<AsteroidResponse> getTopTenAsteroids(String startDate, String endDate);

    AsteroidDetailResponse getAsteroidDetail(String asteroidId);
}
