package com.dawmecnagtrt.healthypurr.dto.Role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRoleDto {
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String roleName;
    @NotEmpty
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String roleDescription;
}
