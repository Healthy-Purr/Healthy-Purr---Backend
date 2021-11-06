package com.dawmecnagtrt.healthypurr.dto.Meal;

import java.time.LocalTime;

public class CreateMealDto {
    private LocalTime hour;

    private String quantity;

    private Boolean dry;

    private Boolean damp;

    private Boolean medicine;

    private Integer scheduleId;
}
