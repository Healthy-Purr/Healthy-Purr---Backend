package com.dawmecnagtrt.healthypurr.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Data
public class UserFullDataDto {
    private Integer userId;
    private String name;
    private String lastName;

    @Lob
    private byte[] userPic;

    private Integer status;
    private String username;
    private String password;
}
