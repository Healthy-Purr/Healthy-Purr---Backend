package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.Role.CreateRoleDto;
import com.dawmecnagtrt.healthypurr.dto.User.CreateUserDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserInfoDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserSimpleDto;
import com.dawmecnagtrt.healthypurr.entity.Role;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.RoleService;
import com.dawmecnagtrt.healthypurr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<Role>> getAllRoles(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            roleService.getAllRole()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{roleId}")
    public ApiResponse<Role> getRole(@PathVariable Integer roleId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                roleService.getRole(roleId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiResponse<Role> createRole(@RequestBody @Valid CreateRoleDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                roleService.createRole(dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{roleId}")
    public ApiResponse<Role> UpdateRoleById(@RequestBody @Valid CreateRoleDto dto, @PathVariable Integer roleId) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                roleService.updateRole(dto,roleId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{roleId}/delete")
    public String deleteRole(@PathVariable Integer roleId){
        return roleService.deleteRole(roleId);
    }

}
