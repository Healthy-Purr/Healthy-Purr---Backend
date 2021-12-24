package com.dawmecnagtrt.healthypurr.dto.Cat;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CatSimpleDto {
    private Integer catId;
    private String name;
    private BigDecimal weight;
    private Integer age;
    private Boolean gender;
    private Boolean hasDisease;
    private Boolean isAllergic;
}
