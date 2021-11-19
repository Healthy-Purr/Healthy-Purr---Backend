package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.CreateEvaluatedFoodDto;
import com.dawmecnagtrt.healthypurr.dto.EvaluatedFood.EvaluatedFoodDto;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.EvaluatedFoodService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "usePurrApi")
public class EvaluatedFoodController {

    @Autowired
    EvaluatedFoodService evaluatedFoodService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluated-foods")
    public ApiResponse<List<EvaluatedFoodDto>> getAllEvaluatedFood(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            evaluatedFoodService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/evaluated-foods/{id}")
    public ApiResponse<EvaluatedFoodDto> getEvaluatedFoodById(@PathVariable Integer id) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                evaluatedFoodService.getEvaluatedFoodById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/evaluated-foods")
    public ApiResponse<EvaluatedFoodDto> createEvaluatedFood(@RequestBody @Valid CreateEvaluatedFoodDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                evaluatedFoodService.createEvaluatedFood(dto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/evaluated-foods/{id}")
    public ApiResponse<EvaluatedFoodDto> UpdateEvaluatedFoodById(@RequestBody @Valid CreateEvaluatedFoodDto dto, @PathVariable Integer id) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                evaluatedFoodService.updateEvaluatedFoodById(dto,id));
    }


}
