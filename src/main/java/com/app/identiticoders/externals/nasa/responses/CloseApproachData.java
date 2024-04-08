package com.app.identiticoders.externals.nasa.responses;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CloseApproachData {
    @SerializedName("close_approach_date")
    private String closeApproachDate;

    @SerializedName("close_approach_date_full")
    private String closeApproachDateFull;

    @SerializedName("relative_velocity")
    private RelativeVelocity relativeVelocity;

    @SerializedName("miss_distance")
    private MissDistance missDistance;

    @SerializedName("orbiting_body")
    private String orbitingBody;

    @Getter
    public static class RelativeVelocity {
        @SerializedName("kilometers_per_second")
        private Double kilometersPerSecond;

        @SerializedName("kilometers_per_hour")
        private Double kilometersPerHour;
    }

    @Getter
    public static class MissDistance {
        @SerializedName("kilometers")
        private Double kilometers;
    }
}
