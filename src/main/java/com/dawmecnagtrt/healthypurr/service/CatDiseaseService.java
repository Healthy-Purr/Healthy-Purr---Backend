package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatDiseaseDto;

import java.util.List;

public interface CatDiseaseService {
    List<CatDiseaseDto> getAll();
    List<CatDiseaseDto> getAllByDiseaseId(Integer diseaseId);
    List<CatDiseaseDto> getAllByCatId(Integer catId);
    CatDiseaseDto getByCatIdAndDiseaseId(Integer catId, Integer diseaseId);
    CatDiseaseDto createCatDisease(Integer catId, Integer diseaseId);
    CatDiseaseDto updateCatDisease(Integer catId, Integer diseaseId, Integer status);
}
