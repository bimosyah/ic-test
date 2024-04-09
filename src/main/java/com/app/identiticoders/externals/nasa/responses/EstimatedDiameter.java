package com.app.identiticoders.externals.nasa.responses;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
public class EstimatedDiameter {
    @SerializedName("kilometers")
    private Diameter kilometers;

    @Getter
    public static class Diameter {
        @SerializedName("estimated_diameter_min")
        private Double estimatedDiameterMin;
        @SerializedName("estimated_diameter_max")
        private Double estimatedDiameterMax;
    }
}