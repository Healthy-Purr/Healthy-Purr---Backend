package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatDiseaseDto;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.CatDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatDiseaseController {
    @Autowired
    CatDiseaseService catDiseaseService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cat-diseases")
    public ApiResponse<List<CatDiseaseDto>> getAllCatDisease(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            catDiseaseService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats/{catId}/diseases")
    public ApiResponse<List<CatDiseaseDto>> getAllCatDiseaseByCatId(@PathVariable Integer catId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.getAllByCatId(catId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/diseases/{diseaseId}/cats")
    public ApiResponse<List<CatDiseaseDto>> getAllCatDiseaseByDiseaseId(@PathVariable Integer diseaseId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.getAllByDiseaseId(diseaseId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cat-diseases/cat/{catId}/disease/{diseaseId}")
    public ApiResponse<CatDiseaseDto> getByDiseaseIdAndCatId(@PathVariable Integer catId, @PathVariable Integer diseaseId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.getByCatIdAndDiseaseId(catId,diseaseId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cat-diseases/cat/{catId}/disease/{diseaseId}")
    public ApiResponse<CatDiseaseDto> createCatDisease(@PathVariable Integer catId, @PathVariable Integer diseaseId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.createCatDisease(catId,diseaseId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cat-diseases/cat/{catId}/disease/{diseaseId}/activate")
    public ApiResponse<CatDiseaseDto> activateCatDisease(@PathVariable Integer catId, @PathVariable Integer diseaseId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.updateCatDisease(catId,diseaseId,true));
    }
    @PutMapping("/cat-diseases/cat/{catId}/disease/{diseaseId}/deactivate")
    public ApiResponse<CatDiseaseDto> desactivateCatDisease(@PathVariable Integer catId, @PathVariable Integer diseaseId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catDiseaseService.updateCatDisease(catId,diseaseId,false));
    }
}
