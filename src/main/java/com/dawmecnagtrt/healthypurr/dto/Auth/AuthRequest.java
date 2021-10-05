package com.dawmecnagtrt.healthypurr.dto.Auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    private String username;
    private String password;
}