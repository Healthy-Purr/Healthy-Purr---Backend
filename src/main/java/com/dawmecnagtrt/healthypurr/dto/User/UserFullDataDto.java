package com.dawmecnagtrt.healthypurr.dto.User;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatSimpleDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserFullDataDto {
    private Integer userId;
    private Date createdDate;
    private Date birthDate;
    private String name;
    private String lastName;
    private String username;
    private String password;
    //private Resource photo;
    private Boolean status;
    private List<CatSimpleDto> cats;
}
