package com.dawmecnagtrt.healthypurr.dto.Cat;

import com.dawmecnagtrt.healthypurr.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class CatDto {
    private Integer catId;
    private String name;
    private BigDecimal weight;
    private Integer age;
    private Boolean gender;
    private Boolean hasDisease;
    private Boolean isAllergic;
    private Boolean status;
    private Integer userId;
}
