package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatAllergicDto;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.CatAllergicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatAllergicController {
    @Autowired
    CatAllergicService catAllergicService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cat-allergics")
    public ApiResponse<List<CatAllergicDto>> getAllCatAllergic(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            catAllergicService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats/{catId}/allergics")
    public ApiResponse<List<CatAllergicDto>> getAllCatAllergicByCatId(@PathVariable Integer catId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.getAllByCatId(catId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allergics/{allergicId}/cats")
    public ApiResponse<List<CatAllergicDto>> getAllCatAllergicByAllergicId(@PathVariable Integer allergicId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.getAllByAllergicId(allergicId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cat-allergics/cat/{catId}/allergic/{allergicId}")
    public ApiResponse<CatAllergicDto> getByAllergicIdAndCatId(@PathVariable Integer catId, @PathVariable Integer allergicId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.getByCatIdAndAllergicId(catId,allergicId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/cat-allergics/cat/{catId}/allergic/{allergicId}")
    public ApiResponse<CatAllergicDto> createCatAllergic(@PathVariable Integer catId, @PathVariable Integer allergicId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.createCatAllergic(catId,allergicId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cat-allergics/cat/{catId}/allergic/{allergicId}/activate")
    public ApiResponse<CatAllergicDto> activateCatAllergic(@PathVariable Integer catId, @PathVariable Integer allergicId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.updateCatAllergic(catId,allergicId,true));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cat-allergics/cat/{catId}/allergic/{allergicId}/deactivate")
    public ApiResponse<CatAllergicDto> deactivateCatAllergic(@PathVariable Integer catId, @PathVariable Integer allergicId){
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catAllergicService.updateCatAllergic(catId,allergicId,false));
    }
}
