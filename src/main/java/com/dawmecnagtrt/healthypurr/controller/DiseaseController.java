package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Disease;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<Disease>> getAllAllergic(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            diseaseService.getAllDisease()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{diseaseId}")
    public ApiResponse<Disease> getAllergicById(@PathVariable Integer diseaseId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                diseaseService.getDisease(diseaseId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiResponse<Disease> createAllergic(@RequestBody @Valid CreateProblemDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                diseaseService.createDisease(dto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{diseaseId}")
    public ApiResponse<Disease> updateAllergicById(@RequestBody @Valid CreateProblemDto dto, @PathVariable Integer diseaseId) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                diseaseService.updateDisease(dto,diseaseId));
    }
}
