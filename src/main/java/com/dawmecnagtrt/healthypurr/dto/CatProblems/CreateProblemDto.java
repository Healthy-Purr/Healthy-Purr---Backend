package com.dawmecnagtrt.healthypurr.dto.CatProblems;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateProblemDto {
    @NotEmpty
    private String description;
}
