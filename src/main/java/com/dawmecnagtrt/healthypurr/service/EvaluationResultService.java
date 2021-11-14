package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.CreateEvaluationResultDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.EvaluationResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EvaluationResultService {
    List<EvaluationResultDto> getAll();
    EvaluationResultDto getEvaluationResult(Integer id);
    List<EvaluationResultDto> getAllByUserId(Integer userId);
    List<EvaluationResultDto> getAllByCatId(Integer catId);
    List<EvaluationResultDto> getAllByEvFoodId(Integer evFoodId);
    EvaluationResultDto createEvaluationResult(CreateEvaluationResultDto dto) throws Exception;
    EvaluationResultDto updateEvaluationResult(CreateEvaluationResultDto dto, Integer id) throws Exception;
}
