package com.app.identiticoders.services;

import com.app.identiticoders.responses.AsteroidResponse;

import java.util.Date;

public interface AsteroidServices {

    AsteroidResponse getTopTenAsteroids(Date startDate, Date endDate);
}
