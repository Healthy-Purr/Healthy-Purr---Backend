package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.CreateEvaluatedFoodDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.EvaluatedFoodDto;
import com.dawmecnagtrt.healthypurr.entity.EvaluatedFood;
import com.dawmecnagtrt.healthypurr.entity.EvaluationResult;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.EvaluatedFoodRepository;
import com.dawmecnagtrt.healthypurr.service.EvaluatedFoodService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluatedFoodServiceImpl implements EvaluatedFoodService {
    @Autowired
    private EntityConverter converter;

    @Autowired
    private EvaluatedFoodRepository evaluatedFoodRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EvaluatedFoodDto> getAll() {
        return converter.convertEntityToEvaluatedFoodDto(evaluatedFoodRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluatedFoodDto getEvaluatedFoodById(Integer id) {
        return converter.convertEntityToEvaluatedFoodDto(evaluatedFoodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Evaluated Food with id: " + id +" not found")));
    }

    @Override
    @Transactional
    public EvaluatedFoodDto createEvaluatedFood(CreateEvaluatedFoodDto dto) throws Exception {

        EvaluatedFood evaluatedFood = EvaluatedFood.builder()
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .fiber(dto.getFiber())
                .moisture(dto.getMoisture())
                .calcium(dto.getCalcium())
                .phosphorus(dto.getPhosphorus())
                .hasTaurine(dto.getHasTaurine())
                .build();
        return converter.convertEntityToEvaluatedFoodDto(evaluatedFoodRepository.save(evaluatedFood));
    }

    @Override
    @Transactional
    public EvaluatedFoodDto updateEvaluatedFoodById(CreateEvaluatedFoodDto dto, Integer id) throws Exception {
        Optional<EvaluatedFood> evaluatedFood = evaluatedFoodRepository.findById(id);
        if(!evaluatedFood.isPresent()){
            throw new EntityNotFoundException("Evaluation Result with id: " + id +" not found");
        }
        EvaluatedFood evaluatedFoodUpdate = evaluatedFood.get();
        evaluatedFoodUpdate.setProtein(dto.getProtein());
        evaluatedFoodUpdate.setFat(dto.getFat());
        evaluatedFoodUpdate.setFiber(dto.getFiber());
        evaluatedFoodUpdate.setMoisture(dto.getMoisture());
        evaluatedFoodUpdate.setCalcium(dto.getCalcium());
        evaluatedFoodUpdate.setPhosphorus(dto.getPhosphorus());
        evaluatedFoodUpdate.setHasTaurine(dto.getHasTaurine());
        return converter.convertEntityToEvaluatedFoodDto(evaluatedFoodRepository.save(evaluatedFoodUpdate));
    }
}
