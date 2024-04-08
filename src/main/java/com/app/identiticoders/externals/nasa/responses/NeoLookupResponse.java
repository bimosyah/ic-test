package com.app.identiticoders.externals.nasa.responses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class NeoLookupResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("neo_reference_id")
    private String neo_reference_id;

    @SerializedName("name")
    private String name;

    @SerializedName("estimated_diameter")
    private EstimatedDiameter estimatedDiameter;

    @SerializedName("is_potentially_hazardous_asteroid")
    private boolean isPotentiallyHazardousAsteroid;

    @SerializedName("close_approach_data")
    private List<CloseApproachData> closeApproachData;

    @SerializedName("orbital_data")
    private OrbitalData orbitalData;


    @Getter
    public static class OrbitalData {
        @SerializedName("orbit_id")
        private String orbitId;

        @SerializedName("orbit_determination_date")
        private String orbitDeterminationDate;

        @SerializedName("first_observation_date")
        private String firstObservationDate;

        @SerializedName("last_observation_date")
        private String lastObservationDate;

        @SerializedName("data_arc_in_days")
        private int dataArcInDays;

        @SerializedName("observations_used")
        private int observationsUsed;

        @SerializedName("orbit_uncertainty")
        private String orbitUncertainty;

        @SerializedName("minimum_orbit_intersection")
        private String minimumOrbitIntersection;

        @SerializedName("orbital_period")
        private String orbitalPeriod;

        @SerializedName("orbit_class")
        private OrbitClass orbitClass;
    }

    @Getter
    public static class OrbitClass {
        @SerializedName("orbit_class_type")
        private String orbitClassType;

        @SerializedName("orbit_class_description")
        private String orbitClassDescription;

        @SerializedName("orbit_class_range")
        private String orbitClassRange;
    }
}
