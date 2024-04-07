package com.app.identiticoders.services.impl;


import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import com.app.identiticoders.externals.nasa.services.NasaService;
import com.app.identiticoders.responses.AsteroidResponse;
import com.app.identiticoders.services.AsteroidServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AsteroidServicesImpl implements AsteroidServices {

    @Autowired
    NasaService nasaService;

    @Override
    public AsteroidResponse getTopTenAsteroids(String startDate, String endDate) {
        NeoFeedResponse neoFeedData = nasaService.getNeoFeedData(startDate, endDate);
        Map<String, Double> distanceEachAsteroid = getDistanceEachAsteroid(neoFeedData);
        distanceEachAsteroid = sortedAndLimitMap(distanceEachAsteroid);
        return null;
    }

    private LinkedHashMap<String, Double> sortedAndLimitMap(Map<String, Double> distanceEachAsteroid) {
        int maxSize = 10;
        return distanceEachAsteroid.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(maxSize)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private Map<String, Double> getDistanceEachAsteroid(NeoFeedResponse response) {
        Map<String, Double> distanceEachId = new HashMap<>();

        response.getNearEarthObjects().forEach((date, asteroids) -> {
            for (NeoFeedResponse.Asteroid asteroid : asteroids) {
                String asteroidId = asteroid.getId();
                if (asteroid.getCloseApproachData().isEmpty()) {
                    continue;
                }
                Double kilometers = asteroid.getCloseApproachData().get(0).getMissDistance().getKilometers();
                distanceEachId.put(asteroidId, kilometers);
            }
        });
        return distanceEachId;
    }
}
