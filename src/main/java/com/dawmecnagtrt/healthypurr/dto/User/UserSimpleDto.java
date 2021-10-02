package com.dawmecnagtrt.healthypurr.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class UserSimpleDto {
    private Integer userId;
    private String name;
    private String lastName;

    @Lob
    private byte[] userPic;


}
