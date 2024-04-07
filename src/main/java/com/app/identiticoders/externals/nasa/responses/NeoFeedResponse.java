package com.app.identiticoders.externals.nasa.responses;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NeoFeedResponse {

    @SerializedName("element_count")
    private int elementCount;

    @SerializedName("near_earth_objects")
    private Map<String, List<Asteroid>> nearEarthObjects;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Asteroid {
        @SerializedName("id")
        private String id;

        @SerializedName("neo_reference_id")
        private String neoReferenceId;

        @SerializedName("name")
        private String name;

        @SerializedName("estimated_diameter")
        private EstimatedDiameter estimatedDiameter;

        @SerializedName("is_potentially_hazardous_asteroid")
        private boolean isPotentiallyHazardousAsteroid;

        @SerializedName("close_approach_data")
        private List<CloseApproachData> closeApproachData;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EstimatedDiameter {
        @SerializedName("kilometers")
        private Diameter kilometers;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Diameter {
        @SerializedName("estimated_diameter_min")
        private Double estimatedDiameterMin;
        @SerializedName("estimated_diameter_max")
        private Double estimatedDiameterMax;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CloseApproachData {
        @SerializedName("close_approach_date_full")
        private String closeApproachDateFull;

        @SerializedName("relative_velocity")
        private RelativeVelocity relativeVelocity;

        @SerializedName("miss_distance")
        private MissDistance missDistance;

        @SerializedName("orbiting_body")
        private String orbitingBody;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelativeVelocity {
        @SerializedName("kilometers_per_second")
        private Double kilometersPerSecond;

        @SerializedName("kilometers_per_hour")
        private Double kilometersPerHour;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissDistance {
        @SerializedName("kilometers")
        private Double kilometers;
    }
}
