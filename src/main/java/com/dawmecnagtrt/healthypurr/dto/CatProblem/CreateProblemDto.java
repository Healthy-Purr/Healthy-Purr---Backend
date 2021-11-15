package com.dawmecnagtrt.healthypurr.dto.CatProblem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateProblemDto {
    @NotEmpty
    @Size(min = 2, message = "description should have at least 2 characters")
    private String description;
    @NotEmpty
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
}
