package com.app.identiticoders.services.impl;


import com.app.identiticoders.externals.nasa.responses.CloseApproachData;
import com.app.identiticoders.externals.nasa.responses.NeoFeedResponse;
import com.app.identiticoders.externals.nasa.responses.NeoLookupResponse;
import com.app.identiticoders.externals.nasa.services.NasaService;
import com.app.identiticoders.responses.AsteroidDetailResponse;
import com.app.identiticoders.responses.AsteroidResponse;
import com.app.identiticoders.services.AsteroidServices;
import com.app.identiticoders.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.time.LocalDate;
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
        Response<NeoFeedResponse> response = nasaService.getNeoFeedData(startDate, endDate);
        NeoFeedResponse neoFeedData = response.body();
        Map<String, Double> distanceEachAsteroid = getDistanceEachAsteroid(neoFeedData);
        distanceEachAsteroid = getTopTesClosestDistance(distanceEachAsteroid);
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

    private LinkedHashMap<String, Double> getTopTesClosestDistance(Map<String, Double> distanceEachAsteroid) {
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
        Response<NeoLookupResponse> response = nasaService.getNeoLookupData(asteroidId);
        NeoLookupResponse neoLookupData = response.body();
        AsteroidDetailResponse.AsteroidDetailResponseBuilder builder = AsteroidDetailResponse.builder();
        builder.id(neoLookupData.getId());
        builder.name(neoLookupData.getName());
        builder.neoReferenceId(neoLookupData.getNeoReferenceId());
        builder.estimatedDiameterMinKilometers(neoLookupData.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin());
        builder.estimatedDiameterMaxKilometers(neoLookupData.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax());
        builder.isPotentiallyHazardousAsteroid(neoLookupData.isPotentiallyHazardousAsteroid());
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
        builder.closeApproachData(getCloseFiveDataFromNow(neoLookupData.getCloseApproachData()));
        return builder.build();
    }

    private AsteroidDetailResponse.CloseApproachData mapCloseApproachData(CloseApproachData closeApproachDatum) {
        return AsteroidDetailResponse.CloseApproachData.builder()
                .closeApproachDate(closeApproachDatum.getCloseApproachDate())
                .closeApproachDateFull(closeApproachDatum.getCloseApproachDateFull())
                .velocityKilometersPerSecond(closeApproachDatum.getRelativeVelocity().getKilometersPerSecond())
                .velocityKilometersPerHour(closeApproachDatum.getRelativeVelocity().getKilometersPerHour())
                .distanceKilometers(closeApproachDatum.getMissDistance().getKilometers())
                .orbitingBody(closeApproachDatum.getOrbitingBody())
                .build();
    }

    private List<AsteroidDetailResponse.CloseApproachData> getCloseFiveDataFromNow(List<CloseApproachData> closeApproachData) {
        List<AsteroidDetailResponse.CloseApproachData> responses = new ArrayList<>();

        int maxSize = 5;
        int currentDataAfterNow = 0; // i want to capture only from begining until 5 data after now
        for (CloseApproachData data : closeApproachData) {
            LocalDate now = LocalDate.now();
            LocalDate approachDate = DateUtils.convertStringToLocalDate(data.getCloseApproachDate());
            if (approachDate.isAfter(now)) {
                currentDataAfterNow++;
            }
            responses.add(mapCloseApproachData(data));
            if (currentDataAfterNow == maxSize) { // i want to capture only from begining until 5 data after now
                break;
            }
        }

        return responses;
    }
}
