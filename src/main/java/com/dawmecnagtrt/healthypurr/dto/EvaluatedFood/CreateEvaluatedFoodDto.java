package com.dawmecnagtrt.healthypurr.dto.EvaluatedFood;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEvaluatedFoodDto {
    private Float protein;
    private Float fat;
    private Float fiber;
    private Float moisture;
    private Float calcium;
    private Float phosphorus;
    private Boolean hasTaurine;
}
