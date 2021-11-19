package com.dawmecnagtrt.healthypurr.dto.User;

import lombok.Data;

@Data
public class UserInfoDto {
    private Integer userId;
    private String username;
    private String password;
    private Boolean status;
}
