package com.app.identiticoders.controllers.impl;

import com.app.identiticoders.controllers.AsteroidController;
import com.app.identiticoders.responses.BaseResponse;
import com.app.identiticoders.services.AsteroidServices;
import com.app.identiticoders.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AsteroidControllerImpl implements AsteroidController {

    @Autowired
    private AsteroidServices asteroidServices;

    @Override
    public ResponseEntity<BaseResponse> getClosestAsteroid(String startDate, String endDate) {
        return ResponseHelper.buildOkResponse(asteroidServices.getTopTenAsteroids(startDate, endDate));
    }
}
