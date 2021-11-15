package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatDiseaseDto;
import com.dawmecnagtrt.healthypurr.entity.*;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.CatDiseaseRepository;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.repository.DiseaseRepository;
import com.dawmecnagtrt.healthypurr.service.CatDiseaseService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatDiseaseServiceImpl implements CatDiseaseService {
    @Autowired
    private CatDiseaseRepository catDiseaseRepository;
    @Autowired
    private CatRepository catRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private EntityConverter converter;


    @Override
    public List<CatDiseaseDto> getAll() {
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.findAll());
    }

    @Override
    public List<CatDiseaseDto> getAllByDiseaseId(Integer diseaseId) {
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.findAllByDiseaseDiseaseId(diseaseId));
    }

    @Override
    public List<CatDiseaseDto> getAllByCatId(Integer catId) {
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.findAllByCatCatId(catId));
    }

    @Override
    public CatDiseaseDto getByCatIdAndDiseaseId(Integer catId, Integer diseaseId) {
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.findCatDisease(catId,diseaseId)
                .orElseThrow(()-> new EntityNotFoundException("Cat-Allergic with catId: " + catId + " and diseaseId:"+ diseaseId +"not found")));
    }

    @Override
    public CatDiseaseDto createCatDisease(Integer catId, Integer diseaseId) {
        Optional<Cat> cat = catRepository.findById(catId);
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + catId +" not found");
        }
        Cat catBD = cat.get();
        Optional<Disease> disease = diseaseRepository.findById(diseaseId);
        if(!disease.isPresent()){
            throw new EntityNotFoundException("Disease with id: " + diseaseId +" not found");
        }
        Disease diseaseBD = disease.get();
        CatDisease catDisease = CatDisease.builder()
                .cat(catBD)
                .disease(diseaseBD)
                .diseaseId(diseaseId)
                .catId(catId)
                .status(true)
                .build();
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.save(catDisease));
    }
    @Override
    public CatDiseaseDto updateCatDisease(Integer catId, Integer diseaseId, Boolean status) {
        CatDisease catDisease = catDiseaseRepository.findCatDisease(catId,diseaseId)
                .orElseThrow(()-> new EntityNotFoundException("Cat-Disease with catId: " + catId + " and diseaseId:"+ diseaseId +"not found"));
        catDisease.setStatus(status);
        return converter.convertEntityToCatDiseaseDto(catDiseaseRepository.save(catDisease));
    }
}
