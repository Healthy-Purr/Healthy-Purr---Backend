package com.dawmecnagtrt.healthypurr.dto.Auth;


import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthResponse implements Serializable {

    private final String jwt;
    private final UserFullDataDto user;

    public AuthResponse(String jwt, UserFullDataDto user) {
        this.jwt = jwt;
        this.user = user;
    }

}
