package com.app.identiticoders.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AsteroidResponse {
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
}
