package com.app.identiticoders.services;

import com.app.identiticoders.responses.AsteroidResponse;

public interface AsteroidServices {

    AsteroidResponse getTopTenAsteroids(String startDate, String endDate);
}
