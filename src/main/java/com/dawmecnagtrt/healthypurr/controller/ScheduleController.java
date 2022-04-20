package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.Schedule.CreateScheduleDto;
import com.dawmecnagtrt.healthypurr.dto.Schedule.ScheduleDto;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "usePurrApi")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/schedules")
    public ApiResponse<List<ScheduleDto>> getAllSchedules(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            scheduleService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/schedules/{scheduleId}")
    public ApiResponse<ScheduleDto> getScheduleById(@PathVariable Integer scheduleId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                scheduleService.getSchedule(scheduleId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats/{catId}/schedules")
    public ApiResponse<List<ScheduleDto>> getSchedulesByCatId(@PathVariable Integer catId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                scheduleService.getSchedulesByCatId(catId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/schedules")
    public ApiResponse<ScheduleDto> createSchedule(@RequestBody @Valid CreateScheduleDto dto) throws Exception {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                scheduleService.createSchedule(dto));
    }
}
