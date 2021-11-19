package com.dawmecnagtrt.healthypurr.dto.Meal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class MealDto {
    private Integer mealId;

    private String description;

    private LocalTime hour;

    private Boolean isDry;

    private Boolean isDamp;

    private Boolean hasMedicine;

    private Integer scheduleId;

    private Boolean status;
}
