package com.dawmecnagtrt.healthypurr.dto.EvaluationResult;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EvaluationResultDto {
    private Integer evaluationResultId;
    private Integer userId;
    private Integer catId;
    private Integer evaluatedFoodId;
    private String description;
    private BigDecimal accuracyRate;
    private Date createdDate;
    private String location;
}
