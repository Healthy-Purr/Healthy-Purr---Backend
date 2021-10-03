package com.dawmecnagtrt.healthypurr.dto.User;

import lombok.Data;
import org.springframework.core.io.Resource;


@Data
public class UserSimpleDto {
    private Integer userId;
    private String name;
    private String lastName;
    private String username;
    //private Resource photo;

}
