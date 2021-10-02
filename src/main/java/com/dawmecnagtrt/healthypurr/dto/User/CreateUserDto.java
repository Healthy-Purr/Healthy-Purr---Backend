package com.dawmecnagtrt.healthypurr.dto.User;

import lombok.Data;

@Data
public class CreateUserDto {

    private String name;
    private String lastName;
    private String username;
    private String password;
}
