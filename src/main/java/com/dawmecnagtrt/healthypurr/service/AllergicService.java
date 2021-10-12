package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.CatProblems.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Allergic;

import java.util.List;

public interface AllergicService {
    List<Allergic> getAllAllergic();
    Allergic getAllergic(Integer id);
    Allergic createAllergic(CreateProblemDto dto) throws Exception;
    Allergic updateAllergic(CreateProblemDto dto, Integer id) throws Exception;
    String deleteAllergic(Integer id);
}
