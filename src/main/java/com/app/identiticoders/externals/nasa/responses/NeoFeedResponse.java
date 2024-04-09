package com.app.identiticoders.externals.nasa.responses;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
public class NeoFeedResponse {

    @SerializedName("element_count")
    private int elementCount;

    @SerializedName("near_earth_objects")
    private Map<String, List<Asteroid>> nearEarthObjects;

    @Getter
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
}
