package com.dawmecnagtrt.healthypurr.dto.Cat;

import com.dawmecnagtrt.healthypurr.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CatDto {
    private Integer catId;
    private String name;
    private Integer weight;
    private Integer age;
    private Boolean hasDisease;
    private Boolean isAllergic;
    private Integer status;
    private Integer userId;
}
