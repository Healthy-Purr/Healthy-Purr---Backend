package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.CreateEvaluatedFoodDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.EvaluatedFoodDto;

import java.util.List;

public interface EvaluatedFoodService {
    List<EvaluatedFoodDto> getAll();
    EvaluatedFoodDto getEvaluatedFoodById(Integer id);
    EvaluatedFoodDto createEvaluatedFood(CreateEvaluatedFoodDto dto) throws Exception;
    EvaluatedFoodDto updateEvaluatedFoodById(CreateEvaluatedFoodDto dto, Integer id) throws Exception;

}
