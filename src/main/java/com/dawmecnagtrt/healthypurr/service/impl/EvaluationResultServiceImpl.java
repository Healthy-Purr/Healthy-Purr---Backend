package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.CreateEvaluationResultDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.EvaluationResultDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.EvaluatedFood;
import com.dawmecnagtrt.healthypurr.entity.EvaluationResult;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.repository.EvaluatedFoodRepository;
import com.dawmecnagtrt.healthypurr.repository.EvaluationResultRepository;
import com.dawmecnagtrt.healthypurr.repository.UserRepository;
import com.dawmecnagtrt.healthypurr.service.EvaluationResultService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private EvaluatedFoodRepository evaluatedFoodRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResultDto> getAll() {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluationResultDto getEvaluationResult(Integer id) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Evaluation Result with id: " + id +" not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResultDto> getAllByUserId(Integer userId) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAllByUserUserId(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResultDto> getAllByCatId(Integer catId) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAllByCatCatId(catId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResultDto> getAllByEvFoodId(Integer evFoodId) {
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.findAllByEvaluatedFoodEvaluatedFoodId(evFoodId));
    }

    @Override
    @Transactional
    public EvaluationResultDto createEvaluationResult(CreateEvaluationResultDto dto, MultipartFile file) throws Exception {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + dto.getUserId() +" not found");
        }
        Optional<Cat> cat = catRepository.findById(dto.getCatId());
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + dto.getCatId() +" not found");
        }
        Optional<EvaluatedFood> evaluatedFood = evaluatedFoodRepository.findById(dto.getEvaluatedFoodId());
        if(!evaluatedFood.isPresent()){
            throw new EntityNotFoundException("Evaluated Food with id: " + dto.getEvaluatedFoodId() +" not found");
        }
        EvaluatedFood evaluatedFoodBD = evaluatedFood.get();
        User userBD = user.get();
        Cat catBD = cat.get();
        EvaluationResult evaluationResult = EvaluationResult.builder()
                .cat(catBD)
                .user(userBD)
                .evaluatedFood(evaluatedFoodBD)
                .accuracyRate(dto.getAccuracyRate())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .evaluationPic( !file.isEmpty() ? file.getBytes(): null)
                .build();
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.save(evaluationResult));
    }

    @Override
    @Transactional
    public EvaluationResultDto updateEvaluationResult(CreateEvaluationResultDto dto, Integer id) throws Exception {
        Optional<EvaluationResult> evaluationResult = evaluationResultRepository.findById(id);
        if(!evaluationResult.isPresent()){
            throw new EntityNotFoundException("Evaluation Result with id: " + id +" not found");
        }
        Optional<Cat> cat = catRepository.findById(dto.getCatId());
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + dto.getCatId() +" not found");
        }
        Optional<EvaluatedFood> evaluatedFood = evaluatedFoodRepository.findById(dto.getEvaluatedFoodId());
        if(!evaluatedFood.isPresent()){
            throw new EntityNotFoundException("Evaluated Food with id: " + dto.getEvaluatedFoodId() +" not found");
        }
        Cat catBD = cat.get();
        EvaluatedFood evaluatedFoodBD = evaluatedFood.get();
        EvaluationResult evaluationResultUpdate = evaluationResult.get();
        evaluationResultUpdate.setCat(catBD);
        evaluationResultUpdate.setEvaluatedFood(evaluatedFoodBD);
        evaluationResultUpdate.setAccuracyRate(dto.getAccuracyRate());
        evaluationResultUpdate.setLocation(dto.getLocation());
        evaluationResultUpdate.setDescription(dto.getDescription());
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.save(evaluationResultUpdate));
    }

    @Override
    public EvaluationResultDto updateEvaluationResultPicture(MultipartFile file, Integer id) throws IOException {
        Optional<EvaluationResult> evaluationResult = evaluationResultRepository.findById(id);
        if(!evaluationResult.isPresent()){
            throw new EntityNotFoundException("Evaluation Result with id: " + id +" not found");
        }
        EvaluationResult evaluationResultUpdate = evaluationResult.get();
        if(!file.isEmpty()){
            evaluationResultUpdate.setEvaluationPic(file.getBytes());
        }
        return converter.convertEntityToEvaluationResultDto(evaluationResultRepository.save(evaluationResultUpdate));
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluationResult getEvaluationResultEntity(Integer id) {
        return evaluationResultRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Evaluation Result with id: " + id +" not found"));
    }

}
