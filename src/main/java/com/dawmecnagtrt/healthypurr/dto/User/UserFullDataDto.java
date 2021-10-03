package com.dawmecnagtrt.healthypurr.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Data
public class UserFullDataDto {
    private Integer userId;
    private String name;
    private String lastName;
    //private Resource photo;
    private Integer status;
    private String username;
    private String password;
}
