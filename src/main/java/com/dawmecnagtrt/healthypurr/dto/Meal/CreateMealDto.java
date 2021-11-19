package com.dawmecnagtrt.healthypurr.dto.Meal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class CreateMealDto {
    private String hour;

    private String description;

    private Boolean isDry;

    private Boolean isDamp;

    private Boolean hasMedicine;

    private Integer scheduleId;
}
