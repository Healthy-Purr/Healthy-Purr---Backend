package com.dawmecnagtrt.healthypurr.dto.Meal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class CreateMealDto {
    private String hour;

    private String quantity;

    private Boolean dry;

    private Boolean damp;

    private Boolean medicine;

    private Integer scheduleId;
}
