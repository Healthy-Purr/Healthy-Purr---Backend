package com.dawmecnagtrt.healthypurr.dto.Cat;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateCatDto {
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;

    private BigDecimal weight;

    @Min(1)
    @Max(25)
    private Integer age;

    @NotNull
    private Boolean hasDisease;

    @NotNull
    private Boolean isAllergic;
}
