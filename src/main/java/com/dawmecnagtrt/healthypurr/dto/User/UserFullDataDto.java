package com.dawmecnagtrt.healthypurr.dto.User;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatSimpleDto;
import lombok.Data;
import java.util.List;

@Data
public class UserFullDataDto {
    private Integer userId;
    private String name;
    private String lastName;
    //private Resource photo;
    private Integer status;
    private String username;
    private String password;
    private List<CatSimpleDto> cats;
}
