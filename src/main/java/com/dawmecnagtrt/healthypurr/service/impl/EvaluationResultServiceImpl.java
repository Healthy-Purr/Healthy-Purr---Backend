package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.CreateEvaluationResultDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.EvaluationResultDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.EvaluationResult;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.repository.EvaluationResultRepository;
import com.dawmecnagtrt.healthypurr.repository.UserRepository;
import com.dawmecnagtrt.healthypurr.service.EvaluationResultService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationResultServiceImpl implements EvaluationResultService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EvaluationResultRepository evaluationResultRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    public List<EvaluationResultDto> getAll() {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAll());
    }

    @Override
    public EvaluationResultDto getEvaluationResult(Integer id) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Evaluation Result with id: " + id +" not found")));
    }

    @Override
    public List<EvaluationResultDto> getAllByUserId(Integer userId) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAllByUserUserId(userId));
    }

    @Override
    public List<EvaluationResultDto> getAllByCatId(Integer catId) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAllByCatCatId(catId));
    }

    @Override
    public EvaluationResultDto createEvaluationResult(CreateEvaluationResultDto dto) throws Exception {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + dto.getUserId() +" not found");
        }
        Optional<Cat> cat = catRepository.findById(dto.getCatId());
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + dto.getCatId() +" not found");
        }
        User userBD = user.get();
        Cat catBD = cat.get();
        EvaluationResult evaluationResult = EvaluationResult.builder()
                .cat(catBD)
                .user(userBD)
                .accuracyRate(dto.getAccuracyRate())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .build();
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.save(evaluationResult));
    }

    @Override
    public EvaluationResultDto updateEvaluationResult(CreateEvaluationResultDto dto, Integer id) throws Exception {
        Optional<EvaluationResult> evaluationResult = evaluationResultRepository.findById(id);
        if(!evaluationResult.isPresent()){
            throw new EntityNotFoundException("Evaluation Result with id: " + id +" not found");
        }
        Optional<Cat> cat = catRepository.findById(dto.getCatId());
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + dto.getCatId() +" not found");
        }
        Cat catBD = cat.get();
        EvaluationResult evaluationResultUpdate = evaluationResult.get();
        evaluationResultUpdate.setCat(catBD);
        evaluationResultUpdate.setAccuracyRate(dto.getAccuracyRate());
        evaluationResultUpdate.setLocation(dto.getLocation());
        evaluationResultUpdate.setDescription(dto.getDescription());
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.save(evaluationResultUpdate));
    }

}