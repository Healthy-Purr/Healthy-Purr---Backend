package com.dawmecnagtrt.healthypurr.dto.Schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateScheduleDto {
    private Integer catId;
    private Date day;
}
