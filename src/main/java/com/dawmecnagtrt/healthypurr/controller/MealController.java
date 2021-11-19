package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.Meal.CreateMealDto;
import com.dawmecnagtrt.healthypurr.dto.Meal.MealDto;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.MealService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "usePurrApi")
public class MealController {
    @Autowired
    MealService mealService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/meals")
    public ApiResponse<List<MealDto>> getAllSchedules(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            mealService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/meals/{mealId}")
    public ApiResponse<MealDto> getMealById(@PathVariable Integer mealId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                mealService.getMeal(mealId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/schedules/{scheduleId}/meals")
    public ApiResponse<List<MealDto>> getAllByScheduleId(@PathVariable Integer scheduleId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                mealService.getAllByScheduleId(scheduleId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/meals")
    public ApiResponse<MealDto> createMeal(@RequestBody @Valid CreateMealDto dto) throws Exception {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                mealService.createMeal(dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/meals/{mealId}")
    public ApiResponse<MealDto> updateMeal(@RequestBody @Valid CreateMealDto dto, @PathVariable Integer mealId) throws Exception {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                mealService.updateMeal(dto,mealId));
    }
}
