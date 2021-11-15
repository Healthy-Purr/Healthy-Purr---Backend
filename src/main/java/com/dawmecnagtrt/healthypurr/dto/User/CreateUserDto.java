package com.dawmecnagtrt.healthypurr.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class CreateUserDto {

    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    @Size(min = 4, message = "user name should have at least 4 characters")
    private String username;
    @NotEmpty
    @Size(min = 8, message = "user name should have at least 8 characters")
    private String password;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthDate;
}
