package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.CatProblems.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Disease;

import java.util.List;

public interface DiseaseService {
    List<Disease> getAllDisease();
    Disease getDisease(Integer id);
    Disease createDisease(CreateProblemDto dto) throws Exception;
    Disease updateDisease(CreateProblemDto dto, Integer id) throws Exception;
    String deleteDisease(Integer id);
}
