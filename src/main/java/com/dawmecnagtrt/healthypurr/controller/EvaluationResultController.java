package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.CreateEvaluationResultDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluationResult.EvaluationResultDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.EvaluationResult;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.EvaluationResultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "usePurrApi")
public class EvaluationResultController {

    @Autowired
    EvaluationResultService evaluationResultService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluations-result")
    public ApiResponse<List<EvaluationResultDto>> getAllEvaluationResult(){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            evaluationResultService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluations-result/{id}")
    public ApiResponse<EvaluationResultDto> getEvaluationResultById(@PathVariable Integer id) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                evaluationResultService.getEvaluationResult(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/evaluations-result")
    public ApiResponse<List<EvaluationResultDto>> getAllEvaluationResultByUserId(@PathVariable Integer userId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            evaluationResultService.getAllByUserId(userId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats/{catId}/evaluations-result")
    public ApiResponse<List<EvaluationResultDto>> getAllEvaluationResultByCatId(@PathVariable Integer catId){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            evaluationResultService.getAllByCatId(catId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluated-foods/{evFoodId}/evaluations-result")
    public ApiResponse<List<EvaluationResultDto>> getAllEvaluationResultByEvaluatedFoodId(@PathVariable Integer evFoodId){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            evaluationResultService.getAllByEvFoodId(evFoodId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/evaluations-result")
    public ApiResponse<EvaluationResultDto> createEvaluationResult(@RequestBody @Valid CreateEvaluationResultDto dto, @RequestParam MultipartFile file) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                evaluationResultService.createEvaluationResult(dto, file));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/evaluations-result/{id}")
    public ApiResponse<EvaluationResultDto> UpdateEvaluationResultById(@RequestBody @Valid CreateEvaluationResultDto dto, @PathVariable Integer id) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                evaluationResultService.updateEvaluationResult(dto,id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/evaluations-result/{id}/picture")
    public ApiResponse<EvaluationResultDto> UpdateEvaluationResultPictureById(@PathVariable Integer id, @RequestParam MultipartFile file) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                evaluationResultService.updateEvaluationResultPicture(file,id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluations-result/{id}/picture")
    public ResponseEntity<?> getFotEvaluation(@PathVariable Integer evaluationId)  {
        EvaluationResult optional = evaluationResultService.getEvaluationResultEntity(evaluationId);
        System.out.println(optional);
        if(optional.getEvaluationPic() != null){
            Resource imagen = new ByteArrayResource(optional.getEvaluationPic());
            System.out.println(imagen);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        else{
            return ResponseEntity.ok().body("Cat does not have picture");
        }
    }
}
