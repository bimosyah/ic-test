package com.app.identiticoders.services.impl;


import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import com.app.identiticoders.externals.nasa.responses.NeoLookupResponse;
import com.app.identiticoders.externals.nasa.services.NasaService;
import com.app.identiticoders.responses.AsteroidDetailResponse;
import com.app.identiticoders.responses.AsteroidResponse;
import com.app.identiticoders.services.AsteroidServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AsteroidServicesImpl implements AsteroidServices {

    @Autowired
    NasaService nasaService;

    @Override
    public List<AsteroidResponse> getTopTenAsteroids(String startDate, String endDate) {
        NeoFeedResponse neoFeedData = nasaService.getNeoFeedData(startDate, endDate);
        Map<String, Double> distanceEachAsteroid = getDistanceEachAsteroid(neoFeedData);
        distanceEachAsteroid = sortedAndLimitMap(distanceEachAsteroid);
        List<NeoFeedResponse.Asteroid> asteroidObjects = getAsteroidObjects(neoFeedData, distanceEachAsteroid);
        return getAsteroidResponse(asteroidObjects);
    }

    private List<AsteroidResponse> getAsteroidResponse(List<NeoFeedResponse.Asteroid> asteroidObjects) {
        List<AsteroidResponse> responses = new ArrayList<>();
        for (NeoFeedResponse.Asteroid asteroidObject : asteroidObjects) {
            AsteroidResponse response = AsteroidResponse.builder().build();
            response.setId(asteroidObject.getId());
            response.setName(asteroidObject.getName());
            response.setNeoReferenceId(asteroidObject.getNeoReferenceId());
            response.setEstimatedDiameterMaxKilometers(asteroidObject.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax());
            response.setEstimatedDiameterMinKilometers(asteroidObject.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin());
            response.setPotentiallyHazardousAsteroid(asteroidObject.isPotentiallyHazardousAsteroid());
            response.setCloseApproachDateFull(asteroidObject.getCloseApproachData().get(0).getCloseApproachDateFull());
            response.setVelocityKilometersPerHour(asteroidObject.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour());
            response.setVelocityKilometersPerSecond(asteroidObject.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerSecond());
            response.setDistanceKilometers(asteroidObject.getCloseApproachData().get(0).getMissDistance().getKilometers());
            responses.add(response);
        }
        return responses;
    }

    private List<NeoFeedResponse.Asteroid> getAsteroidObjects(NeoFeedResponse neoFeedData, Map<String, Double> distanceEachAsteroid) {
        List<NeoFeedResponse.Asteroid> asteroidList = new ArrayList<>();
        neoFeedData.getNearEarthObjects().forEach((s, asteroids) -> {
            for (NeoFeedResponse.Asteroid asteroid : asteroids) {
                if (distanceEachAsteroid.containsKey(asteroid.getId())) {
                    asteroidList.add(asteroid);
                }
            }
        });
        return asteroidList;
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

    @Override
    public AsteroidDetailResponse getAsteroidDetail(String asteroidId) {
        NeoLookupResponse neoLookupData = nasaService.getNeoLookupData(asteroidId);
        AsteroidDetailResponse.AsteroidDetailResponseBuilder builder = AsteroidDetailResponse.builder();
        builder.id(neoLookupData.getId());
        builder.name(neoLookupData.getName());
        builder.neoReferenceId(neoLookupData.getNeoReferenceId());
        builder.estimatedDiameterMinKilometers(neoLookupData.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin());
        builder.estimatedDiameterMaxKilometers(neoLookupData.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax());
        builder.isPotentiallyHazardousAsteroid(neoLookupData.isPotentiallyHazardousAsteroid());
//        builder.closeApproachDateFull();
//        builder.velocityKilometersPerSecond();
//        builder.velocityKilometersPerHour();
//        builder.distanceKilometers();
        builder.orbitId(neoLookupData.getOrbitalData().getOrbitId());
        builder.orbitDeterminationDate(neoLookupData.getOrbitalData().getOrbitDeterminationDate());
        builder.firstObservationDate(neoLookupData.getOrbitalData().getFirstObservationDate());
        builder.lastObservationDate(neoLookupData.getOrbitalData().getLastObservationDate());
        builder.observationsUsed(neoLookupData.getOrbitalData().getObservationsUsed());
        builder.minimumOrbitIntersection(neoLookupData.getOrbitalData().getMinimumOrbitIntersection());
        builder.orbitalPeriod(neoLookupData.getOrbitalData().getOrbitalPeriod());
        builder.orbitClassType(neoLookupData.getOrbitalData().getOrbitClass().getOrbitClassType());
        builder.orbitClassDescription(neoLookupData.getOrbitalData().getOrbitClass().getOrbitClassDescription());
        builder.orbitClassRange(neoLookupData.getOrbitalData().getOrbitClass().getOrbitClassRange());
        return builder.build();
    }
}
