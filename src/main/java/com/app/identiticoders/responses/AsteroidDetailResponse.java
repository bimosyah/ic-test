package com.app.identiticoders.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AsteroidDetailResponse {
    private String id;
    private String name;
    private String neoReferenceId;
    private Double estimatedDiameterMinKilometers;
    private Double estimatedDiameterMaxKilometers;
    private boolean isPotentiallyHazardousAsteroid;
    private String closeApproachDateFull;
    private Double velocityKilometersPerSecond;
    private Double velocityKilometersPerHour;
    private Double distanceKilometers;
    private String orbitId;
    private String orbitDeterminationDate;
    private String firstObservationDate;
    private String lastObservationDate;
    private int observationsUsed;
    private String minimumOrbitIntersection;
    private String orbitalPeriod;
    private String orbitClassType;
    private String orbitClassDescription;
    private String orbitClassRange;

}
