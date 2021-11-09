package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.Meal.CreateMealDto;
import com.dawmecnagtrt.healthypurr.dto.Meal.MealDto;

import java.util.List;

public interface MealService {
    List<MealDto> getAll();
    MealDto getMeal(Integer id);
    List<MealDto> getAllByScheduleId(Integer scheduleId);
    MealDto createMeal(CreateMealDto dto) throws Exception;
    MealDto updateMeal(CreateMealDto dto, Integer id) throws Exception;

}
