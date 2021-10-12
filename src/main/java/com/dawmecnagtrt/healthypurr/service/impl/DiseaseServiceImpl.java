package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.CatProblems.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Disease;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.DiseaseRepository;
import com.dawmecnagtrt.healthypurr.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> getAllDisease() {
        return diseaseRepository.findAll();
    }

    @Override
    public Disease getDisease(Integer id) {
        return diseaseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Disease with id: " + id +" not found"));
    }

    @Override
    public Disease createDisease(CreateProblemDto dto) throws Exception {
        Disease disease = Disease.builder().description(dto.getDescription()).build();
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease updateDisease(CreateProblemDto dto, Integer id) throws Exception {
        Optional<Disease> disease = diseaseRepository.findById(id);
        if(!disease.isPresent()){
            throw new EntityNotFoundException("Disease with id: " + id +" not found");
        }
        Disease diseaseUpdated = disease.get();
        diseaseUpdated.setDescription(dto.getDescription());
        return diseaseRepository.save(diseaseUpdated);
    }

    @Override
    public String deleteDisease(Integer id) {
        return null;
    }
}
