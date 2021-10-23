package com.dawmecnagtrt.healthypurr.dto.Cat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatSimpleDto {
    private Integer catId;
    private String name;
    private Integer weight;
    private Integer age;
    private Boolean hasDisease;
    private Boolean isAllergic;
}
