package com.app.identiticoders.services;

import com.app.identiticoders.responses.AsteroidResponse;

import java.util.List;

public interface AsteroidServices {

    List<AsteroidResponse> getTopTenAsteroids(String startDate, String endDate);
}
