package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Allergic;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.AllergicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/allergics")
public class AllergicController {

    @Autowired
    AllergicService allergicService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<Allergic>> getAllAllergic(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            allergicService.getAllAllergic()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{allergicId}")
    public ApiResponse<Allergic> getAllergicById(@PathVariable Integer allergicId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                allergicService.getAllergic(allergicId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiResponse<Allergic> createAllergic(@RequestBody @Valid CreateProblemDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                allergicService.createAllergic(dto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{allergicId}")
    public ApiResponse<Allergic> updateAllergicById(@RequestBody @Valid CreateProblemDto dto, @PathVariable Integer allergicId) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                allergicService.updateAllergic(dto,allergicId));
    }
}
