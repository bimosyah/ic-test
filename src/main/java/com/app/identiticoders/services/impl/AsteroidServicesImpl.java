package com.app.identiticoders.services.impl;


import com.app.identiticoders.responses.AsteroidResponse;
import com.app.identiticoders.services.AsteroidServices;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AsteroidServicesImpl implements AsteroidServices {

    @Override
    public AsteroidResponse getTopTenAsteroids(Date startDate, Date endDate) {
        return null;
    }
}
