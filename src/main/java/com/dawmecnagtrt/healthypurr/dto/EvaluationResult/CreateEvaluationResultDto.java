package com.dawmecnagtrt.healthypurr.dto.EvaluationResult;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateEvaluationResultDto {
    private Integer userId;
    private Integer catId;
    private Integer evFoodId;
    @NotEmpty
    private String description;

    @DecimalMax(value = "1.0")
    @DecimalMin(value = "0.0")
    private BigDecimal accuracyRate;
    @NotEmpty
    private String location;
}
